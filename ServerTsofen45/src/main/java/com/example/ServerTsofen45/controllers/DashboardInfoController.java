package com.example.ServerTsofen45.controllers;

import javax.tools.JavaFileObject;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	//http://localhost:8080/Dashboard/AdminDashboardInfo?id=1
	@SuppressWarnings("unchecked")
	@GetMapping("AdminDashboardInfo")
	public JSONObject  getAllDevices(@RequestParam int id) {
		
		String faultyAccountsNumber  = deviceBL.faultyAccountsNumber() ;
		String healtyAccountsNumber = deviceBL.healtyAccountsNumber();
		String faultyDevicesNumber = deviceBL.faultyDevicesNumber();
		String healtyDevicesNumber = deviceBL.healtyDevicesNumber();
		String unreadNotificationsNumber = notificationBL.unreadNotificationsNumber(id);
		
		JSONObject params = new JSONObject();
		params.put("faultyAccountsNumber", faultyAccountsNumber);
		params.put("healtyAccountsNumber", healtyAccountsNumber);
		params.put("faultyDevicesNumber", faultyDevicesNumber);
		params.put("healtyDevicesNumber", healtyDevicesNumber);
		params.put("unreadNotificationsNumber", unreadNotificationsNumber);
		
		return params;
	}
	
	

}
