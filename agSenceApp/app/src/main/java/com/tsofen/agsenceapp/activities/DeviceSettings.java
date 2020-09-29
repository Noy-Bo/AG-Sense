package com.tsofen.agsenceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.DeviceDataAdapter;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.Place;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.VISIBLE;


public class DeviceSettings extends BackBaseActivity {

    Devices device;
    SearchableSpinner spinner;
    int flag = 0;
    ArrayAdapter<String> dataAdapter;
    ArrayList<Devices> devicesList = new ArrayList<>();
    Devices chosenDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_settings);
        spinner = (SearchableSpinner) findViewById(R.id.settingSpinner);
        final LinearLayout b1 = findViewById(R.id.buttonsPanel);
        b1.setVisibility(View.INVISIBLE);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                b1.setVisibility(View.VISIBLE);
                chosenDevice = devicesList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });


        final ArrayList<String> list = new ArrayList<>();
        device = (Devices) getIntent().getSerializableExtra("device");
        if (device != null) {
            list.add(device.getImei().toString());
            dataAdapter = new ArrayAdapter<>(DeviceSettings.this, android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);
            spinner.setSelection(0);
            b1.setVisibility(View.VISIBLE);
        } else {
            DeviceDataAdapter.getInstance().getAllDevices(0, 0, new DeviceDataRequestHandler() {
                @Override
                public void onDeviceDataLoaded(final List<Devices> devices) {
                    for (Devices devices1 : devices) {
                        devicesList.add(devices1);
                        list.add(devices1.getImei().toString());
                    }
                    dataAdapter = new ArrayAdapter<>(DeviceSettings.this, android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    DeviceSettings.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            spinner.setAdapter(dataAdapter);
                        }
                    });
                }
            });
        }
    }

    public Spinner getSpinner() {
        return spinner;
    }

    public void openSpeedingAlertAndGeoFence(View view) {
        Intent intent = new Intent(this, SpeedingAlertAndGeoFenceSetting.class);
        intent.putExtra("chosenPlace", new Place(chosenDevice.getLatitude(),chosenDevice.getLogitude()));
        startActivity(intent);
    }

    public void openAuthorizationNumber(View view) {
        Intent intent1 = new Intent(this, AuthorizationNumberSetting.class);
        startActivity(intent1);
    }

    public void openTracking(View view) {
        Intent intent2 = new Intent(this, TrackingSetting.class);
        startActivity(intent2);
    }
}