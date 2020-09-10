package com.example.ServerTsofen45.BL;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ServerTsofen45.Beans.Notification;
import com.example.ServerTsofen45.Beans.NotificationDTO;
import com.example.ServerTsofen45.Repo.NotificationRepository;



@Service
public class NotificationBL {
    @Autowired
    NotificationRepository NotificationRepository;
    
   
    public List<NotificationDTO> getAllNotifications() {
    	
    	return  NotificationRepository.findAll1();
    	
    }
    
    public List<Notification> getNotificationRelatedToDevice(int id ,long IMEI){
    	
    	
    	return NotificationRepository.findByDeviceImeiAndDeviceId(IMEI, id);
    }

   public List<NotificationDTO> getNotificationsRelatedToUser(int userId) {
	  
	   
	  List<NotificationDTO> notification = NotificationRepository.findByUserId(userId);
	   return notification;
	   
   }

   public void setNotificationsReaded(int id) {

	   Notification notification = NotificationRepository.findById(id);
	   notification.setReaded(true);
	   NotificationRepository.save(notification);
	
   }


}