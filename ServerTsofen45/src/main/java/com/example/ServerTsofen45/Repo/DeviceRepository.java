package com.example.ServerTsofen45.Repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.ServerTsofen45.Beans.Device;

public interface DeviceRepository extends CrudRepository<Device, Integer> {

	Device findById(int id);

	ArrayList<Device> findByNameContaining(String name);

	ArrayList<Device> findByName(String name);

	ArrayList<Device> findAllByOrderByIdDesc();

	ArrayList<Device> findByIdAndType(int id ,int type);

	ArrayList<Device> findByaccountId(int id);

	ArrayList<Device> findAll();
	
	ArrayList<Device> findByIdAndFaultyAndType(int id,boolean faulty,String type);
}
