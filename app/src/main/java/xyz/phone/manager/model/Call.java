package xyz.phone.manager.model;

import java.io.Serializable;

public class Call implements Serializable {

    private String id;
    private String name;
    private String callDate;
    private String duration;
    private String number;
    private String type;
    private String phoneAccountId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoneAccountId() {
        return phoneAccountId;
    }

    public void setPhoneAccountId(String phoneAccountId) {
        this.phoneAccountId = phoneAccountId;
    }

}
