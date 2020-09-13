package com.tsofen.agsenceapp.dataAdapters;

import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.CacheManager;
import com.tsofen.agsenceapp.adaptersInterfaces.onDeviceDataLoadedHandler;
import com.tsofen.agsenceapp.entities.Devices;

import java.util.ArrayList;
import java.util.List;

public class DeviceDataAdapter {

    public static void getFaultyDevices(final onDeviceDataLoadedHandler handler){

        CacheMgr cacheManager = CacheMgr.getInstance();

        /*cacheManager.setOnDownloadFinishedHandler(new CacheManager.onDownloadFinishedHandler() {
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
        cacheManager.start();*/ // TODO need to create this end after creating handler
    }


}
