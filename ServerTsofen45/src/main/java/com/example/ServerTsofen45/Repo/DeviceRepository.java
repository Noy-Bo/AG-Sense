package com.example.ServerTsofen45.Repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.ServerTsofen45.Beans.Device;

public interface DeviceRepository extends CrudRepository<Device, Integer> {

	Device findById(int id);

	ArrayList<Device> findByNameContaining(String name);

	ArrayList<Device> findByName(String name);

	ArrayList<Device> findAll();

	ArrayList<Device> findByType(String type);
	
	ArrayList<Device> findByaccountId(int id);

}
