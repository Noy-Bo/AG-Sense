package com.tsofen.agsenceapp.BackgroundServices;

import android.util.Log;

import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.dataServices.AccountsHandler;
import com.tsofen.agsenceapp.dataServices.BaseHandler;
import com.tsofen.agsenceapp.dataServices.DevicesHandler;
import com.tsofen.agsenceapp.dataServices.NotificationsHandler;
import com.tsofen.agsenceapp.dataServices.OnDataReadyHandler;
import com.tsofen.agsenceapp.dataServices.ServicesName;
import com.tsofen.agsenceapp.dataServices.TextDownloader;
import com.tsofen.agsenceapp.dataServices.UrlConnectionMaker;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.Notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminGetDevicesPeriodicRunnable implements Runnable {

    private BaseHandler handler;
    Map<String, String> params;
    ServicesName serviceName;

    public AdminGetDevicesPeriodicRunnable(BaseHandler handler, Map<String, String> params,ServicesName serviceName) {
        this.serviceName = serviceName;
        this.handler = handler;
        this.params = params;
    }


    @Override
    public void run() {
        UrlConnectionMaker urlConnectionMaker = new UrlConnectionMaker();
        TextDownloader.getInstance().getText(urlConnectionMaker.createUrl(serviceName, this.params), new OnDataReadyHandler() {
            @Override
            public void onDataDownloadCompleted(String downloadedData) {
                Log.d("AdminPeriodic","onDataDownloadCompleted");
                // JSON Parser

                List retrievedEntitiesList = new ArrayList<>();

                if (handler instanceof DevicesHandler)
                {
                    retrievedEntitiesList = CacheManagerHandlers.parseToJsonArray(downloadedData, new Devices());
                    ((DevicesHandler) handler).onDevicesDownloadFinished((List<Devices>) retrievedEntitiesList);
                }
            }

            @Override
            public void onDownloadError() {
                Log.d("AdminPeriodic","onDownloadError");
            }
        });
    }
}
