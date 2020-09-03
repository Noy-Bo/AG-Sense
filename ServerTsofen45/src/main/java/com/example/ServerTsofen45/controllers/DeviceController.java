package com.example.ServerTsofen45.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ServerTsofen45.BL.DeviceBL;
import com.example.ServerTsofen45.Beans.Device;

@RestController
@RequestMapping("Device")
public class DeviceController {

	@Autowired
	DeviceBL deviceBL;

	@GetMapping("DeviceById")
	public Device getSpicificDeviceById(@RequestParam int id, @RequestParam int imei) {

		// get device from device logic

		Device device = deviceBL.getSpecificDeviceById(id, imei);
		if (device == null)
			return null;
		return device;

	}

}
