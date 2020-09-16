package com.tsofen.agsenceapp.dataServices;

import com.tsofen.agsenceapp.entities.Notification;

import java.util.ArrayList;

public interface AccountNotificationsHandler extends BaseHandler {
    void onNotificationsRelatedToAccountDownloadFinished(ArrayList<Notification> notifications);
}
