package ru.sibdigital.lexpro_migr.model.lexpro;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "cls_position", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ClsPosition implements Serializable {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "CLS_POSITION_SEQ_GEN", sequenceName = "cls_position_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLS_POSITION_SEQ_GEN")
    private Long id;
    private String name;
    private String code;

    @Column(name = "is_deputy_position", nullable = false)
    private Boolean isDeputyPosition;
    @Column(name = "time_create", nullable = false)
    private Timestamp timeCreate;
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClsPosition that = (ClsPosition) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(code, that.code)
                && Objects.equals(isDeputyPosition, that.isDeputyPosition)
                && Objects.equals(timeCreate, that.timeCreate)
                && Objects.equals(isDeleted, that.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, isDeputyPosition, timeCreate, isDeleted);
    }
}
