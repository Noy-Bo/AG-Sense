package com.tsofen.agsenceapp.dataAdapters;

import com.tsofen.agsenceapp.adaptersInterfaces.EditDataAdapterAPI;
import com.tsofen.agsenceapp.adaptersInterfaces.EditDataRequestHandler;
import com.tsofen.agsenceapp.dataServices.EditAccountHandler;
import com.tsofen.agsenceapp.dataServices.EditDeviceHandler;

public class EditDataAdapter extends  BaseDataAdapter implements EditDataAdapterAPI {
    private static EditDataAdapter instance;
    private EditDataAdapter() {}
    public static EditDataAdapter getInstance() {
        if (instance == null)
            instance = new EditDataAdapter();
        return instance;
    }

    @Override
    public void editAccount(String prevName, String newName, final EditDataRequestHandler handler) {
    cacheManager.editAccountJob(prevName, newName, new EditAccountHandler() {
        @Override
        public void onAccountEditedFinished(Boolean finishedSuccessfully) {
            if(finishedSuccessfully)
                handler.onDataEditedSuccess();
            else
                handler.onDataEditedFailure();
        }
    });
    }

    @Override
    public void editDevice(Long deviceIMEI, String newPhoneNumber, String newPass, final EditDataRequestHandler handler) {
        cacheManager.editDeviceJob(deviceIMEI, newPhoneNumber, newPass, new EditDeviceHandler() {
            @Override
            public void onDeviceEditedFinished(Boolean finishedSuccessfully) {
                if(finishedSuccessfully)
                    handler.onDataEditedSuccess();
                else
                    handler.onDataEditedFailure();
            }
        });
    }
}
