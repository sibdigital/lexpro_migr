package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "usergroups")
public class UsergroupsEntity {
    @Id
    @Column(name = "usergroups_guid")
    private Long usergroupsGuid;

    @Basic
    @Column(name = "login")
    private String login;

    @Basic
    @Column(name = "rgroup")
    private String rgroup;

    public Long getUsergroupsGuid() {
        return usergroupsGuid;
    }

    public void setUsergroupsGuid(Long usergroupsGuid) {
        this.usergroupsGuid = usergroupsGuid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRgroup() {
        return rgroup;
    }

    public void setRgroup(String rgroup) {
        this.rgroup = rgroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsergroupsEntity that = (UsergroupsEntity) o;
        return Objects.equals(usergroupsGuid, that.usergroupsGuid) && Objects.equals(login, that.login) && Objects.equals(rgroup, that.rgroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usergroupsGuid, login, rgroup);
    }
}
