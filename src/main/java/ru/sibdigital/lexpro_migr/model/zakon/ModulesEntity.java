package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "modules")
public class ModulesEntity {
    @Id
    @Column(name = "modules_guid")
    private Long modulesGuid;

    @Basic
    @Column(name = "mod_name")
    private String modName;

    @Basic
    @Column(name = "mod_prim")
    private String modPrim;

    public Long getModulesGuid() {
        return modulesGuid;
    }

    public void setModulesGuid(Long modulesGuid) {
        this.modulesGuid = modulesGuid;
    }

    public String getModName() {
        return modName;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    public String getModPrim() {
        return modPrim;
    }

    public void setModPrim(String modPrim) {
        this.modPrim = modPrim;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModulesEntity that = (ModulesEntity) o;
        return modulesGuid == that.modulesGuid && Objects.equals(modName, that.modName) && Objects.equals(modPrim, that.modPrim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modulesGuid, modName, modPrim);
    }
}
