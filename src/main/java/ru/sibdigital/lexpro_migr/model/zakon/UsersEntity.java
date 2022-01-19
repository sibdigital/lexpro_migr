package ru.sibdigital.lexpro_migr.model.zakon;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "users")
@Data
public class UsersEntity {
    @Id
    @Column(name = "users_guid")
    private Long usersGuid;

    @Basic
    @Column(name = "login")
    private String login;

    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
    @ManyToOne
    private PersonEntity personEntity;

    @Basic
    @Column(name = "passdata")
    private String passdata;

    @Basic
    @Column(name = "flashserial")
    private String flashserial;

    @Basic
    @Column(name = "dns_name")
    private String dnsName;

    @Basic
    @Column(name = "enter_mode")
    private String enterMode;

    @Basic
    @Column(name = "enter_timestamp")
    private Timestamp enterTimestamp;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return Objects.equals(usersGuid, that.usersGuid) && Objects.equals(login, that.login) && Objects.equals(personEntity.getId(), that.personEntity.getId()) && Objects.equals(passdata, that.passdata) && Objects.equals(flashserial, that.flashserial) && Objects.equals(dnsName, that.dnsName) && Objects.equals(enterMode, that.enterMode) && Objects.equals(enterTimestamp, that.enterTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersGuid, login, personEntity.getId(), passdata, flashserial, dnsName, enterMode, enterTimestamp);
    }
}
