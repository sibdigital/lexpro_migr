package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ORG", schema = "", catalog = "")
public class OrgEntity {
    private Long id;
    private String name;
    private String shortName;
    private Long personId;
    private String postindx;
    private String adres;
    private String phone;
    private String email;
    private String flagKomitet;
    private String flagSubject;
    private String flagOrg;
    private String flagDep;
    private Short flagRep;
    private Short flagSort;
    private String name2;

    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    @Column(name = "person_id")
    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "POSTINDX")
    public String getPostindx() {
        return postindx;
    }

    public void setPostindx(String postindx) {
        this.postindx = postindx;
    }

    @Basic
    @Column(name = "ADRES")
    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    @Basic
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "FLAG_KOMITET")
    public String getFlagKomitet() {
        return flagKomitet;
    }

    public void setFlagKomitet(String flagKomitet) {
        this.flagKomitet = flagKomitet;
    }

    @Basic
    @Column(name = "FLAG_SUBJECT")
    public String getFlagSubject() {
        return flagSubject;
    }

    public void setFlagSubject(String flagSubject) {
        this.flagSubject = flagSubject;
    }

    @Basic
    @Column(name = "FLAG_ORG")
    public String getFlagOrg() {
        return flagOrg;
    }

    public void setFlagOrg(String flagOrg) {
        this.flagOrg = flagOrg;
    }

    @Basic
    @Column(name = "FLAG_DEP")
    public String getFlagDep() {
        return flagDep;
    }

    public void setFlagDep(String flagDep) {
        this.flagDep = flagDep;
    }

    @Basic
    @Column(name = "FLAG_REP")
    public Short getFlagRep() {
        return flagRep;
    }

    public void setFlagRep(Short flagRep) {
        this.flagRep = flagRep;
    }

    @Basic
    @Column(name = "FLAG_SORT")
    public Short getFlagSort() {
        return flagSort;
    }

    public void setFlagSort(Short flagSort) {
        this.flagSort = flagSort;
    }

    @Basic
    @Column(name = "NAME2")
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
        OrgEntity orgEntity = (OrgEntity) o;
        return Objects.equals(id, orgEntity.id) && Objects.equals(name, orgEntity.name) && Objects.equals(shortName, orgEntity.shortName) && Objects.equals(personId, orgEntity.personId) && Objects.equals(postindx, orgEntity.postindx) && Objects.equals(adres, orgEntity.adres) && Objects.equals(phone, orgEntity.phone) && Objects.equals(email, orgEntity.email) && Objects.equals(flagKomitet, orgEntity.flagKomitet) && Objects.equals(flagSubject, orgEntity.flagSubject) && Objects.equals(flagOrg, orgEntity.flagOrg) && Objects.equals(flagDep, orgEntity.flagDep) && Objects.equals(flagRep, orgEntity.flagRep) && Objects.equals(flagSort, orgEntity.flagSort) && Objects.equals(name2, orgEntity.name2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, shortName, personId, postindx, adres, phone, email, flagKomitet, flagSubject, flagOrg, flagDep, flagRep, flagSort, name2);
    }
}
