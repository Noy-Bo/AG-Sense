package com.example.ServerTsofen45.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;

import Enums.Errors;
import Enums.Severity;

@Entity
public class Notification {
	int Id;
	int UserId;
	Errors ErrorCode;
	String Message;
	Severity Sever;
	boolean Read_stat;
	
	
	public Notification() {
		// TODO Auto-generated constructor stub
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public void setErrorCode(Errors errorCode) {
		ErrorCode = errorCode;
	}
	public void setId(int id) {
		Id = id;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public void setRead_stat(boolean read_stat) {
		this.Read_stat = read_stat;
	}
	@Column
	public boolean getRead_stat() {
		return Read_stat;
	}
	@Column
	public Severity getSever() {
		return Sever;
	}
	@Column
	public Errors getErrorCode() {
		return ErrorCode;
	}
	@Column
	@javax.persistence.Id
	public int getId() {
		return Id;
	}
	public void setSever(Severity sever) {
		Sever = sever;
	}
	@Column
	public String getMessage() {
		return Message;
	}
	@Column
	public int getUserId() {
		return UserId;
	}
}
