package com.tsofen.agsenceapp.dataAdapters;

import com.tsofen.agsenceapp.CacheManager;
import com.tsofen.agsenceapp.adaptersInterfaces.onDeviceDataLoadedHandler;
import com.tsofen.agsenceapp.entities.Devices;

import java.util.ArrayList;
import java.util.List;

public class DeviceDataAdapter {

    public static void getFaultyDevices(final onDeviceDataLoadedHandler handler){

        CacheManager cacheManager = CacheManager.getInstance();

        cacheManager.setOnDownloadFinishedHandler(new CacheManager.onDownloadFinishedHandler() {
            @Override
            public void onDownloadFinished(List<Devices> devices) {
                List<Devices> newData = new ArrayList<>();

                for(Devices device : devices){
                    if(device.getFaulty())
                        newData.add(device);
                }

                handler.deviceDataLoaded(newData);
            }
        });
        cacheManager.start();
    }


}
