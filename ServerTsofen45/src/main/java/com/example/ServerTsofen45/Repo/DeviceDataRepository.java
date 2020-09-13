package com.example.ServerTsofen45.Repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.ServerTsofen45.Beans.DeviceData;

public interface DeviceDataRepository extends CrudRepository<DeviceData, Integer> {
	
	ArrayList<DeviceData> findAll();


}
