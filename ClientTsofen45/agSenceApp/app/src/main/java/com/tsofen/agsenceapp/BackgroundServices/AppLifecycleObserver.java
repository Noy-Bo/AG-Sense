package com.tsofen.agsenceapp.BackgroundServices;


import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;


import com.tsofen.agsenceapp.dataServices.DevicesHandler;
import com.tsofen.agsenceapp.entities.Devices;

import java.util.ArrayList;
import java.util.List;

public class AppLifecycleObserver implements LifecycleObserver {

    public static final String TAG = AppLifecycleObserver.class.getName();
    Runnable runnableCode;
    Handler handler = new Handler();
    CacheMgr cacheMgr = CacheMgr.getInstance();
    public static int count = 0; // test
    /*
    private final static int waitInterval = 10000;
    // periodic task design
    private DevicesHandler handlerForRepeatedJob  = new DevicesHandler() {
        @Override
        public void onDevicesDownloadFinished(List<Devices> devices) {
            Log.d("repeated","repeated task completed");
            cacheMgr.getThreadHandlerForServerPeriod().post(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(waitInterval);
                    cacheMgr.getDevicesJob(handlerForRepeatedJob);
                }
            });
        }

        @Override
        public void onDevicesRelatedToAccountDownloadFinished(ArrayList<Devices> devices) {

        }
    };
    */



    /// -----------this class is used only for testing at the moment..-----------

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onEnterForeground() {

   //test for periodicjob
   // cacheMgr.getDevicesJob(handlerForRepeatedJob);

   cacheMgr.getDevicesJob(0, 0, new DevicesHandler() {
       @Override
       public void onDevicesDownloadFinished(List<Devices> devices) {
           Log.d("innerclasstest","onDevicesDownloadFinished");
       }

       @Override
       public void onDevicesRelatedToAccountDownloadFinished(ArrayList<Devices> devices) {
           Log.d("innerclasstest","onDevicesRelatedToAccountDownloadFinished");
       }
   });
        /*cacheMgr.getThreadHandlerForGetDevices().post(cacheMgr.getDevicesJob(0, 0, new DevicesHandler() {
            @Override
            public void onDevicesDownloadFinished(List<Devices> devices) {

            }

            @Override
            public void onDevicesRelatedToAccountDownloadFinished(ArrayList<Devices> devices) {

            }
        });)*/



    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onEnterBackground() {
        Log.d("Handlers", "onstop");
        //cacheMgr.getThreadHandlerForServerPeriod().removeCallbacksAndMessages();

    }

}