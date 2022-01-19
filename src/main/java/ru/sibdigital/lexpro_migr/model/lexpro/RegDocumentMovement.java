package ru.sibdigital.lexpro_migr.model.lexpro;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reg_document_movement", schema = "public")
@Entity
public class RegDocumentMovement implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idSource;

    @ManyToOne
    @JoinColumn(name = "id_stage")
    private ClsRkkStage idStage;

    @ManyToOne
    @JoinColumn(name = "id_status")
    private ClsRkkStatus idStatus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_type_destination", nullable = false)
    private ClsTypeDestination idTypeDestination;

    private Long idDestination;
    private Timestamp timeCreate;
    private Timestamp timeUpdate;
    private Timestamp timeAction;
    private Boolean isDeleted;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "id_source", nullable = false)
    public Long getIdSource() {
        return idSource;
    }
    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public ClsRkkStage getIdStage() {
        return idStage;
    }
    public void setIdStage(ClsRkkStage idStage) {
        this.idStage = idStage;
    }

    public ClsRkkStatus getIdStatus() {
        return idStatus;
    }
    public void setIdStatus(ClsRkkStatus idStatus) {
        this.idStatus = idStatus;
    }

    public ClsTypeDestination getIdTypeDestination() {
        return idTypeDestination;
    }
    public void setIdTypeDestination(ClsTypeDestination idTypeDestination) {
        this.idTypeDestination = idTypeDestination;
    }

    @Column(name = "id_destination")
    public Long getIdDestination() {
        return idDestination;
    }
    public void setIdDestination(Long idDestination) {
        this.idDestination = idDestination;
    }

    @Column(name = "time_create", nullable = false)
    public Timestamp getTimeCreate() {
        return timeCreate;
    }
    public void setTimeCreate(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }

    @Column(name = "time_update", nullable = false)
    public Timestamp getTimeUpdate() {
        return timeUpdate;
    }
    public void setTimeUpdate(Timestamp timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    @Column(name = "time_action")
    public Timestamp getTimeAction() {
        return timeAction;
    }
    public void setTimeAction(Timestamp timeAction) {
        this.timeAction = timeAction;
    }

    @Column(name = "is_deleted")
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
        RegDocumentMovement that = (RegDocumentMovement) o;
        return Objects.equals(id, that.id)
                && Objects.equals(idSource, that.idSource)
                && Objects.equals(idStage, that.idStage)
                && Objects.equals(idStatus, that.idStatus)
                && Objects.equals(idTypeDestination, that.idTypeDestination)
                && Objects.equals(idDestination, that.idDestination)
                && Objects.equals(timeCreate, that.timeCreate)
                && Objects.equals(timeUpdate, that.timeUpdate)
                && Objects.equals(timeAction, that.timeAction)
                && Objects.equals(isDeleted, that.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idSource, idStage, idStatus, idTypeDestination, idDestination, timeCreate,
                timeUpdate, timeAction, isDeleted);
    }
}
