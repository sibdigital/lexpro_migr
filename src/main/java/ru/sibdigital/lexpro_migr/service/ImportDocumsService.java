package ru.sibdigital.lexpro_migr.service;

import com.vladmihalcea.hibernate.type.range.Range;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sibdigital.lexpro_migr.model.lexpro.*;
import ru.sibdigital.lexpro_migr.model.zakon.DocumsEntity;
import ru.sibdigital.lexpro_migr.repo.lexpro.ClsNpaTypeRepo;
import ru.sibdigital.lexpro_migr.repo.lexpro.ClsRkkStageRepo;
import ru.sibdigital.lexpro_migr.repo.lexpro.ClsRkkStatusRepo;
import ru.sibdigital.lexpro_migr.repo.lexpro.ClsSessionRepo;
import ru.sibdigital.lexpro_migr.repo.zakon.FilesRepo;

import javax.persistence.EntityTransaction;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class ImportDocumsService extends ImportService<DocumsEntity, DocRkk> {

    @Autowired
    ClsNpaTypeRepo clsNpaTypeRepo;

    @Autowired
    ClsSessionRepo clsSessionRepo;

    @Autowired
    ClsRkkStageRepo clsRkkStageRepo;

    @Autowired
    ClsRkkStatusRepo clsRkkStatusRepo;

    @Autowired
    FilesRepo filesRepo;

    private final static String entity = "docums";

    public ImportDocumsService() {
        super(entity);
    }

    @Override
    protected DocRkk convertEntity(DocumsEntity documsEntity) {
        ClsSession clsSession = null;
        if(documsEntity.getSessiaNum() != null) {
            var sessionList = clsSessionRepo.findAllByDateAndNumberOrderByDate(documsEntity.getSessiaDate(), documsEntity.getSessiaNum());
            if(sessionList.size() > 0){
                clsSession = sessionList.get(0);
            } else {
                clsSession = ClsSession.builder()
                        .date(documsEntity.getSessiaDate())
                        .isDeleted(documsEntity.getFlagDeleted().equalsIgnoreCase("T") ? true : false)
                        .number(documsEntity.getSessiaNum())
                        .timeCreate(new Timestamp(System.currentTimeMillis()))
                        .build();

                clsSessionRepo.saveAndFlush(clsSession);
            }
        }

        ClsOrganization subject = null;
        if(documsEntity.getSubject() != null) {
            IdMap org = idMapRepo.findByEntityNameAndOldId("org", documsEntity.getSubject().getId());
            if (org != null) {
                subject = ClsOrganization.builder().id(org.getNewId()).build();
            }
        }

        ClsOrganization komitet = null;
        if(documsEntity.getOtvetstvKomitet() != null) {
            IdMap kom = idMapRepo.findByEntityNameAndOldId("org", documsEntity.getOtvetstvKomitet().getId());
            if (kom != null) {
                komitet = ClsOrganization.builder().id(kom.getNewId()).build();
            }
        }

        ClsEmployee dokl = null;
        if(documsEntity.getDokladchik() != null) {
            IdMap pers = idMapRepo.findByEntityNameAndOldId("person", documsEntity.getDokladchik().getId());
            if (pers != null) {
                dokl = ClsEmployee.builder().id(pers.getNewId()).build();
            }
        }

        ClsEmployee otvestv = null;
        if(documsEntity.getOtvetstvPerson() != null) {
            IdMap persOtvestv = idMapRepo.findByEntityNameAndOldId("person", documsEntity.getOtvetstvPerson().getId());
            if (persOtvestv != null) {
                otvestv = ClsEmployee.builder().id(persOtvestv.getNewId()).build();
            }
        }

        StatStage documStatStage = getDocumStatStage(documsEntity);

        return DocRkk.builder()
                .id(documsEntity.getId())
                .rkkNumber(documsEntity.getRegNum())
                .npaName(documsEntity.getDescr())
                .registrationDate(documsEntity.getRegDate())
                .introductionDate(documsEntity.getDdate())
                .npaType(getClsNpaType(documsEntity))
                .legislativeBasis(documsEntity.getZakOsnova())
                .lawSubject(subject)
                .speaker(dokl)
                .readyForSession((documsEntity.getRkkReady() != null && documsEntity.getRkkReady().equalsIgnoreCase("да")) ? true : false)
                .responsibleOrganization(komitet)
                .responsibleEmployee(otvestv)
                .deadline(documsEntity.getControlDate())
                .session(clsSession)
                .includedInAgenda(documsEntity.getPovestDate())
                .agendaNumber(documsEntity.getNpp())
                .status(documStatStage.status)
                .stage(documStatStage.stage)
                .headSignature(documsEntity.getPrezidentPodpisDate())
                .publicationDate(documsEntity.getOpublikDate())
                .timeCreate(new Timestamp(System.currentTimeMillis()))
                .timeUpdate(documsEntity.getEditDate())
                .isArchived(false)
                .isDeleted(documsEntity.getFlagDeleted().equalsIgnoreCase("T") ? true : false)
                .sysPeriod(Range.openInfinite(ZonedDateTime.now()))
                .isDeleted(false)
                .build();
    }

    private ClsNpaType getClsNpaType(DocumsEntity documsEntity) {
        ClsNpaType npaType = null;
        if(documsEntity.getDkind().getName().equalsIgnoreCase("закон")) {
            npaType = ClsNpaType.builder().id(1L).build();
        } else {
            npaType = ClsNpaType.builder().id(2L).build();
        }
        return npaType;
    }

    private StatStage getDocumStatStage(DocumsEntity documsEntity){
        StatStage statStage = new StatStage();
        try {

            // default init
            statStage.status = clsRkkStatusRepo.findByName(documsEntity.getStatus());
            statStage.stage = clsRkkStageRepo.findByCode("INTRODUCTION").orElseThrow(() -> new Exception("stage not found"));

            if (checkInWorkStageA(documsEntity)) {
                statStage.status = clsRkkStatusRepo.findByName("IN_WORK");
                statStage.stage = clsRkkStageRepo.findByCode("INTRODUCTION").orElseThrow(() -> new Exception("stage not found"));
            }
            if (checkWithdrawn(documsEntity)) {
                statStage.status = clsRkkStatusRepo.findByName("WITHDRAWN");
                statStage.stage = clsRkkStageRepo.findByCode("INTRODUCTION").orElseThrow(() -> new Exception("stage not found"));
            }
            if (checkInWorkStageB(documsEntity)) {
                statStage.status = clsRkkStatusRepo.findByName("IN_WORK");
                statStage.stage = clsRkkStageRepo.findByCode("PREPARATION_FOR_READING").orElseThrow(() -> new Exception("stage not found"));
            }

        } catch (Exception e){
            log.error("getDocumStatStage", e.getMessage());
        }
        return statStage;
    }

    // условия для статуса в работе, стадия а
    private Boolean checkInWorkStageA(DocumsEntity documsEntity){
        Boolean check = false;
        if(
            documsEntity.getStatus().equalsIgnoreCase("в работе")
            && documsEntity.getPovestDate() == null
            && documsEntity.getRkkReady() == null
            && documsEntity.getPrezidentPodpisDate() == null
            && documsEntity.getOpublikDate() == null
            && documsEntity.getDatePuZ() == null
            && documsEntity.getDatePuP() == null
            && documsEntity.getDatePuP2() == null
            && documsEntity.getDatePuT() == null
            && documsEntity.getDatePu2Ch() == null
            && documsEntity.getDatePrez() == null
        ) {
            check = true;
        }
        return check;
    }

    // условия для статуса в работе, стадия б
    private Boolean checkInWorkStageB(DocumsEntity documsEntity){
        Boolean check = false;
        if(
            (
                documsEntity.getStatus().equalsIgnoreCase("в работе")
                && documsEntity.getPovestDate() == null
                && documsEntity.getRkkReady() == null
                && documsEntity.getPrezidentPodpisDate() == null
                && documsEntity.getOpublikDate() == null
                && documsEntity.getDatePuZ() == null
                && documsEntity.getDatePuP() == null
                && documsEntity.getDatePuP2() == null
                && documsEntity.getDatePuT() == null
                && documsEntity.getDatePu2Ch() == null
                && documsEntity.getDatePrez() == null
                //НЕОБ, РЕК, К ЗАСЕД
                && (
                    filesRepo.existsByDocumIdAndFgroupIdInAndFdataIsNull(documsEntity.getId(), List.of(2L, 3L, 7L))
                    ||
                    documsEntity.getDatePuP() != null
                    ||
                    documsEntity.getDatePuZ() != null
                )
            )
            ||
            (
                documsEntity.getStatus().equalsIgnoreCase("в работе")
                && documsEntity.getPovestDate() != null
                && documsEntity.getRkkReady() != null
                && documsEntity.getPrezidentPodpisDate() == null
                && documsEntity.getOpublikDate() == null
                && documsEntity.getDatePuP2() == null
                && documsEntity.getDatePuT() == null
                && documsEntity.getDatePu2Ch() == null
                && documsEntity.getDatePrez() == null
                &&
                (
                    (
                        documsEntity.getDatePuZ() != null
                        && documsEntity.getDatePuP() != null
                        && documsEntity.getDateDeputat() == null
                        &&
                        !filesRepo.existsByDocumIdAndFdataIsNull(documsEntity.getId())
                    )
                    ||
                    (
                        documsEntity.getDatePuZ() != null
                        && documsEntity.getDateDeputat() != null
                    )
                )
            )
        ) {
            check = true;
        }
        return check;
    }



    // условия для статуса отозван, стадия а
    private Boolean checkWithdrawn(DocumsEntity documsEntity){
        Boolean check = false;
        if(
            documsEntity.getStatus().equalsIgnoreCase("отозван")
            && documsEntity.getPovestDate() == null
            && documsEntity.getRkkReady() == null
            && documsEntity.getPrezidentPodpisDate() == null
            && documsEntity.getOpublikDate() == null
            && documsEntity.getDatePuZ() == null
            && documsEntity.getDatePuP() == null
            && documsEntity.getDatePuP2() == null
            && documsEntity.getDatePuT() == null
            && documsEntity.getDatePu2Ch() == null
        ) {
            check = true;
        }
        return check;
    }


    @Override
    public Integer saveToDb(List<DocRkk> list) {
        EntityTransaction transaction = psqlLexproEntityManager.getTransaction();
        transaction.begin();
        String entityName = "docums";
        log.info("Entity : " + entityName);
        log.info("source count: " + list.size());
        AtomicInteger posCount = new AtomicInteger(0);
        try {
            list.stream()
                    .filter(obj -> obj != null)
                    .forEach(obj -> {
                        if(idMapRepo.findByEntityNameAndOldId(entityName, obj.getId()) == null) {
                            obj.setIsDeleted(false);
                            obj.setTimeCreate(new Timestamp(System.currentTimeMillis()));

                            DocRkk docRkk = psqlLexproEntityManager.merge(obj);

                            IdMap idMap = IdMap.builder()
                                    .entityName(entityName)
                                    .newId(docRkk.getId())
                                    .oldId(obj.getId())
                                    .build();

                            psqlLexproEntityManager.persist(idMap);
                            log.info("new_id: " + docRkk.getId() + ", old_id: " + obj.getId());
                            posCount.getAndIncrement();
                        }
                    });

            transaction.commit();
            log.info(entityName + " save complete. count: " + posCount.get());
        } catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            log.info(entityName + " save error", e.getMessage());
        }

        return posCount.get();
    }


    private class StatStage{
        ClsRkkStatus status;
        ClsRkkStage stage;
    }
}
