package ru.sibdigital.lexpro_migr.repo.lexpro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.lexpro_migr.model.lexpro.DocRkk;

@Repository
public interface DocRkkRepo extends JpaRepository<DocRkk, Long> {


}
