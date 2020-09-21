package com.tsofen.agsenceapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.entities.User;

import java.util.ArrayList;
import java.util.List;

public class NewDevice extends AppCompatActivity {
EditText IEMIEdit,DevicePhoneNumberEdit,DevicePasswordEdit;
    Spinner DeviceTypeSpinner,AccountNameSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_device);

        List<User> accounts = (ArrayList<User>) getIntent().getSerializableExtra("accounts");
        AccountNameSpinner =  (Spinner) findViewById(R.id.AccountNameSpinner);
        List<String> type = new ArrayList<>();
        type.add(0, "Choose Type");
        type.add("Out of the fence");
        type.add("In the fence");
        System.out.println(accounts.toString());
        ArrayAdapter<User> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, accounts);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        AccountNameSpinner.setAdapter(dataAdapter);

        DeviceTypeSpinner = (Spinner) findViewById(R.id.DeviceTypeSpinner);
        List<String> DeviceSpinner_type = new ArrayList<>();
        DeviceSpinner_type.add(0, "Choose Type");
        DeviceSpinner_type.add("Type 1");
        DeviceSpinner_type.add("Type 2");
        DeviceSpinner_type.add("Type 3");
        ArrayAdapter<String> DeviceSpinner_dataAdapter;
        DeviceSpinner_dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, DeviceSpinner_type);
        DeviceSpinner_dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        DeviceTypeSpinner.setAdapter(DeviceSpinner_dataAdapter);


    }

    public void UpdateDevice(View view) {
    }
}

