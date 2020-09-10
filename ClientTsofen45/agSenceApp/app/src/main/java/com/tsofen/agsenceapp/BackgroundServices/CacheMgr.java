package com.tsofen.agsenceapp.BackgroundServices;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.tsofen.agsenceapp.dataServices.OnDataReadyHandler;
import com.tsofen.agsenceapp.dataServices.OnDevicesReadyHandler;
import com.tsofen.agsenceapp.dataServices.TextDownloader;
import com.tsofen.agsenceapp.entities.Devices;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class CacheMgr {
    private static CacheMgr cacheMgr = null;

    //handles
    private HandlerThread handlerThread = new HandlerThread("serverPeriodicJobHandler");
    private Handler threadHandler;
    private CacheMgr() {}

    public static CacheMgr getInstance()
    {
        if (cacheMgr == null)
            cacheMgr = new CacheMgr(); // TODO - add synchronized.

        return cacheMgr;
    }


    public class getDevicesRunnable implements Runnable
    {
        private OnDevicesReadyHandler handler;
        @Override
        public void run()
        {
                TextDownloader downloader = new TextDownloader();
                downloader.setOnDownloadCompletedListener(new OnDataReadyHandler() { // specifying a new handler for textDownloader
                    @Override
                    public void onDataDownloadCompleted(String downloadedData) { // overriding the method when finished.
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
                            handler.onDevicesReady(devices); // updating the main handler that devices are ready ---changing
                        }
                    }
                });
                downloader.getText("https://www.google.com"); // TODO create the URL in getDevicesRunnable Ctor. // via field.


        }
    }


   /*  -------IMPLEMENTATION WITHOUT HANDLER. --------
   public void getDevices(final OnDevicesReadyHandler handler)
    {

        TextDownloader downloader = new TextDownloader();
        downloader.setOnDownloadCompletedListener(new OnDataReadyHandler() { // specifying a new handler for textDownloader
            @Override
            public void onDataDownloadCompleted(String downloadedData) { // overriding the method when finished.
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
                    handler.onDevicesReady(devices); // updating the main handler that devices are ready
                }
            }
        });
       //Thread t = new Thread(downloader);
       //t.start();
        // return dummy data.




        //end
    }*/

    public void initializeAllServices() // remove public
    {
        //initializing the serverPeriodJob
        handlerThread.start();
        threadHandler = new Handler(handlerThread.getLooper());
        serverPeriodicJob();

    }

    public void serverPeriodicJob() // remove public
    {
            threadHandler.post(new getDevicesRunnable());

            //threadHandler.post(new WaitPeriod());

    }




}
