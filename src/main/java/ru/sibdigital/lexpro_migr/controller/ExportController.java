package ru.sibdigital.lexpro_migr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @GetMapping("/table/{table_name}")
    public @ResponseBody
    List<?> exportDkind(@PathVariable("table_name") String tableName) {
        List<?> dkindEntities = exportFbService.getEntities(dir, tableName);
        importPsqlZakonService.saveEntities(dkindEntities);
        return dkindEntities;
    }




}
