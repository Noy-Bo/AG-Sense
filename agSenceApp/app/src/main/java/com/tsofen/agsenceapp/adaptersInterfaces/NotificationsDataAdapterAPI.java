package com.tsofen.agsenceapp.adaptersInterfaces;

import java.util.ArrayList;

public interface NotificationsDataAdapterAPI {
    void getAllNotifications(int start, int num, NotificationsDataRequestHandler handler);
    void getNotificationsBySpecificDevice(int deviceId, int start, int num, NotificationsDataRequestHandler handler);
    void getNotificationsBySpecificAccount(int accountId, int start, int num, NotificationsDataRequestHandler handler);
    void setNotificationAsRead( int userID ,ArrayList<Integer> arrayList, MarkNotificationRequestHandler handler);
}
