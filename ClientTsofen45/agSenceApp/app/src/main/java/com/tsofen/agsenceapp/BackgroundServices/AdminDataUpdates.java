package com.tsofen.agsenceapp.BackgroundServices;

import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.dataServices.AccountsHandler;
import com.tsofen.agsenceapp.dataServices.DevicesHandler;
import com.tsofen.agsenceapp.dataServices.NotificationsHandler;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.Notification;

import java.util.ArrayList;
import java.util.List;

public class AdminDataUpdates implements Runnable {

    @Override
    public void run() {
       final CacheMgr c = CacheMgr.getInstance();
        c.getAccountsJob(0, 0,  new AccountsHandler() {
            @Override
            public void onAccountsDownloadFinished(List<Account> accounts) {
                c.setAccounts(accounts);
            }
        });

        c.getDevicesJob(0, 0, new DevicesHandler() {
            @Override
            public void onDevicesDownloadFinished(List<Devices> devices) {
                c.setDevices(devices);
            }

            @Override
            public void onDevicesRelatedToAccountDownloadFinished(ArrayList<Devices> devices) {

            }
        });


        for (Devices device : c.getDevices()) {

            c.getNotificationRelatedToDeviceJob(device.getId(), 0, 0, new NotificationsHandler() {
                @Override
                public void onNotificationsDownloadFinished(ArrayList<Notification> notifications) {
                    c.setNotifications(notifications);
                }

                @Override
                public void onNotificationsRelatedToAccountDownloadFinished(ArrayList<Notification> notifications) {

                }

                @Override
                public void onNotificationsRelatedToDeviceDownloadFinished(ArrayList<Notification> notifications) {

                }
            });

        }
    }


}
