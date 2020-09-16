package com.tsofen.agsenceapp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
public class Devices implements Serializable {



    protected long imei;
    protected int id;
    protected int Userid;
    protected String deviceType;
    protected Date faultTime;
    protected Date lastUpdate;
    protected Boolean isFaulty;
    protected List<DeviceData> deviceData;
    protected List<Notification> notifications;

    public Boolean getFaulty() {
        return isFaulty;
    }

    public void setFaulty(Boolean faulty) {
        isFaulty = faulty;
    }
    public Devices()
    {


    }

    public Devices(long imei, int id, int userid, String deviceType, Date faultTime, Date lastUpdate, Boolean isFaulty, List<DeviceData> deviceData, List<Notification> notifications) {
        this.imei = imei;
        this.id = id;
        Userid = userid;
        this.deviceType = deviceType;
        this.faultTime = faultTime;
        this.lastUpdate = lastUpdate;
        this.isFaulty = isFaulty;
        this.deviceData = deviceData;
        this.notifications = notifications;
    }


    public Devices(long imei, int id, int userid, String deviceType, Date faultTime, Date lastUpdate, Boolean isFaulty) {
        this.imei = imei;
        this.id = id;
        Userid = userid;
        this.deviceType = deviceType;
        this.faultTime = faultTime;
        this.lastUpdate = lastUpdate;
        this.isFaulty = isFaulty;
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

    public int getUserid() {
        return Userid;
    }

    public void setUserid(int userid) {
        Userid = userid;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
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
}
