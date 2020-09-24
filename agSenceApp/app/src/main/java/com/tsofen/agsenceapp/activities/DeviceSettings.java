package com.tsofen.agsenceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_settings);
        spinner = findViewById(R.id.settingSpinner);
        final ArrayList<String> list = new ArrayList<>();
        device = (Devices) getIntent().getSerializableExtra("device");
        if (device != null)
            list.add(device.getImei().toString());
        else {

            DeviceDataAdapter.getInstance().getAllDevices(0, 0, new DeviceDataRequestHandler() {
                @Override
                public void onDeviceDataLoaded(List<Devices> devices) {
                    for (Devices devices1 : devices)
                        list.add(devices1.getImei().toString());

                }
            });
        }
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        final Button b1 = findViewById(R.id.speedAlertGeoSettingbutton);
        final Button b2 = findViewById(R.id.authorizationNumber);
        final Button b3 = findViewById(R.id.tracking);
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                b1.setVisibility(VISIBLE);
                b2.setVisibility(VISIBLE);
                b3.setVisibility(VISIBLE);
            }
        });
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