package com.tsofen.agsenceapp.dataServices;

import com.tsofen.agsenceapp.entities.Notification;

import java.util.ArrayList;
import java.util.List;

public interface NotificationsHandler  extends  BaseHandler{
    void onNotificationsDownloadFinished(List<Notification> notifications);
}
