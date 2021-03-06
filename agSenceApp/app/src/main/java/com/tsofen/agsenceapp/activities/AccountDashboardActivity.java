package com.tsofen.agsenceapp.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.DevicesAdapter;
import com.tsofen.agsenceapp.adapters.NotificationListAdaptor;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceDataRequestHandler;
import com.tsofen.agsenceapp.adaptersInterfaces.NotificationsDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.DeviceDataAdapter;
import com.tsofen.agsenceapp.dataAdapters.NotificationsDataAdapter;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.Notification;
import com.tsofen.agsenceapp.entities.Place;
import com.tsofen.agsenceapp.entities.UserMap;
import com.tsofen.agsenceapp.utils.GeneralProgressBar;
import com.tsofen.agsenceapp.utils.updateDeviceNotifNumbers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class AccountDashboardActivity extends SearchBaseActivity {
    static ArrayList<Notification> notificationArray = new ArrayList<>();
    ArrayAdapter<Notification> notificationArrayAdapter;
    ArrayList<Devices> devicesList = new ArrayList<>();
    Dialog popUpDialog;
    boolean displayReadNotifications = false;
    boolean displayUnreadNotifications = false;
    Date after;
    Date before;
    View contentView;
    ListView notificationListView;
    Button reset; //?
    ImageView closePopUpImage;
    ImageView fromDateCalenderImage;
    ImageView toDateCalenderImage;
    private long backPressedTime;
    private Toast backtoast;
    private Account account;
    UserMap userMap = new UserMap();
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.activity_account_dashboard, null, false);
        pd = GeneralProgressBar.displayProgressDialog(this,"loading notifications...");
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_account_dashboard);
        popUpDialog = new Dialog(this);
        searchView = (AutoCompleteTextView) contentView.findViewById(R.id.search_text_view);
        searchView.setHint(R.string.search_device_hint);

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                devicesList.clear();
                getDevicesFromCache();
                getNotificationsFromCache();

            }
        });

        if (AppBaseActivity.getUser() instanceof Admin) {
            account = (Account) getIntent().getSerializableExtra("account");
        }
        else {
            account = (Account) AppBaseActivity.getUser();
        }
        // get notification related to account
        NotificationsDataAdapter.getInstance().getNotificationsBySpecificAccount(account.getId(), 0, 0, new NotificationsDataRequestHandler() {
            @Override
            public void onNotificationsReceived(final List<Notification> notifications) {
                AccountDashboardActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        notificationArray.clear();
                        notificationArray.addAll(notifications);
                        notificationListView = findViewById(R.id.notification_list);
                        notificationArrayAdapter = new NotificationListAdaptor(AccountDashboardActivity.this, 0, notificationArray);
                        notificationListView.setAdapter(notificationArrayAdapter);
                    }
                });

            }
        });

        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AccountDashboardActivity.this, DeviceView.class);
                Devices device = (Devices) searchView.getAdapter().getItem(i);
                intent.putExtra("device", device);
                startActivity(intent);
            }
        });

        getDevicesFromCache();
        getNotificationsFromCache();
    }

    private void getNotificationsFromCache() {
        NotificationsDataAdapter.getInstance().getNotificationsBySpecificAccount(account.getId(), 0, 0, new NotificationsDataRequestHandler() {
            @Override
            public void onNotificationsReceived(final List<Notification> notifications) {
                AccountDashboardActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        notificationArray.clear();
                        notificationArray.addAll(notifications);
                        notificationListView = findViewById(R.id.notification_list);
                        notificationArrayAdapter = new NotificationListAdaptor(AccountDashboardActivity.this, 0, notificationArray);
                        notificationListView.setAdapter(notificationArrayAdapter);
                        updateUIAfterSearch(notifications.size());
                    }
                });

            }
        });
    }

    /**
     * function responsible for getting devices from cache
     */
    public void getDevicesFromCache()
    {
        DeviceDataAdapter.getInstance().getDevicesRelatedToAccount(account.getId(), 0, 0, new DeviceDataRequestHandler() {

            @Override
            public void onDeviceDataLoaded(final List<Devices> devices) {
                AccountDashboardActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        searchView.setAdapter(new DevicesAdapter<Devices>(AccountDashboardActivity.this, devices));
                        devicesList.clear();
                        devicesList.addAll(devices);
                        //                adapter = new ArrayAdapter<>(AccountDashboardActivity.this, 0,devicesList.toArray());
                        //                searchView.setAdapter(adapter);
                        initialUpdateUI();
                    }
                });

            }

        });
    }


    /**
     * function responsible fot transitioning to device status, must send with extra account and filter
     * if its healthy or faulty
     * @param view : view of current activity
     */
    public void goToDevicesStatus(View view) {

        Intent intent = new Intent(this, AccountDevicesStatus.class);
        String filterString;
        if (view.getId() == R.id.account_faulty_devices)
            filterString = "faulty";
        else
            filterString = "healthy";

        intent.putExtra("filter", filterString);
        intent.putExtra("account", account);
        startActivity(intent);
    }

    /**
     * open dialog box to the filter popup
     * @param view : view of current activity
     */
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

    /**
     * listener used for date picker
     * @param view : view of current activity
     */
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

    /**
     * listener used for date picker
     * @param view : view of current activity
     */
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


    /**
     * resets filter popup options
     * @param view : view of current activity
     */
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


    /**
     * function responsible for taking options in filter popup and filtering the results
     * @param view : view of current activity
     */
    public void search(View view) {
        ArrayList<Notification> filterArr = new ArrayList<>();
        if(after ==null || before == null){
            for (Notification notification: notificationArray) {
                if((notification.getReaded()==true && displayReadNotifications) ||
                        (notification.getReaded()==false &&  displayUnreadNotifications)){
                    filterArr.add(notification);
                }
            }

        }else{
            for (Notification notification: notificationArray) {
                if(notification.getDate_time().after(after) && notification.getDate_time().before(before) &&
                        ((notification.getReaded()==true && displayReadNotifications) ||
                                (notification.getReaded()==false &&  displayUnreadNotifications))){
                    filterArr.add(notification);
                }
            }
        }
        notificationArrayAdapter = new NotificationListAdaptor(AccountDashboardActivity.this, 0, filterArr);
        notificationListView.setAdapter(notificationArrayAdapter);
        updateUIAfterSearch(filterArr.size());
        popUpDialog.cancel();
        after =null;
        before =null;
        displayReadNotifications = false;
        displayUnreadNotifications = false;
    }

    /**
     * function responsible for changing color of read/unread button in filter popup and save user's
     * choice
     * @param view : view of current activity
     */
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

    /**
     * function responsible for changing color of read/unread button in filter popup and save user's
     * choice
     * @param view : view of current activity
     */
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
        if (AppBaseActivity.getUser() instanceof Admin) {
            finish();
            return;
        }
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            CacheMgr.getInstance().clearCache();
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

        int healthy = 0;
        int faulty = 0;
        for (Devices device : devicesList) {
            if (device.getFaulty() == true) {
                faulty++;
            } else {
                healthy++;
            }

        }
        runOnUiThread(new updateDeviceNotifNumbers(healthy, faulty, notificationArray.size(), this));
        GeneralProgressBar.removeProgressDialog(pd);
        swipeRefreshLayout.setRefreshing(false);
    }

    public void updateUIAfterSearch(int notificationNumber) {

        TextView textView = contentView.findViewById(R.id.textView4);
        if (textView != null) {
            textView.setText(String.valueOf(notificationNumber));
        }

    }

    /**
     * opens google maps to view all devices
     * @param view : view of current activity
     */
  /*  public void openMap(View view) {
        if (devicesList == null || devicesList.size() == 0) {
            Toast.makeText(this, "No devices to display", Toast.LENGTH_LONG).show();
        } else {
            for (Devices device : devicesList) {
                Place newPlace = new Place(Float.parseFloat( device.getLatitude()), Float.parseFloat(device.getLogitude()));
                if(device.getName()!=null) {
                    newPlace.setTitle(device.getName());
                }
                if(device.getLastUpdate()!=null) {
                    newPlace.setSnippet(device.getLastUpdate().toString());
                }
                userMap.addPlace(newPlace);
            }
            Intent intent = new Intent(this, MapsActivity.class);
            intent.putExtra("user_map", userMap);
            intent.putExtra("opcode", 1);
            startActivity(intent);
        }
    } */
}

