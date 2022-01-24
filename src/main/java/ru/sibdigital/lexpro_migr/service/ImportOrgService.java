package ru.sibdigital.lexpro_migr.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sibdigital.lexpro_migr.model.lexpro.ClsOrganization;
import ru.sibdigital.lexpro_migr.model.lexpro.IdMap;
import ru.sibdigital.lexpro_migr.model.zakon.OrgEntity;
import ru.sibdigital.lexpro_migr.repo.lexpro.ClsOrganizationTypeRepo;

import javax.persistence.EntityTransaction;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ImportOrgService extends ImportService<OrgEntity, ClsOrganization> {

    @Autowired
    ClsOrganizationTypeRepo clsOrganizationTypeRepo;

    private final static String entityName = "org";

    public ImportOrgService() {
        super(entityName);
    }

    @Override
    protected ClsOrganization convertEntity(OrgEntity sourceEntity) {
        return ClsOrganization.builder()
                .id(sourceEntity.getId())
                .email(sourceEntity.getEmail())
                .fullName(sourceEntity.getName())
                .name(sourceEntity.getName())
                .idParent(sourceEntity.getPersonEntity() != null ? sourceEntity.getPersonEntity().getOrgId() : null)
                .build();
    }

    @Override
    public void saveToDb(List<ClsOrganization> list) {
        EntityTransaction transaction = psqlLexproEntityManager.getTransaction();
        try{
            transaction.begin();

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
}
