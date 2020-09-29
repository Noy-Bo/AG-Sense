package com.tsofen.agsenceapp.dataAdapters;

import android.util.Log;

import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceDataAdapterAPI;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceDataRequestHandler;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceInfoDataRequestHandler;
import com.tsofen.agsenceapp.dataServices.AccountDevicesHandler;
import com.tsofen.agsenceapp.dataServices.DeviceDataHandler;
import com.tsofen.agsenceapp.dataServices.DevicesHandler;
import com.tsofen.agsenceapp.entities.DeviceData;
import com.tsofen.agsenceapp.entities.Devices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceDataAdapter extends BaseDataAdapter implements DeviceDataAdapterAPI {

    private static DeviceDataAdapter instance;

    private DeviceDataAdapter() {
    }

    public static DeviceDataAdapter getInstance() {
        if (instance == null)
            instance = new DeviceDataAdapter();
        return instance;
    }

    @Override
    public void getFaultyDevices(final DeviceDataRequestHandler handler) {
        System.out.println("inside getFaultyDevices");
        cacheManager.getDevicesJob(0, 0, new DevicesHandler() {
            @Override
            public void onDevicesDownloadFinished(List<Devices> devices) {
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
    public void getHealthyDevices(final DeviceDataRequestHandler handler) {
        cacheManager.getDevicesJob(0, 0, new DevicesHandler() {
            @Override
            public void onDevicesDownloadFinished(List<Devices> devices) {
                List<Devices> newData = new ArrayList<>();
                for (Devices device : devices) {
                    if (!device.getFaulty())
                        newData.add(device);
                }
                handler.onDeviceDataLoaded(newData);
            }
        });

    }

    @Override
    public void getDevicesRelatedToAccount(final int accountId, int start, int num, final DeviceDataRequestHandler handler) {
        cacheManager.getDevicesRelatedToAccountJob(accountId, 0, 0, new AccountDevicesHandler() {

            @Override
            public void onDevicesRelatedToAccountDownloadFinished(List<Devices> devices) {
                handler.onDeviceDataLoaded(devices);
            }
        });
    }

    @Override
    public void getAllDevices(int start, int num, final DeviceDataRequestHandler handler) {
        cacheManager.getDevicesJob(0, 0, new DevicesHandler() {
            @Override
            public void onDevicesDownloadFinished(List<Devices> devices) {
                handler.onDeviceDataLoaded(devices);
            }
        });
    }

    @Override
    public void getDeviceDataList(int deviceId, final DeviceInfoDataRequestHandler handler) {
        cacheManager.getSpecificDeviceDataByIdJob(deviceId, new DeviceDataHandler() {
            @Override
            public void onDeviceDataRelatedToDeviceDownloadFinished(List<DeviceData> deviceData) {
                handler.getDeviceDataInfo(deviceData);
            }
        });
    }

   /* @Override
    public ArrayList<Long> getAllDeviceIMEI() {

        getAllDevices(0, 0, new DeviceDataRequestHandler() {
            @Override
            public void onDeviceDataLoaded(List<Devices> devices) {
                ArrayList<Long> arrayList=new ArrayList<>();
                for ( Devices devices1: devices)
                    arrayList.add(devices1.getImei());

            }
        });}*/




}