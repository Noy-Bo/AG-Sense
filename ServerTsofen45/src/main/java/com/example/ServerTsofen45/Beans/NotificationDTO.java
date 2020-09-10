package com.example.ServerTsofen45.Beans;

import java.sql.Time;

import Enums.Severity;

public interface  NotificationDTO {

	int getId();
	long getDevice_id() ;
	long getDevice_imei();
	int getUser_id();
	Time getDate_time();
	Severity getSeverity();
	boolean getReaded();
	int getCode();
	String getMessage();

	
	
}