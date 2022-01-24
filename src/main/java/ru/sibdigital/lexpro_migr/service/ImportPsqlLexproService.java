package ru.sibdigital.lexpro_migr.service;

import com.vladmihalcea.hibernate.type.range.Range;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.sibdigital.lexpro_migr.dto.FileContainer;
import ru.sibdigital.lexpro_migr.model.lexpro.*;
import ru.sibdigital.lexpro_migr.model.zakon.*;
import ru.sibdigital.lexpro_migr.repo.lexpro.*;
import ru.sibdigital.lexpro_migr.repo.zakon.PersonRepo;
import ru.sibdigital.lexpro_migr.repo.zakon.UsersRepo;
import ru.sibdigital.lexpro_migr.utils.FileUtils;
import ru.sibdigital.lexpro_migr.utils.StrUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ImportPsqlLexproService {

    @Value("${upload.path}")
    String uploadingDir;

    //zakon.gdb
    @Autowired
    PersonRepo personRepo;

    @Autowired
    UsersRepo usersRepo;


    //lexpro
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
    ClsOrganizationRepo clsOrganizationRepo;

    @Autowired
    ClsEmployeeStatusRepo clsEmployeeStatusRepo;

    @Autowired
    IdMapRepo idMapRepo;

    @Autowired
    ClsNpaTypeRepo clsNpaTypeRepo;

    @Autowired
    ClsSessionRepo clsSessionRepo;

    @Autowired
    DocRkkRepo docRkkRepo;

    @Autowired
    ClsGroupAttachmentRepo clsGroupAttachmentRepo;

    @Autowired
    ClsTypeAttachmentRepo clsTypeAttachmentRepo;

    private static Map<Long, Long> fgroupIds = new HashMap<>();

    public ImportPsqlLexproService(){
        this.fgroupIds.put(1L, 1L);
        this.fgroupIds.put(2L, 5L);
        this.fgroupIds.put(3L, 4L);
        this.fgroupIds.put(4L, 2L);
        this.fgroupIds.put(5L, 2L);
        this.fgroupIds.put(6L, 2L);
        this.fgroupIds.put(7L, 3L);
    }

    //*************************** ORGS ******************************

/*
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

*/

    //*********************** PERSON *************************

/*
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
*/
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
                        }*//*

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
*/

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

        ClsEmployee empl = id != null ? clsEmployeeRepo.findById(id.getNewId()).orElse(null) : null;

        if(empl != null) {
            return ClsUser.builder()
                    .id(usersEntity.getUsersGuid())
                    .lastname(empl.getLastname())
                    .firstname(empl.getFirstname())
                    .patronymic(empl.getPatronymic())
                    .login(usersEntity.getLogin())
//                    .idEmployee(empl.getId())
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
                        obj.setPassword("$2a$10$xLirbhZ9mchUlvlYXT/qrerSYswtiV/.F/9FwP4AA8Ka.29B0cAIO");

                        ClsUser user = psqlLexproEntityManager.merge(obj);

                        IdMap idMap = IdMap.builder()
                                .entityName(entityName)
                                .newId(user.getId())
                                .oldId(obj.getId())
                                .build();

                        psqlLexproEntityManager.persist(idMap);

                        log.info("new_id: " + user.getId() + ", old_id: " + obj.getId());
                        userCount.getAndIncrement();
/*                        RegEmployeeUser regEmployeeUser = RegEmployeeUser.builder()
                                .user(user)
                                .employee(clsEmployeeRepo.findById(user.getIdEmployee()).orElse(null))
                                .timeCreate(new Timestamp(System.currentTimeMillis()))
                                .build();

                        psqlLexproEntityManager.persist(regEmployeeUser);*/
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

/*    public List<ClsPosition> convertDoljnEntities(List<? extends Object> list){
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
    }*/

    //*********************** SP_FKIND *************************

/*    public List<ClsTypeAttachment> convertSpFkindEntities(List<? extends Object> list){
        return list.stream()
                .map(
                        obj -> {
                            if(obj instanceof SpFkindEntity){
                                return convertSpFkindEntityToClsTypeAttachment((SpFkindEntity) obj);
                            }
                            return null;
                        }
                )
                .distinct()
                .collect(Collectors.toList());
    }

    private ClsTypeAttachment convertSpFkindEntityToClsTypeAttachment(SpFkindEntity spFkindEntity){
        String code = StrUtils.transliterate(spFkindEntity.getName())
                .toUpperCase()
                .trim()
                .replaceAll(" ", "_");
        return ClsTypeAttachment.builder()
                .id(spFkindEntity.getId())
                .code(code.substring(0, code.length() > 25 ? 25 : code.length()))
                .name(spFkindEntity.getName())
                .isDeleted(false)
                .build();
    }

    public void saveClsTypeAttachment(List<ClsTypeAttachment> list){
        EntityTransaction transaction = psqlLexproEntityManager.getTransaction();
        transaction.begin();
        String entityName = "sp_fkind";
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

                            ClsTypeAttachment clsTypeAttachment = psqlLexproEntityManager.merge(obj);

                            IdMap idMap = IdMap.builder()
                                    .entityName(entityName)
                                    .newId(clsTypeAttachment.getId())
                                    .oldId(obj.getId())
                                    .build();

                            psqlLexproEntityManager.persist(idMap);

                            log.info("new_id: " + clsTypeAttachment.getId() + ", old_id: " + obj.getId());
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
    }*/


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
                .status(getDocumStatus(documsEntity))
                .stage(getDocumStage(documsEntity))
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

    private ClsRkkStatus getDocumStatus(DocumsEntity documsEntity){
        ClsRkkStatus status = null;

        switch (documsEntity.getStatus()) {
            case "В работе": status = ClsRkkStatus.builder().id(1L).build(); break;
            case "Отклонен": status = ClsRkkStatus.builder().id(4L).build(); break;
            case "Отозван": status = ClsRkkStatus.builder().id(2L).build(); break;
            case "Отправлен на доработку": status = ClsRkkStatus.builder().id(3L).build(); break;
            case "Принят":
            case "Принят в первом чтении": status = ClsRkkStatus.builder().id(5L).build(); break;
        }

        return status;
    }

    private ClsRkkStage getDocumStage(DocumsEntity documsEntity){
        return null;
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
                                try {
                                    return convertFilesEntityToTpRkkFile((FilesEntity) obj);
                                } catch (IOException e){
                                    log.info(((FilesEntity) obj).getId() + " not saved");
                                }
                            }
                            return null;
                        }
                )
                .distinct()
                .collect(Collectors.toList());
    }

    private TpRkkFile convertFilesEntityToTpRkkFile(FilesEntity filesEntity) throws IOException {

        DocRkk doc = getDocRkk(filesEntity);

        if(doc != null && filesEntity.getFname() != null) {
            // составить относительный путь
            final String originalFilename = filesEntity.getFname();
            final String extension = filesEntity.getFext();

            final String directory = "rkk" + File.separator + doc.getId();
            final File folder = getSavingDirectory(directory);

            final String prefix = doc.getId().toString();
            final String randomUUID = UUID.randomUUID().toString();

            FileContainer container = getFileContainer(originalFilename, extension, directory, folder, prefix, randomUUID);

            // сохранить файл
            if(filesEntity.getFdata() != null) {
                saveToFile(container.getFile(), filesEntity.getFdata());
                getFileParameters(container);
            }

            return TpRkkFile.builder()
                    .id(filesEntity.getId())
                    .docRkk(getDocRkk(filesEntity))
                    .group(getGroupAttachment(filesEntity))
                    .type(getTypeAttachment(filesEntity))
                    .participant(getParticipant(filesEntity))
                    .numberAttachment(filesEntity.getDnum())
                    .signingDate(null)
                    .pageCount(filesEntity.getPageCount())
                    .attachmentPath(container.getAttachmentPath())
                    .fileName(container.getFileName())
                    .originalFileName(filesEntity.getFname())
                    .fileExtension(filesEntity.getFext())
                    .hash(filesEntity.getMd5Sum())
                    .fileSize(container.getFileSize())
                    .operator(getOperator(filesEntity.getEditorLogin()))
                    .isDeleted(false)
                    .timeCreate(new Timestamp(System.currentTimeMillis()))
                    .hashSignature(null)
                    .idFileSignature(null)
                    .certificateInformation(filesEntity.getEcpDescr())
                    .build();

        } else {
            return null;
        }
    }

    private String getAbsolutePath(File folder, String filename) {
        String absolutePath = folder.getAbsolutePath() + File.separator + filename;

        return absolutePath;
    }

    private String getRelativePath(String directory, String filename) {
        String relativePath = directory + File.separator + filename;

        return relativePath;
    }

    private FileContainer getFileContainer(String originalFilename, String extension, String directory, File folder,
                                           String prefix, String randomUUID) {

        final String filename = prefix + "_" + randomUUID + extension;
        final String absolutePath = getAbsolutePath(folder, filename);
        final String relativePath = getRelativePath(directory, filename);

        File file = new File(absolutePath);
        FileContainer container = createFileContainer(relativePath, filename, originalFilename, extension, file);

        return container;
    }

    private FileContainer createFileContainer(String relativePath, String filename, String originalFilename,
                                              String extension, File file) {

        FileContainer container = FileContainer.builder()
                .attachmentPath(relativePath)
                .fileName(filename)
                .originalFileName(originalFilename)
                .fileExtension(extension)
                .file(file)
                .build();

        return container;
    }

    private File getSavingDirectory(String directory) {
        String filepath = uploadingDir + File.separator + directory;
        File folder = new File(filepath);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        return folder;
    }

    private void getFileParameters(FileContainer container) throws IOException {
//        final Integer pageCount = FileUtils.getPageCount(container.getFile(), container.getFileExtension());
        final String fileHash = FileUtils.getFileHash(container.getFile());
        final long fileSize = Files.size(container.getFile().toPath());

        //container.setPageCount(pageCount);
        container.setHash(fileHash);
        container.setFileSize(fileSize);
    }


    private DocRkk getDocRkk(FilesEntity filesEntity){
        DocRkk docRkk = null;
        if(filesEntity.getDocumId() != null) {
            IdMap doc = idMapRepo.findByEntityNameAndOldId("docums", filesEntity.getDocumId());
            if (doc != null) {
                docRkk = docRkkRepo.findById(doc.getNewId()).orElse(null);
            }
        }
        return docRkk;
    }

    private ClsGroupAttachment getGroupAttachment(FilesEntity filesEntity){
        if(filesEntity.getFgroupId() != null) {
            Long newId = fgroupIds.containsKey(filesEntity.getFgroupId()) ? fgroupIds.get(filesEntity.getFgroupId()) : -1L;
            return clsGroupAttachmentRepo.findById(newId).orElse(null);
        } else {
            return null;
        }
    }

    private ClsTypeAttachment getTypeAttachment(FilesEntity filesEntity){
        ClsTypeAttachment typeAttachment = null;
        if(filesEntity.getDocumId() != null) {
            IdMap doc = idMapRepo.findByEntityNameAndOldId("sp_fkind", filesEntity.getFkindId());
            if (doc != null) {
                typeAttachment = clsTypeAttachmentRepo.findById(doc.getNewId()).orElse(null);
            }
        }
        return typeAttachment;
    }

    private ClsOrganization getParticipant(FilesEntity filesEntity){
        ClsOrganization participant = null;
        if(filesEntity.getOrgId() != null) {
            IdMap kom = idMapRepo.findByEntityNameAndOldId("org", filesEntity.getOrgId());
            if (kom != null) {
                participant = ClsOrganization.builder().id(kom.getNewId()).build();
            }
        }
        return participant;
    }

    private ClsEmployee getOperator(String login){
        ClsEmployee result = null;
        UsersEntity user = usersRepo.findByLogin(login).orElse(null);
        if(user != null){
            PersonEntity person = personRepo.findById(user.getPersonEntity().getId()).orElse(null);
            if(person != null){
                IdMap doc = idMapRepo.findByEntityNameAndOldId("person", person.getId());
                if (doc != null) {
                    result = clsEmployeeRepo.findById(doc.getNewId()).orElse(null);
                }
            }
        }
        return result;
    }

    private void saveToFile(File file, byte[] fileData){
        try (FileOutputStream fos = new FileOutputStream(file))
        {
            fos.write(fileData);
            log.info("Successfully written data to the file: " + file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    //******************************* MESSAGES **************************************

/*
    public List<ClsTypeAttachment> convertSpFkindEntities(List<? extends Object> list){
        return list.stream()
                .map(
                        obj -> {
                            if(obj instanceof SpFkindEntity){
                                return convertSpFkindEntityToClsTypeAttachment((SpFkindEntity) obj);
                            }
                            return null;
                        }
                )
                .distinct()
                .collect(Collectors.toList());
    }

    private ClsTypeAttachment convertSpFkindEntityToClsTypeAttachment(SpFkindEntity spFkindEntity){
        String code = StrUtils.transliterate(spFkindEntity.getName())
                .toUpperCase()
                .trim()
                .replaceAll(" ", "_");
        return ClsTypeAttachment.builder()
                .id(spFkindEntity.getId())
                .code(code.substring(0, code.length() > 25 ? 25 : code.length()))
                .name(spFkindEntity.getName())
                .isDeleted(false)
                .build();
    }

    public void saveClsTypeAttachment(List<ClsTypeAttachment> list){
        EntityTransaction transaction = psqlLexproEntityManager.getTransaction();
        transaction.begin();
        String entityName = "sp_fkind";
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

                            ClsTypeAttachment clsTypeAttachment = psqlLexproEntityManager.merge(obj);

                            IdMap idMap = IdMap.builder()
                                    .entityName(entityName)
                                    .newId(clsTypeAttachment.getId())
                                    .oldId(obj.getId())
                                    .build();

                            psqlLexproEntityManager.persist(idMap);

                            log.info("new_id: " + clsTypeAttachment.getId() + ", old_id: " + obj.getId());
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

*/
}
