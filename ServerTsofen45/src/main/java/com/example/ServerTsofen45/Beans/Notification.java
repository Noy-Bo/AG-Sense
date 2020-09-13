package com.example.ServerTsofen45.Beans;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;

import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import Enums.Severity;

@Entity
@Table(name = "notifications")
public class Notification {
	private int id;
	private Device device;
	private int userId;
	private Timestamp dateTime;
	private Severity severity;
	private boolean readed;
	private Error error;
	
	
	
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setDateTime(Timestamp dateTime) {
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
	
	@ManyToOne
	@JoinColumns({
	    @JoinColumn(name="deviceId", referencedColumnName="id"),
	    @JoinColumn(name="deviceImei", referencedColumnName="imei")})
	public Device getDevice() {
		return device;
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
	public Timestamp getDateTime() {
		return dateTime;
	}
	@Column
	public boolean isReaded() {
		return readed;
	}
	
	@ManyToOne
    @JoinColumn(name = "errorCode", referencedColumnName="code")   
	public Error getError() {
		return error;
	}
	
	@Override
	public String toString() {
		return "Notification [id=" + id + ", device=" + device + ", userId=" + userId + ", dateTime=" + dateTime
				+ ", severity=" + severity + ", readed=" + readed + ", error=" + error + "]";
	}
	
	
	
	
	
	
	
	
	
}