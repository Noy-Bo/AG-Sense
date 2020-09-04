package com.example.ServerTsofen45.BL;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ServerTsofen45.Beans.Device;
import com.example.ServerTsofen45.Beans.Notification;
import com.example.ServerTsofen45.Repo.DeviceRepository;
import com.example.ServerTsofen45.Repo.NotificationRepository;

@Service
public class DeviceBL {

	@Autowired
	DeviceRepository deviceRepository;

	public Device getDeviceById(int deviceId) {

		Device device = deviceRepository.findById(deviceId);
		return device;

	}

	public ArrayList<Device> getDevicesByName(String name) {

		ArrayList<Device> devices = deviceRepository.findByName(name);
		return devices;

	}

	public ArrayList<Device> findByNameContaining(String name) {

		ArrayList<Device> devices = deviceRepository.findByName(name);
		return devices;

	}
}
