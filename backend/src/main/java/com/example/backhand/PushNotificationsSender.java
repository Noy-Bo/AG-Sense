package com.example.backhand;

import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;

import java.util.ArrayList;
import java.util.Collection;

public class PushNotificationsSender {

    private static RemoteTokenRetreiver remoteTokenRetreiver = new RemoteTokenRetreiver();

    public static void publishNotificationToSpecificUser(String userNameToNotify,
                                                         String msgTitle,
                                                         String msgBody) {
        String token = remoteTokenRetreiver.getTokenForUser(userNameToNotify);
        if (token != null){
            Message message = Message.builder()
                    .setNotification(Notification.builder()
                            .setTitle(msgTitle)
                            .setBody(msgBody)
                            .build())
                    .setToken(token)
                    .build();

            String response = null;
            try {
                response = FirebaseMessaging.getInstance().send(message);
            } catch (FirebaseMessagingException e) {
                e.printStackTrace();
            }

            if (response != null)
                // Response is a message ID string.
                System.out.println("Dry run successful: " + response);
        } else {
            System.out.println("Tried to notify: " + userNameToNotify + ", but couldn't find him..");
        }

    }

    public static void publishNotificationForGroupOfUsers(Collection<String> usersToNotify,
                                                          String msgTitle,
                                                          String msgBody) {
        Collection<String> tokens = userNamesToTokens(usersToNotify);
        MulticastMessage multicastMessage = MulticastMessage.builder()
                .setNotification(Notification.builder()
                        .setTitle(msgTitle)
                        .setBody(msgBody)
                        .build()).addAllTokens(tokens)
                .build();

        BatchResponse response = null;
        try {
            response = FirebaseMessaging.getInstance().sendMulticast(multicastMessage);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
        if (response != null)
            // Response is a message ID string.
            System.out.println("Dry run successful: " + response);
    }

    private static Collection<String> userNamesToTokens(Collection<String> userNames) {
        ArrayList<String> tokens = new ArrayList<>();
        for (String userName : userNames) {
            String token = remoteTokenRetreiver.getTokenForUser(userName);
            if (token != null)
                tokens.add(token);
        }

        return tokens;
    }
}
