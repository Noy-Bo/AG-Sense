package com.tsofen.agsenceapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.DevicesAdapter;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.DeviceDataAdapter;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.Place;
import com.tsofen.agsenceapp.entities.UserMap;

import java.util.ArrayList;
import java.util.List;


public class AccountDevicesStatus extends SearchBaseActivity {
    boolean displayFaultyDevice = true;
    boolean displayHealthyDevice = true;
    ArrayList<Devices> devicesArr = new ArrayList<>();
    ListView devicesList;
    Account account;
    UserMap userMap = new UserMap();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_account_devices_status, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_account_devices_status);
        devicesList = findViewById(R.id.account_devices_list);
        account = (Account) getIntent().getSerializableExtra("account");

        DeviceDataAdapter.getInstance().getDevicesRelatedToAccount(account.getAccountid(), 0, 0, new DeviceDataRequestHandler() {
            @Override
            public void onDeviceDataLoaded(final List<Devices> devices) {
                AccountDevicesStatus.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        devicesArr = (ArrayList<Devices>) devices;
                        updatingUI();
                    }
                });

            }
        });

    }

    public void displayHealthyClicked(View view) {
        TextView displayHealthyBox = view.findViewById(R.id.display_healthy_button);
        if (displayHealthyDevice == true) // do not display healthy devices.
        {
            displayHealthyBox.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
            displayHealthyBox.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
            displayHealthyDevice = false;
        } else if (displayHealthyDevice == false) // displaying the healthy device.
        {
            displayHealthyBox.setBackground(ContextCompat.getDrawable(this, R.drawable.white_shape_squares));
            displayHealthyBox.setTextColor(ContextCompat.getColor(this, R.color.white));
            displayHealthyDevice = true;
        }
        updateList();
    }

    public void displayFaultyClicked(View view) {
        TextView displayFaultyBox = view.findViewById(R.id.display_faulty_button);
        if (displayFaultyDevice == true) // do not display faulty devices.
        {
            displayFaultyBox.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
            displayFaultyBox.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
            displayFaultyDevice = false;
        } else if (displayFaultyDevice == false) // displaying the faulty device.
        {
            displayFaultyBox.setBackground(ContextCompat.getDrawable(this, R.drawable.white_shape_squares));
            displayFaultyBox.setTextColor(ContextCompat.getColor(this, R.color.white));
            displayFaultyDevice = true;
        }
        updateList();
    }


    public void updateList() {
        ArrayList<Devices> filteredDevices = new ArrayList<>();
        for (Devices device : devicesArr) {
            if ((displayFaultyDevice && account.isFaulty() == true) ||
                    (displayHealthyDevice && account.isFaulty() == false)) {
                filteredDevices.add(device);
            }
        }
        ListAdapter myAdapter = new DevicesAdapter(AccountDevicesStatus.this, 0, filteredDevices);
        devicesList.setAdapter(myAdapter);
    }

    public void openMap(View view) {
        if (devicesArr == null || devicesArr.size() == 0) {
            Toast.makeText(this, "No devices to display", Toast.LENGTH_LONG).show();
        } else {
            for (Devices device : devicesArr) {
                userMap.addPlace(new Place(device.getName(), device.getLastUpdate().toString(), (float) device.getLatitude(), (float) device.getLogitude()));
            }
            Intent intent = new Intent(this, MapsActivity.class);
            intent.putExtra("user_map", userMap);
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
        final ListAdapter myAdapter = new DevicesAdapter(AccountDevicesStatus.this, 0, filteredDevices);
        devicesList.setAdapter(myAdapter);
    }
}