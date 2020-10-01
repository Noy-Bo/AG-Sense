package com.tsofen.agsenceapp.adaptersInterfaces;

public interface EditDataAdapterAPI {
    public Boolean editAccount(String prevName, String newName, EditDataRequestHandler handler);
    public Boolean editDevice(Long deviceIMEI, String newPhoneNumber, String newPass, EditDataRequestHandler handler);
}
