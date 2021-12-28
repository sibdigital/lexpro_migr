package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "USERS", schema = "", catalog = "")
public class UsersEntity {
    private int usersGuid;
    private String login;
    private String passdata;
    private String flashserial;
    private String dnsName;
    private String enterMode;
    private Timestamp enterTimestamp;

    @Id
    @Column(name = "USERS_GUID")
    public int getUsersGuid() {
        return usersGuid;
    }

    public void setUsersGuid(int usersGuid) {
        this.usersGuid = usersGuid;
    }

    @Basic
    @Column(name = "LOGIN")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "PASSDATA")
    public String getPassdata() {
        return passdata;
    }

    public void setPassdata(String passdata) {
        this.passdata = passdata;
    }

    @Basic
    @Column(name = "FLASHSERIAL")
    public String getFlashserial() {
        return flashserial;
    }

    public void setFlashserial(String flashserial) {
        this.flashserial = flashserial;
    }

    @Basic
    @Column(name = "DNS_NAME")
    public String getDnsName() {
        return dnsName;
    }

    public void setDnsName(String dnsName) {
        this.dnsName = dnsName;
    }

    @Basic
    @Column(name = "ENTER_MODE")
    public String getEnterMode() {
        return enterMode;
    }

    public void setEnterMode(String enterMode) {
        this.enterMode = enterMode;
    }

    @Basic
    @Column(name = "ENTER_TIMESTAMP")
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
        return usersGuid == that.usersGuid && Objects.equals(login, that.login) && Objects.equals(passdata, that.passdata) && Objects.equals(flashserial, that.flashserial) && Objects.equals(dnsName, that.dnsName) && Objects.equals(enterMode, that.enterMode) && Objects.equals(enterTimestamp, that.enterTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersGuid, login, passdata, flashserial, dnsName, enterMode, enterTimestamp);
    }
}
