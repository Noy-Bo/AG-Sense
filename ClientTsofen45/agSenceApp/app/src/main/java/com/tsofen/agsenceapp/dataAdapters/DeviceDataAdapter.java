package com.tsofen.agsenceapp.dataAdapters;

import android.util.Log;

import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceDataAdapterAPI;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceDataRequestHandler;
import com.tsofen.agsenceapp.dataServices.DevicesHandler;
import com.tsofen.agsenceapp.entities.Devices;

import java.util.ArrayList;
import java.util.Date;
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
        System.out.println("inside getFaultyDevices");
        cacheManager.getDevicesJob(0, 0, new DevicesHandler() {
            @Override
            public void onDevicesDownloadFinished(List<Devices> devices) {
                List<Devices> newData = new ArrayList<>();
//
//                for(Devices device : devices){
//                    if(device.getFaulty())
//                        newData.add(device);
//                }
//

                Date date = new Date();
                date.getTime();
                Date date1 = new Date();
                date.setTime(20102020);
                newData.add(new Devices(1,1,1,"Device1",date,date1,true));
                newData.add(new Devices(2,2,2,"Device2",date,date1,true));
                newData.add(new Devices(3,3,1,"Device3",date,date1,true));
                newData.add(new Devices(4,4,2,"Device1",date,date1,true));
                newData.add(new Devices(5,5,2,"Device2",date,date1,true));
                newData.add(new Devices(6,6,1,"Device2",date,date1,true));
                handler.onDeviceDataLoaded(newData);
            }

            @Override
            public void onDevicesRelatedToAccountDownloadFinished(ArrayList<Devices> devices) {

            }
        });
    }

    @Override
    public void getHealthyDevices(final DeviceDataRequestHandler handler) {
        cacheManager.getDevicesJob(0, 0, new DevicesHandler() {
            @Override
            public void onDevicesDownloadFinished(List<Devices> devices) {
                List<Devices> newData = new ArrayList<>();
//
//                for(Devices device : devices){
//                    if(!device.getFaulty())
//                        newData.add(device);
//                }

               Date date = new Date();
                date.getTime();
                Date date1 = new Date();
                date.setTime(20102020);
                newData.add(new Devices(7,7,2,"Device3",date,date1,false));
                newData.add(new Devices(8,8,2,"Device3",date,date1,false));
                newData.add(new Devices(9,9,1,"Device1",date,date1,false));
                newData.add(new Devices(10,10,1,"Device1",date,date1,false));
                newData.add(new Devices(11,11,1,"Device1",date,date1,false));
                newData.add(new Devices(12,12,2,"Device2",date,date1,false));
                newData.add(new Devices(13,13,1,"Device3",date,date1,false));
                newData.add(new Devices(14,14,2,"Device1",date,date1,false));
                newData.add(new Devices(15,15,1,"Device3",date,date1,false));

                handler.onDeviceDataLoaded(devices);
            }

            @Override
            public void onDevicesRelatedToAccountDownloadFinished(ArrayList<Devices> devices) {

            }
        });

    }

    @Override
    public void getDevicesRelatedToAccount(final int accountId, int start, int num, final DeviceDataRequestHandler handler) {
        cacheManager.getDevicesRelatedToAccountJob(accountId, 0, 0, new DevicesHandler() {
            @Override
            public void onDevicesDownloadFinished(List<Devices> devices) {

            }

            @Override
            public void onDevicesRelatedToAccountDownloadFinished(ArrayList<Devices> devices) {
                // handler.onDeviceDataLoaded(devices);
                List<Devices> newData = new ArrayList<>();
                Date date = new Date();
                date.getTime();
                Date date1 = new Date();
                date.setTime(20102020);
                if(accountId == 2) {
                    newData.add(new Devices(7, 7, 2, "Device3", date, date1, false));
                    newData.add(new Devices(8, 8, 2, "Device3", date, date1, false));
                    newData.add(new Devices(12, 12, 2, "Device2", date, date1, false));
                    newData.add(new Devices(14, 14, 2, "Device1", date, date1, false));
                }else {
                    newData.add(new Devices(9, 9, 1, "Device1", date, date1, false));
                    newData.add(new Devices(10, 10, 1, "Device1", date, date1, false));
                    newData.add(new Devices(11, 11, 1, "Device1", date, date1, false));
                    newData.add(new Devices(13, 13, 1, "Device3", date, date1, false));
                    newData.add(new Devices(15, 15, 1, "Device3", date, date1, false));
                }
                handler.onDeviceDataLoaded(newData);
            }
        });
    }

    @Override
    public void getAllDevices(int start, int num, DeviceDataRequestHandler handler) {
        List<Devices> newData = new ArrayList<>();
        Date date = new Date();
        date.getTime();
        Date date1 = new Date();
        date.setTime(20102020);
        newData.add(new Devices(1,1,1,"Device1",date,date1,true));
        newData.add(new Devices(2,2,2,"Device2",date,date1,true));
        newData.add(new Devices(3,3,1,"Device3",date,date1,true));
        newData.add(new Devices(4,4,2,"Device1",date,date1,true));
        newData.add(new Devices(5,5,2,"Device2",date,date1,true));
        newData.add(new Devices(6,6,1,"Device2",date,date1,true));
        newData.add(new Devices(7,7,2,"Device3",date,date1,false));
        newData.add(new Devices(8,8,2,"Device3",date,date1,false));
        newData.add(new Devices(9,9,1,"Device1",date,date1,false));
        newData.add(new Devices(10,10,1,"Device1",date,date1,false));
        newData.add(new Devices(11,11,1,"Device1",date,date1,false));
        newData.add(new Devices(12,12,2,"Device2",date,date1,false));
        newData.add(new Devices(13,13,1,"Device3",date,date1,false));
        newData.add(new Devices(14,14,2,"Device1",date,date1,false));
        newData.add(new Devices(15,15,1,"Device3",date,date1,false));

        handler.onDeviceDataLoaded(newData);


    }


}