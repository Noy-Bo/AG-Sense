package com.tsofen.agsenceapp.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.NotificationListAdaptor;
import com.tsofen.agsenceapp.entities.Notification;
import com.tsofen.agsenceapp.utils.ColorStatus;

import java.util.ArrayList;
import java.util.Date;

public class AccountDashboardActivity extends SearchBaseActivity {
    static ArrayList<Notification> notificationArray = new ArrayList<>();
    ArrayAdapter<Notification> notificationArrayAdapter;
    Dialog myDialog;
    View popupView;
    Button reset ;
    boolean displayReadNotifications = false;
    boolean displayUnreadNotifications = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_account_dashboard, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_account_dashboard);
        myDialog = new Dialog(this);

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


    public void gotoFilter(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        popupView = inflater.inflate(R.layout.pop_up1, null);
        reset=(Button) popupView.findViewById(R.id.filterResetButton);
        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched

    }

    public void listen(final View view) {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        final TextView textView = (TextView)  popupView.findViewById(R.id.fromDate);
        DatePickerDialog picker = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1++;

                        textView.setText(i+"/"+i1+"/"+i2);
                    }

                }, year, month, day);
        picker.show();
    }
    public void listen1(final View view) {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        final TextView textView = (TextView)  popupView.findViewById(R.id.toDate);
        DatePickerDialog picker = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1++;

                        textView.setText(i+"/"+i1+"/"+i2);
                    }

                }, year, month, day);
        picker.show();
    }


    public void reset(View view) {

        TextView fromDate =(TextView) popupView.findViewById(R.id.fromDate);
        TextView toDate =(TextView) popupView.findViewById(R.id.toDate);
        toDate.setText("");
        fromDate.setText("");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_admin_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity();
            } else {
                super.onBackPressed();
            }
        }
    }

}