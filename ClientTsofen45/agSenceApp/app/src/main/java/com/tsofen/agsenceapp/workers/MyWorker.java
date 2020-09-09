package com.tsofen.agsenceapp.workers;

import android.content.Context;


import androidx.annotation.NonNull;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {

    public MyWorker(@NonNull Context appContext, @NonNull WorkerParameters workerParams)
    {
        super(appContext, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        // Do the work here--in this case, upload the images.
        // Write function to check battery status

        // Indicate whether the work finished successfully with the Result
        return Result.success();
    }
    //Result.success(): The work finished successfully.
    //Result.failure(): The work failed.
    //Result.retry(): The work failed and should be tried at another time according to its retry policy.


    WorkRequest uploadWorkRequest =
            new PeriodicWorkRequest.Builder(MyWorker.class).build();
}
