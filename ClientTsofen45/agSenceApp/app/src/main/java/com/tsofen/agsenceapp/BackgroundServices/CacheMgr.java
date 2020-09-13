package com.tsofen.agsenceapp.BackgroundServices;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.tsofen.agsenceapp.CacheManagerAPI;
import com.tsofen.agsenceapp.dataServices.AccountsHandler;
import com.tsofen.agsenceapp.dataServices.DeviceDataHandler;
import com.tsofen.agsenceapp.dataServices.NotificationsHandler;
import com.tsofen.agsenceapp.dataServices.OnDataReadyHandler;
import com.tsofen.agsenceapp.dataServices.DevicesHandler;
import com.tsofen.agsenceapp.dataServices.LoginHandler;
import com.tsofen.agsenceapp.dataServices.TextDownloader;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.User;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CacheMgr implements CacheManagerAPI {
    private static CacheMgr cacheMgr = null;

    //handles
    private HandlerThread handlerThreadServerPeriodic = new HandlerThread("serverPeriodicJobHandler");
    private HandlerThread handlerThreadLogin = new HandlerThread("handlerThreadLogin");
    private Handler threadHandler;
    private CacheMgr() {
        initializeAllServices();
    }

    public static CacheMgr getInstance()
    {
        if (cacheMgr == null)
            cacheMgr = new CacheMgr(); // TODO - add synchronized.

        return cacheMgr;
    }


    public class getDevicesRunnable implements Runnable // TODO - transform to anonymous class
    {
        private DevicesHandler handler;
        @Override
        public void run()
        {
                TextDownloader downloader = new TextDownloader();
                downloader.setOnDownloadCompletedListener(new OnDataReadyHandler() { // specifying a new handler for textDownloader
                    @Override
                    public void onDataDownloadCompleted(String downloadedData) { // creating a new OnDataReadyHandler, and inserting it to downloader as handler.
                        Log.d("DOWNLOAD","Download text is "+downloadedData);

                        // we have  results at downloadedData, but we now presenting dummy data.

                        Date date = new Date();
                        date.getTime();
                        Date date1 = new Date();
                        date.setTime(20102020);

                        List<Devices> devices = new ArrayList<>();
                        devices.add(new Devices(0,1,1,"Device",date,date1,true));
                        devices.add(new Devices(0,1,1,"Device",date,date1,true));
                        devices.add(new Devices(0,1,1,"Device",date,date1,true));
                        devices.add(new Devices(0,1,1,"Device",date,date1,true));
                        devices.add(new Devices(0,1,1,"Device",date,date1,true));
                        devices.add(new Devices(0,1,1,"Device",date,date1,true));
                        devices.add(new Devices(0,1,1,"Device",date,date1,true));
                        devices.add(new Devices(0,1,1,"Device",date,date1,true));
                        devices.add(new Devices(0,1,1,"Device",date,date1,true));
                        devices.add(new Devices(0,1,1,"Device",date,date1,true));
                        devices.add(new Devices(0,1,1,"Device",date,date1,true));
                        devices.add(new Devices(0,1,1,"Device",date,date1,true));
                        devices.add(new Devices(0,1,1,"Device",date,date1,true));
                        devices.add(new Devices(0,1,1,"Device",date,date1,true));
                        devices.add(new Devices(0,1,1,"Device",date,date1,true));
                        devices.add(new Devices(0,1,1,"Device",date,date1,true));
                        devices.add(new Devices(0,1,1,"Device",date,date1,true));

                        if (handler != null) {
                            handler.onDevicesDownloadFinished(devices); //  chaining the handlers. -> updating the main handler that devices are ready ---changing
                        }
                    }

                    @Override
                    public void onDownloadError() {

                    }
                });
                downloader.getText("https://www.google.com"); // TODO create the URL in getDevicesRunnable Ctor. // via field.


        }
    }


    private void initializeAllServices() // remove public
    {
        //initializing the serverPeriodJob
        handlerThreadLogin.start();
        threadHandler = new Handler(handlerThreadLogin.getLooper());


    }

   // public void serverPeriodicJob()
   // {
            //threadHandler.post(new getDevicesRunnable());

            //threadHandler.post(new WaitPeriod());

   // }

   public void loginJob(final String username, final String password, final LoginHandler handler)
    {
        threadHandler.post(new Runnable() {
            @Override
            public void run() {
                TextDownloader downloader = new TextDownloader();
                downloader.setOnDownloadCompletedListener(new OnDataReadyHandler() {
                    @Override
                    public void onDataDownloadCompleted(String downloadedData) {
                        try {
                            //parsing json
                            JSONObject userJSON = new JSONObject(downloadedData);
                            User user;
                            //{"accountid":8,"id":9,"type":"account","email":"ibra123@gmail.com","username":"ibra"}

                            if(userJSON.getString("type").equals("account"))
                            {
                                user = new Account(userJSON.getInt("id"), userJSON.getString("username")   , userJSON.getString("email"),false, userJSON.getInt("accountid"));
                            }
                            else { 
                                user = new Admin(userJSON.getInt("id"), userJSON.getString("username"), userJSON.getString("email"));
                            }
                            handler.onLoginSuccess(user);
                        }
                        catch (Exception e)
                        {
                            handler.onLoginFailure();
                        }
                    }

                    @Override
                    public void onDownloadError() {
                        handler.onLoginFailure();
                    }
                });
                downloader.getText("http://206.72.198.59:8080/ServerTsofen45/User/Login?username="+username+"&password="+password);

            }
        });
    }

    @Override
    public void getAccountsJob(int start, int num, AccountsHandler handler) {

    }

    @Override
    public void getDevicesJob(int start, int num, DevicesHandler handler) {

    }

    @Override
    public void getNotificationsJob(int start, int num, NotificationsHandler handler) {

    }

    @Override
    public void getDevicesRelatedToAccountJob(int accountId, int start, int num, DevicesHandler handler) {

    }

    @Override
    public void getNotificationRelatedToDeviceJob(int deviceId, int start, int num, NotificationsHandler handler) {

    }

    @Override
    public void getNotificationRelatedToAccountJob(int accountId, int start, int num, NotificationsHandler handler) {

    }

    @Override
    public void getSpecificDeviceDataByIdJob(int deviceId, int start, int num, DeviceDataHandler handler) {

    }

}
