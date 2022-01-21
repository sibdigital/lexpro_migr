package ru.sibdigital.lexpro_migr.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.sibdigital.lexpro_migr.model.lexpro.*;
import ru.sibdigital.lexpro_migr.model.zakon.PersonEntity;
import ru.sibdigital.lexpro_migr.service.ImportPsqlLexproService;
import ru.sibdigital.lexpro_migr.service.ImportPsqlZakonService;
import ru.sibdigital.lexpro_migr.service.ExportFbService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(path="/export_zakon")
public class ExportController {

    @Autowired
    ExportFbService exportFbService;

    @Autowired
    ImportPsqlZakonService importPsqlZakonService;

    @Autowired
    ImportPsqlLexproService importPsqlLexproService;

    private final String dir = "classpath:sql-scripts/export_data";
    private final String sql_dir = "classpath:sql-scripts/export_zakon_data";
    private final Long step = 50L; //1000L;

    @GetMapping("/table/{table_name}")
    public @ResponseBody
    String exportEntity(@PathVariable("table_name") String tableName, @RequestParam(value = "startId", required = false) Long startId) {
        try {
            Long maxId = exportFbService.getMaxIdInTable(tableName);
            if (startId == null) {
                startId = 0L;
            }
            Long size = 0L;
            while (startId < maxId) {
                List<?> entities = exportFbService.getEntities(dir, tableName, startId, startId + step);
                importPsqlZakonService.saveEntities(entities);
                size += entities.size();
                startId += step;
            }
            return "Сохранено " + size + " элементов";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    @GetMapping("/spr")
    public @ResponseBody
    String exportUserPersonEntity(@RequestParam(value = "startId", required = false) Long startId) {
        try {

            log.info("export zakon");
            // import org
            String entityName = "org";
            Long maxId = exportFbService.getMaxIdInTable(entityName);
            if (startId == null) {
                startId = 0L;
            }
            Long size = 0L;
            while (startId < maxId) {
                List<?> entities = exportFbService.getEntities(dir, entityName, startId, startId + step);

                List<ClsOrganization> resultList = importPsqlLexproService.convertOrgEntities(entities);

                importPsqlLexproService.saveClsOrganization(resultList);

                size += entities.size();
                startId += step;
            }


            // import sp_doljnost
            entityName = "sp_doljnost";
            maxId = exportFbService.getMaxIdInTable(entityName);
            startId = 0L;
            if (startId == null) {
                startId = 0L;
            }
            size = 0L;
            while (startId < maxId) {
                List<?> entities = exportFbService.getEntities(dir, entityName, startId, startId + step);

                List<ClsPosition> resultList = importPsqlLexproService.convertDoljnEntities(entities);

                importPsqlLexproService.saveClsPosition(resultList);

                size += entities.size();
                startId += step;
            }

            // import person
            entityName = "person";
            maxId = exportFbService.getMaxIdInTable(entityName);
            startId = 0L;
            if (startId == null) {
                startId = 0L;
            }
            size = 0L;
            while (startId < maxId) {
                List<?> entities = exportFbService.getEntities(dir, entityName, startId, startId + step);

                List<ClsEmployee> resultList = importPsqlLexproService.convertPersonEntities(entities);

                importPsqlLexproService.saveClsEmployee(resultList, (List< PersonEntity>)entities);

                size += entities.size();
                startId += step;
            }


            // import users
/*
            entityName = "users";
            maxId = exportFbService.getMaxIdInTable(entityName);
            startId = 0L;
            if (startId == null) {
                startId = 0L;
            }
            size = 0L;
            while (startId < maxId) {
                List<?> entities = exportFbService.getEntities(dir, entityName, startId, startId + step);

                List<ClsUser> resultList = importPsqlLexproService.convertUsersEntities(entities);

                importPsqlLexproService.saveClsUser(resultList);

                size += entities.size();
                startId += step;
            }
*/

            return "Migration sp_doljnost, person, users complete.";

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @GetMapping("/docums")
    public @ResponseBody
    String exportDocumsEntity(@RequestParam(value = "startId", required = false) Long startId) {
        try {

            log.info("export docums");
            // import org
            String entityName = "docums";
            Long maxId = exportFbService.getMaxIdInTable(entityName);
            startId = 0L;
            if (startId == null) {
                startId = 0L;
            }
            Long size = 0L;
            while (startId < maxId) {
                List<?> entities = exportFbService.getEntities(dir, entityName, startId, startId + step);

                log.info("converting documsEntity start");
                List<DocRkk> resultList = importPsqlLexproService.convertDocumsEntities(entities);
                log.info("converting documsEntity end");

                importPsqlLexproService.saveDocRkk(resultList);

                size += entities.size();
                startId += step;
            }

            return "Migration complete.";

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    @GetMapping("/files")
    public @ResponseBody
    String exportFilesEntity(@RequestParam(value = "startId", required = false) Long startId) {
        try {

            log.info("export files");
            // import org
            String entityName = "files";
            Long maxId = exportFbService.getMaxIdInTable(entityName);
            startId = 0L;
            if (startId == null) {
                startId = 0L;
            }
            Long size = 0L;
            while (startId < maxId) {
                List<?> entities = exportFbService.getEntities(dir, entityName, startId, startId + step);

                log.info("converting start");
                List<TpRkkFile> resultList = importPsqlLexproService.convertFilesEntities(entities);
                log.info("converting end");

                importPsqlLexproService.saveTpRkkFile(resultList);

                size += entities.size();
                startId += step;
            }

            return "Migration complete.";

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}

