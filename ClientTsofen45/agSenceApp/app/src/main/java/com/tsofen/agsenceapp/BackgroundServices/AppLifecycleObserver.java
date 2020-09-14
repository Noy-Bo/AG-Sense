package com.tsofen.agsenceapp.BackgroundServices;


import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.tsofen.agsenceapp.dataServices.OnDevicesReadyHandler;
import com.tsofen.agsenceapp.entities.Devices;

import java.util.List;

public class AppLifecycleObserver implements LifecycleObserver {

    public static final String TAG = AppLifecycleObserver.class.getName();
    Runnable runnableCode;
    Handler handler = new Handler();
    CacheMgr cacheMgr = CacheMgr.getInstance();
    public static int count = 0; // test

    // periodic task design
    private OnDevicesReadyHandler handlerForRepeatedJob  = new OnDevicesReadyHandler() {
        @Override
        public void onDevicesReady(List<Devices> devices) {
            Log.d("repeated","repeated task completed");
            cacheMgr.getThreadHandlerForServerPeriod().post(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(10000);
                    cacheMgr.getDevicesJob(handlerForRepeatedJob);
                }
            });
        }
    };


    /// -----------this class is used only for testing at the moment..-----------

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onEnterForeground() {

    cacheMgr.getDevicesJob(handlerForRepeatedJob);

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onEnterBackground() {
        Log.d("Handlers", "onstop");
        //cacheMgr.getThreadHandlerForServerPeriod().removeCallbacksAndMessages();

    }

}