package ru.sibdigital.lexpro_migr.repo.lexpro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.lexpro_migr.model.lexpro.ClsEmployeeStatus;

@Repository
public interface ClsEmployeeStatusRepo extends JpaRepository<ClsEmployeeStatus, Long> {
}
