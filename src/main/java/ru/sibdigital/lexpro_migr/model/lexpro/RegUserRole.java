package ru.sibdigital.lexpro_migr.model.lexpro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "reg_user_role", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RegUserRole implements Serializable {

        @Id
        @Column(name = "id", nullable = false)
        @SequenceGenerator(name = "REG_USER_ROLE_GEN", sequenceName = "reg_user_role_id_seq", allocationSize = 1, schema = "public")
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REG_USER_ROLE_GEN")
        private Long id;

        @ManyToOne
        @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
        private ClsUser user;

        @ManyToOne
        @JoinColumn(name = "id_role", referencedColumnName = "id", nullable = false)
        private ClsRole role;

        @Column(name = "time_create")
        private Timestamp timeCreate;


        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                RegUserRole that = (RegUserRole) o;
                return Objects.equals(id, that.id)
                        && Objects.equals(user, that.user)
                        && Objects.equals(role, that.role)
                        && Objects.equals(timeCreate, that.timeCreate);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, user, role, timeCreate);
        }
}
