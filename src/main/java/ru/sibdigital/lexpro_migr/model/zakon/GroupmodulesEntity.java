package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "GROUPMODULES", schema = "", catalog = "")
public class GroupmodulesEntity {
    private int groupmodulesGuid;

    @Id
    @Column(name = "GROUPMODULES_GUID")
    public int getGroupmodulesGuid() {
        return groupmodulesGuid;
    }

    public void setGroupmodulesGuid(int groupmodulesGuid) {
        this.groupmodulesGuid = groupmodulesGuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupmodulesEntity that = (GroupmodulesEntity) o;
        return groupmodulesGuid == that.groupmodulesGuid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupmodulesGuid);
    }
}
