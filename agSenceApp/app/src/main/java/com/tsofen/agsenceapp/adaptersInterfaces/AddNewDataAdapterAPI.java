package com.tsofen.agsenceapp.adaptersInterfaces;

public interface AddNewDataAdapterAPI {
    public Boolean addNewAccount(String accountName, AddNewDataRequestHandler handler);
    public Boolean addNewUser(String username, String email, String userType, String accountName, AddNewDataRequestHandler handler);
    public Boolean addNewDevice(Long imei, String deviceType, String accountName, String devicePhoneNumber, String devicePassword, AddNewDataRequestHandler handler);
}
