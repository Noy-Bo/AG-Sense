package com.tsofen.agsenceapp.BackgroundServices;

import android.os.SystemClock;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.tsofen.agsenceapp.adaptersInterfaces.DeviceDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.DeviceDataAdapter;
import com.tsofen.agsenceapp.dataServices.DevicesHandler;
import com.tsofen.agsenceapp.entities.Devices;
import java.util.ArrayList;
import java.util.List;


//  ============= MAIN PERIODIC THREAD HERE + OBSERVER FORGOUNRD \ BACKGROUND ================


public class AppLifecycleObserver implements LifecycleObserver {

    public static final String TAG = AppLifecycleObserver.class.getName();
    private CacheMgr cacheMgr = CacheMgr.getInstance();
    private final static int waitInterval = 30000;

    // periodic task design
    private DevicesHandler handlerForRepeatedJob  = new DevicesHandler() {
        @Override
        public void onDevicesDownloadFinished(List<Devices> devices) {
            Log.d("repeated","repeated task completed");
            cacheMgr.getThreadHandlerForServerPeriod().post(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(waitInterval);
                    cacheMgr.getThreadHandlerForServerPeriod().post(mainThread);
                }
            });
        }

        @Override
        public void onDevicesRelatedToAccountDownloadFinished(ArrayList<Devices> devices) {
        }
    };
    private Runnable mainThread;


    @OnLifecycleEvent(Lifecycle.Event.ON_START)

    //Test
    public void onEnterForeground() {


       /* mainThread = new CacheMgr.GetDevicesJobRunnable(0, 5, handlerForRepeatedJob);
        cacheMgr.getThreadHandlerForServerPeriod().post(mainThread);*/
    }

   @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onEnterBackground() {
        Log.d("Handlers", "onstop");
        /*
        cacheMgr.gethandlerThreadServerPeriodic().interrupt();

         */
    }

}