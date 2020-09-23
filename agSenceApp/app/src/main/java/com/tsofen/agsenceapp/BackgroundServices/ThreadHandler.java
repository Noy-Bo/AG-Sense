package com.tsofen.agsenceapp.BackgroundServices;


import android.os.HandlerThread;



public class ThreadHandler extends HandlerThread {


    public ThreadHandler(String name) {
        super(name);
    }

    public ThreadHandler(String name, int priority) {
        super(name, priority);
    }
}
