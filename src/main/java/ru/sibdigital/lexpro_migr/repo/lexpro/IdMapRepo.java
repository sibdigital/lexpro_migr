package ru.sibdigital.lexpro_migr.repo.lexpro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.lexpro_migr.model.lexpro.IdMap;

@Repository
public interface IdMapRepo extends JpaRepository<IdMap, Long> {

    IdMap findByEntityNameAndOldId(String entityName, Long oldId);

    IdMap findByEntityNameAndNewIdAndOldId(String entityName, Long newId, Long oldId);
}
