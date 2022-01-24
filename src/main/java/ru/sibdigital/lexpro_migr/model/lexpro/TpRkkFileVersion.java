package ru.sibdigital.lexpro_migr.model.lexpro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tp_rkk_file_version", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TpRkkFileVersion implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "TP_RKK_FILE_VERSION_SEQ_GEN", sequenceName = "tp_rkk_file_version_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TP_RKK_FILE_VERSION_SEQ_GEN")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_version", referencedColumnName = "id")
    private TpRkkFile rkkFile;

    @OneToOne
    @JoinColumn(name = "id_rkk", referencedColumnName = "id")
    private DocRkk docRkk;

    @OneToOne
    @JoinColumn(name = "id_group", referencedColumnName = "id")
    private ClsGroupAttachment group;

    @OneToOne
    @JoinColumn(name = "id_type", referencedColumnName = "id")
    private ClsTypeAttachment type;

    @OneToOne
    @JoinColumn(name = "id_participant", referencedColumnName = "id")
    private ClsOrganization participant;

    private String numberAttachment;
    private Date signingDate;
    private Integer pageCount;
    private String attachmentPath;
    private String fileName;
    private String originalFileName;
    private String fileExtension;
    private String hash;
    private Long fileSize;

    @OneToOne
    @JoinColumn(name = "id_operator", referencedColumnName = "id")
    private ClsEmployee operator;

    private Timestamp timeCreate;
    private Boolean isDeleted;
    private Long idFileSignature;
    private String hashSignature;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public TpRkkFile getRkkFile() {return rkkFile;}
    public void setRkkFile(TpRkkFile rkkFile) {this.rkkFile = rkkFile;}

    public DocRkk getDocRkk() {
        return docRkk;
    }
    public void setDocRkk(DocRkk docRkk) {
        this.docRkk = docRkk;
    }

    public ClsGroupAttachment getGroup() {
        return group;
    }
    public void setGroup(ClsGroupAttachment group) {
        this.group = group;
    }

    public ClsTypeAttachment getType() {
        return type;
    }
    public void setType(ClsTypeAttachment type) {
        this.type = type;
    }

    public ClsOrganization getParticipant() {
        return participant;
    }
    public void setParticipant(ClsOrganization participant) {
        this.participant = participant;
    }

    @Basic
    @Column(name = "number_attachment")
    public String getNumberAttachment() {
        return numberAttachment;
    }
    public void setNumberAttachment(String numberAttachment) {
        this.numberAttachment = numberAttachment;
    }

    @Basic
    @Column(name = "signing_date")
    public Date getSigningDate() {
        return signingDate;
    }
    public void setSigningDate(Date signingDate) {
        this.signingDate = signingDate;
    }

    @Basic
    @Column(name = "page_count")
    public Integer getPageCount() {
        return pageCount;
    }
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @Basic
    @Column(name = "attachment_path", nullable = true, length = -1)
    public String getAttachmentPath() {
        return attachmentPath;
    }
    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    @Basic
    @Column(name = "file_name", nullable = true, length = -1)
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "original_file_name", nullable = true, length = -1)
    public String getOriginalFileName() {
        return originalFileName;
    }
    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    @Basic
    @Column(name = "file_extension", nullable = true, length = 16)
    public String getFileExtension() {
        return fileExtension;
    }
    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    @Basic
    @Column(name = "hash", nullable = true, length = -1)
    public String getHash() {
        return hash;
    }
    public void setHash(String hash) {
        this.hash = hash;
    }

    @Basic
    @Column(name = "file_size", nullable = true)
    public Long getFileSize() {
        return fileSize;
    }
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public ClsEmployee getOperator() {
        return operator;
    }
    public void setOperator(ClsEmployee operator) {
        this.operator = operator;
    }

    @Basic
    @Column(name = "time_create")
    public Timestamp getTimeCreate() {
        return timeCreate;
    }
    public void setTimeCreate(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }

    @Basic
    @Column(name = "is_deleted", nullable = true)
    public Boolean getDeleted() {
        return isDeleted;
    }
    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Basic
    @Column(name = "id_file_signature")
    public Long getIdFileSignature() {
        return idFileSignature;
    }
    public void setIdFileSignature(Long idFileSignature) {
        this.idFileSignature = idFileSignature;
    }

    @Basic
    @Column(name = "hash_signature")
    public String getHashSignature() {
        return hashSignature;
    }
    public void setHashSignature(String hashSignature) {
        this.hashSignature = hashSignature;
    }

    public TpRkkFileVersion(TpRkkFile rkkFile) {
        this.rkkFile = rkkFile;
        this.docRkk = rkkFile.getDocRkk();
        this.group = rkkFile.getGroup();
        this.type = rkkFile.getType();
        this.participant = rkkFile.getParticipant();
        this.numberAttachment = rkkFile.getNumberAttachment();
        this.signingDate = rkkFile.getSigningDate();
        this.pageCount = rkkFile.getPageCount();
        this.attachmentPath = rkkFile.getAttachmentPath();
        this.fileName = rkkFile.getFileName();
        this.originalFileName = rkkFile.getOriginalFileName();
        this.fileExtension = rkkFile.getFileExtension();
        this.hash = rkkFile.getHash();
        this.fileSize = rkkFile.getFileSize();
        this.operator = rkkFile.getOperator();
        this.timeCreate = rkkFile.getTimeCreate();
        this.isDeleted = rkkFile.getIsDeleted();
        this.idFileSignature = rkkFile.getIdFileSignature();
        this.hashSignature = rkkFile.getHashSignature();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TpRkkFileVersion that = (TpRkkFileVersion) o;
        return Objects.equals(id, that.id)
                && Objects.equals(rkkFile, that.rkkFile)
                && Objects.equals(docRkk, that.docRkk)
                && Objects.equals(group, that.group)
                && Objects.equals(type, that.type)
                && Objects.equals(participant, that.participant)
                && Objects.equals(numberAttachment, that.numberAttachment)
                && Objects.equals(signingDate, that.signingDate)
                && Objects.equals(pageCount, that.pageCount)
                && Objects.equals(attachmentPath, that.attachmentPath)
                && Objects.equals(fileName, that.fileName)
                && Objects.equals(originalFileName, that.originalFileName)
                && Objects.equals(fileExtension, that.fileExtension)
                && Objects.equals(hash, that.hash)
                && Objects.equals(fileSize, that.fileSize)
                && Objects.equals(operator, that.operator)
                && Objects.equals(timeCreate, that.timeCreate)
                && Objects.equals(isDeleted, that.isDeleted)
                && Objects.equals(idFileSignature, that.idFileSignature)
                && Objects.equals(hashSignature, that.hashSignature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rkkFile, docRkk, group, type, participant, numberAttachment, signingDate, pageCount,
                attachmentPath, fileName, originalFileName, fileExtension, hash, fileSize, operator, timeCreate,
                isDeleted, idFileSignature, hashSignature);
    }
}

