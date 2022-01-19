package ru.sibdigital.lexpro_migr.model.lexpro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "reg_mailing_history", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RegMailingHistory implements Serializable {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "REG_MAILING_HISTORY_SEQ_GEN", sequenceName = "reg_mailing_history_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REG_MAILING_HISTORY_SEQ_GEN")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private ClsUser clsUser;

    private Integer status;
    private String email;
    private Long idMailingSource;

    @ManyToOne
    @JoinColumn(name = "id_type_mailing_source", referencedColumnName = "id")
    private ClsTypeDestination mailingSource;

    private Long idMailingDestination;

    @ManyToOne
    @JoinColumn(name = "id_type_mailing_destination", referencedColumnName = "id")
    private ClsTypeDestination mailingDestination;

    private Timestamp timeSend;
    private Timestamp timeCreate;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public ClsUser getClsUser() {
        return clsUser;
    }
    public void setClsUser(ClsUser clsUser) {
        this.clsUser = clsUser;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "id_mailing_source")
    public Long getIdMailingSource() {
        return idMailingSource;
    }
    public void setIdMailingSource(Long idMailingSource) {
        this.idMailingSource = idMailingSource;
    }

    public ClsTypeDestination getMailingSource() {
        return mailingSource;
    }
    public void setMailingSource(ClsTypeDestination mailingSource) {
        this.mailingSource = mailingSource;
    }

    @Basic
    @Column(name = "id_mailing_destination")
    public Long getIdMailingDestination() {
        return idMailingDestination;
    }
    public void setIdMailingDestination(Long idMailingDestination) {
        this.idMailingDestination = idMailingDestination;
    }

    public ClsTypeDestination getMailingDestination() {
        return mailingDestination;
    }
    public void setMailingDestination(ClsTypeDestination mailingDestination) {
        this.mailingDestination = mailingDestination;
    }

    @Basic
    @Column(name = "time_send", nullable = false)
    public Timestamp getTimeSend() {
        return timeSend;
    }
    public void setTimeSend(Timestamp timeSend) {
        this.timeSend = timeSend;
    }

    @Basic
    @Column(name = "time_create", nullable = false)
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
        RegMailingHistory that = (RegMailingHistory) o;
        return Objects.equals(id, that.id)
                && Objects.equals(clsUser, that.clsUser)
                && Objects.equals(status, that.status)
                && Objects.equals(email, that.email)
                && Objects.equals(idMailingSource, that.idMailingSource)
                && Objects.equals(mailingSource, that.mailingSource)
                && Objects.equals(idMailingDestination, that.idMailingDestination)
                && Objects.equals(mailingDestination, that.mailingDestination)
                && Objects.equals(timeSend, that.timeSend)
                && Objects.equals(timeCreate, that.timeCreate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clsUser, status, email, idMailingSource, mailingSource, idMailingDestination,
                mailingDestination, timeSend, timeCreate);
    }
}
