package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "protokol")
public class ProtokolEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "docum_id")
    private Long documId;

    @Basic
    @Column(name = "type")
    private String type;

    @Basic
    @Column(name = "field_name")
    private String fieldName;

    @Basic
    @Column(name = "old_value")
    private String oldValue;

    @Basic
    @Column(name = "new_value")
    private String newValue;

    @Basic
    @Column(name = "edit_date")
    private Timestamp editDate;

    @Basic
    @Column(name = "editor_login")
    private String editorLogin;

    @Basic
    @Column(name = "dns_name")
    private String dnsName;

    @Basic
    @Column(name = "enter_mode")
    private String enterMode;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
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

    public String getDnsName() {
        return dnsName;
    }

    public void setDnsName(String dnsName) {
        this.dnsName = dnsName;
    }

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
