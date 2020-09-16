package com.tsofen.agsenceapp.dataAdapters;

import com.tsofen.agsenceapp.adaptersInterfaces.NotificationsDataAdapterAPI;
import com.tsofen.agsenceapp.adaptersInterfaces.NotificationsDataRequestHandler;
import com.tsofen.agsenceapp.dataServices.AccountNotificationsHandler;
import com.tsofen.agsenceapp.dataServices.DeviceNotificationsHandler;
import com.tsofen.agsenceapp.dataServices.NotificationsHandler;
import com.tsofen.agsenceapp.entities.Notification;

import java.util.ArrayList;
import java.util.Date;

public class NotificationsDataAdapter extends BaseDataAdapter implements NotificationsDataAdapterAPI {
    private static  NotificationsDataAdapter instance;
    private NotificationsDataAdapter(){}
    public static NotificationsDataAdapter getInstance(){
        if(instance == null)
            instance = new NotificationsDataAdapter();
        return  instance;
    }
    @Override
    public void getAllNotifications(int start, int num, final NotificationsDataRequestHandler handler) {
        cacheManager.getNotificationsJob(0, 0, new NotificationsHandler() {
            @Override
            public void onNotificationsDownloadFinished(ArrayList<Notification> notifications) {
//                handler.onNotificationsReceived(notifications);
                java.util.Date date = new Date();
                date.setTime(20102020);
                ArrayList<Notification> notificationArray = new ArrayList<>();

                notificationArray.add(new Notification(1, 1, 1, 1, date,
                        58, false, "Hey this is error message1", 15));
                notificationArray.add(new Notification(2, 2, 2, 2, date,
                        58, false, "Hey this is error message2", 15));
                notificationArray.add(new Notification(3, 3, 3, 1, date,
                        58, false, "Hey this is error message3", 15));
                notificationArray.add(new Notification(4, 4, 4, 2, date,
                        58, false, "Hey this is error message4", 15));
                notificationArray.add(new Notification(5, 5, 5, 2, date,
                        58, false, "Hey this is error message5", 15));
                notificationArray.add(new Notification(6, 6, 6, 1, date,
                        58, false, "Hey this is error message6", 15));
                notificationArray.add(new Notification(7, 1, 1, 1, date,
                        58, false, "Hey this is error message7", 15));
                notificationArray.add(new Notification(8, 1, 1, 1, date,
                        58, false, "Hey this is error message8", 15));
                notificationArray.add(new Notification(9, 1, 1, 1, date,
                        58, false, "Hey this is error message9", 15));
                notificationArray.add(new Notification(10, 2, 2, 1, date,
                        58, false, "Hey this is error message10", 15));
                notificationArray.add(new Notification(11, 2, 2, 1, date,
                        58, false, "Hey this is error message11", 15));
                notificationArray.add(new Notification(12, 3, 3, 1, date,
                        58, false, "Hey this is error message12", 15));
                notificationArray.add(new Notification(13, 4, 4, 1, date,
                        58, false, "Hey this is error message13", 15));
                notificationArray.add(new Notification(14, 5, 5, 1, date,
                        58, false, "Hey this is error message14", 15));
                notificationArray.add(new Notification(15, 6, 6, 1, date,
                        58, false, "Hey this is error message15", 15));
                notificationArray.add(new Notification(16, 5, 5, 1, date,
                        58, false, "Hey this is error message16", 15));

                handler.onNotificationsReceived(notificationArray);

            }
        });
    }

