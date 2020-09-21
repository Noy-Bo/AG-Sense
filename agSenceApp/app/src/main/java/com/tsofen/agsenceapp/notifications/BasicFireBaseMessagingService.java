package com.tsofen.agsenceapp.notifications;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.RemoteMessage;
import com.tsofen.agsenceapp.CacheManagerAPI;

import static android.content.ContentValues.TAG;

/**
 * Must create this class to register the service
 */
public class BasicFireBaseMessagingService extends RemoteMessageHandler {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        RemoteMessage.Notification not = remoteMessage.getNotification();
        Log.d(TAG,"Message arrived!!!");
    }
}