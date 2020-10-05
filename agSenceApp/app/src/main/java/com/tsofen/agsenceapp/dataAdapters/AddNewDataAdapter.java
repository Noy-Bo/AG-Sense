package com.tsofen.agsenceapp.dataAdapters;

import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.adaptersInterfaces.AddNewDataAdapterAPI;
import com.tsofen.agsenceapp.adaptersInterfaces.AddNewDataRequestHandler;
import com.tsofen.agsenceapp.dataServices.NewCompanyHandler;
import com.tsofen.agsenceapp.dataServices.NewDeviceAddedHandler;
import com.tsofen.agsenceapp.dataServices.NewUserAddedHandler;

public class AddNewDataAdapter extends BaseDataAdapter implements AddNewDataAdapterAPI {
    private static AddNewDataAdapter instance;
    private AddNewDataAdapter() {}
    public static AddNewDataAdapter getInstance() {
        if (instance == null)
            instance = new AddNewDataAdapter();
        return instance;
    }

    @Override
    public void addNewAccount(String accountName, final AddNewDataRequestHandler handler) {
        cacheManager.addNewCompanyJob(accountName, new NewCompanyHandler() {
            @Override
            public void onNewCompanyAddedFinished(Boolean finishedSuccessfully) {
                if(finishedSuccessfully)
                    handler.onNewDataAddedSuccess();
                else
                    handler.onNewDataAddedFailure();
            }
        });
    }

    @Override
    public void addNewUser(String username, String email, String userType, String accountName, final AddNewDataRequestHandler handler) {
        cacheManager.addNewUserJob(username, email, userType, accountName, new NewUserAddedHandler() {
            @Override
            public void onNewUserAddedFinished(Boolean finishedSuccessfully) {
                if(finishedSuccessfully)
                    handler.onNewDataAddedSuccess();
                else
                    handler.onNewDataAddedFailure();
            }
        });
    }

    @Override
    public void addNewDevice(Long imei, String deviceType,String deviceName, String accountName, String devicePhoneNumber, String devicePassword, final AddNewDataRequestHandler handler) {
        cacheManager.addNewDeviceJob(imei, deviceType, deviceName, accountName, devicePhoneNumber, devicePassword, new NewDeviceAddedHandler() {
            @Override
            public void onNewDeviceAddedFinished(Boolean finishedSuccessfully) {
                if(finishedSuccessfully)
                    handler.onNewDataAddedSuccess();
                else
                    handler.onNewDataAddedFailure();
            }
        });
    }

}
