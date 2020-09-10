package com.tsofen45.TCP_Server.Repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.tsofen45.TCP_Server.Bean.DeviceData;

@Service
public interface DeviceDataRepo extends CrudRepository<DeviceData, Long> {

}
