package ru.sibdigital.lexpro_migr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.sibdigital.lexpro_migr.model.zakon.DkindEntity;
import ru.sibdigital.lexpro_migr.service.ImportPsqlZakonService;
import ru.sibdigital.lexpro_migr.service.ExportFbService;

import java.util.List;

@Controller
@RequestMapping(path="/export_zakon")
public class ExportController {

    @Autowired
    ExportFbService exportFbService;

    @Autowired
    ImportPsqlZakonService importPsqlZakonService;

    private final String dir = "classpath:sql-scripts/export_data";
    private final Long step = 1000L;

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




}
