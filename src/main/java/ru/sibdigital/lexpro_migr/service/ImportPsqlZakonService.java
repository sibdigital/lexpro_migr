package ru.sibdigital.lexpro_migr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Service
public class ImportPsqlZakonService {

    @Autowired
    @Qualifier("psqlZakonEntityManager")
    EntityManager psqlZakonEntityManager;

    @Transactional("psqlZakonTransactionManager")
    public void saveEntities(List<? extends Object> list){
        EntityTransaction transaction = psqlZakonEntityManager.getTransaction();
        transaction.begin();
        list.forEach(
            obj -> {
                psqlZakonEntityManager.merge(obj);
            }
        );
        transaction.commit();
    }
}
