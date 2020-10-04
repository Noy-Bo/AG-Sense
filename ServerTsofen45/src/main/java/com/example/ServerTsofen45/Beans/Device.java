package com.example.ServerTsofen45.Beans;

import java.beans.Transient;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import Enums.DeviceType;

@Entity(name = "devices")

public class Device {
	int id;
	long imei;
	
	String name;
	int accountId;
	DeviceType type;
	Timestamp lastUpdate;
	String logitude;
	String latitude;
	boolean isRegistered;
	boolean isFaulty;
	Timestamp faultyTime;
	String phoneNumber;
	String password;
	List<Notification> notifications;
	List<DeviceData> deviceData;

	public Device() {
		super();
	}

	public Device(int id, long imei, String name, int accountId, DeviceType type, Timestamp lastUpdate, String logitude,
			String altitude, boolean isRegistered, List<Notification> notifications, List<DeviceData> deviceData) {
		super();
		this.id = id;
		this.imei = imei;
		this.name = name;
		this.accountId = accountId;
		this.type = type;
		this.lastUpdate = lastUpdate;
		this.logitude = logitude;
		this.latitude = altitude;
		this.isRegistered = isRegistered;
		this.notifications = notifications;
		this.deviceData = deviceData;
	}

	
	
	
	
	
	public Device(long imei, int accountId, DeviceType type, String phoneNumber, String password) {
		super();
		this.imei = imei;
		this.accountId = accountId;
		this.type = type;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}
	
	
	@Column
	public long getImei() {
		return imei;
	}

	public void setImei(long imei) {
		this.imei = imei;
	}
	@Column
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
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
	public boolean isRegistered() {
		return isRegistered;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Transient
	@OneToMany
	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
	@Transient
	@OneToMany
	public List<DeviceData> getDeviceData() {
		return deviceData;
	}

	public void setDeviceData(List<DeviceData> deviceData) {
		this.deviceData = deviceData;
	}

	
	
	@Column
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column
	public boolean isFaulty() {
		return isFaulty;
	}

	public void setFaulty(boolean isFaulty) {
		this.isFaulty = isFaulty;
	}

	
	@Column
	public Timestamp getFaultyTime() {
		return faultyTime;
	}

	public void setFaultyTime(Timestamp faultyTime) {
		this.faultyTime = faultyTime;
	}

	@Column
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", imei=" + imei + ", name=" + name + ", accountId=" + accountId + ", type=" + type
				+ ", lastUpdate=" + lastUpdate + ", logitude=" + logitude + ", altitude=" + latitude + ", isRegistered="
				+ isRegistered + ", notifications=" + notifications + ", deviceData=" + deviceData + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + ((deviceData == null) ? 0 : deviceData.hashCode());
		result = prime * result + ((faultyTime == null) ? 0 : faultyTime.hashCode());
		result = prime * result + id;
		result = prime * result + (int) (imei ^ (imei >>> 32));
		result = prime * result + (isFaulty ? 1231 : 1237);
		result = prime * result + (isRegistered ? 1231 : 1237);
		result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((logitude == null) ? 0 : logitude.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notifications == null) ? 0 : notifications.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Device other = (Device) obj;
		if (accountId != other.accountId)
			return false;
		if (deviceData == null) {
			if (other.deviceData != null)
				return false;
		} else if (!deviceData.equals(other.deviceData))
			return false;
		if (faultyTime == null) {
			if (other.faultyTime != null)
				return false;
		} else if (!faultyTime.equals(other.faultyTime))
			return false;
		if (id != other.id)
			return false;
		if (imei != other.imei)
			return false;
		if (isFaulty != other.isFaulty)
			return false;
		if (isRegistered != other.isRegistered)
			return false;
		if (lastUpdate == null) {
			if (other.lastUpdate != null)
				return false;
		} else if (!lastUpdate.equals(other.lastUpdate))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (logitude == null) {
			if (other.logitude != null)
				return false;
		} else if (!logitude.equals(other.logitude))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (notifications == null) {
			if (other.notifications != null)
				return false;
		} else if (!notifications.equals(other.notifications))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	

}