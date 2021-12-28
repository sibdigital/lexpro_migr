package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "MESSAGES", schema = "", catalog = "")
public class MessagesEntity {
    private int id;
    private String text;
    private Timestamp messageDateTime;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TEXT")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "MESSAGE_DATE_TIME")
    public Timestamp getMessageDateTime() {
        return messageDateTime;
    }

    public void setMessageDateTime(Timestamp messageDateTime) {
        this.messageDateTime = messageDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessagesEntity that = (MessagesEntity) o;
        return id == that.id && Objects.equals(text, that.text) && Objects.equals(messageDateTime, that.messageDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, messageDateTime);
    }
}
