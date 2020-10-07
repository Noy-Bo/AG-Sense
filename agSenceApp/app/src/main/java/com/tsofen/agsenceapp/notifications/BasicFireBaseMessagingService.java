package com.tsofen.agsenceapp.notifications;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.RemoteMessage;
import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.activities.AppBaseActivity;
import com.tsofen.agsenceapp.dataServices.AccountNotificationsHandler;
import com.tsofen.agsenceapp.dataServices.NotificationsHandler;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.Notification;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Must create this class to register the service
 */
public class BasicFireBaseMessagingService extends RemoteMessageHandler {
    CacheMgr cacheMgr = CacheMgr.getInstance();

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        RemoteMessage.Notification not = remoteMessage.getNotification();



        // Getting new notifications.

        if (AppBaseActivity.getUser() instanceof Admin)
        {
            cacheMgr.getNotificationsJob(0, 0, new NotificationsHandler() {
                @Override
                public void onNotificationsDownloadFinished(List<Notification> notifications) {
                    cacheMgr.setNotifications(notifications);
                }
            });
        }
        else if (AppBaseActivity.getUser() instanceof Account)
        {
            cacheMgr.getNotificationRelatedToAccountJob(( AppBaseActivity.getUser().getId()), 0, 0, new AccountNotificationsHandler() {
                        @Override
                        public void onNotificationsRelatedToAccountDownloadFinished(List<Notification> notifications) {
                            cacheMgr.setNotifications(notifications);
                        }
                    });
        }



        Log.d(TAG,"Message arrived!!!");
    }
}