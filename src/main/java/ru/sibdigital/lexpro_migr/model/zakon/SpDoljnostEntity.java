package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sp_doljnost")
public class SpDoljnostEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "flag_seealltasks")
    private String flagSeealltasks;

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
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(flagSeealltasks, that.flagSeealltasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, flagSeealltasks);
    }
}
