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

	  
	  @GetMapping("NotificationRelatedToDevice")
	  public List<Notification>  getNotificationRelatedToDevice(@RequestParam int id ,@RequestParam long IMEI)
		{
			List<Notification> notifications = new ArrayList<Notification>();
			notifications = notificationBL.getNotificationRelatedToDevice(id, IMEI);
		
			return notifications;
		}
	  
	 
	 
	  @GetMapping("NotificationsRelatedToAccount")
	  public List<NotificationDTO> getNotificationsRelatedToAccount(int id,int start , int num)
		{
		
		  if(start == 0 && num == 0 ) {
				
				return notificationBL.getAllNotifications();
			}

		
		  List<NotificationDTO> notifications = new ArrayList<NotificationDTO>();
			 notifications = notificationBL.getNotificationsRelatedToUser(id);
			
			 List<NotificationDTO> sublist = notifications.subList(start, start + num);
			return  sublist;
		  
		 
		}
	  
	  @GetMapping("Readed")
	  public void setNotificationsReaded(@RequestParam int id)
		{
			
		    
			   notificationBL.setNotificationsReaded(id);
	
		}
	
}
