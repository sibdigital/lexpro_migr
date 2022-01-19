package ru.sibdigital.lexpro_migr.model.zakon;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "org")
@Data
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

    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
    @ManyToOne
    private PersonEntity personEntity;
}
