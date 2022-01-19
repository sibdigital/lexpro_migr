package ru.sibdigital.lexpro_migr.repo.zakon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.lexpro_migr.model.zakon.PersonEntity;

@Repository
public interface PersonRepo extends JpaRepository<PersonEntity, Long> {
}
