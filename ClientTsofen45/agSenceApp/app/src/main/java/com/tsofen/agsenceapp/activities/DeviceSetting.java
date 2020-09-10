package com.tsofen.agsenceapp.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tsofen.agsenceapp.R;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Float.parseFloat;

public class DeviceSetting extends BackBaseActivity {


    EditText longitude, latitude, speed, fence_radius_m;
    EditText trackingintervale1, trackingintervale2, distance, headingdir;
    Button SpeedingAlertButtonUpdate, SpeedingAlertSecondButtonUpdate, AuthorizationNumberButtonUpdate;
    EditText editAdminNumber, editauthorizednum1, editauthorizednum2, editauthorizednum3;
    Spinner FenceTypeSpinner;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_setting);
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

        //SpeedingAlertSecondUpdate
        trackingintervale1 = (EditText) findViewById(R.id.trackingintervale1);
        trackingintervale2 = (EditText) findViewById(R.id.trackingintervale2);
        distance = (EditText) findViewById(R.id.distance);
        headingdir = (EditText) findViewById(R.id.headingdir);

        //Ends here

        //Authorization Number starts here

        editAdminNumber = (EditText) findViewById(R.id.editAdminNumber);
        editauthorizednum1 = (EditText) findViewById(R.id.editauthorizednum1);
        editauthorizednum2 = (EditText) findViewById(R.id.editauthorizednum2);
        editauthorizednum3 = (EditText) findViewById(R.id.editauthorizednum3);
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
                Toast toast = Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT);
                toast.show();
            }
        }


    }

    public void SpeedingAlertUSecondUpdate(View view) {
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
                if ((editauthorizednum1.getText().toString().length()) !=10 && !editAdminNumber.getText().toString().startsWith("05")) {
                    editauthorizednum1.setError("Invalid Number");
                }

                if(editauthorizednum1.getText().toString().length() == 10 && editAdminNumber.getText().toString().length() == 10
                        && editAdminNumber.getText().toString().startsWith("05") && editauthorizednum1.getText().toString().startsWith("05")
                &&(editauthorizednum2.getText().toString().length() == 10 || editauthorizednum2.getText().toString().length() == 0)
                    &&(editauthorizednum3.getText().toString().length() == 10 || editauthorizednum3.getText().toString().length() == 0))
            {
                Toast toast = Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT);
                toast.show();
            }

                else
                {
                    Toast.makeText(this, "One or more Argument is invalid", Toast.LENGTH_SHORT).show();
                }



        }
    }
}