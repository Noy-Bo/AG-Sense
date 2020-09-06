package com.example.ServerTsofen45.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ServerTsofen45.BL.DeviceBL;
import com.example.ServerTsofen45.Beans.Device;
import com.example.ServerTsofen45.Beans.Notification;

@RestController
@RequestMapping("Device")
public class DeviceController {

	@Autowired
	DeviceBL deviceBL;

	@GetMapping("DeviceById")
	public Device getDeviceById(@RequestParam int deviceId) {

		return deviceBL.getDeviceById(deviceId);
	}

	@GetMapping("DeviceByName")
	public ArrayList<Device> geDevicesByName(@RequestParam String name) {

		ArrayList<Device> devices = new ArrayList<Device>();
		devices = deviceBL.getDevicesByName(name);
		return devices;
	}

	@GetMapping("DeviceByNameContaining")
	public ArrayList<Device> geDevicesByNameContaining(@RequestParam String name) {

		ArrayList<Device> devices = new ArrayList<Device>();
		devices = deviceBL.getDevicesByName(name);
		return devices;
	}

	@GetMapping("AllDevices")

	public ArrayList<Device> getAllDevices(@RequestParam String name) {

		ArrayList<Device> devices = new ArrayList<Device>();
		devices = deviceBL.findAll();
		return devices;
	}

	@GetMapping("FaultyDevices")

	public ArrayList<Device> getFaultyDevices() {

		ArrayList<Device> devices = new ArrayList<Device>();
		ArrayList<Device> faultyDevices = new ArrayList<Device>();
		devices = deviceBL.findAll();

		for (Device device : devices) {
		//	if (device.isFaulty())
				faultyDevices.add(device);

		}

		return faultyDevices;
	}

	@GetMapping("HealthyDevices")

	public ArrayList<Device> getHealthyDevices() {

		ArrayList<Device> devices = new ArrayList<Device>();
		ArrayList<Device> healthyDevices = new ArrayList<Device>();
		devices = deviceBL.findAll();

		for (Device device : devices) {

		//	if (!device.isFaulty())
				healthyDevices.add(device);

		}

		return healthyDevices;
	}

	@GetMapping("DevicesByType")
	public ArrayList<Device> getDvicesByType(String type) {

		ArrayList<Device> devices = new ArrayList<Device>();

		devices = deviceBL.findByType(type);
		return devices;
	}

	
	
	

}
