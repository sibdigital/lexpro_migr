package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "person")
public class PersonEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "fio")
    private String fio;

    @Basic
    @Column(name = "org_id")
    private Long orgId;

    @Basic
    @Column(name = "depart_id")
    private Long departId;

    @Basic
    @Column(name = "doljnost_id")
    private Long doljnostId;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getDepartId() {
        return departId;
    }

    public void setDepartId(Long departId) {
        this.departId = departId;
    }

    public Long getDoljnostId() {
        return doljnostId;
    }

    public void setDoljnostId(Long doljnostId) {
        this.doljnostId = doljnostId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonEntity that = (PersonEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(fio, that.fio) && Objects.equals(orgId, that.orgId) && Objects.equals(departId, that.departId) && Objects.equals(doljnostId, that.doljnostId) && Objects.equals(postindx, that.postindx) && Objects.equals(adres, that.adres) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, orgId, departId, doljnostId, postindx, adres, phone, email);
    }
}
