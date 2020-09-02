package com.example.ServerTsofen45.BL;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ServerTsofen45.Beans.Notification;
import com.example.ServerTsofen45.Repo.NotificationRepository;



@Service
public class NotificationBL {
    @Autowired
    NotificationRepository NotificationRepository;
    
   public List<Notification> getNotificationRelatedToDevice(int id ,long IMEI){
    	
    	return NotificationRepository.findByDeviceImeiAndDeviceId(IMEI, id);
    }

   public List<Notification> getNotificationsRelatedToUser(int userId) {
	  
	   
	  List<Notification> notification = NotificationRepository.findByUserId(userId);
	   return notification;
	   
   }
}