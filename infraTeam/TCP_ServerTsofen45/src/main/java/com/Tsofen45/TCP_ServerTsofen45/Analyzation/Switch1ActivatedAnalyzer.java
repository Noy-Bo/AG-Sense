package com.Tsofen45.TCP_ServerTsofen45.Analyzation;

import java.io.IOException;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;

public class Switch1ActivatedAnalyzer extends Analyzer {

	@Override
	public void Analyze(DeviceData d) throws IOException {
		// TODO Auto-generated method stub
		if(d.isSwitch1Activated()) {
			SendPostRequest(d);
		}
	}

	@Override
	public void SendPostRequest(DeviceData d) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
