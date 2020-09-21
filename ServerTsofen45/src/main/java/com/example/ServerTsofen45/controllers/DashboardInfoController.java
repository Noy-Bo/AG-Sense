package com.example.ServerTsofen45.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ServerTsofen45.BL.DeviceBL;
import com.example.ServerTsofen45.BL.NotificationBL;

@RestController
@RequestMapping("Dashboard")
public class DashboardInfoController {
	
	@Autowired
	NotificationBL notificationBL;
	@Autowired
	DeviceBL deviceBL;
	
	
	
	@GetMapping("AdminDashboardInfo")
	public String  getAllDevices() {
		
		String faultyAccountsNumber  = deviceBL.faultyAccountsNumber() ;
		String healtyAccountsNumber = deviceBL.healtyAccountsNumber();
		String faultyDevicesNumber = deviceBL.faultyDevicesNumber();
		String healtyDevicesNumber = deviceBL.healtyDevicesNumber();
		String unreadNotificationsNumber = notificationBL.unreadNotificationsNumber();
		
		return "{\"faultyAccountsNumber\":" +faultyAccountsNumber+ ",\"healtyAccountsNumber\":" +healtyAccountsNumber +
				",\"faultyDevicesNumber\":" + faultyDevicesNumber+ ",\"healtyDevicesNumber\":" + healtyDevicesNumber +
				",\"unreadNotificationsNumber\":" + unreadNotificationsNumber +"}";
		
		
	}
	
	

}
