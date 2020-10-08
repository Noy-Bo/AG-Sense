package com.Tsofen45.TCP_ServerTsofen45.Routers;

import com.Tsofen45.TCP_ServerTsofen45.Repos.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;
import com.Tsofen45.TCP_ServerTsofen45.Repos.DeviceDataRepo;

@Service
public class DeviceDataRouter {
	
	@Autowired
	DeviceDataRepo deviceDataRepo;

	@Autowired
	DeviceRepository deviceRepository;
	
	//TODO make data structure to save device data and save them on data base after one minute
	public void saveDeviceData(DeviceData d) {
		deviceDataRepo.save(d);	
	}


}
