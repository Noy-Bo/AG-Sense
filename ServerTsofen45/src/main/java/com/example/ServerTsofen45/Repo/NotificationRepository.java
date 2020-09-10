package com.example.ServerTsofen45.Repo;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.ServerTsofen45.Beans.Notification;
import com.example.ServerTsofen45.Beans.NotificationDTO;

import Enums.Severity;


public interface NotificationRepository extends CrudRepository<Notification, Integer> {
	
	
	@Query(nativeQuery = true, value ="SELECT n.id , n.date_time , n.readed, n.severity, n.user_id, n.device_id, n.device_imei, e.code, e.message"
									  + " FROM notifications AS n "
									  +"INNER JOIN errors AS e "
									  + "ON n.error_code=e.code;")
	public List<NotificationDTO> findAll1();
	public Notification findById(int id);
	public List<Notification> findAll();
	public List<Notification> findByDeviceId(int deviceId);
	public List<Notification> findByUserId(int UserId);
	public List<Notification> findBySeverity(Severity severity);
	public List<Notification> findByDeviceImei(long deviceImei);
	public List<Notification> findByDeviceImeiAndDeviceId(long deviceImei, int deviceId);
}
