package ru.sibdigital.lexpro_migr.model.lexpro;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "cls_npa_type", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ClsNpaType implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "CLS_NPA_TYPE_SEQ_GEN", sequenceName = "cls_npa_type_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLS_NPA_TYPE_SEQ_GEN")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "code", nullable = false, length = 25)
    private String code;
    @Column(name = "time_create")
    private Timestamp timeCreate;
    @Column(name = "is_deleted")
    private Boolean isDeleted;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClsNpaType that = (ClsNpaType) o;
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
