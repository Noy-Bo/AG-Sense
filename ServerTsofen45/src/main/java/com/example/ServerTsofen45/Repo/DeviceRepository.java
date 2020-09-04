package com.example.ServerTsofen45.Repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.ServerTsofen45.Beans.Device;

public interface DeviceRepository extends CrudRepository<Device, Integer> {

	public Device findById(int id);
	ArrayList<Device> findByNameContaining(String name);
	public ArrayList<Device> findByName(String name);

}
