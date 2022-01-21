package ru.sibdigital.lexpro_migr.repo.lexpro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.lexpro_migr.model.lexpro.ClsSession;

import java.util.Date;
import java.util.List;

@Repository
public interface ClsSessionRepo extends JpaRepository<ClsSession, Long>, JpaSpecificationExecutor<ClsSession> {

    List<ClsSession> findAllByOrderByIdAsc();

    List<ClsSession> findAllByDateAndNumberOrderByDate(Date date, String number);
}
