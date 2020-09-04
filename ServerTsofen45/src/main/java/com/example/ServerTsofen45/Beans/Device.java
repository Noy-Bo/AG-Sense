package com.example.ServerTsofen45.Beans;

import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import Enums.DeviceType;

@Entity(name = "devices")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Device {
	int id;
	long imei;
	int accountId;
	DeviceType type;
	Time lastUpdate;
	String logitude;
	String altitude;
	boolean isRegistered;
	List<Notification> notifications;
	List<DeviceData> deviceData;

	 
	@Column
	public long getImei() {
		return imei;
	}
	
	public void setImei(long imei) {
		this.imei = imei;
	}
	@Column
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	@Column
	public DeviceType getType() {
		return type;
	}

	public void setType(DeviceType type) {
		this.type = type;
	}
	@Column
	public Time getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Time lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	@Column
	public String getLogitude() {
		return logitude;
	}

	public void setLogitude(String logitude) {
		this.logitude = logitude;
	}
	@Column
	public String getAltitude() {
		return altitude;
	}	

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}
	@Column
	public boolean isRegistered() {
		return isRegistered;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToMany
	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
	
	
	@OneToMany
	public List<DeviceData> getDeviceData() {
		return deviceData;
	}

	public void setDeviceData(List<DeviceData> deviceData) {
		this.deviceData = deviceData;
	}
	
	

	
	
}
