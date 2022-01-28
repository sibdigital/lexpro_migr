package ru.sibdigital.lexpro_migr.repo.lexpro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.lexpro_migr.model.lexpro.ClsRkkStage;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClsRkkStageRepo extends JpaRepository<ClsRkkStage, Long> {

    List<ClsRkkStage> findAllByOrderByIdAsc();

    Optional<ClsRkkStage> findByCode(String code);
}
