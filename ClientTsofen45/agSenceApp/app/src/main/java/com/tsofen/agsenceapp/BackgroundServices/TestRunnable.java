package com.tsofen.agsenceapp.BackgroundServices;

import android.util.Log;

public class TestRunnable implements Runnable {
    public static int count = 0; // test
    @Override
    public void run() {
        // Do something here on the main thread
        Log.d("Handlers", "thread "+ count + " launched succesfully");
        count++;
    }
}
