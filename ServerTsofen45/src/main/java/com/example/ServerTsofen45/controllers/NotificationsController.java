package com.example.ServerTsofen45.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.ServerTsofen45.BL.NotificationBL;
import com.example.ServerTsofen45.Beans.Notification;

@RestController
@RequestMapping("Notifications")
public class NotificationsController {
	
	@Autowired
	 NotificationBL notificationBL;

	  
	  @GetMapping("/GetNotifications/Device/ById")
	  public List<Notification>  getNotificationRelatedToDevice(@RequestParam int id ,@RequestParam long IMEI)
		{
			List<Notification> notifications = new ArrayList<Notification>();
			notifications = notificationBL.getNotificationRelatedToDevice(id, IMEI);
		
			return notifications;
		}
	  
	  @GetMapping("/GetNotifications/User/ById")
	  public List<Notification> getNotificationsRelatedToUser(@RequestParam int id)
		{
			
		     List<Notification> notifications = new ArrayList<Notification>();
			 notifications = notificationBL.getNotificationsRelatedToUser(id);
	
			return notifications;
		}
	
}
