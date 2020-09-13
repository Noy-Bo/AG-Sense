package com.tsofen.agsenceapp.dataAdapters;

import com.tsofen.agsenceapp.adaptersInterfaces.NotificationsDataAdapterAPI;
import com.tsofen.agsenceapp.adaptersInterfaces.NotificationsDataRequestHandler;

public class NotificationsDataAdapter extends BaseDataAdapter implements NotificationsDataAdapterAPI {

    @Override
    public void getAllNotifications(int start, int num, NotificationsDataRequestHandler handler) {

    }

    @Override
    public void getNotificationsBySpecificDevice(int deviceId, int start, int num, NotificationsDataRequestHandler handler) {

    }

    @Override
    public void getNotificationsBySpecificAccount(int accountId, int start, int num, NotificationsDataRequestHandler handler) {

    }


}
