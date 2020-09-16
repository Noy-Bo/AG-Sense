package com.example.ServerTsofen45.Repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.ServerTsofen45.Beans.DeviceData;

public interface DeviceDataRepository extends CrudRepository<DeviceData, Integer> {
	
	ArrayList<DeviceData> findAll();
	
	DeviceData findByID(int deviceId);
	ArrayList<DeviceData> findAllByID(int id);
	ArrayList<DeviceData> findById(int id);
	ArrayList<DeviceData> findByDeviceId(int id);

}
