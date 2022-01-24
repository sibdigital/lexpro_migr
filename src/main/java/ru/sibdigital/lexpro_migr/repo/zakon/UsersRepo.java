package ru.sibdigital.lexpro_migr.repo.zakon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.lexpro_migr.model.zakon.UsersEntity;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<UsersEntity, Long> {

    Optional<UsersEntity> findByLogin(String login);
}
