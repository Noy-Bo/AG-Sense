package com.Tsofen45.TCP_ServerTsofen45.UPCommands;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;

public abstract class UpCommand {
    DeviceData device;
    String data;

    public UpCommand(){
        device = new DeviceData();
    }

    public DeviceData getDevice() {
        return device;
    }

    public abstract boolean unserialize(String msg);
    public abstract boolean parse_data();
}
