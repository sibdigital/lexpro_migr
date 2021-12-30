package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "groups")
public class GroupsEntity {

    @Id
    @Column(name = "groups_guid")
    private Long groupsGuid;

    @Basic
    @Column(name = "group_name")
    private String groupName;

    @Basic
    @Column(name = "group_prim")
    private String groupPrim;

    public Long getGroupsGuid() {
        return groupsGuid;
    }

    public void setGroupsGuid(Long groupsGuid) {
        this.groupsGuid = groupsGuid;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

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
