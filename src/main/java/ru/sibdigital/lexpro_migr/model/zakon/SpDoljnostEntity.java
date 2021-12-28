package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SP_DOLJNOST")
public class SpDoljnostEntity {
    private int id;
    private String name;
    private String flagSeealltasks;

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
    @Column(name = "FLAG_SEEALLTASKS")
    public String getFlagSeealltasks() {
        return flagSeealltasks;
    }

    public void setFlagSeealltasks(String flagSeealltasks) {
        this.flagSeealltasks = flagSeealltasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpDoljnostEntity that = (SpDoljnostEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(flagSeealltasks, that.flagSeealltasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, flagSeealltasks);
    }
}
