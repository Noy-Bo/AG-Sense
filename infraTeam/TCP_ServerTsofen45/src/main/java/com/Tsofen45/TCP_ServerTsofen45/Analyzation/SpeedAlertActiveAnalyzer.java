package com.Tsofen45.TCP_ServerTsofen45.Analyzation;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;


@Service
public class SpeedAlertActiveAnalyzer extends Analyzer {

	@Override
	public void Analyze(DeviceData d) throws IOException {
		// TODO Auto-generated method stub
		if(d.isSpeedingAlertActive()) {
			sendNotify(d,json);

		}
	}



}