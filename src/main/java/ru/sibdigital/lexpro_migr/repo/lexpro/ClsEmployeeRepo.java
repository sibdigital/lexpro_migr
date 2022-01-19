package ru.sibdigital.lexpro_migr.repo.lexpro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sibdigital.lexpro_migr.model.lexpro.ClsEmployee;

import java.util.List;

@Repository
public interface ClsEmployeeRepo extends JpaRepository<ClsEmployee, Long>, JpaSpecificationExecutor<ClsEmployee> {

    List<ClsEmployee> findAllByOrderByIdAsc();

    @Query(value =
            "WITH employee AS (\n" +
            "   SELECT *\n" +
            "   FROM cls_employee\n" +
            "   WHERE is_deleted = false\n" +
            "),\n" +
            "    status AS (\n" +
            "       SELECT roe.*\n" +
            "       FROM reg_organization_employee AS roe\n" +
            "           INNER JOIN cls_employee_status AS ces\n" +
            "               ON ces.id = roe.id_employee_status\n" +
            "       WHERE ces.code = 'WORK'\n" +
            "     )\n" +
            "\n" +
            "SELECT e.*\n" +
            "FROM employee AS e\n" +
            "       INNER JOIN status AS s\n" +
            "           ON e.id = s.id_employee\n" +
            "ORDER BY e.lastname ASC", nativeQuery = true)
    List<ClsEmployee> findAllEmployee();
}
