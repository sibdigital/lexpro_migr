package ru.sibdigital.lexpro_migr.model.lexpro;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "reg_employee_user", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RegEmployeeUser implements Serializable {

        @Id
        @Column(name = "id", nullable = false)
        @SequenceGenerator(name = "REG_EMPLOYEE_USER_GEN", sequenceName = "reg_employee_user_id_seq", allocationSize = 1, schema = "public")
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REG_EMPLOYEE_USER_GEN")
        private Long id;

        @OneToOne
        @JoinColumn(name = "id_user", referencedColumnName = "id")
        private ClsUser user;

        @OneToOne
        @JoinColumn(name = "id_employee", referencedColumnName = "id")
        private ClsEmployee employee;

        @Column(name = "time_create")
        private Timestamp timeCreate;

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                RegEmployeeUser that = (RegEmployeeUser) o;
                return Objects.equals(id, that.id)
                        && Objects.equals(user, that.user)
                        && Objects.equals(employee, that.employee)
                        && Objects.equals(timeCreate, that.timeCreate);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, user, employee, timeCreate);
        }
}
