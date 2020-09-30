package com.tsofen.agsenceapp.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.entities.Place;
import com.tsofen.agsenceapp.entities.UserMap;

import java.util.ArrayList;
import java.util.List;
import android.os.Build;
import android.Manifest;

import static java.lang.Float.parseFloat;


public class SpeedingAlertAndGeoFenceSetting extends BackBaseActivity {

    EditText longitude, latitude, speed, fence_radius_m;
    Spinner FenceTypeSpinner;
    Button SpeedingAlertButtonUpdate;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speeding_alert_and_geo_fence_setting);
        Spinner spinner = (Spinner) findViewById(R.id.FenceTypeSpinner);
        List<String> type = new ArrayList<>();
        type.add(0, "Choose Type");
        type.add("Out of the fence");
        type.add("In the fence");
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


        /// SpeedingAlertUpdate
        FenceTypeSpinner = (Spinner) findViewById(R.id.FenceTypeSpinner);
        longitude = (EditText) findViewById(R.id.longitude);
        latitude = (EditText) findViewById(R.id.latitude);
        speed = (EditText) findViewById(R.id.speednumbertextbox);
        fence_radius_m = (EditText) findViewById(R.id.fenceradiustextnumber);
        SpeedingAlertButtonUpdate = (Button) findViewById(R.id.speedAlertGeoSettingbutton);
        //Ends Here

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
                Toast toast = Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT);
                toast.show();
            }
        }


    }
    public void sendMsg(String phoneNumber, String message) {
        SmsManager smsMgr;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {  //settings check
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS) == getPackageManager().PERMISSION_GRANTED)
            {
                try {

                    smsMgr = SmsManager.getDefault();
                    smsMgr.sendTextMessage(phoneNumber, null, message, null, null);
                    Toast.makeText(this,R.string.msg_sent, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this,R.string.error_send_msg, Toast.LENGTH_SHORT).show();
                }
            } else {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                Toast.makeText(this,R.string.send_msg_again, Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "latA: " + pointA.getLocation().latitude + " longA: " + pointA.getLocation().longitude, Toast.LENGTH_LONG).show();
            Toast.makeText(this, "latB: " + pointB.getLocation().latitude + " longB: " + pointB.getLocation().longitude, Toast.LENGTH_LONG).show();
        }
    }
}