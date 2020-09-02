package com.example.ServerTsofen45;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ServerTsofen45.Beans.Notification;
import com.example.ServerTsofen45.Repo.NotificationRepository;

import Enums.ErrorCode;
import Enums.Severity;



@SpringBootApplication
public class ServerTsofen45Application {
	
	@Autowired
	static
    NotificationRepository NotificationRepository;

	public static void main(String[] args) {
		SpringApplication.run(ServerTsofen45Application.class, args);
		
		
		
		
	}

}
