package com.Tsofen45.TCP_ServerTsofen45.Routers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;
import com.Tsofen45.TCP_ServerTsofen45.Repos.DeviceDataRepo;

@Service
public class DeviceDataRouter {
	
	@Autowired
	DeviceDataRepo deviceDataRepo;
	
	
	public void saveDeviceData(DeviceData d) {
		deviceDataRepo.save(d);	
	}
	
}
