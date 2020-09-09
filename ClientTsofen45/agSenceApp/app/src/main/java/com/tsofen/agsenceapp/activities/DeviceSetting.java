package com.tsofen.agsenceapp.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.tsofen.agsenceapp.R;


import java.util.ArrayList;
import java.util.List;

public class DeviceSetting extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_setting);
        Spinner spinner = (Spinner)findViewById(R.id.FenceTypeSpinner);
        List<String> type = new ArrayList<>();
        type.add(0,"Choose Type");
        type.add("Out of the fence");
        type.add("In the fence");
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,type);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


spinner.setAdapter(dataAdapter);



    }
}