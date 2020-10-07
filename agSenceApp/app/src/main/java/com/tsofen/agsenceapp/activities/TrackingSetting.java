package com.tsofen.agsenceapp.activities;

import android.annotation.SuppressLint;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.dataServices.DeviceSmsInfoHandler;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.smsServices.OnAllSmsRecievedHandler;
import com.tsofen.agsenceapp.smsServices.SmsMgr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.os.Build;
import android.Manifest;

import static java.lang.Float.parseFloat;

public class TrackingSetting extends BackBaseActivity {

    EditText trackingintervale1, trackingintervale2, distance, headingdir;
    String trackingintervale1String, trackingintervale2String, distanceString , headingdirString;
    Devices device = null;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_setting);
        //Spinner spinner = (Spinner) findViewById(R.id.FenceTypeSpinner);
        device = (Devices) getIntent().getSerializableExtra("device");
        List<String> type = new ArrayList<>();
        type.add(0, "Choose Type");
        type.add("Out of the fence");
        type.add("In the fence");
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(dataAdapter);


        //SpeedingAlertSecondUpdate
        trackingintervale1 = (EditText) findViewById(R.id.trackingintervale1);
        trackingintervale2 = (EditText) findViewById(R.id.trackingintervale2);
        distance = (EditText) findViewById(R.id.distance);
        headingdir = (EditText) findViewById(R.id.headingdir);



        trackingintervale1String = trackingintervale1.getText().toString();
        trackingintervale2String = trackingintervale2.getText().toString();
        distanceString = distance.getText().toString();
        headingdirString = headingdir.getText().toString();
        //Ends here


    }
    public void trackingUpdate(final View view) {


        if (trackingintervale1String.equals("") || trackingintervale2String.equals("") || distanceString.equals("") || headingdirString.equals("")) {
            Toast.makeText(this, "One or more Argument is invalid", Toast.LENGTH_SHORT).show();
        } else {
            if (parseFloat(trackingintervale1String) < 0 || parseFloat(trackingintervale1String) > 65535) {
                trackingintervale1.setError("Invalid Number");

            }
            if (parseFloat(trackingintervale2String) < 0 || parseFloat(trackingintervale2String) > 65535) {
                trackingintervale2.setError("Invalid Number");

            }

            if (parseFloat(distanceString) < 0 || parseFloat((distanceString)) > 65535) {
                distance.setError("Invalid Number");

            }
            if (parseFloat(headingdirString) < 0 || parseFloat((headingdirString)) > 359) {
                headingdir.setError("Invalid Number");

            }


            if (parseFloat(trackingintervale1String) > 0 && parseFloat(trackingintervale1String) < 65535 &&
                    parseFloat(trackingintervale2String) > 0 && parseFloat(trackingintervale2String) < 65535 &&
                    parseFloat(distanceString) > 0 && parseFloat((distanceString)) < 65535 &&
                    parseFloat(headingdirString) > 0 && parseFloat((headingdirString)) < 359) {
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

                        if(SmsMgr.getInstance().getTracking().containsKey(devicePhone))
                        {
                            Log.d("ERROR", "same request is being processed");
                            return;
                        }

                        sendSMS(view,devicePhone,
                                String.format(SmsMgr.SendFormat.SET_INTERVAL.getUrl(),devicePass,trackingintervale1String,
                                        trackingintervale2String, distance, headingdirString));
                    }
                });


            }
        }
    }


    public void sendSMS(View view , final String phoneNumber, final String SMS ) {
        if (checkSelfPermission(Manifest.permission.RECEIVE_SMS) != getPackageManager().PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale( Manifest.permission.RECEIVE_SMS)) {
                //user denied.
                ;
            } else {
                //pop up for permission.
                requestPermissions( new String[]{Manifest.permission.RECEIVE_SMS}, SmsMgr.MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
            }
        }

        sendMsg(phoneNumber,SMS);
        ArrayList arr= new ArrayList<SmsMgr.Response>(Arrays.asList(SmsMgr.Response.SET_INTERVAL));
        SmsMgr.getInstance().createTracker(phoneNumber,arr, SmsMgr.settingType.TRACKING, new OnAllSmsRecievedHandler() {
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