package ru.sibdigital.lexpro_migr.repo.zakon;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sibdigital.lexpro_migr.model.zakon.FilesEntity;

import java.util.List;

public interface FilesRepo extends JpaRepository<FilesEntity, Long> {

    Boolean existsByDocumIdAndFgroupIdInAndFdataIsNull(Long documId, List<Long> fgroupIds);

    Boolean existsByDocumIdAndFdataIsNull(Long documId);
}
