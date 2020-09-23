package com.tsofen.agsenceapp.adaptersInterfaces;

public interface NotificationsDataAdapterAPI {
    void getAllNotifications(int start, int num, NotificationsDataRequestHandler handler);
    void getNotificationsBySpecificDevice(int deviceId, int start, int num, NotificationsDataRequestHandler handler);
    void getNotificationsBySpecificAccount(int accountId, int start, int num, NotificationsDataRequestHandler handler);

}
