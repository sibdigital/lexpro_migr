package ru.sibdigital.lexpro_migr.model.lexpro;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

/**
 * Правила движений документов
 * (правила доступа к различному функционалу docRkk в зависимости от статуса и состояния)
 */

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reg_movement_rules", schema = "public")
@Entity
public class RegMovementRules implements Serializable {
    /**
     * ID правила
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Стадия РКК
     */
    @ManyToOne
    @JoinColumn(name = "id_stage")
    private ClsRkkStage stage;

    /**
     * Статус РКК
     */
    @ManyToOne
    @JoinColumn(name = "id_status")
    private ClsRkkStatus status;

    /**
     * Тип назначения (Роли, Права, Служащие, Организации и пр.)
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_type_destination", nullable = false)
    private ClsTypeDestination typeDestination;

    /**
     * ID конкретного назначения в tables, который зависит от выбранного типа назначения
     */
    @Basic
    @Column(name = "id_destination")
    private Long idDestination;

    /**
     * Время создания
     */
    @Basic
    @Column(name = "time_create", nullable = false)
    private Timestamp timeCreate = Timestamp.from(Instant.now());

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public ClsRkkStage getStage() {
        return stage;
    }
    public void setStage(ClsRkkStage stage) {
        this.stage = stage;
    }

    public ClsRkkStatus getStatus() {
        return status;
    }
    public void setStatus(ClsRkkStatus status) {
        this.status = status;
    }

    public ClsTypeDestination getTypeDestination() {
        return typeDestination;
    }
    public void setTypeDestination(ClsTypeDestination typeDestination) {
        this.typeDestination = typeDestination;
    }

    public Long getIdDestination() {
        return idDestination;
    }
    public void setIdDestination(Long idDestination) {
        this.idDestination = idDestination;
    }
    
    public Timestamp getTimeCreate() {
        return timeCreate;
    }
    public void setTimeCreate(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegMovementRules that = (RegMovementRules) o;
        return Objects.equals(id, that.id)
                && Objects.equals(stage, that.stage)
                && Objects.equals(status, that.status)
                && Objects.equals(typeDestination, that.typeDestination)
                && Objects.equals(idDestination, that.idDestination)
                && Objects.equals(timeCreate, that.timeCreate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stage, status, typeDestination, idDestination, timeCreate);
    }
}
