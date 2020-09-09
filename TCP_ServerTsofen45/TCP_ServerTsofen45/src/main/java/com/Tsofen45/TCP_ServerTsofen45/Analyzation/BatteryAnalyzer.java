package com.Tsofen45.TCP_ServerTsofen45.Analyzation;

import org.springframework.stereotype.Service;

@Service
public class BatteryAnalyzer extends Analyzer {

	private static int minBattery;

	@Override
	public void Analyze(String s) {
		// TODO Auto-generated method stub
		if(Integer.parseInt(s) < minBattery) {
			//Push Notification
		}
	}
	


	
}
