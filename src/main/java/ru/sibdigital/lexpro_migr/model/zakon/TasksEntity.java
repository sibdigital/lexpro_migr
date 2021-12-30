package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class TasksEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "docum_id")
    private Long documId;

    @Basic
    @Column(name = "fkind_id")
    private Long fkindId;

    @Basic
    @Column(name = "depart_id")
    private Long departId;

    @Basic
    @Column(name = "what2do")
    private String what2Do;

    @Basic
    @Column(name = "recenz_person")
    private Long recenzPerson;

    @Basic
    @Column(name = "plan_date")
    private Date planDate;

    @Basic
    @Column(name = "real_date")
    private Date realDate;

    @Basic
    @Column(name = "child_count")
    private Integer childCount;

    @Basic
    @Column(name = "not_ex_chld")
    private Integer notExChld;

    @Basic
    @Column(name = "executed")
    private String executed;

    @Basic
    @Column(name = "parent_task_id")
    private Long parentTaskId;

    @Basic
    @Column(name = "color")
    private String color;

    @Basic
    @Column(name = "pr_date")
    private Date prDate;

    @Basic
    @Column(name = "pr_descr")
    private String prDescr;

    @Basic
    @Column(name = "login")
    private String login;

    @Basic
    @Column(name = "edit_date")
    private Timestamp editDate;

    @Basic
    @Column(name = "editor_login")
    private String editorLogin;

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

    public Long getFkindId() {
        return fkindId;
    }

    public void setFkindId(Long fkindId) {
        this.fkindId = fkindId;
    }

    public Long getDepartId() {
        return departId;
    }

    public void setDepartId(Long departId) {
        this.departId = departId;
    }

    public String getWhat2Do() {
        return what2Do;
    }

    public void setWhat2Do(String what2Do) {
        this.what2Do = what2Do;
    }

    public Long getRecenzPerson() {
        return recenzPerson;
    }

    public void setRecenzPerson(Long recenzPerson) {
        this.recenzPerson = recenzPerson;
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public Date getRealDate() {
        return realDate;
    }

    public void setRealDate(Date realDate) {
        this.realDate = realDate;
    }

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    public Integer getNotExChld() {
        return notExChld;
    }

    public void setNotExChld(Integer notExChld) {
        this.notExChld = notExChld;
    }

    public String getExecuted() {
        return executed;
    }

    public void setExecuted(String executed) {
        this.executed = executed;
    }

    public Long getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(Long parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getPrDate() {
        return prDate;
    }

    public void setPrDate(Date prDate) {
        this.prDate = prDate;
    }

    public String getPrDescr() {
        return prDescr;
    }

    public void setPrDescr(String prDescr) {
        this.prDescr = prDescr;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TasksEntity that = (TasksEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(documId, that.documId) && Objects.equals(fkindId, that.fkindId) && Objects.equals(departId, that.departId) && Objects.equals(what2Do, that.what2Do) && Objects.equals(recenzPerson, that.recenzPerson) && Objects.equals(planDate, that.planDate) && Objects.equals(realDate, that.realDate) && Objects.equals(childCount, that.childCount) && Objects.equals(notExChld, that.notExChld) && Objects.equals(executed, that.executed) && Objects.equals(color, that.color) && Objects.equals(prDate, that.prDate) && Objects.equals(prDescr, that.prDescr) && Objects.equals(login, that.login) && Objects.equals(editDate, that.editDate) && Objects.equals(editorLogin, that.editorLogin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documId, fkindId, departId, what2Do, recenzPerson, planDate, realDate, childCount, notExChld, executed, color, prDate, prDescr, login, editDate, editorLogin);
    }
}
