package com.tsofen.agsenceapp.activities;

import android.annotation.SuppressLint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.R;

import com.tsofen.agsenceapp.dataServices.DeviceSmsInfoHandler;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.smsServices.OnAllSmsRecievedHandler;
import com.tsofen.agsenceapp.smsServices.SmsMgr;
import com.tsofen.agsenceapp.entities.Place;
import com.tsofen.agsenceapp.entities.UserMap;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.os.Build;
import android.Manifest;

import static java.lang.Float.parseFloat;


public class SpeedingAlertAndGeoFenceSetting extends BackBaseActivity {

    EditText longitude1,longitude2, latitude1,latitude2 , speed;
    String longitude1String, longitude2String, latitude1String, latitude2String, speedString;
    Button setByMapButton;
    Devices device = null;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speeding_alert_and_geo_fence_setting);

        device = (Devices) getIntent().getSerializableExtra("device");

        longitude1 = (EditText) findViewById(R.id.longitude1);
        longitude2 = (EditText) findViewById(R.id.longitude2);

        latitude1 = (EditText) findViewById(R.id.latitude1);
        latitude2 = (EditText) findViewById(R.id.latitude2);

        speed = (EditText) findViewById(R.id.speednumbertextbox);

        longitude1String =longitude1.getText().toString();
        longitude2String =longitude1.getText().toString();
        latitude1String =longitude1.getText().toString();
        latitude2String =longitude1.getText().toString();
        speedString =longitude1.getText().toString();
    }




    public void goToMapsGeoFence()
    {
        Intent intent = new Intent(this,MapsActivity.class);
        intent.putExtra("opCode","3");
        startActivity(intent);
    }

    public void SpeedingAlertUpdate(final View view) {

        if (longitude1String.equals("") || longitude2String.equals("") || latitude1String.equals("") ||latitude2String.equals("")  || speedString.equals("")) {
            Toast.makeText(this, "One or more Argument is invalid", Toast.LENGTH_SHORT).show();
        } else {
            if (parseFloat(longitude1String) < 0 || parseFloat(longitude1String) > 999.999999) {
                longitude1.setError("Invalid Number");

            }
            if (parseFloat(longitude2String) < 0 || parseFloat(longitude2String) > 999.999999) {
                longitude2.setError("Invalid Number");

            }
            if (parseFloat(latitude1String) < 0 || parseFloat(latitude1String) > 99.999999) {
                latitude1.setError("Invalid Number");

            }
            if (parseFloat(latitude2String) < 0 || parseFloat(latitude2String) > 99.999999) {
                latitude2.setError("Invalid Number");

            }

            if (parseFloat(speedString) < 0 || parseFloat((speedString)) > 255) {
                speed.setError("Invalid Number");
            }


            if (parseFloat(longitude1String) > 0 && parseFloat(longitude1String) < 999.999999 &&
                    parseFloat(longitude2String) > 0 && parseFloat(longitude2String) < 999.999999 &&
                    parseFloat(latitude1String) > 0 && parseFloat(latitude1String) < 99.999999 &&
                    parseFloat(latitude2String) > 0 && parseFloat(latitude2String) < 99.999999 &&
                    parseFloat(speedString) > 0 && parseFloat((speedString)) < 255 ) {
                CacheMgr.getInstance().getDeviceSmsinfoJob(String.valueOf(device.getImei()),  new DeviceSmsInfoHandler() {
                    @Override
                    public void onDeviceSmsInfoReceived(String devicePasswordAndPhoneNumber) {
                        Log.d("testing sms","displaying results: " + devicePasswordAndPhoneNumber);
                        String[] SmsPassPhone = devicePasswordAndPhoneNumber.split(",");
                        if(SmsPassPhone.length!=2){
                            Log.d("ERROR", "Server response not in right format");
                            return;
                        }
                        String devicePass =SmsPassPhone[0];
                        String devicePhone =SmsPassPhone[1];


                        if ((devicePhone.length()) != 13 && !devicePhone.startsWith("+9725")) {
                            Log.d("ERROR", "Wrong number format");
                            return;
                        }

                        if(SmsMgr.getInstance().getSpeedingAlert().containsKey(devicePhone))
                        {
                            Log.d("ERROR", "same request is being processed");
                            return;
                        }
                        sendSMS(view,
                                devicePhone,
                                String.format(SmsMgr.SendFormat.SET_SPEEDING_ALARM.getUrl(),devicePass,speedString),
                                String.format(SmsMgr.SendFormat.SET_GEO_FENCE.getUrl(),
                                        latitude1String, longitude1String, latitude2String, longitude1String));
                    }
                });

            }
        }


    }


    public void openMap(View view) {
        Place place = (Place) getIntent().getSerializableExtra("chosenPlace");
        UserMap userMap = new UserMap();
        userMap.addPlace(place);
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("user_map", userMap);
        intent.putExtra("opcode", 3);
        startActivityForResult(intent, 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 3 && resultCode == Activity.RESULT_OK) {
            Place pointA = (Place) data.getSerializableExtra("pointA");
            Place pointB = (Place) data.getSerializableExtra("pointB");
            longitude1.setText(String.valueOf(pointA.getLocation().longitude));
            longitude2.setText(String.valueOf(pointB.getLocation().longitude));
            latitude1.setText(String.valueOf(pointA.getLocation().latitude));
            latitude2.setText(String.valueOf(pointB.getLocation().latitude));

        }
    }

    public void sendSMS(View view , final String phoneNumber, final String SMS1,  final String SMS2 ) {
        if (checkSelfPermission(Manifest.permission.RECEIVE_SMS) != getPackageManager().PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale( Manifest.permission.RECEIVE_SMS)) {
                //user denied.
                ;
            } else {
                //pop up for permission.
                requestPermissions( new String[]{Manifest.permission.RECEIVE_SMS}, SmsMgr.MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
            }
        }

        sendMsg(phoneNumber,SMS1);
        sendMsg(phoneNumber,SMS2);
        ArrayList arr= new ArrayList<SmsMgr.Response>(Arrays.asList(SmsMgr.Response.SET_SPEEDING_ALARM, SmsMgr.Response.SET_GEO_FENCE));
        SmsMgr.getInstance().createTracker(phoneNumber,arr, SmsMgr.settingType.SPEEDING_ALERT, new OnAllSmsRecievedHandler() {
            @Override
            public void onAllSmsReceivedHandler() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "setting updated", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }

    public void sendMsg(String phoneNumber, String message) {
        SmsManager smsMgr;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != getPackageManager().PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale( Manifest.permission.RECEIVE_SMS)) {
                //user denied.
                ;
            } else {
                //pop up for permission.
                requestPermissions( new String[]{Manifest.permission.RECEIVE_SMS}, SmsMgr.MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {  //settings check
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == getPackageManager().PERMISSION_GRANTED) {
                try {

                    smsMgr = SmsManager.getDefault();
                    smsMgr.sendTextMessage(phoneNumber, null, message, null, null);

                } catch (Exception e) {
                    e.printStackTrace();

                }
            } else {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
            }

        }

    }
}