package com.tsofen.agsenceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.DeviceDataAdapter;
import com.tsofen.agsenceapp.entities.Devices;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.VISIBLE;


public class DeviceSettings extends BackBaseActivity {

    Devices device;
    Spinner spinner;
    int flag = 0;
    ArrayAdapter<String> dataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_settings);
        spinner = (Spinner) findViewById(R.id.settingSpinner);
        final LinearLayout b1 = findViewById(R.id.buttonsPanel);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (flag == 0) {
                    flag = 1;
                } else {
                    b1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                b1.setVisibility(View.INVISIBLE);
            }

        });


        final ArrayList<String> list = new ArrayList<>();
        device = (Devices) getIntent().getSerializableExtra("device");
        if (device != null) {
            list.add(device.getImei().toString());
            dataAdapter = new ArrayAdapter<>(DeviceSettings.this, android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);
        }
        else {
            DeviceDataAdapter.getInstance().getAllDevices(0, 0, new DeviceDataRequestHandler() {
                @Override
                public void onDeviceDataLoaded(final List<Devices> devices) {
                    for (Devices devices1 : devices)
                        list.add(devices1.getImei().toString());
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