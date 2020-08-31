package com.example.ServerTsofen45.Repo;

import org.springframework.data.repository.CrudRepository;

import com.example.ServerTsofen45.Beans.Notification;

import Emums.Severity;

public interface NotificationRepository extends CrudRepository<Notification, Integer> {
	Notification findById(int Id);
	Notification findByUserId(int UserId);
	Notification findBySever(Severity sever);
}
