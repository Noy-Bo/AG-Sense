package com.tsofen.agsenceapp;

import com.tsofen.agsenceapp.dataServices.AccountDevicesHandler;
import com.tsofen.agsenceapp.dataServices.AccountNotificationsHandler;
import com.tsofen.agsenceapp.dataServices.AccountsHandler;
import com.tsofen.agsenceapp.dataServices.DeviceDataHandler;
import com.tsofen.agsenceapp.dataServices.DeviceNotificationsHandler;
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
    void getDevicesRelatedToAccountJob(int accountId, int start, int num, AccountDevicesHandler handler);


    // Account methods
    void getNotificationRelatedToDeviceJob(int deviceId, int start, int num, DeviceNotificationsHandler handler);
    void getNotificationRelatedToAccountJob(int accountId, int start, int num, AccountNotificationsHandler handler);
    void getSpecificDeviceDataByIdJob(int deviceId,DeviceDataHandler handler);




}
