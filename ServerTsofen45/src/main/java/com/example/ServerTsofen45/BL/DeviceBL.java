package com.example.ServerTsofen45.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ServerTsofen45.Repo.DeviceRepository;
import com.example.ServerTsofen45.Repo.NotificationRepository;

@Service
public class DeviceBL {

	@Autowired
	DeviceRepository deviceRepository;

}
