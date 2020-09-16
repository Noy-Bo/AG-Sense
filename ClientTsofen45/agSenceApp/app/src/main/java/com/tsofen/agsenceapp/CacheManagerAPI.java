package com.tsofen.agsenceapp;

import com.tsofen.agsenceapp.dataServices.AccountsHandler;
import com.tsofen.agsenceapp.dataServices.DeviceDataHandler;
import com.tsofen.agsenceapp.dataServices.DevicesHandler;
import com.tsofen.agsenceapp.dataServices.LoginHandler;
import com.tsofen.agsenceapp.dataServices.NotificationsHandler;

public interface CacheManagerAPI {
    /**
     *
     * @param username
     * @param password
     * @param handler
     */
    void loginJob(String username, String password, LoginHandler handler);

    // Admin Methods

    void getAccountsJob(int start, int num, AccountsHandler handler);

    void getDevicesJob(int start, int num, DevicesHandler handler);
    void getNotificationsJob(int start, int num, NotificationsHandler handler);
    void getDevicesRelatedToAccountJob(int accountId, int start, int num, DevicesHandler handler);


    // Account methods
    void getNotificationRelatedToDeviceJob(int deviceId, int start, int num, NotificationsHandler handler);
    void getNotificationRelatedToAccountJob(int accountId, int start, int num, NotificationsHandler handler);
    void getSpecificDeviceDataByIdJob(int deviceId, int start, int num, DeviceDataHandler handler);




}
