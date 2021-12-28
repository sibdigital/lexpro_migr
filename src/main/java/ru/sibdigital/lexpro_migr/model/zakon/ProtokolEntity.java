package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "PROTOKOL", schema = "", catalog = "")
public class ProtokolEntity {
    private int id;
    private Integer documId;
    private String type;
    private String fieldName;
    private String oldValue;
    private String newValue;
    private Timestamp editDate;
    private String editorLogin;
    private String dnsName;
    private String enterMode;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DOCUM_ID")
    public Integer getDocumId() {
        return documId;
    }

    public void setDocumId(Integer documId) {
        this.documId = documId;
    }

    @Basic
    @Column(name = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "FIELD_NAME")
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Basic
    @Column(name = "OLD_VALUE")
    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    @Basic
    @Column(name = "NEW_VALUE")
    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
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
    @Column(name = "DNS_NAME")
    public String getDnsName() {
        return dnsName;
    }

    public void setDnsName(String dnsName) {
        this.dnsName = dnsName;
    }

    @Basic
    @Column(name = "ENTER_MODE")
    public String getEnterMode() {
        return enterMode;
    }

    public void setEnterMode(String enterMode) {
        this.enterMode = enterMode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProtokolEntity that = (ProtokolEntity) o;
        return id == that.id && Objects.equals(documId, that.documId) && Objects.equals(type, that.type) && Objects.equals(fieldName, that.fieldName) && Objects.equals(oldValue, that.oldValue) && Objects.equals(newValue, that.newValue) && Objects.equals(editDate, that.editDate) && Objects.equals(editorLogin, that.editorLogin) && Objects.equals(dnsName, that.dnsName) && Objects.equals(enterMode, that.enterMode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documId, type, fieldName, oldValue, newValue, editDate, editorLogin, dnsName, enterMode);
    }
}
