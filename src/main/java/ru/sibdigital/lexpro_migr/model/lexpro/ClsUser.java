package ru.sibdigital.lexpro_migr.model.lexpro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cls_user", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ClsUser implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "CLS_USER_SEQ_GEN", sequenceName = "cls_user_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLS_USER_SEQ_GEN")
    private Long id;
    private String lastname;
    private String firstname;
    private String patronymic;
    private String login;
    private String password;
    @Column(name = "time_create")
    private Timestamp timeCreate;
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<RegUserRole> roles;

    private Long idEmployee;

    public String getFullName() {
        String fullName = "";
        fullName += this.lastname != null ? this.lastname : "";
        fullName += this.firstname != null ? ' ' + this.firstname : "";
        fullName += this.patronymic != null ? ' ' + this.patronymic : "";
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClsUser clsUser = (ClsUser) o;
        return Objects.equals(id, clsUser.id)
                && Objects.equals(lastname, clsUser.lastname)
                && Objects.equals(firstname, clsUser.firstname)
                && Objects.equals(patronymic, clsUser.patronymic)
                && Objects.equals(login, clsUser.login)
                && Objects.equals(password, clsUser.password)
                && Objects.equals(timeCreate, clsUser.timeCreate)
                && Objects.equals(isDeleted, clsUser.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastname, firstname, patronymic, login, password, timeCreate, isDeleted);
    }
}
