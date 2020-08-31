package com.tsofen.agsenceapp.entities;

import java.io.Serializable;

public class Devices implements Serializable {
    protected int ID;
    protected int deviceTypeID;
    protected int accountID;
    protected boolean isFaulty;
    String faultTime;
    String name;
    long imei;
    String lastUpdate;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getDeviceTypeID() {
        return deviceTypeID;
    }

    public void setDeviceTypeID(int deviceTypeID) {
        this.deviceTypeID = deviceTypeID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public boolean isFaulty() {
        return isFaulty;
    }

    public void setFaulty(boolean faulty) {
        isFaulty = faulty;
    }

    public String getFaultTime() {
        return faultTime;
    }

    public void setFaultTime(String faultTime) {
        this.faultTime = faultTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getImei() {
        return imei;
    }

    public void setImei(long imei) {
        this.imei = imei;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Devices(int ID, int deviceTypeID, int accountID, boolean isFaulty, String faultTime, String name, long imei, String lastUpdate) {
        this.ID = ID;
        this.deviceTypeID = deviceTypeID;
        this.accountID = accountID;
        this.isFaulty = isFaulty;
        this.faultTime = faultTime;
        this.name = name;
        this.imei = imei;
        this.lastUpdate = lastUpdate;
    }
}
