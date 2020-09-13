package com.tsofen.agsenceapp.dataServices;

import com.tsofen.agsenceapp.entities.Notification;

import java.util.ArrayList;

public interface NotificationsHandler {
    void onNotificationsDownloadFinished(ArrayList<Notification> notifications);
    void onNotificationsRelatedToAccountDownloadFinished(ArrayList<Notification> notifications);
    void onNotificationsRelatedToDeviceDownloadFinished(ArrayList<Notification> notifications);
}
