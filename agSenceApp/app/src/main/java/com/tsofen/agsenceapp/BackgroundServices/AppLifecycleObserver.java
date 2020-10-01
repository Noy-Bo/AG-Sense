package com.tsofen.agsenceapp.BackgroundServices;

import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;


//  ============= MAIN PERIODIC THREAD HERE + OBSERVER FORGOUNRD \ BACKGROUND ================


public class AppLifecycleObserver implements LifecycleObserver {

    public static final String TAG = AppLifecycleObserver.class.getName();
    private CacheMgr cacheMgr = CacheMgr.getInstance();



    @OnLifecycleEvent(Lifecycle.Event.ON_START)

    //Test
    public void onEnterForeground() {
        Log.d("Observer", "entering foreground...");
        cacheMgr.getThreadHandlerForGetDevicesPeriodic().post(cacheMgr.getGetDevicesPeriodicRunnable());
    }

   @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onEnterBackground() {
        Log.d("Observer", "entering background...");
        cacheMgr.getThreadHandlerForGetDevicesPeriodic().removeCallbacksAndMessages(null);
        cacheMgr.setStopGetDevicesPeriodic(true);

    }

}