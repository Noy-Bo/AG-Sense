package com.Tsofen45.TCP_ServerTsofen45.Analyzation;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;

@Service
public class ExternalPowerLowAnalyzer extends Analyzer {

	@Override
	public void Analyze(DeviceData d) throws IOException {
		// TODO Auto-generated method stub
		if(d.isExternalPowerLow()) {
			if(d.isCanExternalPowerLow()) {				
				System.out.println("Entered ext pow low");
				json.put("externalPower", d.getExternalPower());
				sendNotify(d.getImei()+"",14,json);
				d.setCanExternalPowerLow(false);
			}
			
		}
	}


}
