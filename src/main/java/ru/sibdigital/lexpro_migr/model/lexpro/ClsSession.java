package ru.sibdigital.lexpro_migr.model.lexpro;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "cls_session", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ClsSession implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "CLS_SESSION_SEQ_GEN", sequenceName = "cls_session_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLS_SESSION_SEQ_GEN")
    private Long id;
    @Column(name = "number", nullable = false)
    private String number;
    @Column(name = "date")
    private Date date;
    @Column(name = "time_create")
    private Timestamp timeCreate;
    @Column(name = "is_deleted")
    private Boolean isDeleted;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClsSession that = (ClsSession) o;
        return Objects.equals(id, that.id)
                && Objects.equals(number, that.number)
                && Objects.equals(date, that.date)
                && Objects.equals(timeCreate, that.timeCreate)
                && Objects.equals(isDeleted, that.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, date, timeCreate, isDeleted);
    }
}
