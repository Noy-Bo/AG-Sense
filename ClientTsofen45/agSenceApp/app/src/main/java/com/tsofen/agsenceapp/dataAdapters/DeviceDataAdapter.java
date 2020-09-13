package com.tsofen.agsenceapp.dataAdapters;

import com.tsofen.agsenceapp.CacheManager;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceDataAdapterAPI;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceDataRequestHandler;
import com.tsofen.agsenceapp.entities.Devices;

import java.util.ArrayList;
import java.util.List;

public class DeviceDataAdapter extends BaseDataAdapter implements DeviceDataAdapterAPI {

    private static  DeviceDataAdapter instance;
    private DeviceDataAdapter(){}
    public static DeviceDataAdapter getInstance(){
        if(instance == null)
            instance = new DeviceDataAdapter();
        return  instance;
    }

    @Override
    public void getFaultyDevices(final DeviceDataRequestHandler handler){
        cacheManager.setOnDownloadFinishedHandler(new CacheManager.onDownloadFinishedHandler() {
            @Override
            public void onDownloadFinished(List<Devices> devices) {
                List<Devices> newData = new ArrayList<>();

                for(Devices device : devices){
                    if(device.getFaulty())
                        newData.add(device);
                }

                handler.onDeviceDataLoaded(newData);
            }
        });

    }

    @Override
    public void getHealthyDevices(DeviceDataRequestHandler handler) {

    }

    @Override
    public void getDevicesRelatedToAccount(int accountId, int start, int num, DeviceDataRequestHandler handler) {

    }

    @Override
    public void getAllDevices(int start, int num, DeviceDataRequestHandler handler) {

    }


}
