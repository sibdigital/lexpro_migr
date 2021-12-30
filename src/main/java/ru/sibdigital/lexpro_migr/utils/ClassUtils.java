package ru.sibdigital.lexpro_migr.utils;

import com.google.common.base.CaseFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ClassUtils {
    public static Class getClassByTableName(String modelPackage, String tableName) {
        try {
            String className = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName) + "Entity";
            return Class.forName(modelPackage + "." + className);
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
            return null;
        }
    }


    public static String getPKColumnName(Class<?> pojo) {
        if (pojo == null)
            return null;

        String name = null;

        for (Field f : pojo.getDeclaredFields()) {
            Id id = null;
            Column column = null;

            Annotation[] as = f.getAnnotations();
            for (Annotation a : as) {
                if (a.annotationType() == Id.class)
                    id = (Id) a;
                else if (a.annotationType() == Column.class)
                    column = (Column) a;
            }

            if (id != null && column != null){
                name = column.name();
                break;
            }
        }

        if (name == null && pojo.getSuperclass() != Object.class) {
            name = getPKColumnName(pojo.getSuperclass());
        }

        return name;
    }
}
