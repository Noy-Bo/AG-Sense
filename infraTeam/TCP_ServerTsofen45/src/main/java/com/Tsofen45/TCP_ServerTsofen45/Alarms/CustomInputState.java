package com.Tsofen45.TCP_ServerTsofen45.Alarms;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;

public class CustomInputState extends StateSetter{
    CustomInputState(DeviceData device) {
        super(device);
    }

    public void setCustomInputState() {
        String state = deviceData.getState();
        String binary = hexToBinary(state.charAt(2));
        deviceData.setCustomInputBit3(binary.charAt(0)=='1');
        deviceData.setCustomInputBit2(binary.charAt(1)=='1');
        deviceData.setCustomInputBit1(binary.charAt(2)=='1');
        deviceData.setCustomInputBit0(binary.charAt(3)=='1');
    }
}
