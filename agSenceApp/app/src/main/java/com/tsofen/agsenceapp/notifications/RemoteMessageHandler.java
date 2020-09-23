package com.tsofen.agsenceapp.notifications;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;

/**
 * This class wraps event handling for incoming messages from fire base service.
 * In order to use it, you have to register your service in Manifest.xml file
 */
public abstract class RemoteMessageHandler extends FirebaseMessagingService {
    /**
     * message handling, default behavior can be toasting the event, you can override it and set it
     * as needed
     * @param remoteMessage - message arrived from fire-base service
     */
    @Override
    public void onMessageReceived(@NonNull final RemoteMessage remoteMessage) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            public void run() {
                RemoteMessage.Notification notification = remoteMessage.getNotification();
                String msgToShow = "Title: " + notification.getTitle()
                                            + ", Body: " + notification.getBody();
                Toast.makeText(getApplicationContext(), msgToShow, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
