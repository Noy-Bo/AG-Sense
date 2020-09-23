package com.tsofen.agsenceapp.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.tsofen.agsenceapp.R;

public class updateDeviceNotifNumbers implements  Runnable {
    private int healthyDevicesNum;
    private int faultyDevicesNum;
    private int notificationNum;
    private Context context;
    private Activity activity;

    public updateDeviceNotifNumbers(int healthyDevicesNum, int faultyDevicesNum , int notificationNum,Context context) {
        this.healthyDevicesNum = healthyDevicesNum;
        this.faultyDevicesNum = faultyDevicesNum;
        this.context = context;
        activity= (Activity) context;
        this.notificationNum= notificationNum;
    }


    @Override
    public void run() {
        TextView healthyDevices = activity.findViewById(R.id.num_of_healthy_devices);
        TextView faultyDevices = activity.findViewById(R.id.num_of_faulty_devices);
        TextView notification = activity.findViewById(R.id.textView4);
        if (healthyDevices != null) {
            healthyDevices.setText(String.valueOf(healthyDevicesNum));
        }
        if (faultyDevices != null) {
            faultyDevices.setText(String.valueOf(faultyDevicesNum));
        }
        if (healthyDevices != null) {
            notification.setText(String.valueOf(notificationNum));
        }
    }
}
