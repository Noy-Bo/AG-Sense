package com.tsofen.agsenceapp.BackgroundServices;

import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

//  ==========================================================================================
//  ------------------------ OBSERVER FORGOUNRD \ BACKGROUND ---------------------------------
//  ==========================================================================================

/**
 * this class implements an observer that enables to sign activities to notify.
 * the observer notify whenever we go into background \ foreground via onEnterForeground & onEnterBackground.
 * we sign adminDashboard activity here to the onBackground to signal the Admin Periodic task to stop executing.
 */
public class AppLifecycleObserver implements LifecycleObserver {

    public static final String TAG = AppLifecycleObserver.class.getName();
    private CacheMgr cacheMgr = CacheMgr.getInstance();


    /**
     * this function signal the activities that signed to this observer that activities are now on foreground.
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onEnterForeground() {
        Log.d("Observer", "entering foreground...");
        cacheMgr.getThreadHandlerForGetDevicesPeriodic().post(cacheMgr.getGetDevicesPeriodicRunnable());
    }

    /**
     * this function signal the activities that signed to this observer that activities are now on background.
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onEnterBackground() {
        Log.d("Observer", "entering background...");
        cacheMgr.getThreadHandlerForGetDevicesPeriodic().removeCallbacksAndMessages(null);
        cacheMgr.setStopGetDevicesPeriodic(true);

    }

}