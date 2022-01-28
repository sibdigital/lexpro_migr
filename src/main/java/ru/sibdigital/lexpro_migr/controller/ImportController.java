package ru.sibdigital.lexpro_migr.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import ru.sibdigital.lexpro_migr.model.lexpro.*;
import ru.sibdigital.lexpro_migr.model.zakon.*;
import ru.sibdigital.lexpro_migr.protocol.CheckProtocol;
import ru.sibdigital.lexpro_migr.service.*;

import java.util.List;

@Slf4j
@Controller
public class ImportController {
    @Autowired
    ExportFbService exportFbService;

    @Autowired
    ImportFilesService importFilesService;

    @Autowired
    ImportOrgService importOrgService;

    @Autowired
    ImportSpDoljnService importSpDoljnService;

    @Autowired
    ImportSpFkindService importSpFkindService;

    @Autowired
    ImportPersonService importPersonService;

    @Autowired
    ImportUsersService importUsersService;

    @Autowired
    ImportDocumsService importDocumsService;

    private final String dir = "classpath:sql-scripts/export_data";

    @Value("${step:1000}")
    private Long step;

    private CheckProtocol checkProtocol = CheckProtocol.getInstance();

    public void exportSprEntities(Long startId) {
        try {

            log.info("export zakon");
            // import org
            String entityName = "org";
            Long maxId = exportFbService.getMaxIdInTable(entityName);
            if (startId == null) {
                startId = 0L;
            }
            checkProtocol.addRow("импорт организаций: startId: " + startId + ", maxId: " + maxId + ", step: " + step);
            Integer srcCount = 0;
            Integer saveCount = 0;
            while (startId < maxId) {
                List<?> entities = exportFbService.getEntities(dir, entityName, startId, startId + step);
                srcCount += entities.size();
//                checkProtocol.addRow(String.format("выбрано записей: %d, startId: %d, endId: %d", entities.size(), startId, startId + step));
                List<ClsOrganization> resultList = importOrgService.convertEntities((List<OrgEntity>) entities);

//                checkProtocol.addRow("преобразовано записей: " + resultList.size());

                saveCount += importOrgService.saveToDb(resultList);

                startId += step;
            }

            checkProtocol.addRow("--- всего выбрано из исходной БД записей: " + srcCount);
            checkProtocol.addRow("--- всего сохранено в результирующей БД записей: " + saveCount);

            // import sp_doljnost
            entityName = "sp_doljnost";
            maxId = exportFbService.getMaxIdInTable(entityName);
            startId = 0L;
            srcCount = saveCount = 0;

            if (startId == null) {
                startId = 0L;
            }
            checkProtocol.addRow("импорт "+entityName+": startId: " + startId + ", maxId: " + maxId + ", step: " + step);
            while (startId < maxId) {
                List<?> entities = exportFbService.getEntities(dir, entityName, startId, startId + step);
                //checkProtocol.addRow(String.format("выбрано записей: %d, startId: %d, endId: %d", entities.size(), startId, startId + step));
                srcCount += entities.size();
                List<ClsPosition> resultList = importSpDoljnService.convertEntities((List<SpDoljnostEntity>) entities);
                //checkProtocol.addRow("преобразовано записей: " + resultList.size());

                saveCount += importSpDoljnService.saveToDb(resultList);

                startId += step;
            }

            checkProtocol.addRow("--- всего выбрано из исходной БД записей: " + srcCount);
            checkProtocol.addRow("--- всего сохранено в результирующей БД записей: " + saveCount);


            // import person
            entityName = "person";
            maxId = exportFbService.getMaxIdInTable(entityName);
            startId = 0L;
            srcCount = saveCount = 0;
            if (startId == null) {
                startId = 0L;
            }
            checkProtocol.addRow("импорт "+entityName+": startId: " + startId + ", maxId: " + maxId + ", step: " + step);

            while (startId < maxId) {
                List<?> entities = exportFbService.getEntities(dir, entityName, startId, startId + step);
                //checkProtocol.addRow(String.format("выбрано записей: %d, startId: %d, endId: %d", entities.size(), startId, startId + step));
                srcCount += entities.size();
                List<ClsEmployee> resultList = importPersonService.convertEntities((List<PersonEntity>) entities);
                //checkProtocol.addRow("преобразовано записей: " + resultList.size());
                saveCount += importPersonService.saveToDb(resultList);

                startId += step;
            }
            checkProtocol.addRow("--- всего выбрано из исходной БД записей: " + srcCount);
            checkProtocol.addRow("--- всего сохранено в результирующей БД записей: " + saveCount);


            // import sp_fkind
            entityName = "sp_fkind";
            maxId = exportFbService.getMaxIdInTable(entityName);
            startId = 0L;
            saveCount = srcCount = 0;
            if (startId == null) {
                startId = 0L;
            }
            checkProtocol.addRow("импорт "+entityName+": startId: " + startId + ", maxId: " + maxId + ", step: " + step);

            while (startId < maxId) {
                List<?> entities = exportFbService.getEntities(dir, entityName, startId, startId + step);
                //checkProtocol.addRow(String.format("выбрано записей: %d, startId: %d, endId: %d", entities.size(), startId, startId + step));
                srcCount += entities.size();
                List<ClsTypeAttachment> resultList = importSpFkindService.convertEntities((List<SpFkindEntity>) entities);
                //checkProtocol.addRow("преобразовано записей: " + resultList.size());
                importSpFkindService.saveToDb(resultList);

                startId += step;
            }
            checkProtocol.addRow("--- всего выбрано из исходной БД записей: " + srcCount);
            checkProtocol.addRow("--- всего сохранено в результирующей БД записей: " + saveCount);


            // import users
            entityName = "users";
            maxId = exportFbService.getMaxIdInTable(entityName);
            startId = 0L;
            saveCount = srcCount = 0;
            if (startId == null) {
                startId = 0L;
            }
            checkProtocol.addRow("импорт "+entityName+": startId: " + startId + ", maxId: " + maxId + ", step: " + step);
            while (startId < maxId) {
                List<?> entities = exportFbService.getEntities(dir, entityName, startId, startId + step);
                //checkProtocol.addRow(String.format("выбрано записей: %d, startId: %d, endId: %d", entities.size(), startId, startId + step));
                srcCount += entities.size();
                List<ClsUser> resultList = importUsersService.convertEntities((List<UsersEntity>) entities);
                //checkProtocol.addRow("преобразовано записей: " + resultList.size());
                importUsersService.saveToDb(resultList);

                startId += step;
            }
            checkProtocol.addRow("--- всего выбрано из исходной БД записей: " + srcCount);
            checkProtocol.addRow("--- всего сохранено в результирующей БД записей: " + saveCount);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportDocumsEntity(Long startId) {
        try {

            log.info("export docums");
            // import org
            String entityName = "docums";
            Long maxId = exportFbService.getMaxIdInTable(entityName);
            if (startId == null) {
                startId = 0L;
            }
            Long size = 0L;
            Integer saveCount = 0;
            Integer srcCount = 0;
            checkProtocol.addRow("импорт "+entityName+": startId: " + startId + ", maxId: " + maxId + ", step: " + step);            size = 0L;
            while (startId < maxId) {
                List<?> entities = exportFbService.getEntities(dir, entityName, startId, startId + step);
                //checkProtocol.addRow(String.format("выбрано записей: %d, startId: %d, endId: %d", entities.size(), startId, startId + step));
                srcCount += entities.size();
                //log.info("converting documsEntity start");
                List<DocRkk> resultList = importDocumsService.convertEntities((List<DocumsEntity>) entities);
                //log.info("converting documsEntity end");
                //checkProtocol.addRow("преобразовано записей: " + resultList.size());
                saveCount += importDocumsService.saveToDb(resultList);

                size += entities.size();
                startId += step;
            }
            checkProtocol.addRow("--- всего выбрано из исходной БД записей: " + srcCount);
            checkProtocol.addRow("--- всего сохранено в результирующей БД записей: " + saveCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportFilesEntity(Long startId) {
        try {
            log.info("export files");
            String entityName = "files";
            Long maxId = exportFbService.getMaxIdInTable(entityName);
            if (startId == null) {
                startId = 0L;
            }
            Integer saveCount = 0;
            Integer srcCount = 0;
            checkProtocol.addRow("импорт "+entityName+": startId: " + startId + ", maxId: " + maxId + ", step: " + step);
            List<?> entities = null;
            List<TpRkkFile> resultList = null;
            while (startId < maxId) {
                entities = exportFbService.getEntities(dir, entityName, startId, startId + step);
                //checkProtocol.addRow("выбрано записей: " + entities.size());
                srcCount += entities.size();
                log.info("converting start");
                resultList = importFilesService.convertEntities((List<FilesEntity>) entities);
                log.info("converting end");
                entities = null;
                //checkProtocol.addRow("преобразовано записей: " + resultList.size());
                saveCount += importFilesService.saveTpRkkFile(resultList);

                startId += step;

            }

            checkProtocol.addRow("--- всего выбрано из исходной БД записей: " + srcCount);
            checkProtocol.addRow("--- всего сохранено в результирующей БД записей: " + saveCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
