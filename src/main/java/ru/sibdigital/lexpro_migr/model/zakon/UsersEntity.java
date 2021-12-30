package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UsersEntity {
    @Id
    @Column(name = "users_guid")
    private Long usersGuid;

    @Basic
    @Column(name = "login")
    private String login;

    @Basic
    @Column(name = "person_id")
    private Long personId;

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


    public Long getUsersGuid() {
        return usersGuid;
    }

    public void setUsersGuid(Long usersGuid) {
        this.usersGuid = usersGuid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPassdata() {
        return passdata;
    }

    public void setPassdata(String passdata) {
        this.passdata = passdata;
    }

    public String getFlashserial() {
        return flashserial;
    }

    public void setFlashserial(String flashserial) {
        this.flashserial = flashserial;
    }

    public String getDnsName() {
        return dnsName;
    }

    public void setDnsName(String dnsName) {
        this.dnsName = dnsName;
    }

    public String getEnterMode() {
        return enterMode;
    }

    public void setEnterMode(String enterMode) {
        this.enterMode = enterMode;
    }

    public Timestamp getEnterTimestamp() {
        return enterTimestamp;
    }

    public void setEnterTimestamp(Timestamp enterTimestamp) {
        this.enterTimestamp = enterTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return Objects.equals(usersGuid, that.usersGuid) && Objects.equals(login, that.login) && Objects.equals(personId, that.personId) && Objects.equals(passdata, that.passdata) && Objects.equals(flashserial, that.flashserial) && Objects.equals(dnsName, that.dnsName) && Objects.equals(enterMode, that.enterMode) && Objects.equals(enterTimestamp, that.enterTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersGuid, login, personId, passdata, flashserial, dnsName, enterMode, enterTimestamp);
    }
}
