package com.Tsofen45.TCP_ServerTsofen45.Alarms;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;

public class InputState extends StateSetter{
    InputState(DeviceData device) {
        super(device);
    }

    public void setInputState() {
        String state = deviceData.getState();
        String binary = hexToBinary(state.charAt(1));
        /*
        *   Bit3: =1, SOS is triggered (input 4 is active).
            Bit2: =1, ACC is on (input 3 is active).
            Bit1: =1, Input 2 is active.
            Bit0: =1, Input 1 is active.
        * */
        //input 4
        //input 3
        //input 2
        deviceData.setInput1Activated(binary.charAt(3) == '1');
    }
}
