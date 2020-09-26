package com.tsofen.agsenceapp.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.tsofen.agsenceapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import android.os.Build;
import android.Manifest;

import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.smsServices.OnAllSmsRecievedHandler;
import com.tsofen.agsenceapp.smsServices.SmsMgr;

import java.util.HashMap;
import java.util.Map;

import static android.Manifest.*;
import static java.lang.Float.parseFloat;


public class DeviceSetting extends BackBaseActivity {
    public static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 0; // sms permission.
    public String settingUpdateSucceed;
    EditText longitude, latitude, speed, fence_radius_m;
    EditText trackingintervale1, trackingintervale2, distance, headingdir;
    Button SpeedingAlertButtonUpdate, SpeedingAlertSecondButtonUpdate, AuthorizationNumberButtonUpdate;
    EditText editAdminNumber, editauthorizednum1, editauthorizednum2, editauthorizednum3;
    Spinner FenceTypeSpinner;
    private Devices device = (Devices) getIntent().getSerializableExtra("device");
    private String speedD;
    private String longitudeD;
    private String latitudeD;
    private String fence_radius_mD;
    private String FenceTypeSpinnerD;
    private String trackingintervale1D;
    private String trackingintervale2D;
    private String distanceD;
    private String headingdirD;
    private String editAdminNumberD;
    private String editauthorizednum1D;
    private String editauthorizednum2D;
    private String editauthorizednum3D;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_setting);
        List<String> type = new ArrayList<>();
        type.add(0, "Choose Type");
        type.add("Out of the fence");
        type.add("In the fence");
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        Map<String, String> commandMap = new HashMap<>();
        commandMap.put("Resetpwd", "Reset password ok!");
       // commandMap.

