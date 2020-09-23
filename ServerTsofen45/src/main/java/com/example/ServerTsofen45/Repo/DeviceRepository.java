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
	
	Device findByImei(long imei);

	ArrayList<Device> findAllByOrderByIdDesc();

	ArrayList<Device> findByType(DeviceType type);
	
	ArrayList<Device> findByaccountId(int id);
	
	ArrayList<Device> findAll();
	ArrayList<Device> findAllByOrderByLastUpdateDesc();

	
	
	@Query(nativeQuery = true, value =" SELECT " + 
			"			 * " + 
			"			 FROM  " + 
			"			 devices  " + 
			"			  where  faulty in (?1, ?2) AND type in (?3, ?4, ?5) AND account_id = ?6 " + 
		 
			"  ORDER BY last_update DESC ;" )
	List<Device> findFilterdDevices(boolean faulty, boolean healthy,
		int sensorsForBanks, int gpsForPersonal, int lequidHeightForTanks, long id);

	
	@Query(nativeQuery = true, value =
			"select count(distinct account_id) " + 
			"from public.devices " + 
			"where faulty = TRUE;" )
	
	public String getFaultyAccountsNumber();
	
	
	@Query(nativeQuery = true, value =
			"select count(distinct account_id) " + 
			"from public.devices d " + 
			" where not exists (select * " + 
			"                  from public.devices  d2" + 
			"                  where d2. account_id = d.account_id and " + 
			"                        (d2.faulty <> false ) );" )
	
	public String getHealtyAccountsNumber();

	@Query(nativeQuery = true, value =
			"select count(distinct id) " + 
			"from public.devices " + 
			"where faulty = TRUE;" )
	public String getFaultyDevicesNumber();

	@Query(nativeQuery = true, value =
			"select count(distinct id) " + 
			"from public.devices " + 
			"where faulty = FALSE;" )
	public String getHealtyDevicesNumber();
	
	
}

