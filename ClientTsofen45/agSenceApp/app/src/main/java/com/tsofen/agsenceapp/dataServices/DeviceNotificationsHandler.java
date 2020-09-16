package com.tsofen.agsenceapp.dataServices;

import com.tsofen.agsenceapp.entities.Notification;

import java.util.ArrayList;

public interface DeviceNotificationsHandler extends BaseHandler {
    void onNotificationsRelatedToDeviceDownloadFinished(ArrayList<Notification> notifications);
}
