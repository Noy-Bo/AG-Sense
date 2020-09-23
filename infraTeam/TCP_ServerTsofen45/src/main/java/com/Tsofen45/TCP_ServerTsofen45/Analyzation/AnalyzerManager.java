package com.Tsofen45.TCP_ServerTsofen45.Analyzation;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;


@Service
public class AnalyzerManager {
	
	@Autowired
	BatteryAnalyzer batteryAnalyzer;
	@Autowired
	
	
	public void analyze(DeviceData d) throws IOException {
		//analyze if battery is low
		batteryAnalyzer.Analyze(d);
		//analyze if device moved
		
	}
}
