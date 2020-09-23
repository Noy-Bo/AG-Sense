package com.tsofen.agsenceapp.adaptersInterfaces;

import com.tsofen.agsenceapp.entities.Notification;

import java.util.List;

public interface NotificationsDataRequestHandler {
    void onNotificationsReceived(List<Notification> notifications);
}
