package com.tsofen.agsenceapp.BackgroundServices;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.tsofen.agsenceapp.dataServices.OnDataReadyHandler;
import com.tsofen.agsenceapp.dataServices.OnDevicesReadyHandler;
import com.tsofen.agsenceapp.dataServices.OnLogin;
import com.tsofen.agsenceapp.dataServices.OnMainThreadFinished;
import com.tsofen.agsenceapp.dataServices.TextDownloader;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.User;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CacheMgr {
    private static CacheMgr cacheMgr = null;



    //handles
    public Runnable mainThread;
    private HandlerThread handlerThreadServerPeriodic = new HandlerThread("serverPeriodicJobHandler");
    private HandlerThread handlerThreadLogin = new HandlerThread("handlerThreadLogin");
    private Handler threadHandlerForLogin;

    public Handler getThreadHandlerForServerPeriod() {
        return threadHandlerForServerPeriod;
    }

    private Handler threadHandlerForServerPeriod;

    
    private CacheMgr() {
        initializeAllServices();
    }

    public static CacheMgr getInstance()
    {
        if (cacheMgr == null)
            cacheMgr = new CacheMgr(); // TODO - add synchronized.

        return cacheMgr;
    }

    private void initializeAllServices() // remove public
    {
        //initializing the handlers
        handlerThreadLogin.start();
        threadHandlerForLogin = new Handler(handlerThreadLogin.getLooper());

        handlerThreadServerPeriodic.start();
        threadHandlerForServerPeriod = new Handler(handlerThreadServerPeriodic.getLooper());



    }


    public void getDevicesJob(final OnDevicesReadyHandler handler) // result in OnDevicesReadyHandler
        {


            threadHandlerForServerPeriod.post(new Runnable() {
                @Override
                public void run() {

                    TextDownloader downloader = new TextDownloader();
                    downloader.setOnDownloadCompletedListener(new OnDataReadyHandler() {
                        @Override
                        public void onDataDownloadCompleted(String downloadedData) {
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
                                handler.onDevicesReady(devices); //  chaining the handlers. -> updating the main handler that devices are ready ---changing
                            }

                        }

                        @Override
                        public void onDownloadError() {
                            //code this case.
                        }
                    });
                    downloader.getText("https://www.google.com/");
                }
            });
        }




   public void loginJob(final String username, final String password, final OnLogin handler)
    {
        threadHandlerForLogin.post(new Runnable() {
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




     /*public class getDevicesRunnable implements Runnable // TODO - transform to anonymous class
    {
        private OnDevicesReadyHandler handler;
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
                            handler.onDevicesReady(devices); //  chaining the handlers. -> updating the main handler that devices are ready ---changing
                        }
                    }

                    @Override
                    public void onDownloadError() {

                    }
                });
                downloader.getText("https://www.google.com"); // TODO create the URL in getDevicesRunnable Ctor. // via field.


        }
    }*/



}
