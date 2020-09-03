package com.example.ServerTsofen45.Beans;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import Enums.Severity;

@Entity
@Table(name = "notifications")
public class Notification {
	int id;
	int deviceId;
	long deviceImei;
	int userId;
	String dateTime;
	Severity severity;
	boolean readed;
	private Error error;
	
	
	
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public void setDeviceImei(long deviceImei) {
		this.deviceImei = deviceImei;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public void setSeverity(Severity severity) {
		this.severity = severity;
	}
	public void setReaded(boolean readed) {
		this.readed = readed;
	}
	public void setError(Error error) {
		this.error = error;
	}
	
	
	@Column
	@Id
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
	public Severity getSeverity() {
		return severity;
	}
	@Column
	public String getDateTime() {
		return dateTime;
	}
	@Column
	public boolean isReaded() {
		return readed;
	}
	
	@ManyToOne
    @JoinColumn(name = "errorCode")   
	public Error getError() {
		return error;
	}
	
	
	
	
	
	
	
}
