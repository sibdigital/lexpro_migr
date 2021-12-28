package ru.sibdigital.lexpro_migr.service;

import com.google.common.base.CaseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mapping.model.CamelCaseSplittingFieldNamingStrategy;
import org.springframework.stereotype.Service;
import ru.sibdigital.lexpro_migr.model.zakon.DkindEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExportFbService {

    @Autowired
    @Qualifier("fbZakonEntityManager")
    EntityManager fbZakonEntityManager;

    @Autowired
    FileService fileService;

    private final String modelPackage = "ru.sibdigital.lexpro_migr.model.zakon";

    public List<?> getEntities(String dir, String tableName) {
        try {
            String filePath = fileService.findFileBySubstrName(dir, "export_" + tableName + ".sql");
            String className = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName) + "Entity";
            String queryString = fileService.getStringFromFile(dir + "/" + filePath);
            Query query = fbZakonEntityManager.createNativeQuery(queryString, Class.forName(modelPackage + "." + className));
            List<?> entities =  query.getResultList();
            return entities;
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
            return new ArrayList<>();
        }

    }
}
