package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "GROUPS", schema = "", catalog = "")
public class GroupsEntity {
    private int groupsGuid;
    private String groupName;
    private String groupPrim;

    @Id
    @Column(name = "GROUPS_GUID")
    public int getGroupsGuid() {
        return groupsGuid;
    }

    public void setGroupsGuid(int groupsGuid) {
        this.groupsGuid = groupsGuid;
    }

    @Basic
    @Column(name = "GROUP_NAME")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Basic
    @Column(name = "GROUP_PRIM")
    public String getGroupPrim() {
        return groupPrim;
    }

    public void setGroupPrim(String groupPrim) {
        this.groupPrim = groupPrim;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupsEntity that = (GroupsEntity) o;
        return groupsGuid == that.groupsGuid && Objects.equals(groupName, that.groupName) && Objects.equals(groupPrim, that.groupPrim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupsGuid, groupName, groupPrim);
    }
}
