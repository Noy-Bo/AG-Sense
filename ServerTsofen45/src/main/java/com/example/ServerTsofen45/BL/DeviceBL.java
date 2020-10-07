package com.example.ServerTsofen45.BL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ServerTsofen45.Beans.Device;
import com.example.ServerTsofen45.Beans.DeviceData;
import com.example.ServerTsofen45.Beans.Notification;
import com.example.ServerTsofen45.Beans.NotificationDTO;
import com.example.ServerTsofen45.Repo.DeviceRepository;
import com.example.ServerTsofen45.Repo.NotificationRepository;

import Enums.DeviceType;

@Service
public class DeviceBL {

	@Autowired
	DeviceRepository deviceRepository;

	@Autowired
	UserBL userBL;
	
	// if you need the user devices you must use the user account_id not id  
	// account_id = userBL.getAccountIDForUser(id);
	
	public Device getDeviceById(int deviceId) {

		Device device = deviceRepository.findById(deviceId);
		return device;

	}

	public Device getDeviceImei(long imei) {

		Device device = deviceRepository.findByImei(imei);
		return device;

	}
	
	public ArrayList<Device> getDevicesByName(String name) {

		ArrayList<Device> devices = deviceRepository.findByName(name);
		return devices;

	}

	public ArrayList<Device> findByNameContaining(String name, int start, int num) {

		ArrayList<Device> devices;
		if (start == 0 && num == 0) {
			devices = deviceRepository.findByNameContaining(name);
			return devices;
		}

		devices = deviceRepository.findByNameContaining(name);
		if (devices.size() > start + num && devices.size() > start) {
			ArrayList<Device> sublist = (ArrayList<Device>) devices.subList(start, start + num);
			return sublist;
		} else
			return devices;
	}

	public ArrayList<Device> findByType(int type) {

		ArrayList<Device> devices = deviceRepository.findByType(DeviceType.GpsForPersonal);
		return devices;

	}
	
	public ArrayList<Device> findByFaulty(int id,boolean faulty) {

	//	ArrayList<Device> devices = deviceRepository.findByIdAndFaulty(id,faulty);
	//	return devices;
return null;
	}
	

	public ArrayList<Device> findAll() {

		ArrayList<Device> devices = deviceRepository.findAllByOrderByLastUpdateDesc();
		return devices;

	}

	public ArrayList<Device> getDeviceRelatedToAccount(int id, int start, int num) {
		ArrayList<Device> devices;
		id = userBL.getAccountIDForUser(id);
		
		
		if (start == 0 && num == 0) {
			devices = deviceRepository.findByaccountId(id);
			return devices;
		}
		devices = deviceRepository.findByaccountId(id);
		ArrayList<Device> sublist = (ArrayList<Device>) devices.subList(start, start + num);
		return sublist;

	}

	public ArrayList<String> getRecentLocationRelatedToDevice(int id, int start, int num) {

		Device device = deviceRepository.findById(id);
		List<DeviceData> deviceDatas = device.getDeviceData();
		ArrayList<String> locations = new ArrayList<String>();
		id = userBL.getAccountIDForUser(id);
		
		
		for (DeviceData deviceData : deviceDatas) {
			String location = "'" + deviceData.getLat() + "," + deviceData.getLon() + "," + deviceData.getDateAndTime()
					+ "'";
			locations.add(location);
		}

		if (start + num > locations.size())
			return (ArrayList<String>) (locations.subList(start, locations.size() - 1));

		if (!(start == 0 && num == 0)) {
			return (ArrayList<String>) (locations.subList(start, start + num));
		} else
			return locations;

	}

	public ArrayList<Device> getDevices(int start, int num) {
		ArrayList<Device> devices = deviceRepository.findAllByOrderByIdDesc();
		return (ArrayList<Device>) devices.subList(start, devices.size() - 1);
	}
	
	/*
	 * public void editDevice(long imei , String phoneNumber , String password ) {
	 * deviceRepository.updateDeviceByid(imei, phoneNumber, password); }
	 */
	
	
	
//	public ArrayList<Device> filterDevices(int accountId , boolean healthy , boolean faulty , boolean bank , boolean gps ,
//			boolean tank , int start , int num)
//	{
//	
//		ArrayList<Device> devices = new ArrayList<Device>();
//		if(healthy==true && faulty==true)
//		{
//		  if(bank==true && gps==true && tank==true)
//		  {
//			  devices= deviceRepository.findByaccountId(accountId);
//			  return devices;
//		  }
//		 
//		
//		}
//		
//		if(healthy==false&&faulty==true)
//		{
//			
//		}
//		if(healthy==true&&faulty==false)
//		{
//			
//		}
//		
//		return null;
//		
//		
//		
//	}

	public List<Device> getSpicificDeviceByFilter(int id, boolean healthy, boolean faulty, boolean bank,
			boolean gps, boolean tank, int start, int num) {
		
		id = userBL.getAccountIDForUser(id);
		
		boolean _healthy = false;
		boolean _faulty = true ;
		int _sensorsForBanks = -1;
		int _gpsForPersonal = -1;
		int _lequidHeightForTanks = -1;
		
		if(healthy == false) _healthy = true;
		if(faulty == false) _faulty = false;
		if(bank == true) _sensorsForBanks = 0;
		if(gps == true) _gpsForPersonal = 1;
		if (tank == true) _lequidHeightForTanks = 2;
		
		
		List<Device> devices = deviceRepository.findFilterdDevices(_faulty, _healthy, _sensorsForBanks, _gpsForPersonal, _lequidHeightForTanks, id);
		
		int end = start + num;
		if ((start) > devices.size()) start = devices.size();
		if ((start + num) > devices.size() || (start == 0 && (num == 0 ))) end = devices.size();
			
		List<Device> sublist = devices.subList(start, end);
		return  sublist;
		
	
	}

	public String faultyAccountsNumber() {
		
		return  deviceRepository.getFaultyAccountsNumber();
		
		
	}

	public String healtyAccountsNumber() {
		
		return  deviceRepository.getHealtyAccountsNumber();
	}

	public String faultyDevicesNumber() {
		
		return  deviceRepository.getFaultyDevicesNumber();
		
	}

	public String healtyDevicesNumber() {
		return  deviceRepository.getHealtyDevicesNumber();
	}
	
	public String getFaultyDevicesNumberForId(int accountId) {
		
		return  deviceRepository.getFaultyDevicesNumberForId(accountId);
	}
	
	public String getDevicesNumberForId(int accountId) {
		
		return  deviceRepository.getDevicesNumberForId(accountId);
	}
	
	public boolean editDevice(long imei,String newPhonenumber,String newPass) {
		Device device= deviceRepository.findByImei(imei);
		if(!(newPhonenumber.equalsIgnoreCase(null))) {
			device.setPhoneNumber(newPhonenumber);}
		if(!(newPass.equalsIgnoreCase(null))) {
			device.setPassword(newPass);}
		deviceRepository.save(device);
		return true;
		
	}
	
	public JSONObject getDeviceSMSinfo(long imei) {
		Device device =deviceRepository.findByImei(imei);
		JSONObject json = new JSONObject();
		json.put("PhoneNumber",device.getPhoneNumber());
		json.put("password", device.getPassword());
		return json;
	}

	}
