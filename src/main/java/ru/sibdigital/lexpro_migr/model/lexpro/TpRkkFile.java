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
@Table(name = "tp_rkk_file", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TpRkkFile implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "TP_RKK_FILE_SEQ_GEN", sequenceName = "tp_rkk_file_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TP_RKK_FILE_SEQ_GEN")
    private Long id;

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

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

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
    @Column(name = "attachment_path", length = -1)
    public String getAttachmentPath() {
        return attachmentPath;
    }
    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    @Basic
    @Column(name = "file_name", length = -1)
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "original_file_name", length = -1)
    public String getOriginalFileName() {
        return originalFileName;
    }
    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    @Basic
    @Column(name = "file_extension", length = 16)
    public String getFileExtension() {
        return fileExtension;
    }
    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    @Basic
    @Column(name = "hash", length = -1)
    public String getHash() {
        return hash;
    }
    public void setHash(String hash) {
        this.hash = hash;
    }

    @Basic
    @Column(name = "file_size")
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
    @Column(name = "time_create", nullable = false)
    public Timestamp getTimeCreate() {
        return timeCreate;
    }
    public void setTimeCreate(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }

    @Basic
    @Column(name = "is_deleted")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TpRkkFile tpRkkFile = (TpRkkFile) o;
        return Objects.equals(id, tpRkkFile.id)
                && Objects.equals(docRkk, tpRkkFile.docRkk)
                && Objects.equals(group, tpRkkFile.group)
                && Objects.equals(type, tpRkkFile.type)
                && Objects.equals(participant, tpRkkFile.participant)
                && Objects.equals(numberAttachment, tpRkkFile.numberAttachment)
                && Objects.equals(signingDate, tpRkkFile.signingDate)
                && Objects.equals(pageCount, tpRkkFile.pageCount)
                && Objects.equals(attachmentPath, tpRkkFile.attachmentPath)
                && Objects.equals(fileName, tpRkkFile.fileName)
                && Objects.equals(originalFileName, tpRkkFile.originalFileName)
                && Objects.equals(fileExtension, tpRkkFile.fileExtension)
                && Objects.equals(hash, tpRkkFile.hash)
                && Objects.equals(fileSize, tpRkkFile.fileSize)
                && Objects.equals(operator, tpRkkFile.operator)
                && Objects.equals(timeCreate, tpRkkFile.timeCreate)
                && Objects.equals(isDeleted, tpRkkFile.isDeleted)
                && Objects.equals(idFileSignature, tpRkkFile.idFileSignature)
                && Objects.equals(hashSignature, tpRkkFile.hashSignature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, docRkk, group, type, participant, numberAttachment, signingDate, pageCount,
                attachmentPath, fileName, originalFileName, fileExtension, hash, fileSize, operator, timeCreate,
                isDeleted, idFileSignature, hashSignature);
    }
}

