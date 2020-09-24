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
    protected Float logitude;  //
    protected Float latitude;  //
    protected List<DeviceData> deviceData;
    protected String phoneNumber;

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

    public Devices(Long imei, Integer id, Integer accountId, String type, String name, Timestamp faultTime, Timestamp lastUpdate, Boolean faulty, Float logitude, Float latitude, List<DeviceData> deviceData, String phoneNumber, List<Notification> notifications) {
        this.imei = imei;
        this.id = id;
        this.accountId = accountId;
        this.type = type;
        this.name = name;
        this.faultTime = faultTime;
        this.lastUpdate = lastUpdate;
        this.faulty = faulty;
        this.logitude = logitude;
        this.latitude = latitude;
        this.deviceData = deviceData;
        this.phoneNumber = phoneNumber;
        this.notifications = notifications;
    }

    public enum DeviceType {
        GPS("GpsForPersonal"),
        BANKS("SensorForBanks"),
        LIQUID("lequidHeightForTanks");

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


    public Float getLogitude() {
        return logitude;
    }

    public void setLogitude(Float logitude) {
        this.logitude = logitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
