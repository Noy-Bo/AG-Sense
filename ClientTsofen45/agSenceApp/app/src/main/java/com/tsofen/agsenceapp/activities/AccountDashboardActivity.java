package com.tsofen.agsenceapp.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.NotificationListAdaptor;
import com.tsofen.agsenceapp.entities.Notification;
import com.tsofen.agsenceapp.utils.ColorStatus;

import java.util.ArrayList;

public class AccountDashboardActivity extends AppCompatActivity {
    static ArrayList<Notification> notificationArray = new ArrayList<>();
    ArrayAdapter<Notification> notificationArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_dashboard);


        notificationArray.add( new Notification("Main Bank Machine","ATM","Battery running low",
                "Leumi","12:30", ColorStatus.yellow));
        notificationArray.add(new Notification("Tel-Aviv branch machine","ATM","Device has been moved 2 meters",
                "Leumi","jul 23",ColorStatus.red));
        notificationArray.add(new Notification("Ekron street ","ATM","healthy",
                "Discont","Now",ColorStatus.blue));

        notificationArray.add( new Notification("DeviceName","(deviceType)","errorMessage",
                "AccountName","date&time",ColorStatus.yellow));
        notificationArray.add( new Notification("DeviceName","(deviceType)","errorMessage",
                "AccountName","date&time",ColorStatus.red));
        notificationArray.add( new Notification("DeviceName","(deviceType)","errorMessage",
                "AccountName","date&time",ColorStatus.green));
        notificationArray.add( new Notification("DeviceName","(deviceType)","errorMessage",
                "AccountName","date&time",ColorStatus.blue));
        notificationArray.add( new Notification("DeviceName","(deviceType)","errorMessage",
                "AccountName","date&time",ColorStatus.yellow));
        notificationArray.add( new Notification("DeviceName","(deviceType)","errorMessage",
                "AccountName","date&time",ColorStatus.red));
        notificationArray.add( new Notification("DeviceName","(deviceType)","errorMessage",
                "AccountName","date&time",ColorStatus.green));
        notificationArray.add( new Notification("DeviceName","(deviceType)","errorMessage",
                "AccountName","date&time",ColorStatus.blue));
        notificationArray.add( new Notification("DeviceName","(deviceType)","errorMessage",
                "AccountName","date&time",ColorStatus.yellow));
        notificationArray.add( new Notification("DeviceName","(deviceType)","errorMessage",
                "AccountName","date&time",ColorStatus.red));
        notificationArray.add( new Notification("DeviceName","(deviceType)","errorMessage",
                "AccountName","date&time",ColorStatus.green));
        notificationArray.add( new Notification("DeviceName","(deviceType)","errorMessage",
                "AccountName","date&time",ColorStatus.green));



        notificationArrayAdapter = new ArrayAdapter<Notification>(this,R.layout.notifictation_item_shape);
        ListView notificationList = findViewById(R.id.notification_list);
        notificationArrayAdapter = new NotificationListAdaptor(this,notificationArray);
        notificationList.setAdapter(notificationArrayAdapter);







    }
}