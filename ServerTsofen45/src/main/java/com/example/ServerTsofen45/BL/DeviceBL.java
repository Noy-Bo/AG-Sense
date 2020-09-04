package com.example.ServerTsofen45.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ServerTsofen45.Beans.Device;
import com.example.ServerTsofen45.Repo.DeviceRepository;
import com.example.ServerTsofen45.Repo.NotificationRepository;

@Service
public class DeviceBL {

	@Autowired
	DeviceRepository deviceRepository;
	
	
	
	public Device getSpecificDeviceById(int id ,int iMEI) {

		//call from device repository
		return null;
			
	} 


	
	public Device getSpecificDeviceByName(String name){
		
		//call from device repository


		return null;
	}

}
