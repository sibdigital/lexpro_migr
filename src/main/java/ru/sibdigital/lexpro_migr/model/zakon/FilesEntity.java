package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "FILES", schema = "", catalog = "")
public class FilesEntity {
    private int id;
    private String fname;
    private String fext;
    private byte[] fdata;
    private String md5Sum;
    private Date zdate;
    private String dnum;
    private Integer pageCount;
    private Timestamp editDate;
    private String editorLogin;
    private String zdateX;
    private String dnumX;
    private String fnameX;
    private String zipped;
    private String flagArch;
    private String flagLock;
    private String flagEcp;
    private String ecpDescr;
    private String status;
    private Timestamp statusChangeTimestamp;
    private String statusChangeTimestampStr;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FNAME")
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    @Basic
    @Column(name = "FEXT")
    public String getFext() {
        return fext;
    }

    public void setFext(String fext) {
        this.fext = fext;
    }

    @Basic
    @Column(name = "FDATA")
    public byte[] getFdata() {
        return fdata;
    }

    public void setFdata(byte[] fdata) {
        this.fdata = fdata;
    }

    @Basic
    @Column(name = "MD5SUM")
    public String getMd5Sum() {
        return md5Sum;
    }

    public void setMd5Sum(String md5Sum) {
        this.md5Sum = md5Sum;
    }

    @Basic
    @Column(name = "ZDATE")
    public Date getZdate() {
        return zdate;
    }

    public void setZdate(Date zdate) {
        this.zdate = zdate;
    }

    @Basic
    @Column(name = "DNUM")
    public String getDnum() {
        return dnum;
    }

    public void setDnum(String dnum) {
        this.dnum = dnum;
    }

    @Basic
    @Column(name = "PAGE_COUNT")
    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
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
    @Column(name = "ZDATE_X")
    public String getZdateX() {
        return zdateX;
    }

    public void setZdateX(String zdateX) {
        this.zdateX = zdateX;
    }

    @Basic
    @Column(name = "DNUM_X")
    public String getDnumX() {
        return dnumX;
    }

    public void setDnumX(String dnumX) {
        this.dnumX = dnumX;
    }

    @Basic
    @Column(name = "FNAME_X")
    public String getFnameX() {
        return fnameX;
    }

    public void setFnameX(String fnameX) {
        this.fnameX = fnameX;
    }

    @Basic
    @Column(name = "ZIPPED")
    public String getZipped() {
        return zipped;
    }

    public void setZipped(String zipped) {
        this.zipped = zipped;
    }

    @Basic
    @Column(name = "FLAG_ARCH")
    public String getFlagArch() {
        return flagArch;
    }

    public void setFlagArch(String flagArch) {
        this.flagArch = flagArch;
    }

    @Basic
    @Column(name = "FLAG_LOCK")
    public String getFlagLock() {
        return flagLock;
    }

    public void setFlagLock(String flagLock) {
        this.flagLock = flagLock;
    }

    @Basic
    @Column(name = "FLAG_ECP")
    public String getFlagEcp() {
        return flagEcp;
    }

    public void setFlagEcp(String flagEcp) {
        this.flagEcp = flagEcp;
    }

    @Basic
    @Column(name = "ECP_DESCR")
    public String getEcpDescr() {
        return ecpDescr;
    }

    public void setEcpDescr(String ecpDescr) {
        this.ecpDescr = ecpDescr;
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
    @Column(name = "STATUS_CHANGE_TIMESTAMP")
    public Timestamp getStatusChangeTimestamp() {
        return statusChangeTimestamp;
    }

    public void setStatusChangeTimestamp(Timestamp statusChangeTimestamp) {
        this.statusChangeTimestamp = statusChangeTimestamp;
    }

    @Basic
    @Column(name = "STATUS_CHANGE_TIMESTAMP_STR")
    public String getStatusChangeTimestampStr() {
        return statusChangeTimestampStr;
    }

    public void setStatusChangeTimestampStr(String statusChangeTimestampStr) {
        this.statusChangeTimestampStr = statusChangeTimestampStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilesEntity that = (FilesEntity) o;
        return id == that.id && Objects.equals(fname, that.fname) && Objects.equals(fext, that.fext) && Arrays.equals(fdata, that.fdata) && Objects.equals(md5Sum, that.md5Sum) && Objects.equals(zdate, that.zdate) && Objects.equals(dnum, that.dnum) && Objects.equals(pageCount, that.pageCount) && Objects.equals(editDate, that.editDate) && Objects.equals(editorLogin, that.editorLogin) && Objects.equals(zdateX, that.zdateX) && Objects.equals(dnumX, that.dnumX) && Objects.equals(fnameX, that.fnameX) && Objects.equals(zipped, that.zipped) && Objects.equals(flagArch, that.flagArch) && Objects.equals(flagLock, that.flagLock) && Objects.equals(flagEcp, that.flagEcp) && Objects.equals(ecpDescr, that.ecpDescr) && Objects.equals(status, that.status) && Objects.equals(statusChangeTimestamp, that.statusChangeTimestamp) && Objects.equals(statusChangeTimestampStr, that.statusChangeTimestampStr);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, fname, fext, md5Sum, zdate, dnum, pageCount, editDate, editorLogin, zdateX, dnumX, fnameX, zipped, flagArch, flagLock, flagEcp, ecpDescr, status, statusChangeTimestamp, statusChangeTimestampStr);
        result = 31 * result + Arrays.hashCode(fdata);
        return result;
    }
}
