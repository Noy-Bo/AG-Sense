package com.Tsofen45.TCP_ServerTsofen45.Alarms;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;

public class DrivingState extends StateSetter {
    DrivingState(DeviceData deviceData){
        super(deviceData);
    }
    public void setDrivingState(){
        String state = deviceData.getState();
        String binary = hexToBinary(state.charAt(7));
        deviceData.setHarshTurnLeft(binary.charAt(0)=='1');
        deviceData.setHarshTurnRight(binary.charAt(1)=='1');
        deviceData.setHarshAccelerate(binary.charAt(2)=='1');
        deviceData.setHarshBrake(binary.charAt(3)=='1');
    }
}
