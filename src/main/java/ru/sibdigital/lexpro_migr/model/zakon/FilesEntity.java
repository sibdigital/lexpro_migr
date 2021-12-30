package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "files")
public class FilesEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "docum_id")
    private Long documId;

    @Basic
    @Column(name = "org_id")
    private Long orgId;

    @Basic
    @Column(name = "fgroup_id")
    private Long fgroupId;

    @Basic
    @Column(name = "fkind_id")
    private Long fkindId;

    @Basic
    @Column(name = "fname")
    private String fname;

    @Basic
    @Column(name = "fext")
    private String fext;

    @Basic
    @Column(name = "fdata")
    private byte[] fdata;

    @Basic
    @Column(name = "md5sum")
    private String md5Sum;

    @Basic
    @Column(name = "zdate")
    private Date zdate;

    @Basic
    @Column(name = "dnum")
    private String dnum;

    @Basic
    @Column(name = "page_count")
    private Integer pageCount;

    @Basic
    @Column(name = "edit_date")
    private Timestamp editDate;

    @Basic
    @Column(name = "editor_login")
    private String editorLogin;

    @Basic
    @Column(name = "zdate_x")
    private String zdateX;

    @Basic
    @Column(name = "dnum_x")
    private String dnumX;

    @Basic
    @Column(name = "fname_x")
    private String fnameX;

    @Basic
    @Column(name = "zipped")
    private String zipped;

    @Basic
    @Column(name = "flag_arch")
    private String flagArch;

    @Basic
    @Column(name = "flag_lock")
    private String flagLock;

    @Basic
    @Column(name = "flag_ecp")
    private String flagEcp;

    @Basic
    @Column(name = "ecp_descr")
    private String ecpDescr;

    @Basic
    @Column(name = "status")
    private String status;

    @Basic
    @Column(name = "status_change_timestamp")
    private Timestamp statusChangeTimestamp;


    @Basic
    @Column(name = "status_change_timestamp_str")
    private String statusChangeTimestampStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocumId() {
        return documId;
    }

    public void setDocumId(Long documId) {
        this.documId = documId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getFgroupId() {
        return fgroupId;
    }

    public void setFgroupId(Long fgroupId) {
        this.fgroupId = fgroupId;
    }

    public Long getFkindId() {
        return fkindId;
    }

    public void setFkindId(Long fkindId) {
        this.fkindId = fkindId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFext() {
        return fext;
    }

    public void setFext(String fext) {
        this.fext = fext;
    }

    public byte[] getFdata() {
        return fdata;
    }

    public void setFdata(byte[] fdata) {
        this.fdata = fdata;
    }

    public String getMd5Sum() {
        return md5Sum;
    }

    public void setMd5Sum(String md5Sum) {
        this.md5Sum = md5Sum;
    }

    public Date getZdate() {
        return zdate;
    }

    public void setZdate(Date zdate) {
        this.zdate = zdate;
    }

    public String getDnum() {
        return dnum;
    }

    public void setDnum(String dnum) {
        this.dnum = dnum;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
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

    public String getZdateX() {
        return zdateX;
    }

    public void setZdateX(String zdateX) {
        this.zdateX = zdateX;
    }

    public String getDnumX() {
        return dnumX;
    }

    public void setDnumX(String dnumX) {
        this.dnumX = dnumX;
    }

    public String getFnameX() {
        return fnameX;
    }

    public void setFnameX(String fnameX) {
        this.fnameX = fnameX;
    }

    public String getZipped() {
        return zipped;
    }

    public void setZipped(String zipped) {
        this.zipped = zipped;
    }

    public String getFlagArch() {
        return flagArch;
    }

    public void setFlagArch(String flagArch) {
        this.flagArch = flagArch;
    }

    public String getFlagLock() {
        return flagLock;
    }

    public void setFlagLock(String flagLock) {
        this.flagLock = flagLock;
    }

    public String getFlagEcp() {
        return flagEcp;
    }

    public void setFlagEcp(String flagEcp) {
        this.flagEcp = flagEcp;
    }

    public String getEcpDescr() {
        return ecpDescr;
    }

    public void setEcpDescr(String ecpDescr) {
        this.ecpDescr = ecpDescr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getStatusChangeTimestamp() {
        return statusChangeTimestamp;
    }

    public void setStatusChangeTimestamp(Timestamp statusChangeTimestamp) {
        this.statusChangeTimestamp = statusChangeTimestamp;
    }

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
