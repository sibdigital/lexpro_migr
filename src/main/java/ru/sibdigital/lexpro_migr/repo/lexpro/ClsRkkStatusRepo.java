package ru.sibdigital.lexpro_migr.repo.lexpro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.lexpro_migr.model.lexpro.ClsRkkStatus;

import java.util.List;

@Repository
public interface ClsRkkStatusRepo extends JpaRepository<ClsRkkStatus, Long> {

    List<ClsRkkStatus> findAllByOrderByIdAsc();

    ClsRkkStatus findByName(String name);

    ClsRkkStatus findByCode(String code);
}
