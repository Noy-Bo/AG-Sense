package com.Tsofen45.TCP_ServerTsofen45.Analyzation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;


@Service
public class AnalyzerManager {
	
	@Autowired
	BatteryAnalyzer batteryAnalyzer;
	@Autowired
	MoveAnalyzer moveAnalyzer;
	
	public void analyze(DeviceData d) {
		//analyze if battery is low
		batteryAnalyzer.Analyze(d.getInternalBatteryPower()+"");
		//analyze if device moved
		moveAnalyzer.Analyze(d.getSpeed()+"");
		
	}
}
