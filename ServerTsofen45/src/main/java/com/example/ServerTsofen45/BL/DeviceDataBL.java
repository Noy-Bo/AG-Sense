package com.example.ServerTsofen45.BL;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ServerTsofen45.Beans.Device;
import com.example.ServerTsofen45.Beans.DeviceData;
import com.example.ServerTsofen45.Repo.DeviceDataRepository;
import com.example.ServerTsofen45.Repo.DeviceRepository;

@Service
public class DeviceDataBL {
	
	@Autowired
	DeviceDataRepository deviceDataRepository;
	@Autowired
	DeviceRepository deviceRepository;

	public ArrayList<DeviceData> findAll() {

		ArrayList<DeviceData> devices = deviceDataRepository.findAll();
		return devices;

	}
	public ArrayList<DeviceData> getSpecificDeviceDataById(int id, int start , int num){

		if(start == 0 && num == 0 ) {
			ArrayList<DeviceData> devices = (ArrayList<DeviceData>) deviceDataRepository.findAll();
			return devices;
		}
		ArrayList<DeviceData> devicedata = deviceDataRepository.findAllByID(id);
		ArrayList<DeviceData> sublist = (ArrayList<DeviceData>) devicedata.subList(start, start + num);
		return sublist;
	}
	



}
