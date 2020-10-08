package com.example.ServerTsofen45.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ServerTsofen45.BL.DeviceBL;
import com.example.ServerTsofen45.BL.ErrorBL;
import com.example.ServerTsofen45.BL.NotificationBL;
import com.example.ServerTsofen45.BL.UserBL;
import com.example.ServerTsofen45.Beans.Notification;
import com.example.ServerTsofen45.Beans.NotificationDTO;
import com.example.ServerTsofen45.Beans.User;
import com.example.ServerTsofen45.Repo.NotificationRepository;
import com.example.ServerTsofen45.pushNotification.PushNotificationsSender;

import Enums.Severity;
import jdk.jfr.BooleanFlag;

import com.example.ServerTsofen45.Beans.Device;
import com.example.ServerTsofen45.Beans.Error;

import springfox.documentation.spring.web.json.Json;

@RestController
@RequestMapping("Notifications")
public class NotificationsController {
	
	@Autowired
	 NotificationBL notificationBL;
	
	@Autowired
	 NotificationRepository notificationRepo;
	
	@Autowired
	ErrorBL errorBL;
	
	@Autowired
	UserBL userBL;
	
	@Autowired
	DeviceBL deviceBL;

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
	  
	  @GetMapping("Read")
	  public int markNotificationAsRead (@RequestParam ArrayList<Integer> notificationIdList, @RequestParam ArrayList<Integer> accountIdList )
		{
			int count = 0;
		    for (int i = 0 ; i<notificationIdList.size() ; i++) {
		    	
		    	count += notificationBL.setNotificationsReaded(notificationIdList.get(i) , accountIdList.get(i));
		    }
		    
		    return count;
			  
		}
	  
	  @GetMapping("AddNotification")
	  public String AddNotification ( @RequestParam long imei, @RequestParam int code, @RequestParam( defaultValue = "null") String params) 
		{
		  
		  Device device = deviceBL.getDeviceImei(imei);
		  Error error = errorBL.findBycode(code);
		  String message = error.getMessage();
		  List<User> users = userBL.getAllUsersForAccountID(device.getAccountId());
		  List<User> admins = userBL.getAllUsersForAccountID(0);
		  ArrayList<String> usersToNotify = new ArrayList<>();
		  
		  
		  if(!device.isFaulty() ) {
			  device.setFaulty(true);
			  device.setFaultyTime(new Timestamp(System.currentTimeMillis()));
		  }

		  if(!params.equals("null")) {

			  try {
				  params =  URLDecoder.decode(params, StandardCharsets.UTF_8.toString()).toString();
			  } catch (UnsupportedEncodingException e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			  }
			  JSONObject jsonParams = new JSONObject();
			  jsonParams = (JSONObject) JSONValue.parse(params);

			  @SuppressWarnings("unchecked")
			  Iterator<String> keys = jsonParams.keySet().iterator();

			  while(keys.hasNext()) {
				  String key = keys.next();

				  message =  message.replace("{" + key +"}", jsonParams.get(key).toString());     

			  }
		  }

		 

		  for (User user : users) {

			  Notification notification = new Notification();

			  notification.setDevice(device);
			  notification.setDateTime(new Timestamp(System.currentTimeMillis()));
			  notification.setErrorCode(code);
			  notification.setReaded(false);
			  notification.setSeverity(error.getSeverity());
			  notification.setMessage(message);
			  notification.setUserId(user.getSysId());

			  usersToNotify.add(user.getUserName());
			  notificationRepo.save(notification); 

		  }
		  
		  for (User user : admins) {

			  Notification notification = new Notification();

			  notification.setDevice(device);
			  notification.setDateTime(new Timestamp(System.currentTimeMillis()));
			  notification.setErrorCode(code);
			  notification.setReaded(false);
			  notification.setSeverity(error.getSeverity());
			  notification.setMessage(message);
			  notification.setUserId(user.getSysId());

			  usersToNotify.add(user.getUserName());
			  notificationRepo.save(notification); 

		  }

		  PushNotificationsSender.publishNotificationForGroupOfUsers(usersToNotify, "Error code: " + code , device.getName() + ": " + message);
		  
		  return message; 		

		}


}