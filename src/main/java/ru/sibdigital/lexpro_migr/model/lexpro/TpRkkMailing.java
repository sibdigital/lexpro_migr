package ru.sibdigital.lexpro_migr.model.lexpro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tp_rkk_mailing", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TpRkkMailing implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "TP_RKK_MAILING_SEQ_GEN", sequenceName = "tp_rkk_mailing_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TP_RKK_MAILING_SEQ_GEN")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_rkk", referencedColumnName = "id")
    private DocRkk docRkk;

    private String note;
    private Timestamp timeCreate;
    private String subject;
    private String message;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public DocRkk getDocRkk() {return docRkk;}
    public void setDocRkk(DocRkk docRkk) {this.docRkk = docRkk;}

    @Basic
    @Column(name = "note", length = -1)
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
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
    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "message")
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TpRkkMailing that = (TpRkkMailing) o;
        return Objects.equals(id, that.id)
                && Objects.equals(docRkk, that.docRkk)
                && Objects.equals(note, that.note)
                && Objects.equals(timeCreate, that.timeCreate)
                && Objects.equals(subject, that.subject)
                && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, docRkk, note, timeCreate, subject, message);
    }
}

