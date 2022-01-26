package ru.sibdigital.lexpro_migr.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sibdigital.lexpro_migr.dto.FileContainer;
import ru.sibdigital.lexpro_migr.model.lexpro.*;
import ru.sibdigital.lexpro_migr.model.zakon.FilesEntity;
import ru.sibdigital.lexpro_migr.model.zakon.PersonEntity;
import ru.sibdigital.lexpro_migr.model.zakon.UsersEntity;
import ru.sibdigital.lexpro_migr.repo.lexpro.*;
import ru.sibdigital.lexpro_migr.repo.zakon.PersonRepo;
import ru.sibdigital.lexpro_migr.repo.zakon.UsersRepo;
import ru.sibdigital.lexpro_migr.utils.FileUtils;

import javax.persistence.EntityTransaction;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ImportFilesService extends ImportService<FilesEntity, TpRkkFile>{

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    PersonRepo personRepo;

    @Autowired
    ClsEmployeeRepo clsEmployeeRepo;

    @Autowired
    DocRkkRepo docRkkRepo;

    @Autowired
    ClsGroupAttachmentRepo clsGroupAttachmentRepo;

    @Autowired
    ClsTypeAttachmentRepo clsTypeAttachmentRepo;

    // map соответствие id
    private static Map<Long, Long> fgroupIds = new HashMap<>();

    public ImportFilesService(){
        super("files");

        this.fgroupIds.put(1L, 1L);
        this.fgroupIds.put(2L, 5L);
        this.fgroupIds.put(3L, 4L);
        this.fgroupIds.put(4L, 2L);
        this.fgroupIds.put(5L, 2L);
        this.fgroupIds.put(6L, 2L);
        this.fgroupIds.put(7L, 3L);
    }

    @Override
    public List<TpRkkFile> convertEntities(List<FilesEntity> list){
        return list.stream()
                .map(
                        obj -> {
                            if(obj instanceof FilesEntity){
                                return convertEntity((FilesEntity) obj);
                            }
                            return null;
                        }
                )
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    protected TpRkkFile convertEntity(FilesEntity filesEntity) {

        TpRkkFile tpRkkFile = null;

        DocRkk doc = getDocRkk(filesEntity);

        if(doc != null && filesEntity.getFname() != null) {
            // составить относительный путь
            final String originalFilename = filesEntity.getFname();
            final String extension = filesEntity.getFext();

            final String directory = "rkk" + File.separator + doc.getId();
            final File folder = getSavingDirectory(directory);

            final String prefix = doc.getId().toString();
            final String randomUUID = UUID.randomUUID().toString();

            FileContainer container = getFileContainer(originalFilename, extension, directory, folder, prefix, randomUUID);

            try {
                // сохранить файл
                if (filesEntity.getFdata() != null) {
                    saveToFile(container.getFile(), filesEntity.getFdata());
                    getFileParameters(container);

                    tpRkkFile = TpRkkFile.builder()
                            .id(filesEntity.getId())
                            .docRkk(getDocRkk(filesEntity))
                            .group(getGroupAttachment(filesEntity))
                            .type(getTypeAttachment(filesEntity))
                            .participant(getParticipant(filesEntity))
                            .numberAttachment(filesEntity.getDnum())
                            .signingDate(null)
                            .pageCount(filesEntity.getPageCount())
                            .attachmentPath(container.getAttachmentPath())
                            .fileName(container.getFileName())
                            .originalFileName(filesEntity.getFname())
                            .fileExtension(filesEntity.getFext())
                            .hash(filesEntity.getMd5Sum())
                            .fileSize(container.getFileSize())
                            .operator(getOperator(filesEntity.getEditorLogin()))
                            .isDeleted(false)
                            .timeCreate(new Timestamp(System.currentTimeMillis()))
                            .hashSignature(null)
                            .idFileSignature(null)
                            .certificateInformation(filesEntity.getEcpDescr())
                            .build();
                } else {
                    log.info(filesEntity.getId() + ": пустое значение fdata");
                }
            } catch (IOException e) {
                log.error("Files.convertEntity", e.getMessage());
                log.info(filesEntity.getId() + " not saved");
            }
        }

        return tpRkkFile;
    }

    private String getAbsolutePath(File folder, String filename) {
        String absolutePath = folder.getAbsolutePath() + File.separator + filename;

        return absolutePath;
    }

    private String getRelativePath(String directory, String filename) {
        String relativePath = directory + File.separator + filename;

        return relativePath;
    }

    private FileContainer getFileContainer(String originalFilename, String extension, String directory, File folder,
                                           String prefix, String randomUUID) {

        final String filename = prefix + "_" + randomUUID + extension;
        final String absolutePath = getAbsolutePath(folder, filename);
        final String relativePath = getRelativePath(directory, filename);

        File file = new File(absolutePath);
        FileContainer container = createFileContainer(relativePath, filename, originalFilename, extension, file);

        return container;
    }

    private FileContainer createFileContainer(String relativePath, String filename, String originalFilename,
                                              String extension, File file) {

        FileContainer container = FileContainer.builder()
                .attachmentPath(relativePath)
                .fileName(filename)
                .originalFileName(originalFilename)
                .fileExtension(extension)
                .file(file)
                .build();

        return container;
    }

    private File getSavingDirectory(String directory) {
        String filepath = uploadingDir + File.separator + directory;
        File folder = new File(filepath);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        return folder;
    }

    private void getFileParameters(FileContainer container) throws IOException {
//        final Integer pageCount = FileUtils.getPageCount(container.getFile(), container.getFileExtension());
        final String fileHash = FileUtils.getFileHash(container.getFile());
        final long fileSize = Files.size(container.getFile().toPath());

        //container.setPageCount(pageCount);
        container.setHash(fileHash);
        container.setFileSize(fileSize);
    }


    private DocRkk getDocRkk(FilesEntity filesEntity){
        DocRkk docRkk = null;
        if(filesEntity.getDocumId() != null) {
            IdMap doc = idMapRepo.findByEntityNameAndOldId("docums", filesEntity.getDocumId());
            if (doc != null) {
                docRkk = docRkkRepo.findById(doc.getNewId()).orElse(null);
            }
        }
        return docRkk;
    }

    private ClsGroupAttachment getGroupAttachment(FilesEntity filesEntity){
        if(filesEntity.getFgroupId() != null) {
            Long newId = fgroupIds.containsKey(filesEntity.getFgroupId()) ? fgroupIds.get(filesEntity.getFgroupId()) : -1L;
            return clsGroupAttachmentRepo.findById(newId).orElse(null);
        } else {
            return null;
        }
    }

    private ClsTypeAttachment getTypeAttachment(FilesEntity filesEntity){
        ClsTypeAttachment typeAttachment = null;
        if(filesEntity.getDocumId() != null) {
            IdMap doc = idMapRepo.findByEntityNameAndOldId("sp_fkind", filesEntity.getFkindId());
            if (doc != null) {
                typeAttachment = clsTypeAttachmentRepo.findById(doc.getNewId()).orElse(null);
            }
        }
        return typeAttachment;
    }

    private ClsOrganization getParticipant(FilesEntity filesEntity){
        ClsOrganization participant = null;
        if(filesEntity.getOrgId() != null) {
            IdMap kom = idMapRepo.findByEntityNameAndOldId("org", filesEntity.getOrgId());
            if (kom != null) {
                participant = ClsOrganization.builder().id(kom.getNewId()).build();
            }
        }
        return participant;
    }

    private ClsEmployee getOperator(String login){
        ClsEmployee result = null;
        UsersEntity user = usersRepo.findByLogin(login).orElse(null);
        if(user != null){
            PersonEntity person = personRepo.findById(user.getPersonEntity().getId()).orElse(null);
            if(person != null){
                IdMap doc = idMapRepo.findByEntityNameAndOldId("person", person.getId());
                if (doc != null) {
                    result = clsEmployeeRepo.findById(doc.getNewId()).orElse(null);
                }
            }
        }
        return result;
    }

    private void saveToFile(File file, byte[] fileData){
        try (FileOutputStream fos = new FileOutputStream(file))
        {
            fos.write(fileData);
            log.info("Successfully written data to the file: " + file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer saveTpRkkFile(List<TpRkkFile> list){
        EntityTransaction transaction = psqlLexproEntityManager.getTransaction();
        transaction.begin();
        String entityName = "files";
        log.info("Entity : " + entityName);
        log.info("source count: " + list.size());
        AtomicInteger posCount = new AtomicInteger(0);
        try {
            list.stream()
                    .filter(obj -> obj != null)
                    .forEach(obj -> {
                        if(idMapRepo.findByEntityNameAndOldId(entityName, obj.getId()) == null) {
                            obj.setIsDeleted(false);
                            obj.setTimeCreate(new Timestamp(System.currentTimeMillis()));

                            TpRkkFile rkkFile = psqlLexproEntityManager.merge(obj);

                            IdMap idMap = IdMap.builder()
                                    .entityName(entityName)
                                    .newId(rkkFile.getId())
                                    .oldId(obj.getId())
                                    .build();

                            psqlLexproEntityManager.persist(idMap);
                            log.info("new_id: " + rkkFile.getId() + ", old_id: " + obj.getId());
                            posCount.getAndIncrement();
                        }
                    });

            transaction.commit();
            log.info(entityName + " save complete. count: " + posCount.get());
        } catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            log.info(entityName + " save error", e.getMessage());
        }
        return posCount.get();
    }
}
