package com.tsofen.agsenceapp.adaptersInterfaces;

import com.tsofen.agsenceapp.entities.Notification;

import java.util.List;

public interface NotificationsHandler {
    void onNotificationsReceived(List<Notification> notifications);
}
