package ru.sibdigital.lexpro_migr.model.lexpro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "reg_organization_employee", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RegOrganizationEmployee implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "REG_ORGANIZATION_EMPLOYEE_SEQ_GEN", sequenceName = "reg_organization_employee_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REG_ORGANIZATION_EMPLOYEE_SEQ_GEN")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_organization", referencedColumnName = "id")
    private ClsOrganization organization;

    @OneToOne
    @JoinColumn(name = "id_employee", referencedColumnName = "id")
    private ClsEmployee employee;

    private Timestamp timeCreate;

    @ManyToOne
    @JoinColumn(name = "id_type_position", referencedColumnName = "id")
    private ClsPosition typePosition;

    @ManyToOne
    @JoinColumn(name = "id_employee_status", referencedColumnName = "id")
    private ClsEmployeeStatus employeeStatus;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public ClsPosition getTypePosition() {
        return typePosition;
    }
    public void setTypePosition(ClsPosition typePosition) {
        this.typePosition = typePosition;
    }

    public ClsEmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }
    public void setEmployeeStatus(ClsEmployeeStatus typePosition) {
        this.employeeStatus = employeeStatus;
    }

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
        RegOrganizationEmployee that = (RegOrganizationEmployee) o;
        return Objects.equals(id, that.id)
                && Objects.equals(organization, that.organization)
                && Objects.equals(employee, that.employee)
                && Objects.equals(timeCreate, that.timeCreate)
                && Objects.equals(typePosition, that.typePosition)
                && Objects.equals(employeeStatus, that.employeeStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, organization, employee, timeCreate, typePosition, employeeStatus);
    }
}
