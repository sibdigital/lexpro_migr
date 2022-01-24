package ru.sibdigital.lexpro_migr.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.sibdigital.lexpro_migr.model.lexpro.*;
import ru.sibdigital.lexpro_migr.model.zakon.*;
import ru.sibdigital.lexpro_migr.service.*;

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
    private final String sql_dir = "classpath:sql-scripts/export_zakon_data";
    private final Long step = 100L; //1000L;


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

                List<ClsOrganization> resultList = importOrgService.convertEntities((List<OrgEntity>) entities);

                importOrgService.saveToDb(resultList);

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

                List<ClsPosition> resultList = importSpDoljnService.convertEntities((List<SpDoljnostEntity>) entities);

                importSpDoljnService.saveToDb(resultList);

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

                List<ClsEmployee> resultList = importPersonService.convertEntities((List<PersonEntity>) entities);

                importPersonService.saveToDb(resultList);

                size += entities.size();
                startId += step;
            }


            // import sp_fkind
            entityName = "sp_fkind";
            maxId = exportFbService.getMaxIdInTable(entityName);
            startId = 0L;
            if (startId == null) {
                startId = 0L;
            }
            size = 0L;
            while (startId < maxId) {
                List<?> entities = exportFbService.getEntities(dir, entityName, startId, startId + step);

                List<ClsTypeAttachment> resultList = importSpFkindService.convertEntities((List<SpFkindEntity>) entities);

                importSpFkindService.saveToDb(resultList);

                size += entities.size();
                startId += step;
            }



            // import users
            entityName = "users";
            maxId = exportFbService.getMaxIdInTable(entityName);
            startId = 0L;
            if (startId == null) {
                startId = 0L;
            }
            size = 0L;
            while (startId < maxId) {
                List<?> entities = exportFbService.getEntities(dir, entityName, startId, startId + step);

                List<ClsUser> resultList = importUsersService.convertEntities((List<UsersEntity>) entities);

                importUsersService.saveToDb(resultList);

                size += entities.size();
                startId += step;
            }

            return "Migration spr complete.";

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
                List<DocRkk> resultList = importDocumsService.convertEntities((List<DocumsEntity>) entities);
                log.info("converting documsEntity end");

                importDocumsService.saveToDb(resultList);

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
                List<TpRkkFile> resultList = importFilesService.convertEntities((List<FilesEntity>) entities);
                log.info("converting end");

                importFilesService.saveTpRkkFile(resultList);

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

