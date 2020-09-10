package com.tsofen.agsenceapp.entities;

import com.tsofen.agsenceapp.utils.ColorStatus;

import java.io.Serializable;
import java.util.Date;

public class Notification implements Serializable {
    private  ColorStatus imageStatus; //1-4 for color
    protected int id;
    protected long Deviceimei;
    protected int Deviceid;
    protected int Userid;
    protected Date dateTime;
    protected int severity;
    protected boolean isRead;
    protected String errorMessage;
    protected int errorCode;


    public ColorStatus getImageStatus() {
        return imageStatus;
    }

    public Notification(int id, long deviceimei, int deviceid, int userid, Date dateTime, int severity, boolean isRead, String errorMessage, int errorCode) {
        this.id = id;
        Deviceimei = deviceimei;
        Deviceid = deviceid;
        Userid = userid;
        this.dateTime = dateTime;
        this.severity = severity;
        this.isRead = isRead;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDeviceimei() {
        return Deviceimei;
    }

    public void setDeviceimei(long deviceimei) {
        Deviceimei = deviceimei;
    }

    public int getDeviceid() {
        return Deviceid;
    }

    public void setDeviceid(int deviceid) {
        Deviceid = deviceid;
    }

    public int getUserid() {
        return Userid;
    }

    public void setUserid(int userid) {
        Userid = userid;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
