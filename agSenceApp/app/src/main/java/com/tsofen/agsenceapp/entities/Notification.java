package com.tsofen.agsenceapp.entities;

import com.tsofen.agsenceapp.utils.Severity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Notification implements Serializable {
    protected Integer id;
    protected Long device_imei;
    protected Integer device_id;
    protected Integer user_id;
    protected Timestamp date_time;
    protected Severity severity;
    protected Boolean readed;
    protected String message;
    protected Integer code;


    public Notification(){}


    public Notification(Integer id, Long device_imei, Integer device_id, Integer user_id, Timestamp date_time, Severity severity, Boolean readed, String message, Integer code) {
        this.id = id;
        this.device_imei = device_imei;
        this.device_id = device_id;
        this.user_id = user_id;
        this.date_time = date_time;
        this.severity = severity;
        this.readed = readed;
        this.message = message;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDevice_imei() {
        return device_imei;
    }

    public void setDevice_imei(Long device_imei) {
        this.device_imei = device_imei;
    }

    public Integer getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Integer device_id) {
        this.device_id = device_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Timestamp getDate_time() {
        return date_time;
    }

    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public Boolean getReaded() {
        return readed;
    }

    public void setReaded(Boolean readed) {
        this.readed = readed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
