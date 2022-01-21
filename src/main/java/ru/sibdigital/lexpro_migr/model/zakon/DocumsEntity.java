package ru.sibdigital.lexpro_migr.model.zakon;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "docums")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class DocumsEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "ddate")
    private Date ddate;

    @Basic
    @Column(name = "reg_num")
    private String regNum;

    @Basic
    @Column(name = "zakon_num")
    private String zakonNum;

    @Basic
    @Column(name = "reg_date")
    private Date regDate;

    @Basic
    @Column(name = "descr")
    private String descr;

    @Basic
    @Column(name = "zak_osnova")
    private String zakOsnova;

//    @Basic
//    @Column(name = "dkind_id")
//    private Long dkindId;
    @ManyToOne
    @JoinColumn(name = "dkind_id", referencedColumnName = "id")
    private DkindEntity dkind;

//    @Basic
//    @Column(name = "subject_id")
//    private Long subjectId;
    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private OrgEntity subject;

//    @Basic
//    @Column(name = "dokladchik_id")
//    private Long dokladchikId;
    @ManyToOne
    @JoinColumn(name = "dokladchik_id", referencedColumnName = "id")
    private PersonEntity dokladchik;

//    @Basic
//    @Column(name = "otvetstv_komitet_id")
//    private Long otvetstvKomitetId;
    @ManyToOne
    @JoinColumn(name = "otvetstv_komitet_id", referencedColumnName = "id")
    private OrgEntity otvetstvKomitet;

