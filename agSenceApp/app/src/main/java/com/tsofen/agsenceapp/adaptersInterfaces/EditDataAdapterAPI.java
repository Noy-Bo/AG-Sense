package com.tsofen.agsenceapp.adaptersInterfaces;

public interface EditDataAdapterAPI {
    public void editAccount(String prevName, String newName, EditDataRequestHandler handler);
    public void editDevice(Long deviceIMEI, String newPhoneNumber, String newPass, EditDataRequestHandler handler);
    public void editUser(Integer userId, String newEmail, String newPhoneNumber, EditDataRequestHandler handler);
}
