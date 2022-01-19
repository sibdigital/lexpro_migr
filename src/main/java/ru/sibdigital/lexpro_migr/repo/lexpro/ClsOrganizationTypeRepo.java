package ru.sibdigital.lexpro_migr.repo.lexpro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.lexpro_migr.model.lexpro.ClsOrganizationType;

@Repository
public interface ClsOrganizationTypeRepo extends JpaRepository<ClsOrganizationType, Long>, JpaSpecificationExecutor<ClsOrganizationType> {
}
