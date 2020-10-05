package com.tsofen.agsenceapp.dataAdapters;

import com.tsofen.agsenceapp.adaptersInterfaces.MarkNotificationRequestHandler;
import com.tsofen.agsenceapp.adaptersInterfaces.NotificationsDataAdapterAPI;
import com.tsofen.agsenceapp.adaptersInterfaces.NotificationsDataRequestHandler;
import com.tsofen.agsenceapp.dataServices.AccountNotificationsHandler;
import com.tsofen.agsenceapp.dataServices.DeviceNotificationsHandler;
import com.tsofen.agsenceapp.dataServices.NotificationsHandler;
import com.tsofen.agsenceapp.entities.Notification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotificationsDataAdapter extends BaseDataAdapter implements NotificationsDataAdapterAPI {
    private static NotificationsDataAdapter instance;

    private NotificationsDataAdapter() {
    }

    public static NotificationsDataAdapter getInstance() {
        if (instance == null)
            instance = new NotificationsDataAdapter();
        return instance;
    }

    @Override
    public void getAllNotifications(int start, int num, final NotificationsDataRequestHandler handler) {
        cacheManager.getNotificationsJob(0, 0, new NotificationsHandler() {
            @Override
            public void onNotificationsDownloadFinished(List<Notification> notifications) {
                handler.onNotificationsReceived(notifications);
            }
        });
    }

    @Override
    public void getNotificationsBySpecificDevice(final int deviceId, int start, int num, final NotificationsDataRequestHandler handler) {
        cacheManager.getNotificationRelatedToDeviceJob(deviceId, 0, 0, new DeviceNotificationsHandler() {
            @Override
            public void onNotificationsRelatedToDeviceDownloadFinished(List<Notification> notifications) {
                handler.onNotificationsReceived(notifications);
            }
        });
    }

    @Override
    public void getNotificationsBySpecificAccount(final int accountId, int start, int num, final NotificationsDataRequestHandler handler) {
        cacheManager.getNotificationRelatedToAccountJob(accountId, 0, 0, new AccountNotificationsHandler() {

            @Override
            public void onNotificationsRelatedToAccountDownloadFinished(List<Notification> notifications) {
                handler.onNotificationsReceived(notifications);
            }
        });
    }

    @Override
    public void setNotificationAsRead(int userID,int notificationId, MarkNotificationRequestHandler handler) {

    }


}
