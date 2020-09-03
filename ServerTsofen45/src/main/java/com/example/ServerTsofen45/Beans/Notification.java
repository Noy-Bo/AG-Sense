package com.example.ServerTsofen45.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import Enums.Errors;
import Enums.Severity;

@Entity
public class Notification {
	int id;
	int deviceId;
	long deviceImei;
	int userId;
	Errors errorCode;
	String message;
	Severity severity;
	boolean read_stat;
	
	
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setErrorCode(Errors errorCode) {
		this.errorCode = errorCode;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setRead_stat(boolean read_stat) {
		this.read_stat = read_stat;
	}
	public void setDeviceImei(long iMEI) {
		deviceImei = iMEI;
	}
	public void setSeverity(Severity severity) {
		this.severity = severity;
	}
	
	@Column
	@javax.persistence.Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	@Column
	public int getDeviceId() {
		return deviceId;
	}
	@Column
	public long getDeviceImei() {
		return deviceImei;
	}
	@Column
	public int getUserId() {
		return userId;
	}
	@Column
	public Errors getErrorCode() {
		return errorCode;
	}
	@Column
	public String getMessage() {
		return message;
	}
	@Column
	public Severity getSeverity() {
		return severity;
	}
	@Column
	public boolean getRead_stat() {
		return read_stat;
	}
	
	

	
	
	
	
	
}
