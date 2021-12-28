package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "dkind")
public class DkindEntity{
    private int id;
    private String name;
    private String name2;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "name2")
    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DkindEntity that = (DkindEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(name2, that.name2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, name2);
    }
}
