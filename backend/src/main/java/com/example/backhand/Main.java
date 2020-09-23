package com.example.backhand;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("HELLO!!!");
        System.out.println("This main file should run the application's backhand. need to handle notification distribution to specific client. " +
                "it should use library which wraps notifications distribution for logic team to use as they want to");
        PushNotificationsSender.publishNotificationToSpecificUser("admin", "SingleToPhone", "bg");
        PushNotificationsSender.publishNotificationToSpecificUser("ibra", "SingleToPhone", "bg");
        ArrayList<String> usersToNotify = new ArrayList<>();
        usersToNotify.add("admin");
        //usersToNotify.add("ibra");
        PushNotificationsSender.publishNotificationForGroupOfUsers(usersToNotify, "Multi!!!", "HI");
    }
}
