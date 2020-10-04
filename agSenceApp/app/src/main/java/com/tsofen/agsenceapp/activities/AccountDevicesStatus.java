package com.tsofen.agsenceapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.DevicesAdapter;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.DeviceDataAdapter;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.Place;
import com.tsofen.agsenceapp.entities.UserMap;
import com.tsofen.agsenceapp.utils.GeneralProgressBar;

import java.util.ArrayList;
import java.util.List;


public class AccountDevicesStatus extends SearchBaseActivity {
    boolean displayFaultyDevice;
    boolean displayHealthyDevice;
    ArrayList<Devices> devicesArr = new ArrayList<>();
    ListView devicesList;
    Account account;
    UserMap userMap = new UserMap();
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_account_devices_status, null, false);
        pd = GeneralProgressBar.displayProgressDialog(this, "loading devices...");

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (AppBaseActivity.getUser() instanceof Admin) {
                    getDevicesRelatedToAccountFromCache();
                } else if (AppBaseActivity.getUser() instanceof Account) {
                    devicesArr.clear();
                    ((ArrayAdapter) devicesList.getAdapter()).notifyDataSetChanged();
                    getDevicesRelatedToAccountFromCache();
                }

            }
        });
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_account_devices_status);
        devicesList = findViewById(R.id.account_devices_list);
        account = (Account) getIntent().getSerializableExtra("account");
        searchView = (AutoCompleteTextView) contentView.findViewById(R.id.search_text_view);
        searchView.setHint(R.string.search_device_hint);

        getDevicesRelatedToAccountFromCache();
        String filterStr = getIntent().getStringExtra("filter");

        assert filterStr != null;
        if(filterStr.equals("healthy")){
            displayHealthyDevice = true;
            displayFaultyDevice = false;
        }else if(filterStr.equals("faulty")){
            displayHealthyDevice = false;
            displayFaultyDevice = true;
        }else{
            displayHealthyDevice = true;
            displayFaultyDevice = true;
        }


        if (filterStr != null) {
            if (filterStr.equals("faulty")) {
                TextView displayHealthyBox = findViewById(R.id.display_healthy_button);
                displayHealthyBox.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
                displayHealthyBox.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
            } else if (filterStr.equals("healthy")) {
                TextView displayFaultyBox = findViewById(R.id.display_faulty_button);
                displayFaultyBox.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
                displayFaultyBox.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
            } else {
                TextView displayHealthyBox = findViewById(R.id.display_healthy_button);
                displayHealthyBox.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
                displayHealthyBox.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
                TextView displayFaultyBox = findViewById(R.id.display_faulty_button);
                displayFaultyBox.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
                displayFaultyBox.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
            }
        }

        updateList();

        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AccountDevicesStatus.this, DeviceView.class);
                Devices device = (Devices) searchView.getAdapter().getItem(i);
                intent.putExtra("device", device);
                startActivity(intent);
            }
        });

        devicesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AccountDevicesStatus.this, DeviceView.class);
                Devices device = (Devices) devicesList.getAdapter().getItem(i);
                intent.putExtra("device", device);
                startActivity(intent);
            }
        });

        devicesList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int firstVisibleCount, int totalItemCount) {
                int topRowVerticalPosition = (devicesList == null || devicesList.getChildCount() == 0) ? 0 : devicesList.getChildAt(0).getTop();
                swipeRefreshLayout.setEnabled(firstVisibleItem == 0 && topRowVerticalPosition >= 0);
            }
        });

    }

    public void switchButton(View view) {
        TextView typeBox = (TextView) view;
        if (typeBox.getId() == R.id.display_faulty_button) {
            switchColor(typeBox, displayFaultyDevice);
            if (displayFaultyDevice) {
                displayFaultyDevice = false;
            } else {
                displayFaultyDevice = true;
            }

        } else if (typeBox.getId() == R.id.display_healthy_button) {
            switchColor(typeBox, displayHealthyDevice);
            if (displayHealthyDevice) {
                displayHealthyDevice = false;
            }
            else {
                displayHealthyDevice = true;
            }
        }
        updateList();
    }

    private void switchColor(TextView typeBox, boolean isPressed) {
        if (isPressed) {
            typeBox.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
            typeBox.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
        } else {
            typeBox.setBackground(ContextCompat.getDrawable(this, R.drawable.white_shape_squares));
            typeBox.setTextColor(ContextCompat.getColor(this, R.color.white));
        }
    }

    public void updateList() {
        ArrayList<Devices> filteredDevices = new ArrayList<>();
        for (Devices device : devicesArr) {
            if ((displayFaultyDevice && device.getFaulty()) ||
                    (displayHealthyDevice && !device.getFaulty())) {
                filteredDevices.add(device);
            }
        }
        ListAdapter myAdapter = new DevicesAdapter<Devices>(AccountDevicesStatus.this, filteredDevices);
        devicesList.setAdapter(myAdapter);
    }

    public void openMap(View view) {
        if (devicesArr == null || devicesArr.size() == 0) {
            Toast.makeText(this, "No devices to display", Toast.LENGTH_LONG).show();
        } else {
            for (Devices device : devicesArr) {
                if (device.getLatitude() != null && device.getLogitude() != null) {
                    Place newPlace = new Place(Float.parseFloat(device.getLatitude()), Float.parseFloat(device.getLogitude()));
                    if (device.getName() != null) {
                        newPlace.setTitle(device.getName());
                    }
                    if (device.getLastUpdate() != null) {
                        newPlace.setSnippet(device.getLastUpdate().toString());
                    }
                    userMap.addPlace(newPlace);
                    //                userMap.addPlace(new Place(device.getName(), device.getLastUpdate().toString(), (float) device.getLatitude(), (float) device.getLogitude()));
                }
            }
            Intent intent = new Intent(this, MapsActivity.class);
            intent.putExtra("user_map", userMap);
            intent.putExtra("opcode", 1);
            startActivity(intent);
        }
    }

    private void updatingUI() {
        ArrayList<Devices> filteredDevices = new ArrayList<>();
        String filter = getIntent().getExtras().getString("filter");
        if (filter != null) {
            if (filter.equals("faulty")) {
                for (Devices device : devicesArr) {
                    if (device.getFaulty() == true) {
                        filteredDevices.add(device);
                    }
                }
            } else if (filter.equals("healthy")) {
                for (Devices device : devicesArr) {
                    if (device.getFaulty() == false) {
                        filteredDevices.add(device);
                    }
                }
            } else {
                filteredDevices.addAll(devicesArr);
            }

        }
        final ListAdapter myAdapter = new DevicesAdapter<Devices>(AccountDevicesStatus.this, filteredDevices);
        devicesList.setAdapter(myAdapter);
        GeneralProgressBar.removeProgressDialog(pd);
        swipeRefreshLayout.setRefreshing(false);
    }

    public void getDevicesRelatedToAccountFromCache() {
        DeviceDataAdapter.getInstance().getDevicesRelatedToAccount(account.getId(), 0, 0, new DeviceDataRequestHandler() {
            @Override
            public void onDeviceDataLoaded(final List<Devices> devices) {
                AccountDevicesStatus.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        devicesArr.clear();
                        devicesArr.addAll(devices);
                        updatingUI();
                        searchView.setAdapter(new DevicesAdapter<Devices>(AccountDevicesStatus.this, devices));

                    }
                });

            }
        });
    }
}