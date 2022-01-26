package ru.sibdigital.lexpro_migr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.sibdigital.lexpro_migr.model.zakon.PersonEntity;
import ru.sibdigital.lexpro_migr.repo.lexpro.IdMapRepo;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
public abstract class ImportService<S, T> {

    @Value("${upload.path}")
    String uploadingDir;

    @Autowired
    @Qualifier("psqlLexproEntityManager")
    EntityManager psqlLexproEntityManager;

    @Autowired
    IdMapRepo idMapRepo;

    protected String entityName;

    public ImportService(String entityName){
        this.entityName = entityName;
    }

    public List<T> convertEntities(List<S> list) {
        return list.stream()
                .map(
                        obj -> convertEntity((S) obj)
                )
                .distinct()
                .collect(Collectors.toList());
    }

    protected T convertEntity(S sourceEntity) {
        return null;
    }

    public Integer saveToDb(List<T> list) {
        return 0;
    }

}
