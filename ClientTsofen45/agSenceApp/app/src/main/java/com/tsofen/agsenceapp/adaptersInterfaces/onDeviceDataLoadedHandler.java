package com.tsofen.agsenceapp.adaptersInterfaces;
import com.tsofen.agsenceapp.entities.Devices;

import java.util.List;

public interface onDeviceDataLoadedHandler {
    public void deviceDataLoaded(List<Devices> devices);
}
