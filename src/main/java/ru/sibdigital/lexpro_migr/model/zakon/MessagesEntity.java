package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "messages")
public class MessagesEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "text")
    private String text;

    @Basic
    @Column(name = "sender_id")
    private Long senderId;

    @Basic
    @Column(name = "reciever_id")
    private Long recieverId;

    @Basic
    @Column(name = "message_date_time")
    private Timestamp messageDateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(Long recieverId) {
        this.recieverId = recieverId;
    }

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
        return Objects.equals(id, that.id) && Objects.equals(text, that.text) && Objects.equals(senderId, that.senderId) && Objects.equals(recieverId, that.recieverId) && Objects.equals(messageDateTime, that.messageDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, senderId, recieverId, messageDateTime);
    }
}
