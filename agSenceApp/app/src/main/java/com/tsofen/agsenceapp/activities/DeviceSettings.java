package com.tsofen.agsenceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
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
import android.telephony.SmsManager;
import android.os.Build;
import android.Manifest;

import androidx.core.content.ContextCompat;
import android.widget.Toast;


import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.smsServices.OnAllSmsRecievedHandler;
import com.tsofen.agsenceapp.smsServices.SmsMgr;

public class DeviceSettings extends BackBaseActivity {
    public static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 0; // sms permission.
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
                spinner.setSelection(position);
                chosenDevice = devicesList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });


        ArrayList<String> list = new ArrayList<>();
        device = (Devices) getIntent().getSerializableExtra("device");
        if (device != null) {
            list.add(device.getImei().toString());
            devicesList.add(device);
            dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);
            spinner.setSelection(0);
            b1.setVisibility(View.VISIBLE);
        } else {
            DeviceDataAdapter.getInstance().getAllDevices(0, 0, false,new DeviceDataRequestHandler() {
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
            intent.putExtra("chosenPlace", new Place(Float.parseFloat(chosenDevice.getLatitude()), Float.parseFloat(chosenDevice.getLogitude())));
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
    public void sendMsg(String phoneNumber, String message) {
        SmsManager smsMgr;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != getPackageManager().PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale( Manifest.permission.RECEIVE_SMS)) {
                //user denied.
                ;
            } else {
                //pop up for permission.
                requestPermissions( new String[]{Manifest.permission.RECEIVE_SMS}, MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {  //settings check
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == getPackageManager().PERMISSION_GRANTED) {
                try {

                    smsMgr = SmsManager.getDefault();
                    smsMgr.sendTextMessage(phoneNumber, null, message, null, null);
                    Toast.makeText(this, R.string.msg_sent, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, R.string.error_send_msg, Toast.LENGTH_SHORT).show();
                }
            } else {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                Toast.makeText(this, R.string.send_msg_again, Toast.LENGTH_SHORT).show();
            }

        }

    }
}