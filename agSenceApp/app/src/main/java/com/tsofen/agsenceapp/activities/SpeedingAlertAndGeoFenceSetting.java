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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.smsServices.SmsMgr;
import com.tsofen.agsenceapp.entities.Place;
import com.tsofen.agsenceapp.entities.UserMap;

import java.util.ArrayList;
import java.util.List;
import android.os.Build;
import android.Manifest;

import static java.lang.Float.parseFloat;


public class SpeedingAlertAndGeoFenceSetting extends BackBaseActivity {

    EditText longitude1,longitude2, latitude1,latitude2 , speed;
    Button SpeedingAlertButtonUpdate;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speeding_alert_and_geo_fence_setting);



        /// SpeedingAlertUpdate
        longitude1 = (EditText) findViewById(R.id.longitude1);
        longitude2 = (EditText) findViewById(R.id.longitude2);

        latitude1 = (EditText) findViewById(R.id.latitude1);
        latitude2 = (EditText) findViewById(R.id.latitude2);

        speed = (EditText) findViewById(R.id.speednumbertextbox);
        SpeedingAlertButtonUpdate = (Button) findViewById(R.id.speedAlertGeoSettingbutton);
        //Ends Here

    }
    public void SpeedingAlertUpdate(View view) {
        if(SmsMgr.getInstance().getSpeedingAlert().containsKey("phone number"))
        {
            Toast.makeText(this, "an update request is being proccessed,please wait till finish", Toast.LENGTH_SHORT).show();
            return;
        }
        ProgressBar progressBar = view.findViewById(R.id.geoFenceSettingProgressBar);
        progressBar.setVisibility(View.VISIBLE);
        if (longitude1.getText().toString().equals("") || longitude2.getText().toString().equals("") || latitude1.getText().toString().equals("") ||latitude2.getText().toString().equals("")  || speed.getText().toString().equals("")) {
            Toast.makeText(this, "One or more Argument is invalid", Toast.LENGTH_SHORT).show();
        } else {
            if (parseFloat(longitude1.getText().toString()) < 0 || parseFloat(longitude1.getText().toString()) > 999.999999) {
                longitude1.setError("Invalid Number");

            }
            if (parseFloat(longitude2.getText().toString()) < 0 || parseFloat(longitude2.getText().toString()) > 999.999999) {
                longitude2.setError("Invalid Number");

            }
            if (parseFloat(latitude1.getText().toString()) < 0 || parseFloat(latitude1.getText().toString()) > 99.999999) {
                latitude1.setError("Invalid Number");

            }
            if (parseFloat(latitude2.getText().toString()) < 0 || parseFloat(latitude2.getText().toString()) > 99.999999) {
                latitude2.setError("Invalid Number");

            }

            if (parseFloat(speed.getText().toString()) < 0 || parseFloat((speed.getText().toString())) > 255) {
                speed.setError("Invalid Number");
            }


            if (parseFloat(longitude1.getText().toString()) > 0 && parseFloat(longitude1.getText().toString()) < 999.999999 &&
                    parseFloat(longitude2.getText().toString()) > 0 && parseFloat(longitude2.getText().toString()) < 999.999999 &&
                    parseFloat(latitude1.getText().toString()) > 0 && parseFloat(latitude1.getText().toString()) < 99.999999 &&
                    parseFloat(latitude2.getText().toString()) > 0 && parseFloat(latitude2.getText().toString()) < 99.999999 &&
                    parseFloat(speed.getText().toString()) > 0 && parseFloat((speed.getText().toString())) < 255 ) {
                Toast toast = Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT);
                toast.show();
              //  DeviceSettings.sendMsg("phone number","sms");
                progressBar.setVisibility(View.INVISIBLE);   //after updating ...


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
}