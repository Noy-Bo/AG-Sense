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
	ExternalPowerLowAnalyzer externalPowerLowAnalyzer;
	@Autowired
	ExternalPowerOnAnalayzer externalPowerOnAnalyzer;
	@Autowired
	InternalBatteryChargingAnalyzer internalBatterChargingAnalyzer;
	@Autowired
	MoveAlertActiveAnalyzer moveAlertActivityAnalyzer;
	@Autowired
	OutOfGeoFenceAnalyzer outOFGeoFenceAnazlyer;
	@Autowired
	PowerCutAnalyzer powerCutAnalyzer;
	@Autowired
	SesmoActivatedAnalyzer sesmoActibatedAnalyzer;
	@Autowired
	SpeedAlertActiveAnalyzer speedAlertAnalyzer;
	@Autowired
	Switch1ActivatedAnalyzer switch1ActivatedAnalzyer;
	@Autowired
	Switch2ActivatedAnalyzer switch2ActivatedAnalyzer;
	public void analyze(DeviceData d) throws IOException {
		//analyze if battery is low
		batteryAnalyzer.Analyze(d);
		
	}
}
