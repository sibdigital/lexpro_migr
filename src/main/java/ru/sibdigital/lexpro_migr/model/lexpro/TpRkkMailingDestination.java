package ru.sibdigital.lexpro_migr.model.lexpro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tp_rkk_mailing_destination", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TpRkkMailingDestination implements Serializable {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "TP_RKK_MAILING_DESTINATION_SEQ_GEN", sequenceName = "tp_rkk_mailing_destination_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TP_RKK_MAILING_DESTINATION_SEQ_GEN")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_rkk_mailing", referencedColumnName = "id", nullable = false)
    private TpRkkMailing rkkMailing;

    @OneToOne
    @JoinColumn(name = "id_organization", referencedColumnName = "id")
    private ClsOrganization organization;

    @OneToOne
    @JoinColumn(name = "id_employee", referencedColumnName = "id")
    private ClsEmployee employee;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public TpRkkMailing getRkkMailing() {
        return rkkMailing;
    }
    public void setRkkMailing(TpRkkMailing rkkMailing) {
        this.rkkMailing = rkkMailing;
    }

    public ClsOrganization getOrganization() {
        return organization;
    }
    public void setOrganization(ClsOrganization organization) {
        this.organization = organization;
    }

    public ClsEmployee getEmployee() {
        return employee;
    }
    public void setEmployee(ClsEmployee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TpRkkMailingDestination that = (TpRkkMailingDestination) o;
        return Objects.equals(id, that.id)
                && Objects.equals(rkkMailing, that.rkkMailing)
                && Objects.equals(organization, that.organization)
                && Objects.equals(employee, that.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rkkMailing, organization, employee);
    }
}
