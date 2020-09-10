package com.tsofen.agsenceapp.BackgroundServices;


import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class AppLifecycleObserver implements LifecycleObserver {

    public static final String TAG = AppLifecycleObserver.class.getName();
    Runnable runnableCode;
    Handler handler = new Handler();
    CacheMgr cacheMgr = CacheMgr.getInstance();
    public static int count = 0; // test

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onEnterForeground() {
        Log.d("Handlers", "onstart");
        cacheMgr.initializeAllServices();
        /*
        runnableCode = new Runnable() {
            @Override
            public void run() {
                // Do something here on the main thread
                Log.d("Handlers", "thread "+ count + " launched succesfully");
                count++;

                // Repeat this the same runnable code block again another 2 seconds
                // 'this' is referencing the Runnable object
                handler.postDelayed(this, 2000);





        };
        // Start the initial runnable task by posting through the handler
        handler.post(runnableCode);
        */
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onEnterBackground() {
        Log.d("Handlers", "onstop");
        handler.removeCallbacks(runnableCode);

    }

}