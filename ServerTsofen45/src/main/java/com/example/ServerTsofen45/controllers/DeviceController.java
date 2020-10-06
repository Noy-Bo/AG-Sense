package com.example.ServerTsofen45.controllers;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ServerTsofen45.BL.AccountBL;
import com.example.ServerTsofen45.BL.DeviceBL;
import com.example.ServerTsofen45.Beans.Account;
import com.example.ServerTsofen45.Beans.Device;
import com.example.ServerTsofen45.Repo.DeviceRepository;

import Enums.DeviceType;

@RestController
@RequestMapping("Device")
public class DeviceController {

	@Autowired
	DeviceBL deviceBL;

	@Autowired
	DeviceRepository deviceRepository;

	@Autowired

	AccountBL accountBL;

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
	public ArrayList<Device> geDevicesByNameContaining(@RequestParam String name, @RequestParam int start,
			@RequestParam int num) {

		ArrayList<Device> devices = new ArrayList<Device>();
		devices = deviceBL.findByNameContaining(name, start, num);
		return devices;
	}

	@GetMapping("AllDevices")
	public ArrayList<Device> getAllDevices() {

		ArrayList<Device> devices = new ArrayList<Device>();
		devices = deviceBL.findAll();
		return devices;
	}

	@GetMapping("FaultyDevices")

	public ArrayList<Device> getFaultyDevices() {

		ArrayList<Device> devices = new ArrayList<Device>();

		return devices;
	}

	@GetMapping("HealthyDevices")

	public ArrayList<Device> getHealthyDevices() {

		ArrayList<Device> devices = new ArrayList<Device>();

		return devices;
	}

	@GetMapping("DevicesByType")
	public ArrayList<Device> getDevicesByType(@RequestParam String type) {

		ArrayList<Device> devices = new ArrayList<Device>();

		// devices = deviceBL.findByType(type);
		return devices;
	}
	
	
	

	@GetMapping("getDeviceRelatedToAccount")
	public ArrayList<Device> getDeviceRelatedToAccount(@RequestParam int id, @RequestParam int start,
			@RequestParam int num) {
		ArrayList<Device> devices = new ArrayList<Device>();
		devices = deviceBL.getDeviceRelatedToAccount(id, start, num);
		return devices;
	}

	@GetMapping("recentLocations")
	public ArrayList<String> getRecentLocationRelatedToDevice(@RequestParam int id, @RequestParam int start,
			@RequestParam int num) {

		ArrayList<String> locations = new ArrayList<String>();

		locations = deviceBL.getRecentLocationRelatedToDevice(id, start, num);
		return locations;
	}

	@GetMapping("getDevices")
	public ArrayList<Device> getDevices(@RequestParam int start, @RequestParam int num) {
		ArrayList<Device> devices = new ArrayList<>();
		devices = deviceBL.getDevices(start, num);
		return devices;
	}

	// http://localhost:8080/Device/SpicificDeviceByFilter?id=5&healthy=1&faulty=1&bank=0&gps=0&tank=1&start=0&num=0
	@GetMapping("SpicificDeviceByFilter")
	public List<Device> getSpicificDeviceByFilter(@RequestParam int id, @RequestParam boolean healthy,
			@RequestParam boolean faulty, @RequestParam boolean bank, @RequestParam boolean gps,
			@RequestParam boolean tank, @RequestParam int start, @RequestParam int num) {

		List<Device> devices = new ArrayList<Device>();
		devices = deviceBL.getSpicificDeviceByFilter(id, healthy, faulty, bank, gps, tank, start, num);
		return devices;
	}

	@GetMapping("Add")
	public boolean AddNewDevice(@RequestParam long imei, @RequestParam DeviceType type, @RequestParam String deviceName, 
			@RequestParam String accountName, @RequestParam String phoneNumber, String devicePassword) {

		if (deviceBL.getDeviceImei(imei) != null)
			return false;

		Account account = accountBL.getAccountByName(accountName);
		int accountId = account.getId();

		Device device = new Device(imei, accountId, type, deviceName, phoneNumber, devicePassword);

		deviceRepository.save(device);
		return true;
	}
	
	
	
	@GetMapping("getPassAndPhone")

	public String getPassAndPhone(long imei)
	{
		Device device =deviceBL.getDeviceImei(imei);
		return ""+device.getPassword()+","+device.getPhoneNumber()+"";
		
	}
	
	@GetMapping("getSMSInfo")
	public JSONObject getSMSInfo(@RequestParam long imei)
	{
		 return deviceBL.getDeviceSMSinfo(imei);
		
	}
	
	
	/*
	 * @GetMapping("Edit") public boolean editDevice(@RequestParam long
	 * deviceIMEI, @RequestParam String newPhoneNumber, @RequestParam String
	 * newPass) { if (deviceBL.getDeviceImei(deviceIMEI) == null) return false;
	 * 
	 * deviceBL.editDevice(deviceIMEI, newPhoneNumber, newPass); return true; }
	 */
	
	/*************************SETTINGS******************************/
	
	@GetMapping("GetDeviceSettings")
	public void getDeviceSAettings(long deviceImei)
	{
		
	}
	
	
	@GetMapping("SetDeviceSettingAlertGeoForce")
	public void setDeviceSettingAlertGeoForce()
	{
		
	}
	
	
	
	@GetMapping("SetDeviceSettingAuthorizedNumber")
	public void setDeviceSettingAuthorizedNumber()
	{
		
	}
	
	
	@GetMapping("setDeviceSettingInterval")
	public void setDeviceSettingInterval()
	{
		
	}
	
	@GetMapping("editDevice")
	public boolean editDevice(@RequestParam long deviceIMEI,@RequestParam String newPhoneNumber,@RequestParam String newPass) {
		return deviceBL.editDevice(deviceIMEI, newPhoneNumber, newPass);
	}
	
}
