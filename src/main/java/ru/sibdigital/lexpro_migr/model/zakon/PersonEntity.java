package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "person")
public class PersonEntity {
    private Long id;
    private String fio;
    private Long orgId;
    private Long departId;
    private Long doljnostId;
    private String postindx;
    private String adres;
    private String phone;
    private String email;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fio")
    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Basic
    @Column(name = "postindx")
    public String getPostindx() {
        return postindx;
    }

    public void setPostindx(String postindx) {
        this.postindx = postindx;
    }

    @Basic
    @Column(name = "adres")
    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "org_id")
    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    @Basic
    @Column(name = "depart_id")
    public Long getDepartId() {
        return departId;
    }

    public void setDepartId(Long departId) {
        this.departId = departId;
    }

    @Basic
    @Column(name = "doljnost_id")
    public Long getDoljnostId() {
        return doljnostId;
    }

    public void setDoljnostId(Long doljnostId) {
        this.doljnostId = doljnostId;
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
