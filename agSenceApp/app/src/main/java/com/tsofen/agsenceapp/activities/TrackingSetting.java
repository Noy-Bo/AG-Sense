package com.tsofen.agsenceapp.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.smsServices.SmsMgr;

import java.util.ArrayList;
import java.util.List;
import android.os.Build;
import android.Manifest;

import static java.lang.Float.parseFloat;

public class TrackingSetting extends BackBaseActivity {

    EditText trackingintervale1, trackingintervale2, distance, headingdir;
    Button SpeedingAlertSecondButtonUpdate;



    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_setting);
        //Spinner spinner = (Spinner) findViewById(R.id.FenceTypeSpinner);
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
        SpeedingAlertSecondButtonUpdate=(Button)findViewById(R.id.tracking);

        //Ends here


    }
    public void trackingUpdate(View view) {
        if(SmsMgr.getInstance().getTracking().containsKey("phone number.........."))
        {
            Toast.makeText(this, "an update request is being proccessed,please wait till finish", Toast.LENGTH_SHORT).show();
            return;
        }
        ProgressBar progressBar =view.findViewById(R.id.trackingSettingsProgressBar);
        progressBar.setVisibility(View.VISIBLE);

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
                //DeviceSettings.sendMsg("phone number","sms");
                progressBar.setVisibility(View.INVISIBLE);


            }
        }
    }



}