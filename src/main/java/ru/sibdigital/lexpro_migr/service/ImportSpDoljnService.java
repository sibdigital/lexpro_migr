package ru.sibdigital.lexpro_migr.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.lexpro_migr.model.lexpro.ClsPosition;
import ru.sibdigital.lexpro_migr.model.lexpro.IdMap;
import ru.sibdigital.lexpro_migr.model.zakon.SpDoljnostEntity;
import ru.sibdigital.lexpro_migr.utils.StrUtils;

import javax.persistence.EntityTransaction;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class ImportSpDoljnService extends ImportService<SpDoljnostEntity, ClsPosition> {

    private final static String entity = "sp_doljnost";

    public ImportSpDoljnService() {
        super(entity);
    }

    @Override
    protected ClsPosition convertEntity(SpDoljnostEntity sourceEntity) {
        String code = StrUtils.transliterate(sourceEntity.getName())
                .toUpperCase()
                .trim()
                .replaceAll(" ", "_");
        return ClsPosition.builder()
                .id(sourceEntity.getId())
                .code(code.substring(0, code.length() > 25 ? 25 : code.length()))
                .name(sourceEntity.getName())
                .isDeputyPosition(false)
                .isDeleted(false)
                .build();
    }

    @Override
    public Integer saveToDb(List<ClsPosition> list) {
        EntityTransaction transaction = psqlLexproEntityManager.getTransaction();
        transaction.begin();
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

        return posCount.get();
    }
}
