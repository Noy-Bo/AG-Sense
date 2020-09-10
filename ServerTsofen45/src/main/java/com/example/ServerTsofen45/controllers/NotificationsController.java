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
import com.example.ServerTsofen45.Beans.NotificationDTO;

@RestController
@RequestMapping("Notifications")
public class NotificationsController {
	
	@Autowired
	 NotificationBL notificationBL;

	  
	
	
	
	 @GetMapping("AllNotifications")
	  public List<NotificationDTO>  getAllNotifications()
		{
		 	return notificationBL.getAllNotifications();
		
			
		}
	
	  @GetMapping("NotificationRelatedToDevice")
	  public List<Notification>  getNotificationRelatedToDevice(@RequestParam int id ,@RequestParam long IMEI)
		{
			List<Notification> notifications = new ArrayList<Notification>();
			notifications = notificationBL.getNotificationRelatedToDevice(id, IMEI);
		
			return notifications;
		}
	  
	  @GetMapping("NotificationsRelatedToUser")
	  public List<Notification>  getNotificationsRelatedToAccount(@RequestParam int id, @RequestParam int start , @RequestParam int num)

		{
			
		     List<Notification> notifications = new ArrayList<Notification>();
			 notifications = notificationBL.getNotificationsRelatedToUser(id);
	
			return notifications;
		}
	  
	  @GetMapping("Readed")
	  public void setNotificationsReaded(@RequestParam int id)
		{
			
		    
			   notificationBL.setNotificationsReaded(id);
	
		}
	
}
