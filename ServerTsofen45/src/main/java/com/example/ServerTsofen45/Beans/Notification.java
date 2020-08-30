package com.example.ServerTsofen45.Beans;

import javax.persistence.Entity;

import Emums.Severity;

@Entity
public class Notification {
	int Id;
	int UserId;
	int ErrorCode;
	String Message;
	Severity Sever;
	
	
	public Notification() {
		// TODO Auto-generated constructor stub
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public void setErrorCode(int errorCode) {
		ErrorCode = errorCode;
	}
	public void setId(int id) {
		Id = id;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public Severity getSever() {
		return Sever;
	}
	public int getErrorCode() {
		return ErrorCode;
	}
	@javax.persistence.Id
	public int getId() {
		return Id;
	}
	public void setSever(Severity sever) {
		Sever = sever;
	}
	public String getMessage() {
		return Message;
	}
	public int getUserId() {
		return UserId;
	}
}
