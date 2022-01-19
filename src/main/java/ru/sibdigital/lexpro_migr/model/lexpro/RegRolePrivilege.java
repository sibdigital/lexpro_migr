package ru.sibdigital.lexpro_migr.model.lexpro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "reg_role_privilege", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RegRolePrivilege implements Serializable {

        @Id
        @Column(name = "id", nullable = false)
        @SequenceGenerator(name = "REG_ROLE_PRIVILEGE_SEQ_GEN", sequenceName = "reg_role_privilege_id_seq", allocationSize = 1, schema = "public")
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REG_ROLE_PRIVILEGE_SEQ_GEN")
        private Long id;

        @OneToOne
        @JoinColumn(name = "id_role", referencedColumnName = "id")
        private ClsRole role;

        @OneToOne
        @JoinColumn(name = "id_privilege", referencedColumnName = "id")
        private ClsPrivilege privilege;

        private Timestamp timeCreate;

        public Long getId() {return id;}
        public void setId(Long id) {this.id = id;}

        public ClsRole getRole() {return role;}
        public void setRole(ClsRole role) {this.role = role;}

        public ClsPrivilege getPrivilege() {return privilege;}
        public void setPrivilege(ClsPrivilege privilege) {this.privilege = privilege;}

        @Basic
        @Column(name = "time_create")
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
                RegRolePrivilege that = (RegRolePrivilege) o;
                return Objects.equals(id, that.id)
                        && Objects.equals(role, that.role)
                        && Objects.equals(privilege, that.privilege)
                        && Objects.equals(timeCreate, that.timeCreate);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, role, privilege, timeCreate);
        }
}
