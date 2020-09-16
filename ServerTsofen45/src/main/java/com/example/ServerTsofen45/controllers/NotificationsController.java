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

	@GetMapping("getNotifications")
	  public List<NotificationDTO>  getNotifications(@RequestParam int start, @RequestParam int num)
		{
		
		 if(start == 0 && num == 0 ) {
				
				return notificationBL.getAllNotifications();
			}

		
		  List<NotificationDTO> notifications = new ArrayList<NotificationDTO>();
			 notifications = notificationBL.getAllNotifications();
			
			 if ((start) > notifications.size()) start = notifications.size();
			 int end = start + num;
			if ((start + num) > notifications.size()) end = notifications.size();
				
			 List<NotificationDTO> sublist = notifications.subList(start, end);
			return  sublist;
		 
		}
	
	
	
	  
	  @GetMapping("NotificationRelatedToDevice")
	  public List<NotificationDTO>   getNotificationRelatedToDevice(@RequestParam int id, @RequestParam int start, @RequestParam int num)
		{
		  if(start == 0 && num == 0 ) {
				
				return notificationBL.getNotificationRelatedToDevice(id);
			}

		
		  List<NotificationDTO> notifications = new ArrayList<NotificationDTO>();
			 notifications = notificationBL.getNotificationRelatedToDevice(id);
			
			 if ((start) > notifications.size()) start = notifications.size();
			 int end = start + num;
			if ((start + num) > notifications.size()) end = notifications.size();
			 
			 List<NotificationDTO> sublist = notifications.subList(start, end);
			return  sublist;
		}
	  
	 
	 
	  @GetMapping("NotificationsRelatedToAccount")
	  public List<NotificationDTO> getNotificationsRelatedToAccount(@RequestParam int id,@RequestParam int start ,@RequestParam int num)
		{
		
		  if(start == 0 && num == 0 ) {
				
				return notificationBL.getNotificationsRelatedToUser(id);
			}

		
		  List<NotificationDTO> notifications = new ArrayList<NotificationDTO>();
			 notifications = notificationBL.getNotificationsRelatedToUser(id);
			
			 if ((start) > notifications.size()) start = notifications.size();
			 int end = start + num;
			if ((start + num) > notifications.size()) end = notifications.size();
			 
			 List<NotificationDTO> sublist = notifications.subList(start, end);
			return  sublist;
		  
		 
		}
	  
	  @GetMapping("Readed")
	  public boolean markNotificationAsRead (@RequestParam ArrayList<Integer> idList)
		{
			
		    for (Integer id : idList) {
		    	 notificationBL.setNotificationsReaded(id);
		    }
		    
		    return true;
			  
		}
	
}