package com.example.ServerTsofen45.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ServerTsofen45.BL.DeviceBL;
import com.example.ServerTsofen45.BL.DeviceDataBL;

@RestController
@RequestMapping("DeviceData")
public class DeviceDataController {
	
	@Autowired
	DeviceDataBL deviceDataBL;

}
