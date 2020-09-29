package com.Tsofen45.TCP_ServerTsofen45.Device;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import com.Tsofen45.TCP_ServerTsofen45.ENUMS.DeviceType;
import com.Tsofen45.TCP_ServerTsofen45.notification.Notification;





@Entity(name = "devices")
public class Device {
	int id;
	long imei;
	String name;
	int accountId;
	DeviceType type;
	Timestamp lastUpdate;
	double logitude;
	double latitude;
	boolean isRegistered;
	boolean isFaulty;
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
	public double getLogitude() {
		return logitude;
	}

	public void setLogitude(double logitude) {
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

	
	
	@Column
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Column
	public boolean isFaulty() {
		return isFaulty;
	}

	public void setFaulty(boolean isFaulty) {
		this.isFaulty = isFaulty;
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
		result = prime * result + id;
		result = prime * result + (int) (imei ^ (imei >>> 32));
		result = prime * result + (isRegistered ? 1231 : 1237);
		result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(logitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notifications == null) ? 0 : notifications.hashCode());
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
		if (id != other.id)
			return false;
		if (imei != other.imei)
			return false;
		if (isRegistered != other.isRegistered)
			return false;
		if (lastUpdate == null) {
			if (other.lastUpdate != null)
				return false;
		} else if (!lastUpdate.equals(other.lastUpdate))
			return false;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(logitude) != Double.doubleToLongBits(other.logitude))
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
		if (type != other.type)
			return false;
		return true;
	}

}