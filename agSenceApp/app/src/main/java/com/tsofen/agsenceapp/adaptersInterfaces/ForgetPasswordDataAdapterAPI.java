package com.tsofen.agsenceapp.adaptersInterfaces;

public interface ForgetPasswordDataAdapterAPI {
    void getUserDetails(String account , ForgetPasswordDataRequestHandler handler);
 void confirmUserCode(String username,String code, ConfirmCodeDataRequestHandler handler);
 void confirmUserPassword(String username,String password,ConfirmPasswordDataRequestHandler handler);
 void emailPickedConfirmed(String username, EmailPickedConfirmedDataRequestHandler handler);


}
