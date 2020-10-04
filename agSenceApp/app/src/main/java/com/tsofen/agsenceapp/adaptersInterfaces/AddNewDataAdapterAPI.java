package com.tsofen.agsenceapp.adaptersInterfaces;

public interface AddNewDataAdapterAPI {
    public void addNewAccount(String accountName, AddNewDataRequestHandler handler);

    public void addNewUser(String username, String email, String userType, String accountName, AddNewDataRequestHandler handler);

    public void addNewDevice(Long imei, String deviceType, String deviceName, String accountName, String devicePhoneNumber, String devicePassword, AddNewDataRequestHandler handler);
}
