package com.example.ServerTsofen45.Repo;


import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.example.ServerTsofen45.Beans.Notification;
import Enums.Severity;


public interface NotificationRepository extends CrudRepository<Notification, Integer> {
	public Notification findById(int id);
	public List<Notification> findByDeviceId(int deviceId);
	public List<Notification> findByUserId(int UserId);
	public List<Notification> findBySeverity(Severity severity);
	public List<Notification> findByDeviceImei(long deviceImei);
	public List<Notification> findByDeviceImeiAndDeviceId(long deviceImei, int deviceId);
}
