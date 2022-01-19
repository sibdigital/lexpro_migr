package ru.sibdigital.lexpro_migr.model.lexpro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "cls_type_attachment", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ClsTypeAttachment implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "CLS_TYPE_ATTACHMENT_SEQ_GEN", sequenceName = "cls_type_attachment_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLS_TYPE_ATTACHMENT_SEQ_GEN")
    private Long id;
    private String name;
    private String code;
    private Timestamp timeCreate;
    private Boolean isDeleted;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "code", nullable = false, length = 25)
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
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
        ClsTypeAttachment that = (ClsTypeAttachment) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(code, that.code)
                && Objects.equals(timeCreate, that.timeCreate)
                && Objects.equals(isDeleted, that.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, timeCreate, isDeleted);
    }
}