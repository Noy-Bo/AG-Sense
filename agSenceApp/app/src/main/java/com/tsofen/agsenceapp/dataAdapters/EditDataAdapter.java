package com.tsofen.agsenceapp.dataAdapters;

import com.tsofen.agsenceapp.adaptersInterfaces.EditDataAdapterAPI;
import com.tsofen.agsenceapp.adaptersInterfaces.EditDataRequestHandler;

public class EditDataAdapter implements EditDataAdapterAPI {
    private static EditDataAdapter instance;
    private EditDataAdapter() {}
    public static EditDataAdapter getInstance() {
        if (instance == null)
            instance = new EditDataAdapter();
        return instance;
    }

    @Override
    public Boolean editAccount(String prevName, String newName, EditDataRequestHandler handler) {
        return null;
    }

    @Override
    public Boolean editDevice(Long deviceIMEI, String newPhoneNumber, String newPass, EditDataRequestHandler handler) {
        return null;
    }
}
