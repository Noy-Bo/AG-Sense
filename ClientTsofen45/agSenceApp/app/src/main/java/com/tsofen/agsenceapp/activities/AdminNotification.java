package com.tsofen.agsenceapp.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.NotificationListAdaptor;
import com.tsofen.agsenceapp.entities.Notification;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class AdminNotification extends SearchBaseActivity {
    static ArrayList<Notification> notificationArray = new ArrayList<>();
    ArrayAdapter<Notification> notificationArrayAdapter;
    Dialog popUpDialog;
    Button reset ;
    boolean displayReadNotifications = false;
    boolean displayUnreadNotifications = false;
    ImageView closePopUpImage;
    ImageView fromDateCalenderImage;
    ImageView toDateCalenderImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_admin_notification, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_admin_notifications);
        popUpDialog = new Dialog(this);
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

    public void search(View view) {
        setContentView(R.layout.activity_account_dashboard);
    }

    public void displayReadNotifications(final View view) {
        TextView displayFaultyBox = view.findViewById(R.id.read_button);

        if (displayReadNotifications == true) // do not display faulty devices.
        {
            displayFaultyBox.setBackground(ContextCompat.getDrawable(this,R.drawable.blue_shape_squares));
            displayFaultyBox.setTextColor(ContextCompat.getColor(this,R.color.dark_blue));
            displayReadNotifications = false;
        }
        else if (displayReadNotifications == false) // displaying the faulty device.
        {
            displayFaultyBox.setBackground(ContextCompat.getDrawable(this,R.drawable.white_shape_squares));
            displayFaultyBox.setTextColor(ContextCompat.getColor(this,R.color.white));
            displayReadNotifications = true;
        }
    }

    public void displayUnreadNotifications(final View view) {
        TextView displayHealthyBox = view.findViewById(R.id.unread_button);

        if (displayUnreadNotifications == true) // do not display healthy devices.
        {
            displayHealthyBox.setBackground(ContextCompat.getDrawable(this,R.drawable.blue_shape_squares));
            displayHealthyBox.setTextColor(ContextCompat.getColor(this,R.color.dark_blue));
            displayUnreadNotifications = false;
        }
        else if (displayUnreadNotifications == false) // displaying the healthy device.
        {
            displayHealthyBox.setBackground(ContextCompat.getDrawable(this,R.drawable.white_shape_squares));
            displayHealthyBox.setTextColor(ContextCompat.getColor(this,R.color.white));
            displayUnreadNotifications = true;
        }

    }
    public void create(View view) {
        Intent intent = new Intent(this, NewAccount.class);
        startActivity(intent);
    }


    public void gotoFilter(View view) {
        // show the layout of the popup window
        popUpDialog.setContentView(R.layout.pop_up1);
        closePopUpImage = (ImageView) popUpDialog.findViewById(R.id.closePopUp);
        reset = (Button) popUpDialog.findViewById(R.id.filterResetButton);
        fromDateCalenderImage = (ImageView) popUpDialog.findViewById(R.id.fromDateCalender);
        toDateCalenderImage = (ImageView) popUpDialog.findViewById(R.id.toDateCalender);
        //search ??
        closePopUpImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUpDialog.dismiss();
            }
        });
        fromDateCalenderImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listen(popUpDialog);
            }
        });
        toDateCalenderImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listen1(this);
            }
        });
        popUpDialog.show();
    }

    public void listen(final Dialog view) {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        final TextView textView = (TextView) popUpDialog.findViewById(R.id.fromDate);
        DatePickerDialog picker = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1++;
                        textView.setText(i + "/" + i1 + "/" + i2);
                    }
                }, year, month, day);
        picker.show();
    }

    public void listen1(final View.OnClickListener view) {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        final TextView textView = (TextView) popUpDialog.findViewById(R.id.toDate);
        DatePickerDialog picker = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1++;
                        textView.setText(i + "/" + i1 + "/" + i2);
                    }
                }, year, month, day);
        picker.show();
    }


    public void reset(View view) {
        TextView fromDate = (TextView) popUpDialog.findViewById(R.id.fromDate);
        TextView toDate = (TextView) popUpDialog.findViewById(R.id.toDate);
        TextView displayFaultyBox = popUpDialog.findViewById(R.id.read_button);
        TextView displayHealthyBox = popUpDialog.findViewById(R.id.unread_button);
        toDate.setText("");
        fromDate.setText("");
        displayReadNotifications = false;
        displayUnreadNotifications = false; // false
        displayFaultyBox.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
        displayFaultyBox.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
        displayHealthyBox.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
        displayHealthyBox.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
    }

}