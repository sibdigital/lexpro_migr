package ru.sibdigital.lexpro_migr.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.lexpro_migr.model.lexpro.ClsTypeAttachment;
import ru.sibdigital.lexpro_migr.model.lexpro.IdMap;
import ru.sibdigital.lexpro_migr.model.zakon.SpFkindEntity;
import ru.sibdigital.lexpro_migr.utils.StrUtils;

import javax.persistence.EntityTransaction;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class ImportSpFkindService extends ImportService<SpFkindEntity, ClsTypeAttachment> {

    private final static String entity = "sp_doljnost";

    public ImportSpFkindService() {
        super(entity);
    }

    @Override
    protected ClsTypeAttachment convertEntity(SpFkindEntity sourceEntity) {
        String code = StrUtils.transliterate(sourceEntity.getName())
                .toUpperCase()
                .trim()
                .replaceAll(" ", "_");
        return ClsTypeAttachment.builder()
                .id(sourceEntity.getId())
                .code(code.substring(0, code.length() > 25 ? 25 : code.length()))
                .name(sourceEntity.getName())
                .isDeleted(false)
                .build();
    }

    @Override
    public void saveToDb(List<ClsTypeAttachment> list) {
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
}
