package com.tsofen.agsenceapp.dataServices;

import com.tsofen.agsenceapp.entities.DeviceData;

import java.util.ArrayList;
import java.util.List;

public interface DeviceDataHandler extends BaseHandler {
    void onDeviceDataRelatedToDeviceDownloadFinished(List<DeviceData> deviceData);
}
