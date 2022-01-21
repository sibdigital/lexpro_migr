package ru.sibdigital.lexpro_migr.service;

import com.vladmihalcea.hibernate.type.range.Range;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.sibdigital.lexpro_migr.model.lexpro.*;
import ru.sibdigital.lexpro_migr.model.zakon.*;
import ru.sibdigital.lexpro_migr.repo.lexpro.*;
import ru.sibdigital.lexpro_migr.repo.zakon.PersonRepo;
import ru.sibdigital.lexpro_migr.utils.StrUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ImportPsqlLexproService {

    @Autowired
    @Qualifier("psqlLexproEntityManager")
    EntityManager psqlLexproEntityManager;

    @Autowired
    ClsOrganizationTypeRepo clsOrganizationTypeRepo;

    @Autowired
    ClsEmployeeRepo clsEmployeeRepo;

    @Autowired
    ClsPositionRepo clsPositionRepo;

    @Autowired
    PersonRepo personRepo;

    @Autowired
    ClsOrganizationRepo clsOrganizationRepo;

    @Autowired
    ClsEmployeeStatusRepo clsEmployeeStatusRepo;

    @Autowired
    IdMapRepo idMapRepo;

    @Autowired
    ClsNpaTypeRepo clsNpaTypeRepo;

    @Autowired
    ClsSessionRepo clsSessionRepo;

//    @Transactional("psqlLexproEntityManager")
    public void saveClsOrganization(List<ClsOrganization> list){
        EntityTransaction transaction = psqlLexproEntityManager.getTransaction();
        try{
            transaction.begin();

            String entityName = "org";

            AtomicInteger orgCount = new AtomicInteger(0);
            log.info("Entity: " + entityName);
            log.info("source count: " + list.size());
            List<ClsOrganization> parentList = list.stream()
                .filter(obj -> obj.getIdParent() == null)
                .map(obj -> {

                    if(idMapRepo.findByEntityNameAndOldId(entityName, obj.getId()) == null) {
                        obj.setFullName(obj.getName());
                        obj.setEmail("mail@govrb.ru");
                        obj.setOrganizationType(clsOrganizationTypeRepo.getOne(1L));
                        obj.setPath(obj.getId().toString());
                        obj.setTimeCreate(new Timestamp(System.currentTimeMillis()));
                        obj.setIsActivated(false);
                        obj.setIsDeleted(false);

                        ClsOrganization parent = psqlLexproEntityManager.merge(obj);

                        parent.setPath(parent.getId().toString());

                        parent = psqlLexproEntityManager.merge(parent);

                        parent.setOldId(obj.getId());

                        IdMap idMap = IdMap.builder()
                                .entityName(entityName)
                                .newId(parent.getId())
                                .oldId(obj.getId())
                                .build();

                        idMapRepo.save(idMap);

                        //System.out.println("id: " + obj.getId() + ", path: " + obj.getPath());
                        log.info("new_id: " + parent.getId() + ", old_id: " + obj.getId());

                        orgCount.getAndIncrement();

                        return parent;

                    } else {
                        log.info("old_id: " + obj.getId() + " - already migration");
                        return null;
                    }
                })
                .collect(Collectors.toList());

            log.info("parents count: " + parentList.size());

            log.info("childs import start");
            for(int i=0; i<parentList.size(); i++){
                int finalI = i;
                for(int j=0; j < list.size(); j++) {
                    ClsOrganization obj = list.get(j);

                    if ((obj.getIdParent() != null) && (parentList.get(finalI) != null) && (obj.getIdParent() == parentList.get(finalI).getOldId())) {

                        log.info("id: " + obj.getId() + ", parentId: " + parentList.get(finalI).getId());
                        obj.setIdParent(parentList.get(finalI).getId());
                        obj.setEmail("mail@govrb.ru");
                        obj.setFullName(obj.getName());
                        obj.setOrganizationType(clsOrganizationTypeRepo.getOne(2L));
                        obj.setTimeCreate(new Timestamp(System.currentTimeMillis()));
                        obj.setIsActivated(false);
                        obj.setIsDeleted(false);

                        ClsOrganization childOrg = psqlLexproEntityManager.merge(obj);

                        childOrg.setPath(parentList.get(finalI).getId().toString().concat(".").concat(childOrg.getId().toString()));

                        psqlLexproEntityManager.merge(childOrg);

                        IdMap idMap = IdMap.builder()
                                .entityName(entityName)
                                .newId(childOrg.getId())
                                .oldId(obj.getId())
                                .build();

                        psqlLexproEntityManager.persist(idMap);

                        orgCount.getAndIncrement();
                    }
                }
            }
            transaction.commit();
            log.info("save complete. Saved org count: " + orgCount.get());
        } catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            log.info("save error", e.getMessage());
        }
    }

    public List<ClsOrganization> convertOrgEntities(List<? extends Object> list){
        return list.stream()
            .filter(obj -> ((OrgEntity) obj).getFlagOrg().equalsIgnoreCase("T") || ((OrgEntity) obj).getFlagDep().equalsIgnoreCase("T"))
            .map(
                obj -> {
                    if(obj instanceof OrgEntity){
                        return convertOrgEntityToClsOrganization((OrgEntity) obj);
                    }
                    return null;
                }
            )
            .distinct()
        .collect(Collectors.toList());
    }

    private ClsOrganization convertOrgEntityToClsOrganization(OrgEntity orgEntity){
        return ClsOrganization.builder()
                .id(orgEntity.getId())
                .email(orgEntity.getEmail())
                .fullName(orgEntity.getName())
                .name(orgEntity.getName())
                .idParent(orgEntity.getPersonEntity() != null ? orgEntity.getPersonEntity().getOrgId() : null)
                .build();
    }


    //*********************** PERSON *************************

    public List<ClsEmployee> convertPersonEntities(List<? extends Object> list){
        return list.stream()
                .map(
                        obj -> {
                            if(obj instanceof PersonEntity){
                                return convertPersonEntityToClsEmployee((PersonEntity) obj);
                            }
                            return null;
                        }
                )
                .distinct()
                .collect(Collectors.toList());
    }

    private ClsEmployee convertPersonEntityToClsEmployee(PersonEntity personEntity){

        String[] fio = {"", "", ""};

        fio = personEntity.getFio().trim().replaceAll("  ", " ").split("\\s|\\.");

        if(fio.length > 0) {
            return ClsEmployee.builder()
                    .id(personEntity.getId())
                    .email(personEntity.getEmail())
                    .lastname(fio[0])
                    .firstname(fio.length > 1 ? fio[1] : null)
                    .patronymic(fio.length > 2 ? fio[2] : null)
                    .build();

        } else {
            log.info("fio parse error: " + personEntity.getFio());
            return null;
        }
    }

    public void saveClsEmployee(List<ClsEmployee> list, List<PersonEntity> personEntities){

        EntityTransaction transaction = psqlLexproEntityManager.getTransaction();
        transaction.begin();

        String entityName = "person";
        log.info("Entity: " + entityName);
        log.info("source count: " + list.size());
        AtomicInteger personCount = new AtomicInteger(0);
        try{
            list.stream()
                .filter(obj -> obj != null)
                .forEach(obj -> {
                    if(idMapRepo.findByEntityNameAndOldId(entityName, obj.getId()) == null) {
                        obj.setEmail(obj.getEmail() != null ? obj.getEmail() : "default@mail.govrb.ru");
                        obj.setIsDeleted(false);
                        obj.setTimeCreate(new Timestamp(System.currentTimeMillis()));

                        ClsEmployee employee = psqlLexproEntityManager.merge(obj);

                        IdMap idMap = IdMap.builder()
                                .entityName(entityName)
                                .newId(employee.getId())
                                .oldId(obj.getId())
                                .build();

                        psqlLexproEntityManager.persist(idMap);

                        log.info("new_id: " + employee.getId() + ", old_id: " + obj.getId());

                        personCount.getAndIncrement();
/*
                        // save reg_organization__employee
                        PersonEntity person = personEntities.stream()
                                .filter(prs -> prs.getId() == obj.getId())
                                .findFirst().get();

                        IdMap orgId = idMapRepo.findByEntityNameAndOldId("org", person.getOrgId());
                        IdMap doljnId = idMapRepo.findByEntityNameAndOldId("sp_doljnost", person.getDoljnostId());

                        if(orgId!=null && doljnId!=null) {
                            RegOrganizationEmployee regOrganizationEmployee = RegOrganizationEmployee.builder()
                                    .organization(clsOrganizationRepo.getOne(orgId.getNewId()))
                                    .employee(employee)
                                    .typePosition(clsPositionRepo.getOne(doljnId.getNewId()))
                                    .employeeStatus(clsEmployeeStatusRepo.getOne(1L))
                                    .timeCreate(new Timestamp(System.currentTimeMillis()))
                                    .build();

                            psqlLexproEntityManager.persist(regOrganizationEmployee);
                        }*/
                    } else {
                        log.info(obj.getId() + " - already saved");
                    }
                });

            transaction.commit();
            log.info(entityName + " save complete. count: " + personCount.get());
        } catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            log.info(entityName + " save error", e.getMessage());
        }
    }

    //*********************** USERS *************************

    public List<ClsUser> convertUsersEntities(List<? extends Object> list){
        return list.stream()
                .map(
                        obj -> {
                            if(obj instanceof UsersEntity){
                                return convertUsersEntityToClsUser((UsersEntity) obj);
                            }
                            return null;
                        }
                )
                .distinct()
                .collect(Collectors.toList());
    }

    private ClsUser convertUsersEntityToClsUser(UsersEntity usersEntity){

        IdMap id = idMapRepo.findByEntityNameAndOldId("person", usersEntity.getPersonEntity().getId());

        ClsEmployee empl = id != null ? clsEmployeeRepo.getOne(id.getNewId()) : null;

        if(empl != null) {
            return ClsUser.builder()
                    .id(usersEntity.getUsersGuid())
                    .lastname(empl.getLastname())
                    .firstname(empl.getFirstname())
                    .patronymic(empl.getPatronymic())
                    .login(usersEntity.getLogin())
                    .idEmployee(empl.getId())
                    .build();
        } else {
            return null;
        }
    }

    public void saveClsUser(List<ClsUser> list){
        EntityTransaction transaction = psqlLexproEntityManager.getTransaction();
        transaction.begin();

        String entityName = "users";
        log.info("Entity : " + entityName);
        log.info("source count: " + list.size());
        AtomicInteger userCount = new AtomicInteger(0);
        try{
            list.stream()
                .filter(obj -> obj != null)
                .forEach(obj -> {
                    if(idMapRepo.findByEntityNameAndOldId(entityName, obj.getId()) == null) {
                        obj.setIsDeleted(false);
                        obj.setTimeCreate(new Timestamp(System.currentTimeMillis()));

                        ClsUser user = psqlLexproEntityManager.merge(obj);

                        IdMap idMap = IdMap.builder()
                                .entityName(entityName)
                                .newId(user.getId())
                                .oldId(obj.getId())
                                .build();

                        psqlLexproEntityManager.persist(idMap);

                        log.info("new_id: " + user.getId() + ", old_id: " + obj.getId());
                        userCount.getAndIncrement();
                        RegEmployeeUser regEmployeeUser = RegEmployeeUser.builder()
                                .user(user)
                                .employee(clsEmployeeRepo.getOne(user.getIdEmployee()))
                                .timeCreate(new Timestamp(System.currentTimeMillis()))
                                .build();

                        psqlLexproEntityManager.persist(regEmployeeUser);
                    }
                });

           transaction.commit();
           log.info(entityName + " save complete. count: " + userCount.get());
        } catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            log.info(entityName + " save error", e.getMessage());
        }
    }

    //*********************** SP_DOLJNOST *************************

    public List<ClsPosition> convertDoljnEntities(List<? extends Object> list){
        return list.stream()
                .map(
                        obj -> {
                            if(obj instanceof SpDoljnostEntity){
                                return convertDoljnEntityToClsPosition((SpDoljnostEntity) obj);
                            }
                            return null;
                        }
                )
                .distinct()
                .collect(Collectors.toList());
    }

    private ClsPosition convertDoljnEntityToClsPosition(SpDoljnostEntity spDoljnostEntity){
        String code = StrUtils.transliterate(spDoljnostEntity.getName())
                .toUpperCase()
                .trim()
                .replaceAll(" ", "_");
        return ClsPosition.builder()
                .id(spDoljnostEntity.getId())
                .code(code.substring(0, code.length() > 25 ? 25 : code.length()))
                .name(spDoljnostEntity.getName())
                .isDeputyPosition(false)
                .isDeleted(false)
                .build();
    }

    public void saveClsPosition(List<ClsPosition> list){
        EntityTransaction transaction = psqlLexproEntityManager.getTransaction();
        transaction.begin();
        String entityName = "sp_doljnost";
        log.info("Entity : " + entityName);
        log.info("source count: " + list.size());
        AtomicInteger posCount = new AtomicInteger(0);
        try {
            list.stream()
                    .filter(obj -> obj != null)
                    //TODO возможно надо удалять повторы по полю code?
                    .forEach(obj -> {
                        if(idMapRepo.findByEntityNameAndOldId(entityName, obj.getId()) == null) {
                            obj.setIsDeleted(false);
                            obj.setTimeCreate(new Timestamp(System.currentTimeMillis()));

                            ClsPosition clsPosition = psqlLexproEntityManager.merge(obj);

                            IdMap idMap = IdMap.builder()
                                    .entityName(entityName)
                                    .newId(clsPosition.getId())
                                    .oldId(obj.getId())
                                    .build();

                            psqlLexproEntityManager.persist(idMap);
                            log.info("new_id: " + clsPosition.getId() + ", old_id: " + obj.getId());
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
    }


    //*********************** DOCUMS *************************

    public List<DocRkk> convertDocumsEntities(List<? extends Object> list){
        return list.stream()
                .map(
                        obj -> {
                            if(obj instanceof DocumsEntity){
                                return convertDocumsEntityToDocRkk((DocumsEntity) obj);
                            }
                            return null;
                        }
                )
                .distinct()
                .collect(Collectors.toList());
    }

    private DocRkk convertDocumsEntityToDocRkk(DocumsEntity documsEntity){

        //TODO build ClsSession()
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

                //psqlLexproEntityManager.persist(clsSession);
                clsSessionRepo.saveAndFlush(clsSession);
            }
        }

        ClsNpaType npaType = null;
        if(documsEntity.getDkind().getName().equalsIgnoreCase("закон")) {
            npaType = ClsNpaType.builder().id(1L).build();
        } else {
            npaType = ClsNpaType.builder().id(2L).build();
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

        ClsRkkStatus status = null;

        switch (documsEntity.getStatus()) {
            case "В работе": status = ClsRkkStatus.builder().id(1L).build(); break;
            case "Отклонен": status = ClsRkkStatus.builder().id(4L).build(); break;
            case "Отозван": status = ClsRkkStatus.builder().id(2L).build(); break;
            case "Отправлен на доработку": status = ClsRkkStatus.builder().id(3L).build(); break;
            case "Принят":
            case "Принят в первом чтении": status = ClsRkkStatus.builder().id(5L).build(); break;
        }

        return DocRkk.builder()
                .id(documsEntity.getId())
                .rkkNumber(documsEntity.getRegNum())
                .npaName(documsEntity.getDescr())
                .registrationDate(documsEntity.getRegDate())
                .introductionDate(documsEntity.getDdate())
                .npaType(npaType)
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
                .status(status)
                .stage(null)
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

    public void saveDocRkk(List<DocRkk> list){
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

                            //if(obj.getSession().getId() == null) psqlLexproEntityManager.persist(obj.getSession());

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
    }


    //************************** FILES **********************************

    public List<TpRkkFile> convertFilesEntities(List<? extends Object> list){
        return list.stream()
                .map(
                        obj -> {
                            if(obj instanceof FilesEntity){
                                return convertFilesEntityToTpRkkFile((FilesEntity) obj);
                            }
                            return null;
                        }
                )
                .distinct()
                .collect(Collectors.toList());
    }

    private TpRkkFile convertFilesEntityToTpRkkFile(FilesEntity filesEntity) {

        return null;
/*        return TpRkkFile.builder()
                .docRkk(getDocRkk(fileDto))
                .group(getGroupAttachment(fileDto))
                .type(getTypeAttachment(fileDto))
                .participant(getOrganization(fileDto))
                .numberAttachment(fileDto.getNumberAttachment())
                .signingDate(null)
                .pageCount(filesEntity.getPageCount())
                .attachmentPath(filesEntity.getAttachmentPath())
                .fileName(filesEntity.getFileName())
                .originalFileName(filesEntity.getOriginalFileName())
                .fileExtension(filesEntity.getFileExtension())
                .hash(filesEntity.getHash())
                .fileSize(filesEntity.getFileSize())
                .operator(getEmployee(fileDto))
                .isDeleted(false)
                .timeCreate(new Timestamp(System.currentTimeMillis()))
                .hashSignature(fileDto.getHashSignature())
                .idFileSignature()
                .certificateInformation(fileDto.getCertificateInformation())
                .build();*/
    }

    public void saveTpRkkFile(List<TpRkkFile> list){
        EntityTransaction transaction = psqlLexproEntityManager.getTransaction();
        transaction.begin();
        String entityName = "files";
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

                            TpRkkFile rkkFile = psqlLexproEntityManager.merge(obj);

                            IdMap idMap = IdMap.builder()
                                    .entityName(entityName)
                                    .newId(rkkFile.getId())
                                    .oldId(obj.getId())
                                    .build();

                            psqlLexproEntityManager.persist(idMap);
                            log.info("new_id: " + rkkFile.getId() + ", old_id: " + obj.getId());
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
    }

}
