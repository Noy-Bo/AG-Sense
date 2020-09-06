package com.example.ServerTsofen45.Repo;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.example.ServerTsofen45.Beans.Device;

public interface DeviceRepository extends CrudRepository<Device, Integer> {

	 Device findById(int id);

	 ArrayList<Device> findByNameContaining(String name);

	 ArrayList<Device> findByName(String name);

	 ArrayList<Device> findAll();
<<<<<<< Updated upstream
	 
	 ArrayList<Device> findByType(String type);
	 
=======
>>>>>>> Stashed changes

}
