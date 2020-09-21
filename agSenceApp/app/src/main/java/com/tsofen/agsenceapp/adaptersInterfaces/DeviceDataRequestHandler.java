package com.tsofen.agsenceapp.adaptersInterfaces;
import com.tsofen.agsenceapp.entities.Devices;

import java.util.List;

public interface DeviceDataRequestHandler {
    public void onDeviceDataLoaded(List<Devices> devices);
}
