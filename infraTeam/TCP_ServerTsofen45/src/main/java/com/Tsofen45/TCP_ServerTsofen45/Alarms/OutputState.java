package com.Tsofen45.TCP_ServerTsofen45.Alarms;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;

public class OutputState extends StateSetter{
    OutputState(DeviceData device) {
        super(device);
    }

    public void setOutputState() {
        String state = deviceData.getState();
        String binary = hexToBinary(state.charAt(3));
        deviceData.setDoorUnlocked(binary.charAt(0) == '1');
        deviceData.setDoorLocked(binary.charAt(1)=='1');
        deviceData.setFuelCut(binary.charAt(2)=='1');
        deviceData.setPowerCut(binary.charAt(3)=='1');
    }
}
