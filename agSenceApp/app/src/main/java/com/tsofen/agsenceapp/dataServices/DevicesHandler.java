package com.tsofen.agsenceapp.dataServices;

import com.tsofen.agsenceapp.entities.Devices;

import java.util.ArrayList;
import java.util.List;

public interface DevicesHandler  extends  BaseHandler{

    void onDevicesDownloadFinished(List<Devices> devices);
}
