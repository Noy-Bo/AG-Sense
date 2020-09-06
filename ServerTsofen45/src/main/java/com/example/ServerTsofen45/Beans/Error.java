package com.example.ServerTsofen45.Beans;


import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "errors")
public class Error {

	
	int Code;
	String message;
    private List<Notification> notifications;
	
	
	
	public void setCode(int code) {
		Code = code;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
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

	@OneToMany
	public List<Notification> getNotifications() {
		return notifications;
	}
	@Override
	public String toString() {
		return "Error [Code=" + Code + ", message=" + message + "]";
	}
	
	
	
	
	
	
	
}
