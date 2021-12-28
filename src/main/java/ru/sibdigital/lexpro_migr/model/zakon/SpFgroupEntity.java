package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SP_FGROUP", schema = "", catalog = "")
public class SpFgroupEntity {
    private int id;
    private String name;
    private String shortName;
    private Short sortOrder;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "SHORT_NAME")
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Basic
    @Column(name = "SORT_ORDER")
    public Short getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Short sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpFgroupEntity that = (SpFgroupEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(shortName, that.shortName) && Objects.equals(sortOrder, that.sortOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, shortName, sortOrder);
    }
}
