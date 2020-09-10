package com.example.ServerTsofen45.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ServerTsofen45.Repo.DeviceDataRepository;
import com.example.ServerTsofen45.Repo.DeviceRepository;

@Service
public class DeviceDataBL {
	
	@Autowired
	DeviceDataRepository deviceDataRepository;

}