/// SpeedingAlertUpdate

        longitude = (EditText) findViewById(R.id.longitude1);
        longitudeD = longitude.toString();

        latitude = (EditText) findViewById(R.id.latitude1);
        latitudeD = latitude.toString();

        speed = (EditText) findViewById(R.id.speednumbertextbox);
        speedD = speed.toString();

       // fence_radius_m = (EditText) findViewById(R.id.fenceradiustextnumber);
        //fence_radius_mD = fence_radius_m.toString();

        SpeedingAlertButtonUpdate = (Button) findViewById(R.id.speedAlertGeoSettingbutton);
        //Ends Here

        //SpeedingAlertSecondUpdate
        trackingintervale1 = (EditText) findViewById(R.id.trackingintervale1);
        trackingintervale1D = trackingintervale1.toString();

        trackingintervale2 = (EditText) findViewById(R.id.trackingintervale2);
        trackingintervale2D = trackingintervale2.toString();

        distance = (EditText) findViewById(R.id.distance);
        distanceD = distance.toString();

        headingdir = (EditText) findViewById(R.id.headingdir);
        headingdirD = headingdir.toString();
        //Ends here

        //Authorization Number starts here
        editAdminNumber = (EditText) findViewById(R.id.editAdminNumber);
        editAdminNumberD = editAdminNumber.toString();

        editauthorizednum1 = (EditText) findViewById(R.id.editauthorizednum1);
        editauthorizednum1D = editauthorizednum1.toString();

        editauthorizednum2 = (EditText) findViewById(R.id.editauthorizednum2);
        editauthorizednum2D = editauthorizednum2.toString();

        editauthorizednum3 = (EditText) findViewById(R.id.editauthorizednum3);
        editauthorizednum3D = editauthorizednum3.toString();
        //Ends here

    }

    public void SpeedingAlertUpdate(View view) {


        if (longitude.getText().toString().equals("") || latitude.getText().toString().equals("") || FenceTypeSpinner.getSelectedItem().toString().equals("Choose Type") || fence_radius_m.getText().toString().equals("") || speed.getText().toString().equals("")) {
            Toast.makeText(this, "One or more Argument is invalid", Toast.LENGTH_SHORT).show();
        } else {
            if (parseFloat(longitude.getText().toString()) < 0 || parseFloat(longitude.getText().toString()) > 999.999999) {
                longitude.setError("Invalid Number");

            }
            if (parseFloat(latitude.getText().toString()) < 0 || parseFloat(latitude.getText().toString()) > 99.999999) {
                latitude.setError("Invalid Number");

            }

            if (parseFloat(fence_radius_m.getText().toString()) < 0 || parseFloat((fence_radius_m.getText().toString())) > 65635) {
                fence_radius_m.setError("Invalid Number");

            }
            if (parseFloat(speed.getText().toString()) < 0 || parseFloat((speed.getText().toString())) > 255) {
                speed.setError("Invalid Number");
            }


            if (parseFloat(longitude.getText().toString()) > 0 && parseFloat(longitude.getText().toString()) < 999.999999 &&
                    parseFloat(latitude.getText().toString()) > 0 && parseFloat(latitude.getText().toString()) < 99.999999 &&
                    parseFloat(fence_radius_m.getText().toString()) > 0 && parseFloat((fence_radius_m.getText().toString())) < 65635 &&
                    parseFloat(speed.getText().toString()) > 0 && parseFloat((speed.getText().toString())) < 255 && !FenceTypeSpinner.getSelectedItem().toString().equals("Choose Type")) {
                sendMsg("phonenumber", "sms");
                //Toast toast = Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT);
                //toast.show();
            }
        }

        sendMsg("phonenumber", "sms");
    }


    public void SpeedingAlertUSecondUpdate(View view) {
        if (checkSelfPermission(Manifest.permission.RECEIVE_SMS) != getPackageManager().PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale( Manifest.permission.RECEIVE_SMS)) {
                //user denied.
                ;
            } else {
                //pop up for permission.
                requestPermissions( new String[]{Manifest.permission.RECEIVE_SMS}, MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
            }
        }
        sendMsg("+972524448716","djdks,");
        ArrayList arr= new ArrayList<SmsMgr.Response>(
                Arrays.asList(SmsMgr.Response.SET_INTERVAL));
        SmsMgr.getInstance().createTracker("+972524448716", arr, SmsMgr.settingType.TRACKING, new OnAllSmsRecievedHandler() {
            @Override
            public void onAllSmsRecievedHandler() {
                sendMsg("+972524448716","djdks,");

            }
        });


        if (trackingintervale1.getText().toString().equals("") || trackingintervale2.getText().toString().equals("") || distance.getText().toString().equals("") || headingdir.getText().toString().equals("")) {
            Toast.makeText(this, "One or more Argument is invalid", Toast.LENGTH_SHORT).show();
        } else {
            if (parseFloat(trackingintervale1.getText().toString()) < 0 || parseFloat(trackingintervale1.getText().toString()) > 65535) {
                trackingintervale1.setError("Invalid Number");

            }
            if (parseFloat(trackingintervale2.getText().toString()) < 0 || parseFloat(trackingintervale2.getText().toString()) > 65535) {
                trackingintervale2.setError("Invalid Number");

            }

            if (parseFloat(distance.getText().toString()) < 0 || parseFloat((distance.getText().toString())) > 65535) {
                distance.setError("Invalid Number");

            }
            if (parseFloat(headingdir.getText().toString()) < 0 || parseFloat((headingdir.getText().toString())) > 359) {
                headingdir.setError("Invalid Number");

            }


            if (parseFloat(trackingintervale1.getText().toString()) > 0 && parseFloat(trackingintervale1.getText().toString()) < 65535 &&
                    parseFloat(trackingintervale2.getText().toString()) > 0 && parseFloat(trackingintervale2.getText().toString()) < 65535 &&
                    parseFloat(distance.getText().toString()) > 0 && parseFloat((distance.getText().toString())) < 65535 &&
                    parseFloat(headingdir.getText().toString()) > 0 && parseFloat((headingdir.getText().toString())) < 359) {
                Toast toast = Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT);
                toast.show();
            }
            }
        }


    public void AuthorizationNumberUpdate(View view) {
        if (editAdminNumber.getText().toString().equals("") || editauthorizednum1.getText().toString().equals("")) {
            Toast.makeText(this, "One or more Argument is invalid", Toast.LENGTH_SHORT).show();
        } else {
            if ((editAdminNumber.getText().toString().length()) != 10 && !editAdminNumber.getText().toString().startsWith("05")) {
                editAdminNumber.setError("Invalid Number");
            }
            if ((editauthorizednum1.getText().toString().length()) != 10 && !editAdminNumber.getText().toString().startsWith("05")) {
                editauthorizednum1.setError("Invalid Number");
            }

            if (editauthorizednum1.getText().toString().length() == 10 && editAdminNumber.getText().toString().length() == 10
                    && editAdminNumber.getText().toString().startsWith("05") && editauthorizednum1.getText().toString().startsWith("05")
                    && (editauthorizednum2.getText().toString().length() == 10 || editauthorizednum2.getText().toString().length() == 0)
                    && (editauthorizednum3.getText().toString().length() == 10 || editauthorizednum3.getText().toString().length() == 0)) {
                Toast toast = Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Toast.makeText(this, "One or more Argument is invalid", Toast.LENGTH_SHORT).show();
            }


        }
    }

    public void sendMsg(String phoneNumber, String message) {
        SmsManager smsMgr;
        if (ContextCompat.checkSelfPermission(this, permission.RECEIVE_SMS) != getPackageManager().PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale( permission.RECEIVE_SMS)) {
                //user denied.
                ;
            } else {
                //pop up for permission.
                requestPermissions( new String[]{permission.RECEIVE_SMS}, MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {  //settings check
            if (ContextCompat.checkSelfPermission(this, permission.SEND_SMS) == getPackageManager().PERMISSION_GRANTED) {
                try {

                    smsMgr = SmsManager.getDefault();
                    smsMgr.sendTextMessage(phoneNumber, null, message, null, null);
                    Toast.makeText(this, R.string.msg_sent, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, R.string.error_send_msg, Toast.LENGTH_SHORT).show();
                }
            } else {
                requestPermissions(new String[]{permission.SEND_SMS}, 1);
                Toast.makeText(this, R.string.send_msg_again, Toast.LENGTH_SHORT).show();
            }

        }

    }


}