//    @Basic
//    @Column(name = "otvetstv_person_id")
//    private Long otvetstvPersonId;
    @ManyToOne
    @JoinColumn(name = "otvetstv_person_id", referencedColumnName = "id")
    private PersonEntity otvetstvPerson;

    @Basic
    @Column(name = "povest_date")
    private Date povestDate;

    @Basic
    @Column(name = "control_date")
    private Date controlDate;

    @Basic
    @Column(name = "date_deputat")
    private Date dateDeputat;

    @Basic
    @Column(name = "date_prez")
    private Date datePrez;

    @Basic
    @Column(name = "date_gu")
    private Date dateGu;

    @Basic
    @Column(name = "date_prokuror")
    private Date dateProkuror;

    @Basic
    @Column(name = "date_pu_z")
    private Date datePuZ;

    @Basic
    @Column(name = "date_pu_p")
    private Date datePuP;

    @Basic
    @Column(name = "date_pu_p2")
    private Date datePuP2;

    @Basic
    @Column(name = "date_pu_2ch")
    private Date datePu2Ch;

    @Basic
    @Column(name = "date_pu_t")
    private Date datePuT;

    @Basic
    @Column(name = "date_deputat_prim")
    private String dateDeputatPrim;

    @Basic
    @Column(name = "date_prez_prim")
    private String datePrezPrim;

    @Basic
    @Column(name = "date_gu_prim")
    private String dateGuPrim;

    @Basic
    @Column(name = "date_prokuror_prim")
    private String dateProkurorPrim;

    @Basic
    @Column(name = "date_pu_z_prim")
    private String datePuZPrim;

    @Basic
    @Column(name = "date_pu_p_prim")
    private String datePuPPrim;

    @Basic
    @Column(name = "date_pu_p2_prim")
    private String datePuP2Prim;

    @Basic
    @Column(name = "date_pu_2ch_prim")
    private String datePu2ChPrim;

    @Basic
    @Column(name = "date_pu_t_prim")
    private String datePuTPrim;

    @Basic
    @Column(name = "status")
    private String status;

    @Basic
    @Column(name = "prezident_podpis_date")
    private Date prezidentPodpisDate;

    @Basic
    @Column(name = "rkk_ready")
    private String rkkReady;

    @Basic
    @Column(name = "sessia_num")
    private String sessiaNum;

    @Basic
    @Column(name = "sessia_date")
    private Date sessiaDate;

    @Basic
    @Column(name = "color")
    private String color;

    @Basic
    @Column(name = "npp")
    private String npp;

    @Basic
    @Column(name = "prim")
    private String prim;

    @Basic
    @Column(name = "edit_date")
    private Timestamp editDate;

    @Basic
    @Column(name = "editor_login")
    private String editorLogin;

    @Basic
    @Column(name = "flag_deleted")
    private String flagDeleted;

    @Basic
    @Column(name = "flag_downloaded")
    private String flagDownloaded;

    @Basic
    @Column(name = "timestamp_downloaded")
    private Timestamp timestampDownloaded;

    @Basic
    @Column(name = "opublik_date")
    private Date opublikDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumsEntity that = (DocumsEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(ddate, that.ddate) && Objects.equals(regNum, that.regNum) && Objects.equals(zakonNum, that.zakonNum) && Objects.equals(regDate, that.regDate) && Objects.equals(descr, that.descr) && Objects.equals(zakOsnova, that.zakOsnova) && Objects.equals(dkind.getId(), that.dkind.getId()) && Objects.equals(subject.getId(), that.subject.getId()) && Objects.equals(dokladchik.getId(), that.dokladchik.getId()) && Objects.equals(otvetstvKomitet.getId(), that.otvetstvKomitet.getId()) && Objects.equals(otvetstvPerson.getId(), that.otvetstvPerson.getId()) && Objects.equals(povestDate, that.povestDate) && Objects.equals(controlDate, that.controlDate) && Objects.equals(dateDeputat, that.dateDeputat) && Objects.equals(datePrez, that.datePrez) && Objects.equals(dateGu, that.dateGu) && Objects.equals(dateProkuror, that.dateProkuror) && Objects.equals(datePuZ, that.datePuZ) && Objects.equals(datePuP, that.datePuP) && Objects.equals(datePuP2, that.datePuP2) && Objects.equals(datePu2Ch, that.datePu2Ch) && Objects.equals(datePuT, that.datePuT) && Objects.equals(dateDeputatPrim, that.dateDeputatPrim) && Objects.equals(datePrezPrim, that.datePrezPrim) && Objects.equals(dateGuPrim, that.dateGuPrim) && Objects.equals(dateProkurorPrim, that.dateProkurorPrim) && Objects.equals(datePuZPrim, that.datePuZPrim) && Objects.equals(datePuPPrim, that.datePuPPrim) && Objects.equals(datePuP2Prim, that.datePuP2Prim) && Objects.equals(datePu2ChPrim, that.datePu2ChPrim) && Objects.equals(datePuTPrim, that.datePuTPrim) && Objects.equals(status, that.status) && Objects.equals(prezidentPodpisDate, that.prezidentPodpisDate) && Objects.equals(rkkReady, that.rkkReady) && Objects.equals(sessiaNum, that.sessiaNum) && Objects.equals(sessiaDate, that.sessiaDate) && Objects.equals(color, that.color) && Objects.equals(npp, that.npp) && Objects.equals(prim, that.prim) && Objects.equals(editDate, that.editDate) && Objects.equals(editorLogin, that.editorLogin) && Objects.equals(flagDeleted, that.flagDeleted) && Objects.equals(flagDownloaded, that.flagDownloaded) && Objects.equals(timestampDownloaded, that.timestampDownloaded) && Objects.equals(opublikDate, that.opublikDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ddate, regNum, zakonNum, regDate, descr, zakOsnova, dkind, subject, dokladchik, otvetstvKomitet, otvetstvPerson, povestDate, controlDate, dateDeputat, datePrez, dateGu, dateProkuror, datePuZ, datePuP, datePuP2, datePu2Ch, datePuT, dateDeputatPrim, datePrezPrim, dateGuPrim, dateProkurorPrim, datePuZPrim, datePuPPrim, datePuP2Prim, datePu2ChPrim, datePuTPrim, status, prezidentPodpisDate, rkkReady, sessiaNum, sessiaDate, color, npp, prim, editDate, editorLogin, flagDeleted, flagDownloaded, timestampDownloaded, opublikDate);
    }
}
