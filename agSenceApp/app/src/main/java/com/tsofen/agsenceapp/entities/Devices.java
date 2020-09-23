package com.tsofen.agsenceapp.entities;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Devices implements Serializable {


    protected Long imei;
    protected Integer id;
    protected Integer accountId;
    protected String type;
    protected String name;
    protected Timestamp faultTime;  //
    protected Timestamp lastUpdate;
    protected Boolean faulty;
    protected Double logitude;  //
    protected Double latitude;  //
    protected List<DeviceData> deviceData;

    public List<DeviceData> getDeviceData() {
        return deviceData;
    }

    public void setDeviceData(List<DeviceData> deviceData) {
        this.deviceData = deviceData;
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

    public Devices() {
    }

    public Devices(long imei, int id, int accountId, String type, Timestamp faultTime, Timestamp lastUpdate, Boolean faulty, List<DeviceData> deviceData, List<Notification> notifications) {
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


    public Devices(long imei, int id, int accountId, String type, Timestamp faultTime, Timestamp lastUpdate, Boolean faulty) {
        this.imei = imei;
        this.id = id;
        this.accountId = accountId;
        this.type = type;
        this.faultTime = faultTime;
        this.lastUpdate = lastUpdate;
        this.faulty = faulty;
    }


    public enum DeviceType {
        GPS("GpsForPersonal"),
        STRING_TWO("SensorForBanks"),
        STRING_THREE("lequidHeightForTanks");

        private final String text;

        /**
         * @param text
         */
        DeviceType(final String text) {
            this.text = text;
        }

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString() {
            return text;
        }
    }

    public Long getImei() {
        return imei;
    }

    public void setImei(Long imei) {
        this.imei = imei;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getFaultTime() {
        return faultTime;
    }

    public void setFaultTime(Timestamp faultTime) {
        this.faultTime = faultTime;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    public Double getLogitude() {
        return logitude;
    }

    public void setLogitude(Double logitude) {
        this.logitude = logitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
