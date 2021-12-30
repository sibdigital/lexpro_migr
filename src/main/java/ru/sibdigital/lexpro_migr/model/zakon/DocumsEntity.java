package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "docums")
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

    @Basic
    @Column(name = "dkind_id")
    private Long dkindId;

    @Basic
    @Column(name = "subject_id")
    private Long subjectId;

    @Basic
    @Column(name = "dokladchik_id")
    private Long dokladchikId;

    @Basic
    @Column(name = "otvetstv_komitet_id")
    private Long otvetstvKomitetId;

    @Basic
    @Column(name = "otvetstv_person_id")
    private Long otvetstvPersonId;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date ddate) {
        this.ddate = ddate;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public String getZakonNum() {
        return zakonNum;
    }

    public void setZakonNum(String zakonNum) {
        this.zakonNum = zakonNum;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getZakOsnova() {
        return zakOsnova;
    }

    public void setZakOsnova(String zakOsnova) {
        this.zakOsnova = zakOsnova;
    }

    public Long getDkindId() {
        return dkindId;
    }

    public void setDkindId(Long dkindId) {
        this.dkindId = dkindId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getDokladchikId() {
        return dokladchikId;
    }

    public void setDokladchikId(Long dokladchikId) {
        this.dokladchikId = dokladchikId;
    }

    public Long getOtvetstvKomitetId() {
        return otvetstvKomitetId;
    }

    public void setOtvetstvKomitetId(Long otvetstvKomitetId) {
        this.otvetstvKomitetId = otvetstvKomitetId;
    }

    public Long getOtvetstvPersonId() {
        return otvetstvPersonId;
    }

    public void setOtvetstvPersonId(Long otvetstvPersonId) {
        this.otvetstvPersonId = otvetstvPersonId;
    }

    public Date getPovestDate() {
        return povestDate;
    }

    public void setPovestDate(Date povestDate) {
        this.povestDate = povestDate;
    }

    public Date getControlDate() {
        return controlDate;
    }

    public void setControlDate(Date controlDate) {
        this.controlDate = controlDate;
    }

    public Date getDateDeputat() {
        return dateDeputat;
    }

    public void setDateDeputat(Date dateDeputat) {
        this.dateDeputat = dateDeputat;
    }

    public Date getDatePrez() {
        return datePrez;
    }

    public void setDatePrez(Date datePrez) {
        this.datePrez = datePrez;
    }

    public Date getDateGu() {
        return dateGu;
    }

    public void setDateGu(Date dateGu) {
        this.dateGu = dateGu;
    }

    public Date getDateProkuror() {
        return dateProkuror;
    }

    public void setDateProkuror(Date dateProkuror) {
        this.dateProkuror = dateProkuror;
    }

    public Date getDatePuZ() {
        return datePuZ;
    }

    public void setDatePuZ(Date datePuZ) {
        this.datePuZ = datePuZ;
    }

    public Date getDatePuP() {
        return datePuP;
    }

    public void setDatePuP(Date datePuP) {
        this.datePuP = datePuP;
    }

    public Date getDatePuP2() {
        return datePuP2;
    }

    public void setDatePuP2(Date datePuP2) {
        this.datePuP2 = datePuP2;
    }

    public Date getDatePu2Ch() {
        return datePu2Ch;
    }

    public void setDatePu2Ch(Date datePu2Ch) {
        this.datePu2Ch = datePu2Ch;
    }

    public Date getDatePuT() {
        return datePuT;
    }

    public void setDatePuT(Date datePuT) {
        this.datePuT = datePuT;
    }

    public String getDateDeputatPrim() {
        return dateDeputatPrim;
    }

    public void setDateDeputatPrim(String dateDeputatPrim) {
        this.dateDeputatPrim = dateDeputatPrim;
    }

    public String getDatePrezPrim() {
        return datePrezPrim;
    }

    public void setDatePrezPrim(String datePrezPrim) {
        this.datePrezPrim = datePrezPrim;
    }

    public String getDateGuPrim() {
        return dateGuPrim;
    }

    public void setDateGuPrim(String dateGuPrim) {
        this.dateGuPrim = dateGuPrim;
    }

    public String getDateProkurorPrim() {
        return dateProkurorPrim;
    }

    public void setDateProkurorPrim(String dateProkurorPrim) {
        this.dateProkurorPrim = dateProkurorPrim;
    }

    public String getDatePuZPrim() {
        return datePuZPrim;
    }

    public void setDatePuZPrim(String datePuZPrim) {
        this.datePuZPrim = datePuZPrim;
    }

    public String getDatePuPPrim() {
        return datePuPPrim;
    }

    public void setDatePuPPrim(String datePuPPrim) {
        this.datePuPPrim = datePuPPrim;
    }

    public String getDatePuP2Prim() {
        return datePuP2Prim;
    }

    public void setDatePuP2Prim(String datePuP2Prim) {
        this.datePuP2Prim = datePuP2Prim;
    }

    public String getDatePu2ChPrim() {
        return datePu2ChPrim;
    }

    public void setDatePu2ChPrim(String datePu2ChPrim) {
        this.datePu2ChPrim = datePu2ChPrim;
    }

    public String getDatePuTPrim() {
        return datePuTPrim;
    }

    public void setDatePuTPrim(String datePuTPrim) {
        this.datePuTPrim = datePuTPrim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPrezidentPodpisDate() {
        return prezidentPodpisDate;
    }

    public void setPrezidentPodpisDate(Date prezidentPodpisDate) {
        this.prezidentPodpisDate = prezidentPodpisDate;
    }

    public String getRkkReady() {
        return rkkReady;
    }

    public void setRkkReady(String rkkReady) {
        this.rkkReady = rkkReady;
    }

    public String getSessiaNum() {
        return sessiaNum;
    }

    public void setSessiaNum(String sessiaNum) {
        this.sessiaNum = sessiaNum;
    }

    public Date getSessiaDate() {
        return sessiaDate;
    }

    public void setSessiaDate(Date sessiaDate) {
        this.sessiaDate = sessiaDate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNpp() {
        return npp;
    }

    public void setNpp(String npp) {
        this.npp = npp;
    }

    public String getPrim() {
        return prim;
    }

    public void setPrim(String prim) {
        this.prim = prim;
    }

    public Timestamp getEditDate() {
        return editDate;
    }

    public void setEditDate(Timestamp editDate) {
        this.editDate = editDate;
    }

    public String getEditorLogin() {
        return editorLogin;
    }

    public void setEditorLogin(String editorLogin) {
        this.editorLogin = editorLogin;
    }

    public String getFlagDeleted() {
        return flagDeleted;
    }

    public void setFlagDeleted(String flagDeleted) {
        this.flagDeleted = flagDeleted;
    }

    public String getFlagDownloaded() {
        return flagDownloaded;
    }

    public void setFlagDownloaded(String flagDownloaded) {
        this.flagDownloaded = flagDownloaded;
    }

    public Timestamp getTimestampDownloaded() {
        return timestampDownloaded;
    }

    public void setTimestampDownloaded(Timestamp timestampDownloaded) {
        this.timestampDownloaded = timestampDownloaded;
    }

    public Date getOpublikDate() {
        return opublikDate;
    }

    public void setOpublikDate(Date opublikDate) {
        this.opublikDate = opublikDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumsEntity that = (DocumsEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(ddate, that.ddate) && Objects.equals(regNum, that.regNum) && Objects.equals(zakonNum, that.zakonNum) && Objects.equals(regDate, that.regDate) && Objects.equals(descr, that.descr) && Objects.equals(zakOsnova, that.zakOsnova) && Objects.equals(dkindId, that.dkindId) && Objects.equals(subjectId, that.subjectId) && Objects.equals(dokladchikId, that.dokladchikId) && Objects.equals(otvetstvKomitetId, that.otvetstvKomitetId) && Objects.equals(otvetstvPersonId, that.otvetstvPersonId) && Objects.equals(povestDate, that.povestDate) && Objects.equals(controlDate, that.controlDate) && Objects.equals(dateDeputat, that.dateDeputat) && Objects.equals(datePrez, that.datePrez) && Objects.equals(dateGu, that.dateGu) && Objects.equals(dateProkuror, that.dateProkuror) && Objects.equals(datePuZ, that.datePuZ) && Objects.equals(datePuP, that.datePuP) && Objects.equals(datePuP2, that.datePuP2) && Objects.equals(datePu2Ch, that.datePu2Ch) && Objects.equals(datePuT, that.datePuT) && Objects.equals(dateDeputatPrim, that.dateDeputatPrim) && Objects.equals(datePrezPrim, that.datePrezPrim) && Objects.equals(dateGuPrim, that.dateGuPrim) && Objects.equals(dateProkurorPrim, that.dateProkurorPrim) && Objects.equals(datePuZPrim, that.datePuZPrim) && Objects.equals(datePuPPrim, that.datePuPPrim) && Objects.equals(datePuP2Prim, that.datePuP2Prim) && Objects.equals(datePu2ChPrim, that.datePu2ChPrim) && Objects.equals(datePuTPrim, that.datePuTPrim) && Objects.equals(status, that.status) && Objects.equals(prezidentPodpisDate, that.prezidentPodpisDate) && Objects.equals(rkkReady, that.rkkReady) && Objects.equals(sessiaNum, that.sessiaNum) && Objects.equals(sessiaDate, that.sessiaDate) && Objects.equals(color, that.color) && Objects.equals(npp, that.npp) && Objects.equals(prim, that.prim) && Objects.equals(editDate, that.editDate) && Objects.equals(editorLogin, that.editorLogin) && Objects.equals(flagDeleted, that.flagDeleted) && Objects.equals(flagDownloaded, that.flagDownloaded) && Objects.equals(timestampDownloaded, that.timestampDownloaded) && Objects.equals(opublikDate, that.opublikDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ddate, regNum, zakonNum, regDate, descr, zakOsnova, dkindId, subjectId, dokladchikId, otvetstvKomitetId, otvetstvPersonId, povestDate, controlDate, dateDeputat, datePrez, dateGu, dateProkuror, datePuZ, datePuP, datePuP2, datePu2Ch, datePuT, dateDeputatPrim, datePrezPrim, dateGuPrim, dateProkurorPrim, datePuZPrim, datePuPPrim, datePuP2Prim, datePu2ChPrim, datePuTPrim, status, prezidentPodpisDate, rkkReady, sessiaNum, sessiaDate, color, npp, prim, editDate, editorLogin, flagDeleted, flagDownloaded, timestampDownloaded, opublikDate);
    }
}
