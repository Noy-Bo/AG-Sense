package com.tsofen.agsenceapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.tsofen.agsenceapp.R;

public class EditDevice extends BackBaseActivity {
protected Spinner DeviceIEMISpinner;
protected EditText DeviceNewPhoneNumberEdit,DeviceNewPassword,DeviceVerifyPasswordEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_device);

    }


    public void UpdateDevice(View view) {

    }
}