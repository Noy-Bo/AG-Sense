package com.tsofen.agsenceapp.dataServices;

import com.tsofen.agsenceapp.entities.Devices;

import java.util.List;

public interface OnDevicesReadyHandler {

    void onDevicesReady(List<Devices> devices);

}
