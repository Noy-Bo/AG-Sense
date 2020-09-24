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
        deviceData.setInternalBatteryLow(binary.charAt(1) == '1');
        deviceData.setExternalPowerLow(binary.charAt(2) == '1');
        deviceData.setExternalPowerOn(binary.charAt(3)=='1');
    }

}
