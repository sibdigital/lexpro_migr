package ru.sibdigital.lexpro_migr.repo.lexpro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sibdigital.lexpro_migr.model.lexpro.ClsRkkStatus;

import java.util.List;

@Repository
public interface ClsRkkStatusRepo extends JpaRepository<ClsRkkStatus, Long> {

    @Query(value = "select * from cls_rkk_status where lower(name) = lower(:name) fetch first row only"
    , nativeQuery = true)
    ClsRkkStatus findByName(@Param("name") String name);

    ClsRkkStatus findByCode(String code);
}
