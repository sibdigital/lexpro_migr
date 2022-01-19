package ru.sibdigital.lexpro_migr.model.lexpro;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "cls_organization", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ClsOrganization implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "CLS_ORGANIZATION_SEQ_GEN", sequenceName = "cls_organization_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLS_ORGANIZATION_SEQ_GEN")
    private Long id;
    private String name;
    @Column(name = "full_name")
    private String fullName;
    private String email;
    private String path;
    @Column(name = "id_parent")
    private Long idParent;

    @Transient
    private Long oldId;

    @ManyToOne
    @JoinColumn(name = "id_organization_type", referencedColumnName = "id")
    private ClsOrganizationType organizationType;

    @Column(name = "time_create")
    private Timestamp timeCreate;
    @Column(name = "is_activated")
    private Boolean isActivated;
    @Column(name = "is_Deleted")
    private Boolean isDeleted;

    public ClsOrganization(Long idOrganization) {
        this.id = idOrganization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClsOrganization that = (ClsOrganization) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(fullName, that.fullName)
                && Objects.equals(email, that.email)
                && Objects.equals(path, that.path)
                && Objects.equals(idParent, that.idParent)
                && Objects.equals(organizationType, that.organizationType)
                && Objects.equals(timeCreate, that.timeCreate)
                && Objects.equals(isActivated, that.isActivated)
                && Objects.equals(isDeleted, that.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fullName, email, path, idParent, organizationType, timeCreate, isActivated, isDeleted);
    }
}
