package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "USERGROUPS", schema = "", catalog = "")
public class UsergroupsEntity {
    private int usergroupsGuid;

    @Id
    @Column(name = "USERGROUPS_GUID")
    public int getUsergroupsGuid() {
        return usergroupsGuid;
    }

    public void setUsergroupsGuid(int usergroupsGuid) {
        this.usergroupsGuid = usergroupsGuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsergroupsEntity that = (UsergroupsEntity) o;
        return usergroupsGuid == that.usergroupsGuid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usergroupsGuid);
    }
}
