package com.example.ServerTsofen45.Repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.ServerTsofen45.Beans.Device;
import com.example.ServerTsofen45.Beans.NotificationDTO;

import Enums.DeviceType;

public interface DeviceRepository extends CrudRepository<Device, Integer> {

	Device findById(int id);

	ArrayList<Device> findByNameContaining(String name);

	ArrayList<Device> findByName(String name);

	ArrayList<Device> findAllByOrderByIdDesc();

	ArrayList<Device> findByType(DeviceType type);
	
	ArrayList<Device> findByaccountId(int id);
	
	ArrayList<Device> findAll();
	
	@Query(nativeQuery = true, value =" SELECT " + 
			"			 * " + 
			"			 FROM  " + 
			"			 devices  " + 
			"			  where  faulty in (?1, ?2) AND type in (?3, ?4, ?5) AND account_id = ?6 " + 
			"   ;" )
	List<Device> findFilterdDevices(boolean faulty, boolean healthy,
		int sensorsForBanks, int gpsForPersonal, int lequidHeightForTanks, long id);
	
	
}

