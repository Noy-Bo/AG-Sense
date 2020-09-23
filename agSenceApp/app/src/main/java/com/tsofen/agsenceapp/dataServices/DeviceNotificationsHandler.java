package com.tsofen.agsenceapp.dataServices;

import com.tsofen.agsenceapp.entities.Notification;

import java.util.ArrayList;
import java.util.List;

public interface DeviceNotificationsHandler extends BaseHandler {
    void onNotificationsRelatedToDeviceDownloadFinished(List<Notification> notifications);
}
