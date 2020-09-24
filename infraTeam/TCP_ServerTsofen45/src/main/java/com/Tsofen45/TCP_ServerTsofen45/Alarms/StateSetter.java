package com.Tsofen45.TCP_ServerTsofen45.Alarms;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;

public abstract class StateSetter {
    private final String[] hexNumbers = {"0000","0001","0010","0011","0100","0101","0110","0111","1000",
            "1001", "1010","1011","1100","1101","1110", "1111"};
    DeviceData deviceData;
    StateSetter(DeviceData device){
        this.deviceData = device;
    }
    String hexToBinary(char hex){
        if(hex < '0' || hex > 'f'){
            return null;
        }
        int hexNum = Integer.parseInt((String.valueOf(hex)), 16);
        return hexNumbers[hexNum];
    }
}
