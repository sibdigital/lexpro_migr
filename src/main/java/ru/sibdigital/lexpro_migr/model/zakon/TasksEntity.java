package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "TASKS", schema = "", catalog = "")
public class TasksEntity {
    private int id;
    private String what2Do;
    private Date planDate;
    private Date realDate;
    private Integer childCount;
    private Integer notExChld;
    private String executed;
    private String color;
    private Date prDate;
    private String prDescr;
    private String login;
    private Timestamp editDate;
    private String editorLogin;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "WHAT2DO")
    public String getWhat2Do() {
        return what2Do;
    }

    public void setWhat2Do(String what2Do) {
        this.what2Do = what2Do;
    }

    @Basic
    @Column(name = "PLAN_DATE")
    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    @Basic
    @Column(name = "REAL_DATE")
    public Date getRealDate() {
        return realDate;
    }

    public void setRealDate(Date realDate) {
        this.realDate = realDate;
    }

    @Basic
    @Column(name = "CHILD_COUNT")
    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    @Basic
    @Column(name = "NOT_EX_CHLD")
    public Integer getNotExChld() {
        return notExChld;
    }

    public void setNotExChld(Integer notExChld) {
        this.notExChld = notExChld;
    }

    @Basic
    @Column(name = "EXECUTED")
    public String getExecuted() {
        return executed;
    }

    public void setExecuted(String executed) {
        this.executed = executed;
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
    @Column(name = "PR_DATE")
    public Date getPrDate() {
        return prDate;
    }

    public void setPrDate(Date prDate) {
        this.prDate = prDate;
    }

    @Basic
    @Column(name = "PR_DESCR")
    public String getPrDescr() {
        return prDescr;
    }

    public void setPrDescr(String prDescr) {
        this.prDescr = prDescr;
    }

    @Basic
    @Column(name = "LOGIN")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TasksEntity that = (TasksEntity) o;
        return id == that.id && Objects.equals(what2Do, that.what2Do) && Objects.equals(planDate, that.planDate) && Objects.equals(realDate, that.realDate) && Objects.equals(childCount, that.childCount) && Objects.equals(notExChld, that.notExChld) && Objects.equals(executed, that.executed) && Objects.equals(color, that.color) && Objects.equals(prDate, that.prDate) && Objects.equals(prDescr, that.prDescr) && Objects.equals(login, that.login) && Objects.equals(editDate, that.editDate) && Objects.equals(editorLogin, that.editorLogin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, what2Do, planDate, realDate, childCount, notExChld, executed, color, prDate, prDescr, login, editDate, editorLogin);
    }
}
