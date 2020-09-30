package com.Tsofen45.TCP_ServerTsofen45.Repos;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.Tsofen45.TCP_ServerTsofen45.Device.Device;

import javax.transaction.Transactional;


public interface DeviceRepository extends CrudRepository<Device, Integer> {

	Device findById(int id);

	ArrayList<Device> findByNameContaining(String name);

	ArrayList<Device> findByName(String name);

	ArrayList<Device> findAllByOrderByIdDesc();

	ArrayList<Device> findByType(String type);
	
	ArrayList<Device> findByaccountId(int id);
	
	ArrayList<Device> findAll();

	@Transactional
	@Modifying
	@Query("update devices d set d.lastUpdate = ?1, d.latitude= ?2, d.logitude= ?3, d.registered = ?4 where d.imei = ?5")
	void updateDeviceInfo(Timestamp lastUpdated, String lat, String lon, boolean register, long imei);
}