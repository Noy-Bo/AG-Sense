package com.Tsofen45.TCP_ServerTsofen45.UPCommands;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;

public abstract class UpCommand {
    DeviceData deviceData;
    String data;

    
    public abstract DeviceData parse_data(String msg);
    public UpCommand(){
        deviceData = new DeviceData();
    }
    public DeviceData getDevice() {
        return deviceData;
    }
}
