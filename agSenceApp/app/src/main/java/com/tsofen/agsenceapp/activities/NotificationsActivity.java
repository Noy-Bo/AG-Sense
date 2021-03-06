package com.tsofen.agsenceapp.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
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

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.AccountsAdapter;
import com.tsofen.agsenceapp.adapters.DevicesAdapter;
import com.tsofen.agsenceapp.adapters.NotificationListAdaptor;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceDataRequestHandler;
import com.tsofen.agsenceapp.adaptersInterfaces.NotificationsDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.AccountsDataAdapter;
import com.tsofen.agsenceapp.dataAdapters.DeviceDataAdapter;
import com.tsofen.agsenceapp.dataAdapters.NotificationsDataAdapter;
import com.tsofen.agsenceapp.dataServices.AccountsHandler;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.Notification;
import com.tsofen.agsenceapp.utils.GeneralProgressBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class NotificationsActivity extends SearchBaseActivity {
    static ArrayList<Notification> notificationArray = new ArrayList<>();
    ArrayAdapter<Notification> notificationArrayAdapter;
    ListView notificationListView;
    Dialog popUpDialog;
    Button reset;
    boolean displayReadNotifications = false;
    boolean displayUnreadNotifications = false;
    Date after = null;
    Date before = null;
    ImageView closePopUpImage;
    ImageView fromDateCalenderImage;
    ImageView toDateCalenderImage;
    View contentView;
    ProgressDialog pd;
    Object obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.activity_notifications, null, false);
        pd = GeneralProgressBar.displayProgressDialog(this, "loading notifications...");
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_admin_notifications);
        popUpDialog = new Dialog(this);
        notificationListView = findViewById(R.id.notification_list);
        obj = getIntent().getSerializableExtra("obj");

        if (obj instanceof Devices) {
            obj = ((Devices) obj);
            setTitle(((Devices) obj).getName() + " Notifications");
            NotificationsDataAdapter.getInstance().getNotificationsBySpecificDevice(((Devices) obj).getId(), 0, 0, new NotificationsDataRequestHandler() {
                @Override
                public void onNotificationsReceived(final List<Notification> notifications) {

                    (NotificationsActivity.this).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (notifications == null) {
                                Toast.makeText(NotificationsActivity.this, "No notifications to show", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            notificationArray = (ArrayList<Notification>) notifications;
                            notificationArrayAdapter = new NotificationListAdaptor(NotificationsActivity.this, 0, notificationArray);
                            notificationListView.setAdapter(notificationArrayAdapter);
                            initialUpdateUI();
                        }
                    });
                    return;
                }
            });

            searchView = (AutoCompleteTextView) contentView.findViewById(R.id.search_text_view);
            searchView.setHint(R.string.search_device_hint);
            DeviceDataAdapter.getInstance().getAllDevices(0, 0, false, new DeviceDataRequestHandler() {
                @Override
                public void onDeviceDataLoaded(final List<Devices> devices) {
                    NotificationsActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            searchView.setAdapter(new DevicesAdapter<Devices>(NotificationsActivity.this, devices));
                        }
                    });

                }
            });
            searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(NotificationsActivity.this, DeviceView.class);
                    Devices device = (Devices) searchView.getAdapter().getItem(i);
                    intent.putExtra("device", device);
                    startActivity(intent);
                }
            });

        } else if (obj instanceof Admin) {
            obj = ((Admin) obj);
            setTitle("Admin Notifications");
            NotificationsDataAdapter.getInstance().getNotificationsBySpecificAccount(((Admin) obj).getId(), 0, 0, new NotificationsDataRequestHandler() {
                @Override
                public void onNotificationsReceived(List<Notification> notifications) {
                    (NotificationsActivity.this).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (notifications == null) {
                                Toast.makeText(NotificationsActivity.this, "No notifications to show", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            notificationArray = (ArrayList<Notification>) notifications;
                            notificationArrayAdapter = new NotificationListAdaptor(NotificationsActivity.this, 0, notificationArray);
                            notificationListView.setAdapter(notificationArrayAdapter);
                            initialUpdateUI();
                        }
                    });
                }
            });

            searchView = (AutoCompleteTextView) contentView.findViewById(R.id.search_text_view);
            searchView.setHint(R.string.search_account_hint);
            AccountsDataAdapter.getInstance().getAllAccounts(false, new AccountsHandler() {
                @Override
                public void onAccountsDownloadFinished(final List<Account> accounts) {
                    NotificationsActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            searchView.setAdapter(new AccountsAdapter<Account>(NotificationsActivity.this, accounts));
                        }
                    });

                }
            });
            searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(NotificationsActivity.this, AccountDashboardActivity.class);
                    Account account = (Account) searchView.getAdapter().getItem(i);
                    intent.putExtra("account", account);
                    startActivity(intent);
                }
            });
        }

    }

    public void search(View view) {
        ArrayList<Notification> filterArr = new ArrayList<>();
        if (after == null || before == null) {
            for (Notification notification : notificationArray) {
                if ((notification.getReaded() == true && displayReadNotifications) ||
                        (notification.getReaded() == false && displayUnreadNotifications)) {
                    filterArr.add(notification);
                }
            }

        } else {
            for (Notification notification : notificationArray) {
                if (notification.getDate_time().after(after) && notification.getDate_time().before(before) &&
                        ((notification.getReaded() == true && displayReadNotifications) ||
                                (notification.getReaded() == false && displayUnreadNotifications))) {
                    filterArr.add(notification);
                }
            }
        }

        notificationArrayAdapter = new NotificationListAdaptor(NotificationsActivity.this, 0, filterArr);
        notificationListView.setAdapter(notificationArrayAdapter);
        updateUI(filterArr.size());
        popUpDialog.cancel();
        after = null;
        before = null;
        displayReadNotifications = false;
        displayUnreadNotifications = false;

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


    public void updateUI(int notificationNumber) {
        TextView textView = contentView.findViewById(R.id.textView4);
        if (textView != null) {
            textView.setText(String.valueOf(notificationNumber));
        }

    }


    public void initialUpdateUI() {
        TextView notification = findViewById(R.id.textView4);
        if (notification != null) {
            notification.setText(String.valueOf(notificationArray.size()));
        }
        GeneralProgressBar.removeProgressDialog(pd);
    }
}
