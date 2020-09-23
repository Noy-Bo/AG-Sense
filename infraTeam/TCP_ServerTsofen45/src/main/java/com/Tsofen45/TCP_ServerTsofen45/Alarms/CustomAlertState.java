package com.Tsofen45.TCP_ServerTsofen45.Alarms;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;

public class CustomAlertState extends StateSetter{
    CustomAlertState(DeviceData device) {
        super(device);
    }
    public void setCustomAlertState(){
        String state = deviceData.getState();
        String binary = hexToBinary(state.charAt(5));
        deviceData.setCustomAlertBit3(binary.charAt(0)=='1');
        deviceData.setCustomAlertBit2(binary.charAt(1)=='1');
        deviceData.setCustomAlertBit1(binary.charAt(2)=='1');
        deviceData.setCustomAlertBit0(binary.charAt(3)=='1');
    }
}
