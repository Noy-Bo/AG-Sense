package com.Tsofen45.TCP_ServerTsofen45.Alarms;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;

public class PowerState extends StateSetter {

    PowerState(DeviceData device) {
        super(device);
    }

    public void setPowerStates() {
        String state = deviceData.getState();
        String binary = hexToBinary(state.charAt(0));
        
        deviceData.setInternalBatteryCharching(binary.charAt(0) == '1');
        if(binary.charAt(0) == '0') {        	
        	deviceData.setCanInternalBatteryCharging(true);
        }
      
        deviceData.setInternalBatteryLow(binary.charAt(1) == '1');
        if(binary.charAt(1) == '0') {
        	//deviceData.setCanInternalBatteryLow(true);
        }
        
        deviceData.setExternalPowerLow(binary.charAt(2) == '1');
        if(binary.charAt(2) == '0') {
        	deviceData.setCanExternalPowerLow(true);
        }
        
        deviceData.setExternalPowerOn(binary.charAt(3)=='1');
        if(binary.charAt(3)=='0') {
        	deviceData.setCanExternalPowerOn(true);

        }
    }

}
