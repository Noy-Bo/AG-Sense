package com.Tsofen45.TCP_ServerTsofen45.notification;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "errors")
public class Error {

	
	int Code;
	String message;
	
	
	
	public void setCode(int code) {
		Code = code;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Column
	@Id
	public int getCode() {
		return Code;
	}
	@Column
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return "Error [Code=" + Code + ", message=" + message + "]";
	}
	
	
	
	
	
	
	
}
