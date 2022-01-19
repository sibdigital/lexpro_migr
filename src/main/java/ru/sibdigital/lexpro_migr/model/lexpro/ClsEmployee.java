package ru.sibdigital.lexpro_migr.model.lexpro;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "cls_employee", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ClsEmployee implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "CLS_EMPLOYEE_SEQ_GEN", sequenceName = "cls_employee_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLS_EMPLOYEE_SEQ_GEN")
    private Long id;
    private String lastname;
    private String firstname;
    private String patronymic;
    private String email;
    @Column(name = "time_create")
    private Timestamp timeCreate;
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    public String getName() {
        return this.lastname + " "
                + ((!this.firstname.isBlank()) ? this.firstname.charAt(0) + ". ": "")
                + ((!this.patronymic.isBlank()) ? this.patronymic.charAt(0) +"." : "");
    }

    public String getFullName() {
        return ((this.lastname != null && !this.lastname.isBlank()) ? " " + this.lastname: "")
                + ((this.firstname != null && !this.firstname.isBlank()) ? " " + this.firstname: "")
                + ((this.patronymic != null && !this.patronymic.isBlank()) ? " " + this.patronymic : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClsEmployee that = (ClsEmployee) o;
        return Objects.equals(id, that.id)
                && Objects.equals(lastname, that.lastname)
                && Objects.equals(firstname, that.firstname)
                && Objects.equals(patronymic, that.patronymic)
                && Objects.equals(email, that.email)
                && Objects.equals(timeCreate, that.timeCreate)
                && Objects.equals(isDeleted, that.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastname, firstname, patronymic, email, timeCreate, isDeleted);
    }
}
