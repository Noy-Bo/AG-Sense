package com.tsofen.agsenceapp.dataServices;

import com.tsofen.agsenceapp.entities.DeviceData;

import java.util.ArrayList;

public interface DeviceDataHandler extends BaseHandler {

    void onDeviceDataRelatedToDeviceDownloadFinished(ArrayList<DeviceData> deviceData);
}
