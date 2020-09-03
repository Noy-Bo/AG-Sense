package com.tsofen.agsenceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.NotificationListAdaptor;
import com.tsofen.agsenceapp.entities.Notification;
import com.tsofen.agsenceapp.utils.ColorStatus;

import java.util.ArrayList;
import java.util.Date;

public class AccountDashboardActivity extends AppCompatActivity {
    static ArrayList<Notification> notificationArray = new ArrayList<>();
    ArrayAdapter<Notification> notificationArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_dashboard);



        java.util.Date date = new Date();
                date.setTime(20102020);
        notificationArray.add( new Notification(15,25,25,10,date,
                58,false,"Hey this is error message1" , 15));
        notificationArray.add( new Notification(15,25,25,10,date,
                58,false,"Hey this is error message2" , 15));
        notificationArray.add( new Notification(15,25,25,10,date,
                58,false,"Hey this is error message3" , 15));
        notificationArray.add( new Notification(15,25,25,10,date,
                58,false,"Hey this is error message4" , 15));
        notificationArray.add( new Notification(15,25,25,10,date,
                58,false,"Hey this is error message5" , 15));
        notificationArray.add( new Notification(15,25,25,10,date,
                58,false,"Hey this is error message6" , 15));
        notificationArray.add( new Notification(15,25,25,10,date,
                58,false,"Hey this is error message7" , 15));
        notificationArray.add( new Notification(15,25,25,10,date,
                58,false,"Hey this is error message8" , 15));
        notificationArray.add( new Notification(15,25,25,10,date,
                58,false,"Hey this is error message9" , 15));
        notificationArray.add( new Notification(15,25,25,10,date,
                58,false,"Hey this is error message10" , 15));




        notificationArrayAdapter = new ArrayAdapter<Notification>(this,R.layout.notifictation_item_shape);
        ListView notificationList = findViewById(R.id.notification_list);
        notificationArrayAdapter = new NotificationListAdaptor(this,notificationArray);
        notificationList.setAdapter(notificationArrayAdapter);







    }

    public void DeviceView(View view) {
        Intent intent = new Intent(this, DeviceStatus.class);
        startActivity(intent);
    }
}