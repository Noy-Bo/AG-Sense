package com.tsofen.agsenceapp.activities;

import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.DevicesAdapter;
import com.tsofen.agsenceapp.entities.Devices;

import java.io.Serializable;

public class DeviceStatus extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_status);
        ListView NewsListView = findViewById(R.id.listOfDevices);
        Devices devices1 = new Devices(0,1,1,true,"10/10/2020","Device1", 1654164516,"10:26");
        Devices devices2 = new Devices(1,2,3,false,"10/10/2020","Device1", 1654164516,"10:26");
        Devices devices3 = new Devices(0,1,1,true,"10/10/2020","Device1", 1654164516,"10:26");
        Devices devices4 = new Devices(1,2,3,false,"10/10/2020","Device1", 1654164516,"10:26");
        Devices devices5 = new Devices(0,1,1,true,"10/10/2020","Device1", 1654164516,"10:26");
        Devices devices6 = new Devices(1,2,3,false,"10/10/2020","Device1", 1654164516,"10:26");
        Devices devices7 = new Devices(0,1,1,true,"10/10/2020","Device1", 1654164516,"10:26");
        Devices devices8= new Devices(1,2,3,false,"10/10/2020","Device1", 1654164516,"10:26");
        Devices[] devicesTotal = new Devices[8];
        devicesTotal[0] = devices1;
        devicesTotal[1] = devices2;
        devicesTotal[2] = devices3;
        devicesTotal[3] = devices4;
        devicesTotal[4] = devices5;
        devicesTotal[5] = devices6;
        devicesTotal[6] = devices7;
        devicesTotal[7] = devices8;
        //Devices[] devices1 = (Devices[]) getIntent().getSerializableExtra("extra");
        ListAdapter myAdapter = new DevicesAdapter(this,0, devicesTotal) ;
        NewsListView.setAdapter(myAdapter);
    }

    public void filterDevices(View view) {
        Intent intent = new Intent(this, DeviceFilter.class);
        startActivity(intent);
    }

    public void map(View view) {
        Intent intent = new Intent(this, DisplayMapActivity.class);
        startActivity(intent);
    }
}