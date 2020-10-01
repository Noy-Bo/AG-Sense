package com.tsofen.agsenceapp.dataAdapters;

import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.adaptersInterfaces.AddNewDataAdapterAPI;
import com.tsofen.agsenceapp.adaptersInterfaces.AddNewDataRequestHandler;

public class AddNewDataAdapter implements AddNewDataAdapterAPI {
    private static AddNewDataAdapter instance;
    private AddNewDataAdapter() {}
    public static AddNewDataAdapter getInstance() {
        if (instance == null)
            instance = new AddNewDataAdapter();
        return instance;
    }

    @Override
    public Boolean addNewAccount(String accountName, AddNewDataRequestHandler handler) {
        return null;
    }

    @Override
    public Boolean addNewUser(String username, String email, String userType, String accountName, AddNewDataRequestHandler handler) {
        return null;
    }

    @Override
    public Boolean addNewDevice(Long imei, String deviceType, String accountName, String devicePhoneNumber, String devicePassword, AddNewDataRequestHandler handler) {
        return null;
    }

}
