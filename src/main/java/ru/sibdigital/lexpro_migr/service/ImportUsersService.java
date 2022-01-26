package ru.sibdigital.lexpro_migr.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sibdigital.lexpro_migr.model.lexpro.ClsEmployee;
import ru.sibdigital.lexpro_migr.model.lexpro.ClsUser;
import ru.sibdigital.lexpro_migr.model.lexpro.IdMap;
import ru.sibdigital.lexpro_migr.model.zakon.UsersEntity;
import ru.sibdigital.lexpro_migr.repo.lexpro.ClsEmployeeRepo;

import javax.persistence.EntityTransaction;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class ImportUsersService  extends ImportService<UsersEntity, ClsUser>{

    @Autowired
    ClsEmployeeRepo clsEmployeeRepo;

    private final static String entity = "users";

    public ImportUsersService() {
        super(entity);
    }

    @Override
    protected ClsUser convertEntity(UsersEntity sourceEntity) {
        IdMap id = idMapRepo.findByEntityNameAndOldId("person", sourceEntity.getPersonEntity().getId());

        ClsEmployee empl = id != null ? clsEmployeeRepo.findById(id.getNewId()).orElse(null) : null;

        if(empl != null) {
            return ClsUser.builder()
                    .id(sourceEntity.getUsersGuid())
                    .lastname(empl.getLastname())
                    .firstname(empl.getFirstname())
                    .patronymic(empl.getPatronymic())
                    .login(sourceEntity.getLogin())
//                    .idEmployee(empl.getId())
                    .build();
        } else {
            return null;
        }    }

    @Override
    public Integer saveToDb(List<ClsUser> list) {
        EntityTransaction transaction = psqlLexproEntityManager.getTransaction();
        transaction.begin();

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

        return userCount.get();
    }
}
