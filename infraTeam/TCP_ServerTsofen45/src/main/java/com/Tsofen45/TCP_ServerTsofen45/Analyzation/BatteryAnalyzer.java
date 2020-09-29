package com.Tsofen45.TCP_ServerTsofen45.Analyzation;

import java.io.IOException;
import org.springframework.stereotype.Service;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;

@Service
public class BatteryAnalyzer extends Analyzer {

	private static float minBattery = 10f;

	@Override
	public void Analyze(DeviceData d) throws IOException {
		// TODO Auto-generated method stub
		
		float batteryLevel =getBatteryLevel(d.getInternalBatteryPower());
		
		json.put("batteryLeverl", batteryLevel);
		
		if(getBatteryLevel(d.getInternalBatteryPower()) < minBattery) {
			try {
					//Push Notification
					System.out.println("Sending notifaction on battery low batteryPerect: "+getBatteryLevel(d.getInternalBatteryPower()));
					sendNotify(d,json);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public float getBatteryLevel(float num) {
		return (num/4.01f)*100;
	}


	


	
}
