package ru.sibdigital.lexpro_migr.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.lexpro_migr.model.lexpro.ClsEmployee;
import ru.sibdigital.lexpro_migr.model.lexpro.IdMap;
import ru.sibdigital.lexpro_migr.model.zakon.PersonEntity;

import javax.persistence.EntityTransaction;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class ImportPersonService extends ImportService<PersonEntity, ClsEmployee> {

    private final static String entity = "person";

    public ImportPersonService() {
        super(entity);
    }

    @Override
    protected ClsEmployee convertEntity(PersonEntity sourceEntity) {
        String[] fio;

        fio = sourceEntity.getFio().trim().replaceAll("  ", " ").split("\\s|\\.");

        if(fio.length > 0) {
            return ClsEmployee.builder()
                    .id(sourceEntity.getId())
                    .email(sourceEntity.getEmail())
                    .lastname(fio[0])
                    .firstname(fio.length > 1 ? fio[1] : null)
                    .patronymic(fio.length > 2 ? fio[2] : null)
                    .build();

        } else {
            log.info("fio parse error: " + sourceEntity.getFio());
            return null;
        }
    }

    @Override
    public void saveToDb(List<ClsEmployee> list) {
        EntityTransaction transaction = psqlLexproEntityManager.getTransaction();
        transaction.begin();

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
}
