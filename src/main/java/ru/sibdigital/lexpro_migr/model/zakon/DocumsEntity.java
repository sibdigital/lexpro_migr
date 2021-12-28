package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "DOCUMS", schema = "", catalog = "")
public class DocumsEntity {
    private int id;
    private Date ddate;
    private String regNum;
    private String zakonNum;
    private Date regDate;
    private String descr;
    private String zakOsnova;
    private Date povestDate;
    private Date controlDate;
    private Date dateDeputat;
    private Date datePrez;
    private Date dateGu;
    private Date dateProkuror;
    private Date datePuZ;
    private Date datePuP;
    private Date datePuP2;
    private Date datePu2Ch;
    private Date datePuT;
    private String dateDeputatPrim;
    private String datePrezPrim;
    private String dateGuPrim;
    private String dateProkurorPrim;
    private String datePuZPrim;
    private String datePuPPrim;
    private String datePuP2Prim;
    private String datePu2ChPrim;
    private String datePuTPrim;
    private String status;
    private Date prezidentPodpisDate;
    private String rkkReady;
    private String sessiaNum;
    private Date sessiaDate;
    private String color;
    private String npp;
    private String prim;
    private Timestamp editDate;
    private String editorLogin;
    private String flagDeleted;
    private String flagDownloaded;
    private Timestamp timestampDownloaded;
    private Date opublikDate;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DDATE")
    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date ddate) {
        this.ddate = ddate;
    }

    @Basic
    @Column(name = "REG_NUM")
    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    @Basic
    @Column(name = "ZAKON_NUM")
    public String getZakonNum() {
        return zakonNum;
    }

    public void setZakonNum(String zakonNum) {
        this.zakonNum = zakonNum;
    }

    @Basic
    @Column(name = "REG_DATE")
    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Basic
    @Column(name = "DESCR")
    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Basic
    @Column(name = "ZAK_OSNOVA")
    public String getZakOsnova() {
        return zakOsnova;
    }

    public void setZakOsnova(String zakOsnova) {
        this.zakOsnova = zakOsnova;
    }

    @Basic
    @Column(name = "POVEST_DATE")
    public Date getPovestDate() {
        return povestDate;
    }

    public void setPovestDate(Date povestDate) {
        this.povestDate = povestDate;
    }

    @Basic
    @Column(name = "CONTROL_DATE")
    public Date getControlDate() {
        return controlDate;
    }

    public void setControlDate(Date controlDate) {
        this.controlDate = controlDate;
    }

    @Basic
    @Column(name = "DATE_DEPUTAT")
    public Date getDateDeputat() {
        return dateDeputat;
    }

    public void setDateDeputat(Date dateDeputat) {
        this.dateDeputat = dateDeputat;
    }

    @Basic
    @Column(name = "DATE_PREZ")
    public Date getDatePrez() {
        return datePrez;
    }

    public void setDatePrez(Date datePrez) {
        this.datePrez = datePrez;
    }

    @Basic
    @Column(name = "DATE_GU")
    public Date getDateGu() {
        return dateGu;
    }

    public void setDateGu(Date dateGu) {
        this.dateGu = dateGu;
    }

    @Basic
    @Column(name = "DATE_PROKUROR")
    public Date getDateProkuror() {
        return dateProkuror;
    }

    public void setDateProkuror(Date dateProkuror) {
        this.dateProkuror = dateProkuror;
    }

    @Basic
    @Column(name = "DATE_PU_Z")
    public Date getDatePuZ() {
        return datePuZ;
    }

    public void setDatePuZ(Date datePuZ) {
        this.datePuZ = datePuZ;
    }

    @Basic
    @Column(name = "DATE_PU_P")
    public Date getDatePuP() {
        return datePuP;
    }

    public void setDatePuP(Date datePuP) {
        this.datePuP = datePuP;
    }

    @Basic
    @Column(name = "DATE_PU_P2")
    public Date getDatePuP2() {
        return datePuP2;
    }

    public void setDatePuP2(Date datePuP2) {
        this.datePuP2 = datePuP2;
    }

    @Basic
    @Column(name = "DATE_PU_2CH")
    public Date getDatePu2Ch() {
        return datePu2Ch;
    }

    public void setDatePu2Ch(Date datePu2Ch) {
        this.datePu2Ch = datePu2Ch;
    }

    @Basic
    @Column(name = "DATE_PU_T")
    public Date getDatePuT() {
        return datePuT;
    }

    public void setDatePuT(Date datePuT) {
        this.datePuT = datePuT;
    }

    @Basic
    @Column(name = "DATE_DEPUTAT_PRIM")
    public String getDateDeputatPrim() {
        return dateDeputatPrim;
    }

    public void setDateDeputatPrim(String dateDeputatPrim) {
        this.dateDeputatPrim = dateDeputatPrim;
    }

    @Basic
    @Column(name = "DATE_PREZ_PRIM")
    public String getDatePrezPrim() {
        return datePrezPrim;
    }

    public void setDatePrezPrim(String datePrezPrim) {
        this.datePrezPrim = datePrezPrim;
    }

    @Basic
    @Column(name = "DATE_GU_PRIM")
    public String getDateGuPrim() {
        return dateGuPrim;
    }

    public void setDateGuPrim(String dateGuPrim) {
        this.dateGuPrim = dateGuPrim;
    }

    @Basic
    @Column(name = "DATE_PROKUROR_PRIM")
    public String getDateProkurorPrim() {
        return dateProkurorPrim;
    }

    public void setDateProkurorPrim(String dateProkurorPrim) {
        this.dateProkurorPrim = dateProkurorPrim;
    }

    @Basic
    @Column(name = "DATE_PU_Z_PRIM")
    public String getDatePuZPrim() {
        return datePuZPrim;
    }

    public void setDatePuZPrim(String datePuZPrim) {
        this.datePuZPrim = datePuZPrim;
    }

    @Basic
    @Column(name = "DATE_PU_P_PRIM")
    public String getDatePuPPrim() {
        return datePuPPrim;
    }

    public void setDatePuPPrim(String datePuPPrim) {
        this.datePuPPrim = datePuPPrim;
    }

    @Basic
    @Column(name = "DATE_PU_P2_PRIM")
    public String getDatePuP2Prim() {
        return datePuP2Prim;
    }

    public void setDatePuP2Prim(String datePuP2Prim) {
        this.datePuP2Prim = datePuP2Prim;
    }

    @Basic
    @Column(name = "DATE_PU_2CH_PRIM")
    public String getDatePu2ChPrim() {
        return datePu2ChPrim;
    }

    public void setDatePu2ChPrim(String datePu2ChPrim) {
        this.datePu2ChPrim = datePu2ChPrim;
    }

    @Basic
    @Column(name = "DATE_PU_T_PRIM")
    public String getDatePuTPrim() {
        return datePuTPrim;
    }

    public void setDatePuTPrim(String datePuTPrim) {
        this.datePuTPrim = datePuTPrim;
    }

    @Basic
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "PREZIDENT_PODPIS_DATE")
    public Date getPrezidentPodpisDate() {
        return prezidentPodpisDate;
    }

    public void setPrezidentPodpisDate(Date prezidentPodpisDate) {
        this.prezidentPodpisDate = prezidentPodpisDate;
    }

    @Basic
    @Column(name = "RKK_READY")
    public String getRkkReady() {
        return rkkReady;
    }

    public void setRkkReady(String rkkReady) {
        this.rkkReady = rkkReady;
    }

    @Basic
    @Column(name = "SESSIA_NUM")
    public String getSessiaNum() {
        return sessiaNum;
    }

    public void setSessiaNum(String sessiaNum) {
        this.sessiaNum = sessiaNum;
    }

    @Basic
    @Column(name = "SESSIA_DATE")
    public Date getSessiaDate() {
        return sessiaDate;
    }

    public void setSessiaDate(Date sessiaDate) {
        this.sessiaDate = sessiaDate;
    }

    @Basic
    @Column(name = "COLOR")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Basic
    @Column(name = "NPP")
    public String getNpp() {
        return npp;
    }

    public void setNpp(String npp) {
        this.npp = npp;
    }

    @Basic
    @Column(name = "PRIM")
    public String getPrim() {
        return prim;
    }

    public void setPrim(String prim) {
        this.prim = prim;
    }

    @Basic
    @Column(name = "EDIT_DATE")
    public Timestamp getEditDate() {
        return editDate;
    }

    public void setEditDate(Timestamp editDate) {
        this.editDate = editDate;
    }

    @Basic
    @Column(name = "EDITOR_LOGIN")
    public String getEditorLogin() {
        return editorLogin;
    }

    public void setEditorLogin(String editorLogin) {
        this.editorLogin = editorLogin;
    }

    @Basic
    @Column(name = "FLAG_DELETED")
    public String getFlagDeleted() {
        return flagDeleted;
    }

    public void setFlagDeleted(String flagDeleted) {
        this.flagDeleted = flagDeleted;
    }

    @Basic
    @Column(name = "FLAG_DOWNLOADED")
    public String getFlagDownloaded() {
        return flagDownloaded;
    }

    public void setFlagDownloaded(String flagDownloaded) {
        this.flagDownloaded = flagDownloaded;
    }

    @Basic
    @Column(name = "TIMESTAMP_DOWNLOADED")
    public Timestamp getTimestampDownloaded() {
        return timestampDownloaded;
    }

    public void setTimestampDownloaded(Timestamp timestampDownloaded) {
        this.timestampDownloaded = timestampDownloaded;
    }

    @Basic
    @Column(name = "OPUBLIK_DATE")
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
        return id == that.id && Objects.equals(ddate, that.ddate) && Objects.equals(regNum, that.regNum) && Objects.equals(zakonNum, that.zakonNum) && Objects.equals(regDate, that.regDate) && Objects.equals(descr, that.descr) && Objects.equals(zakOsnova, that.zakOsnova) && Objects.equals(povestDate, that.povestDate) && Objects.equals(controlDate, that.controlDate) && Objects.equals(dateDeputat, that.dateDeputat) && Objects.equals(datePrez, that.datePrez) && Objects.equals(dateGu, that.dateGu) && Objects.equals(dateProkuror, that.dateProkuror) && Objects.equals(datePuZ, that.datePuZ) && Objects.equals(datePuP, that.datePuP) && Objects.equals(datePuP2, that.datePuP2) && Objects.equals(datePu2Ch, that.datePu2Ch) && Objects.equals(datePuT, that.datePuT) && Objects.equals(dateDeputatPrim, that.dateDeputatPrim) && Objects.equals(datePrezPrim, that.datePrezPrim) && Objects.equals(dateGuPrim, that.dateGuPrim) && Objects.equals(dateProkurorPrim, that.dateProkurorPrim) && Objects.equals(datePuZPrim, that.datePuZPrim) && Objects.equals(datePuPPrim, that.datePuPPrim) && Objects.equals(datePuP2Prim, that.datePuP2Prim) && Objects.equals(datePu2ChPrim, that.datePu2ChPrim) && Objects.equals(datePuTPrim, that.datePuTPrim) && Objects.equals(status, that.status) && Objects.equals(prezidentPodpisDate, that.prezidentPodpisDate) && Objects.equals(rkkReady, that.rkkReady) && Objects.equals(sessiaNum, that.sessiaNum) && Objects.equals(sessiaDate, that.sessiaDate) && Objects.equals(color, that.color) && Objects.equals(npp, that.npp) && Objects.equals(prim, that.prim) && Objects.equals(editDate, that.editDate) && Objects.equals(editorLogin, that.editorLogin) && Objects.equals(flagDeleted, that.flagDeleted) && Objects.equals(flagDownloaded, that.flagDownloaded) && Objects.equals(timestampDownloaded, that.timestampDownloaded) && Objects.equals(opublikDate, that.opublikDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ddate, regNum, zakonNum, regDate, descr, zakOsnova, povestDate, controlDate, dateDeputat, datePrez, dateGu, dateProkuror, datePuZ, datePuP, datePuP2, datePu2Ch, datePuT, dateDeputatPrim, datePrezPrim, dateGuPrim, dateProkurorPrim, datePuZPrim, datePuPPrim, datePuP2Prim, datePu2ChPrim, datePuTPrim, status, prezidentPodpisDate, rkkReady, sessiaNum, sessiaDate, color, npp, prim, editDate, editorLogin, flagDeleted, flagDownloaded, timestampDownloaded, opublikDate);
    }
}
