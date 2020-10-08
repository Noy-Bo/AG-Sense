package com.Tsofen45.TCP_ServerTsofen45.Analyzation;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;

@Service
public class ExternalPowerOnAnalayzer extends Analyzer{

	@Override
	public void Analyze(DeviceData d) throws IOException {
		// TODO Auto-generated method stub
		if(!d.isExternalPowerOn()) {
			if(d.isCanExternalPowerOn()) {				
				System.out.println("Entered ext pow on");
				sendNotify(d.getImei()+"",2,json);
				d.setCanExternalPowerOn(false);
			}
		}
	}



}
