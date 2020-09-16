package com.tsofen.agsenceapp.dataServices;

import com.tsofen.agsenceapp.entities.Notification;

import java.util.ArrayList;
import java.util.List;

public interface AccountNotificationsHandler extends BaseHandler {
    void onNotificationsRelatedToAccountDownloadFinished(List<Notification> notifications);
}
