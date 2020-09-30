package com.tsofen.agsenceapp.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class Devices implements Serializable {


    protected Long imei;
    protected Integer id;
    protected Integer accountId;
    protected String type;
    protected String name;
    protected Timestamp faultyTime;  //
    protected Timestamp lastUpdate;
    protected Boolean faulty;
    protected String logitude;  //
    protected String  latitude;  //
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

    public Devices(Long imei, Integer id, Integer accountId, String type, String name, Timestamp faultTime, Timestamp lastUpdate, Boolean faulty,
                   String logitude, String  latitude, List<DeviceData> deviceData, String phoneNumber, List<Notification> notifications) {
        this.imei = imei;
        this.id = id;
        this.accountId = accountId;
        this.type = type;
        this.name = name;
        this.faultyTime = faultTime;
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

    public Timestamp getFaultyTime() {
        return faultyTime;
    }

    public void setFaultyTime(Timestamp faultyTime) {
        this.faultyTime = faultyTime;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    public String getLogitude() {
        return logitude;
    }

    public void setLogitude(String logitude) {
        this.logitude = logitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Devices devices = (Devices) o;
        return name.equals(devices.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
