package com.example.ServerTsofen45.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import Enums.ErrorCode;
import Enums.Severity;

@Entity
public class Notification {
	int Id;
	int DeviceId;
	long DeviceImei;
	int UserId;
	ErrorCode errorCode;
	String Message;
	Severity severity;
	boolean Read_stat;
	
	
	
	public void setUserId(int userId) {
		UserId = userId;
	}
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	public void setId(int id) {
		Id = id;
	}
	public void setDeviceId(int deviceId) {
		DeviceId = deviceId;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public void setRead_stat(boolean read_stat) {
		this.Read_stat = read_stat;
	}
	public void setDeviceImei(long iMEI) {
		DeviceImei = iMEI;
	}
	public void setSeverity(Severity severity) {
		this.severity = severity;
	}
	
	@Column
	@javax.persistence.Id
	@GeneratedValue
	public int getId() {
		return Id;
	}
	@Column
	public int getDeviceId() {
		return DeviceId;
	}
	@Column
	public long getDeviceImei() {
		return DeviceImei;
	}
	@Column
	public int getUserId() {
		return UserId;
	}
	@Column
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	@Column
	public String getMessage() {
		return Message;
	}
	@Column
	public Severity getSeverity() {
		return severity;
	}
	@Column
	public boolean getRead_stat() {
		return Read_stat;
	}
	
	

	
	
	
	
	
}
