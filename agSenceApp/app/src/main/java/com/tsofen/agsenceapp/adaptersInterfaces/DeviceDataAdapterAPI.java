package com.tsofen.agsenceapp.adaptersInterfaces;

import java.util.ArrayList;

public interface DeviceDataAdapterAPI {

    void getFaultyDevices(DeviceDataRequestHandler handler);
    void getHealthyDevices(DeviceDataRequestHandler handler);
    void getDevicesRelatedToAccount(int accountId,int start, int num, DeviceDataRequestHandler handler);
    void getAllDevices(int start, int num,boolean requestLatestData, DeviceDataRequestHandler handler);
    void getDeviceDataList(int deviceId, DeviceInfoDataRequestHandler handler);
    //ArrayList<Long> getAllDeviceIMEI();
}
