package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "dkind")
public class DkindEntity{
    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "name2")
    private String name2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
