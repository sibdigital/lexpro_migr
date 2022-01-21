package ru.sibdigital.lexpro_migr.model.lexpro;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tp_rkk_file", schema = "public")
@Getter
@Setter
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

    @Column(name = "number_attachment")
    private String numberAttachment;
    @Column(name = "signing_date")
    private Date signingDate;
    @Column(name = "page_count")
    private Integer pageCount;
    @Column(name = "attachment_path", length = -1)
    private String attachmentPath;
    @Column(name = "file_name", length = -1)
    private String fileName;
    @Column(name = "original_file_name", length = -1)
    private String originalFileName;
    @Column(name = "file_extension", length = 16)
    private String fileExtension;
    private String hash;
    @Column(name = "file_size")
    private Long fileSize;

    @OneToOne
    @JoinColumn(name = "id_operator", referencedColumnName = "id")
    private ClsEmployee operator;

    @Column(name = "time_create", nullable = false)
    private Timestamp timeCreate;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @Column(name = "id_file_signature")
    private Long idFileSignature;
    @Column(name = "hash_signature")
    private String hashSignature;


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

