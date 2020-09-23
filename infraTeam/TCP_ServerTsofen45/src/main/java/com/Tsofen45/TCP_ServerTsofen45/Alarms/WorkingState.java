package com.Tsofen45.TCP_ServerTsofen45.Alarms;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;

public class WorkingState extends StateSetter{
    WorkingState(DeviceData device) {
        super(device);
    }
    public void setWorkingState(){
        String state = deviceData.getState();
        String binary = hexToBinary(state.charAt(6));
        deviceData.setWorkingMode4(binary.charAt(0)=='1');
        deviceData.setWorkingMode3(binary.charAt(1)=='1');
        deviceData.setWorkingMode2(binary.charAt(2)=='1');
        deviceData.setWorkingMode1(binary.charAt(3)=='1');
    }
}
