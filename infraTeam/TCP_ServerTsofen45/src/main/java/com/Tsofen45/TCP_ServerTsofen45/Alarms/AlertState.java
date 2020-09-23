package com.Tsofen45.TCP_ServerTsofen45.Alarms;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;

public class AlertState extends StateSetter{
    AlertState(DeviceData device) {
        super(device);
    }

    public void setAlertState() {
        String state = deviceData.getState();
        String binary = hexToBinary(state.charAt(4));
        deviceData.setIntoGeoFenceActive(binary.charAt(0)=='1');
        deviceData.setOutOfGeoFenceActive(binary.charAt(1)=='1');
        deviceData.setSpeedingAlertActive(binary.charAt(2)=='1');
        deviceData.setMoveAlertActive(binary.charAt(3)=='1');
    }
}
