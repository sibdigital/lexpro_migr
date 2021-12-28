package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "MODULES", schema = "", catalog = "")
public class ModulesEntity {
    private int modulesGuid;
    private String modName;
    private String modPrim;

    @Id
    @Column(name = "MODULES_GUID")
    public int getModulesGuid() {
        return modulesGuid;
    }

    public void setModulesGuid(int modulesGuid) {
        this.modulesGuid = modulesGuid;
    }

    @Basic
    @Column(name = "MOD_NAME")
    public String getModName() {
        return modName;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    @Basic
    @Column(name = "MOD_PRIM")
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