    @Override
    public void getNotificationsBySpecificDevice(final int deviceId, int start, int num, NotificationsDataRequestHandler handler) {
        cacheManager.getNotificationRelatedToDeviceJob(deviceId, 0, 0, new DeviceNotificationsHandler() {
            @Override
            public void onNotificationsRelatedToDeviceDownloadFinished(ArrayList<Notification> notifications) {

                java.util.Date date = new Date();
                ArrayList<Notification> notificationArray = new ArrayList<>();
                date.setTime(20102020);
                switch (deviceId) {
                    case 1:
                        notificationArray.add(new Notification(1, 1, 1, 1, date,
                                58, false, "Hey this is error message1", 15));
                        notificationArray.add(new Notification(7, 1, 1, 1, date,
                                58, false, "Hey this is error message7", 15));
                        notificationArray.add(new Notification(8, 1, 1, 1, date,
                                58, false, "Hey this is error message8", 15));
                        notificationArray.add(new Notification(9, 1, 1, 1, date,
                                58, false, "Hey this is error message9", 15));
                        break;
                    case 2:
                        notificationArray.add(new Notification(10, 2, 2, 1, date,
                                58, false, "Hey this is error message10", 15));
                        notificationArray.add(new Notification(11, 2, 2, 1, date,
                                58, false, "Hey this is error message11", 15));
                        notificationArray.add(new Notification(2, 2, 2, 2, date,
                                58, false, "Hey this is error message2", 15));
                        break;
                    case 3:
                        notificationArray.add(new Notification(3, 3, 3, 1, date,
                                58, false, "Hey this is error message3", 15));
                        notificationArray.add(new Notification(12, 3, 3, 1, date,
                                58, false, "Hey this is error message12", 15));
                        break;
                    case 4:
                        notificationArray.add(new Notification(13, 4, 4, 1, date,
                                58, false, "Hey this is error message13", 15));
                        notificationArray.add(new Notification(4, 4, 4, 2, date,
                                58, false, "Hey this is error message4", 15));
                        break;
                    case 5:
                        notificationArray.add(new Notification(14, 5, 5, 1, date,
                                58, false, "Hey this is error message14", 15));
                        notificationArray.add(new Notification(16, 5, 5, 1, date,
                                58, false, "Hey this is error message16", 15));
                        notificationArray.add(new Notification(5, 5, 5, 2, date,
                                58, false, "Hey this is error message5", 15));
                        break;
                    case 6:
                        notificationArray.add(new Notification(6, 6, 6, 1, date,
                                58, false, "Hey this is error message6", 15));
                        notificationArray.add(new Notification(15, 6, 6, 1, date,
                                58, false, "Hey this is error message15", 15));
                        break;
                }
            }
        });
    }

    @Override
    public void getNotificationsBySpecificAccount(final int accountId, int start, int num, final NotificationsDataRequestHandler handler) {
        cacheManager.getNotificationRelatedToAccountJob(accountId, 0, 0, new AccountNotificationsHandler() {

            @Override
            public void onNotificationsRelatedToAccountDownloadFinished(ArrayList<Notification> notifications) {
                java.util.Date date = new Date();
                date.setTime(20102020);
                ArrayList<Notification> notificationArray = new ArrayList<>();

                if (accountId == 1) {
                    notificationArray.add(new Notification(1, 1, 1, 1, date,
                            58, false, "Hey this is error message1", 15));
                    notificationArray.add(new Notification(3, 3, 3, 1, date,
                            58, false, "Hey this is error message3", 15));
                    notificationArray.add(new Notification(6, 6, 6, 1, date,
                            58, false, "Hey this is error message6", 15));
                    notificationArray.add(new Notification(7, 1, 1, 1, date,
                            58, false, "Hey this is error message7", 15));
                    notificationArray.add(new Notification(8, 1, 1, 1, date,
                            58, false, "Hey this is error message8", 15));
                    notificationArray.add(new Notification(9, 1, 1, 1, date,
                            58, false, "Hey this is error message9", 15));
                    notificationArray.add(new Notification(10, 2, 2, 1, date,
                            58, false, "Hey this is error message10", 15));
                    notificationArray.add(new Notification(11, 2, 2, 1, date,
                            58, false, "Hey this is error message11", 15));
                    notificationArray.add(new Notification(12, 3, 3, 1, date,
                            58, false, "Hey this is error message12", 15));
                    notificationArray.add(new Notification(13, 4, 4, 1, date,
                            58, false, "Hey this is error message13", 15));
                    notificationArray.add(new Notification(14, 5, 5, 1, date,
                            58, false, "Hey this is error message14", 15));
                    notificationArray.add(new Notification(15, 6, 6, 1, date,
                            58, false, "Hey this is error message15", 15));
                    notificationArray.add(new Notification(16, 5, 5, 1, date,
                            58, false, "Hey this is error message16", 15));
                } else {
                    notificationArray.add(new Notification(2, 2, 2, 2, date,
                            58, false, "Hey this is error message2", 15));
                    notificationArray.add(new Notification(4, 4, 4, 2, date,
                            58, false, "Hey this is error message4", 15));
                    notificationArray.add(new Notification(5, 5, 5, 2, date,
                            58, false, "Hey this is error message5", 15));
                }

                handler.onNotificationsReceived(notificationArray);

            }
        });
    }


}
