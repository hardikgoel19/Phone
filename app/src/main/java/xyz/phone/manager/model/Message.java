package xyz.phone.manager.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Message implements Serializable {

    private String id;
    private String messageDate;
    private String address;
    private String content;
    private String type;
    private String subject;
    private String status;
    private String read;
    private String phoneAccountId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getPhoneAccountId() {
        return phoneAccountId;
    }

    public void setPhoneAccountId(String phoneAccountId) {
        this.phoneAccountId = phoneAccountId;
    }
}
