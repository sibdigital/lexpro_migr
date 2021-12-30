package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "org")
public class OrgEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "short_name")
    private String shortName;

    @Basic
    @Column(name = "person_id")
    private Long personId;

    @Basic
    @Column(name = "postindx")
    private String postindx;

    @Basic
    @Column(name = "adres")
    private String adres;

    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "flag_komitet")
    private String flagKomitet;

    @Basic
    @Column(name = "flag_subject")
    private String flagSubject;

    @Basic
    @Column(name = "flag_org")
    private String flagOrg;

    @Basic
    @Column(name = "flag_dep")
    private String flagDep;

    @Basic
    @Column(name = "flag_rep")
    private Short flagRep;

    @Basic
    @Column(name = "flag_sort")
    private Short flagSort;

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPostindx() {
        return postindx;
    }

    public void setPostindx(String postindx) {
        this.postindx = postindx;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFlagKomitet() {
        return flagKomitet;
    }

    public void setFlagKomitet(String flagKomitet) {
        this.flagKomitet = flagKomitet;
    }

    public String getFlagSubject() {
        return flagSubject;
    }

    public void setFlagSubject(String flagSubject) {
        this.flagSubject = flagSubject;
    }

    public String getFlagOrg() {
        return flagOrg;
    }

    public void setFlagOrg(String flagOrg) {
        this.flagOrg = flagOrg;
    }

    public String getFlagDep() {
        return flagDep;
    }

    public void setFlagDep(String flagDep) {
        this.flagDep = flagDep;
    }

    public Short getFlagRep() {
        return flagRep;
    }

    public void setFlagRep(Short flagRep) {
        this.flagRep = flagRep;
    }

    public Short getFlagSort() {
        return flagSort;
    }

    public void setFlagSort(Short flagSort) {
        this.flagSort = flagSort;
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
        OrgEntity orgEntity = (OrgEntity) o;
        return Objects.equals(id, orgEntity.id) && Objects.equals(name, orgEntity.name) && Objects.equals(shortName, orgEntity.shortName) && Objects.equals(personId, orgEntity.personId) && Objects.equals(postindx, orgEntity.postindx) && Objects.equals(adres, orgEntity.adres) && Objects.equals(phone, orgEntity.phone) && Objects.equals(email, orgEntity.email) && Objects.equals(flagKomitet, orgEntity.flagKomitet) && Objects.equals(flagSubject, orgEntity.flagSubject) && Objects.equals(flagOrg, orgEntity.flagOrg) && Objects.equals(flagDep, orgEntity.flagDep) && Objects.equals(flagRep, orgEntity.flagRep) && Objects.equals(flagSort, orgEntity.flagSort) && Objects.equals(name2, orgEntity.name2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, shortName, personId, postindx, adres, phone, email, flagKomitet, flagSubject, flagOrg, flagDep, flagRep, flagSort, name2);
    }
}
