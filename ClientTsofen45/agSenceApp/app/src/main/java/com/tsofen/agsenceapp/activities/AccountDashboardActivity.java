package com.tsofen.agsenceapp.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.NotificationListAdaptor;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceDataRequestHandler;
import com.tsofen.agsenceapp.adaptersInterfaces.NotificationsDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.DeviceDataAdapter;
import com.tsofen.agsenceapp.dataAdapters.NotificationsDataAdapter;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.Notification;
import com.tsofen.agsenceapp.entities.Place;
import com.tsofen.agsenceapp.entities.UserMap;
import com.tsofen.agsenceapp.utils.updateDeviceNotifNumbers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class AccountDashboardActivity extends SearchBaseActivity {
    static ArrayList<Notification> notificationArray = new ArrayList<>();
    ArrayAdapter<Notification> notificationArrayAdapter ;
    ArrayList<Devices> devicesList = new ArrayList<>();
    Dialog popUpDialog;
    boolean displayReadNotifications = false;
    boolean displayUnreadNotifications = false;
    Date after ;
    Date before ;
    View contentView;
    ListView notificationListView;
    Button reset; //?
    ImageView closePopUpImage;
    ImageView fromDateCalenderImage;
    ImageView toDateCalenderImage;
    private  long backPressedTime;
    private Toast backtoast;
    UserMap userMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.activity_account_dashboard, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_account_dashboard);
        popUpDialog = new Dialog(this);


        NotificationsDataAdapter.getInstance().getNotificationsBySpecificAccount(((Account)AppBaseActivity.user).getAccountid(), 0, 0, new NotificationsDataRequestHandler() {
            @Override
            public void onNotificationsReceived(List<Notification> notifications) {
                notificationArray.addAll(notifications);
            }
        });
        notificationArrayAdapter = new ArrayAdapter<Notification>(this, R.layout.notifictation_item_shape);
        notificationListView = findViewById(R.id.notification_list);
        notificationArrayAdapter = new NotificationListAdaptor(this,0, notificationArray);
        notificationListView.setAdapter(notificationArrayAdapter);

        DeviceDataAdapter.getInstance().getDevicesRelatedToAccount(((Account)AppBaseActivity.user).getAccountid(),0,0,new DeviceDataRequestHandler() {
            @Override
            public void onDeviceDataLoaded(List<Devices> devices) {
                for(Devices device : devices){
                    devicesList.add(device);
                }
                initialUpdateUI();
            }
        });

    }



    public void goToDevicesStatus(View view) {
        Intent intent = new Intent(this, AccountDevicesStatus.class);
        intent.putExtra("devices",devicesList);
        String filterString;
        if(view.getId() == R.id.account_faulty_devices)
            filterString = "faulty";
        else
            filterString = "healthy";

        intent.putExtra("filter",filterString);
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
                        after = new Date(i, i1, i2);
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
                        before = new Date(i, i1, i2);
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

    public void search(View view) {
        ArrayList<Notification> filterArr = new ArrayList<>();
        for (Notification notification: notificationArray) {
            if(notification.getDate_time().after(after) && notification.getDate_time().before(before) &&
                    ((notification.getReaded()==true && displayReadNotifications) ||
                            (notification.getReaded()==false &&  displayUnreadNotifications))){
                filterArr.add(notification);
            }
        }
        notificationArrayAdapter = new NotificationListAdaptor(AccountDashboardActivity.this, 0, filterArr);
        notificationListView.setAdapter(notificationArrayAdapter);
        updateUIAfterSearch(filterArr.size());
        popUpDialog.cancel();
    }

    public void displayReadNotifications(final View view) {
        TextView displayFaultyBox = view.findViewById(R.id.read_button);

        if (displayReadNotifications == true) // do not display faulty devices.
        {
            displayFaultyBox.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
            displayFaultyBox.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
            displayReadNotifications = false;
        } else if (displayReadNotifications == false) // displaying the faulty device.
        {
            displayFaultyBox.setBackground(ContextCompat.getDrawable(this, R.drawable.white_shape_squares));
            displayFaultyBox.setTextColor(ContextCompat.getColor(this, R.color.white));
            displayReadNotifications = true;
        }
    }

    public void displayUnreadNotifications(final View view) {
        TextView displayHealthyBox = view.findViewById(R.id.unread_button);

        if (displayUnreadNotifications == true) // do not display healthy devices.
        {
            displayHealthyBox.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
            displayHealthyBox.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
            displayUnreadNotifications = false;
        } else if (displayUnreadNotifications == false) // displaying the healthy device.
        {
            displayHealthyBox.setBackground(ContextCompat.getDrawable(this, R.drawable.white_shape_squares));
            displayHealthyBox.setTextColor(ContextCompat.getColor(this, R.color.white));
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
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backtoast.cancel();
            super.finishAffinity();
            return;
        } else {
            backtoast = Toast.makeText(getBaseContext(), "press back again to exit", Toast.LENGTH_SHORT);
            backtoast.show();
        }
        backPressedTime = System.currentTimeMillis();

    }

    private void initialUpdateUI() {

        int healthy =0;
        int faulty =0;
        for (Devices device: devicesList) {
            if(device.getFaulty()==true){
                faulty++;
            }else{
                healthy++;
            }
        }
        runOnUiThread(new updateDeviceNotifNumbers(healthy,faulty, notificationArray.size(), this));


    }

    public void updateUIAfterSearch(int notificationNumber){

        TextView textView = contentView.findViewById(R.id.textView4);
        if(textView!=null){
            textView.setText(String.valueOf(notificationNumber));
        }

    }

    public void openMap(View view) {
        if (devicesList == null || devicesList.size() == 0) {
            Toast.makeText(this, "No devices to display", Toast.LENGTH_LONG).show();
        } else {
            for (Devices device : devicesList) {
                userMap.addPlace(new Place(device.getLastUpdate().toString(), (float) device.getLatitude(), (float) device.getLogitude()));
            }
            Intent intent = new Intent(this, MapsActivity.class);
            intent.putExtra("user_map", userMap);
            startActivity(intent);
        }
    }
}

