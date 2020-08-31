package com.tsofen.agsenceapp.entities;

import com.tsofen.agsenceapp.utils.ColorStatus;

public class Notification {
    private String deviceName;
    private String deviceType;
    private String errorMessage;
    private String AccountName;
    private String dateTime;
    private ColorStatus imageStatus; // 1-4 for color.


    public Notification(String deviceName, String deviceType, String deviceStatus, String accountName, String dateTime, ColorStatus imageStatus) {
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.errorMessage = deviceStatus;
        AccountName = accountName;
        this.dateTime = dateTime;
        this.imageStatus = imageStatus;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public ColorStatus getImageStatus() {
        return imageStatus;
    }

    public void setImageStatus(ColorStatus imageStatus) {
        this.imageStatus = imageStatus;
    }
}
