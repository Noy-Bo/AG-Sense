package com.tsofen.agsenceapp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
public class Devices implements Serializable {



    protected long imei;
    protected int id;
    protected int accountId;
    protected String type;
    protected String name;
    protected Date faultTime;  //
    protected Date lastUpdate;
    protected Boolean faulty;
    protected  double logitude;  //
    protected  double latitude;  //
    protected List<DeviceData> deviceData;

    public List<DeviceData> getDeviceData() {
        return deviceData;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    protected List<Notification> notifications;

    public Boolean getFaulty() {
        return faulty;
    }

    public void setFaulty(Boolean faulty) {
        this.faulty = faulty;
    }
    public Devices()
    {


    }

    public Devices(long imei, int id, int accountId, String type, Date faultTime, Date lastUpdate, Boolean faulty, List<DeviceData> deviceData, List<Notification> notifications) {
        this.imei = imei;
        this.id = id;
        this.accountId = accountId;
        this.type = type;
        this.faultTime = faultTime;
        this.lastUpdate = lastUpdate;
        this.faulty = faulty;
        this.deviceData = deviceData;
        this.notifications = notifications;
    }


    public Devices(long imei, int id, int accountId, String type, Date faultTime, Date lastUpdate, Boolean faulty) {
        this.imei = imei;
        this.id = id;
        this.accountId = accountId;
        this.type = type;
        this.faultTime = faultTime;
        this.lastUpdate = lastUpdate;
        this.faulty = faulty;
    }

    public long getImei() {
        return imei;
    }

    public void setImei(long imei) {
        this.imei = imei;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getFaultTime() {
        return faultTime;
    }

    public void setFaultTime(Date faultTime) {
        this.faultTime = faultTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    public double getLogitude() {
        return logitude;
    }

    public void setLogitude(double logitude) {
        this.logitude = logitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
