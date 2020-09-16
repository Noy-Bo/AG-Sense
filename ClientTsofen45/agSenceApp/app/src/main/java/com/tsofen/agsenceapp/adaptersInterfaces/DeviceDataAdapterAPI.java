package com.tsofen.agsenceapp.adaptersInterfaces;

public interface DeviceDataAdapterAPI {

    void getFaultyDevices(DeviceDataRequestHandler handler);
    void getHealthyDevices(DeviceDataRequestHandler handler);
    void getDevicesRelatedToAccount(int accountId,int start, int num, DeviceDataRequestHandler handler);
    void getAllDevices(int start, int num, DeviceDataRequestHandler handler);

}
