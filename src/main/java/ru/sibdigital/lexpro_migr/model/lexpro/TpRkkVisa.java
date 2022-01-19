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
@Table(name = "tp_rkk_visa", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TpRkkVisa implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "REG_RKK_VISA_SEQ_GEN", sequenceName = "tp_rkk_visa_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REG_RKK_VISA_SEQ_GEN")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_rkk", referencedColumnName = "id")
    private DocRkk docRkk;

    @OneToOne
    @JoinColumn(name = "id_stage", referencedColumnName = "id")
    private ClsRkkStage stage;

    private String note;
    private Date date;
    private Timestamp timeCreate;
    private Boolean isDeleted;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public DocRkk getDocRkk() {return docRkk;}
    public void setDocRkk(DocRkk docRkk) {this.docRkk = docRkk;}

    public ClsRkkStage getStage() {return stage;}
    public void setStage(ClsRkkStage stage) {this.stage = stage;}

    @Basic
    @Column(name = "note", nullable = true, length = -1)
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
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
    @Column(name = "is_deleted", nullable = false)
    public Boolean getIsDeleted() {
        return isDeleted;
    }
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TpRkkVisa tpRkkVisa = (TpRkkVisa) o;
        return Objects.equals(id, tpRkkVisa.id)
                && Objects.equals(docRkk, tpRkkVisa.docRkk)
                && Objects.equals(stage, tpRkkVisa.stage)
                && Objects.equals(note, tpRkkVisa.note)
                && Objects.equals(date, tpRkkVisa.date)
                && Objects.equals(timeCreate, tpRkkVisa.timeCreate)
                && Objects.equals(isDeleted, tpRkkVisa.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, docRkk, stage, note, date, timeCreate, isDeleted);
    }
}

