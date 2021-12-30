package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "groupmodules")
public class GroupmodulesEntity {
    @Id
    @Column(name = "groupmodules_guid")
    private Long groupmodulesGuid;

    @Basic
    @Column(name = "groups_guid")
    private Long groupsGuid;

    @Basic
    @Column(name = "modules_guid")
    private Long modulesGuid;

    public Long getGroupmodulesGuid() {
        return groupmodulesGuid;
    }

    public void setGroupmodulesGuid(Long groupmodulesGuid) {
        this.groupmodulesGuid = groupmodulesGuid;
    }

    public Long getGroupsGuid() {
        return groupsGuid;
    }

    public void setGroupsGuid(Long groupsGuid) {
        this.groupsGuid = groupsGuid;
    }

    public Long getModulesGuid() {
        return modulesGuid;
    }

    public void setModulesGuid(Long modulesGuid) {
        this.modulesGuid = modulesGuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupmodulesEntity that = (GroupmodulesEntity) o;
        return Objects.equals(groupmodulesGuid, that.groupmodulesGuid) && Objects.equals(groupsGuid, that.groupsGuid) && Objects.equals(modulesGuid, that.modulesGuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupmodulesGuid, groupsGuid, modulesGuid);
    }
}
