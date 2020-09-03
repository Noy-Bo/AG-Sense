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

	
	int errorCode;
	String message;
    private List<Notification> notifications;
	
	
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
	
	@Column
	@Id
	public int getErrorCode() {
		return errorCode;
	}
	@Column
	public String getMessage() {
		return message;
	}

	@OneToMany
	public List<Notification> getNotifications() {
		return notifications;
	}
	
	
	
	
}
