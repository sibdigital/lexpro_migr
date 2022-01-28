package ru.sibdigital.lexpro_migr.service;

import com.google.common.base.CaseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sibdigital.lexpro_migr.utils.ClassUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.File;
import java.util.List;

@Service
public class ExportFbService {

    @Autowired
    @Qualifier("fbZakonEntityManager")
    EntityManager fbZakonEntityManager;

    @Autowired
    FileService fileService;

    private final String modelPackage = "ru.sibdigital.lexpro_migr.model.zakon";

    @Transactional("fbTransactionManager")
    public List<?> getEntities(String dir, String tableName, Long startId, Long endId) {
        //String filePath = fileService.findFileBySubstrName(dir, "export_" + tableName + ".sql");

        fbZakonEntityManager.clear();

        Class clazz = ClassUtils.getClassByTableName(modelPackage, tableName);

        String queryString = fileService.getStringFromFile(dir + File.separator + "export_" + tableName + ".sql");
        Query query = fbZakonEntityManager.createNativeQuery(queryString, clazz);
        query.setParameter("start", startId);
        query.setParameter("end", endId);
        return query.getResultList();
    }

    public Long getMaxIdInTable(String tableName) {
        Class clazz = ClassUtils.getClassByTableName(modelPackage, tableName);
        String pkColumnName = ClassUtils.getPKColumnName(clazz);
        pkColumnName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, pkColumnName);

        CriteriaBuilder criteriaBuilder = fbZakonEntityManager.getCriteriaBuilder();
        CriteriaQuery query = criteriaBuilder.createQuery();
        Root<?> root = query.from(clazz);
        query.select(criteriaBuilder.max(root.<Number>get(pkColumnName)));
        TypedQuery<Long> q = fbZakonEntityManager.createQuery(query);
        return q.getSingleResult();
    }



}
