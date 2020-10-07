package com.tsofen.agsenceapp.dataServices;

public interface DeviceSmsInfoHandler extends BaseHandler {

    void onDeviceSmsInfoReceived(String devicePasswordAndPhoneNumber);
}
