package com.tsofen.agsenceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.DevicesAdapter;
import com.tsofen.agsenceapp.entities.Devices;

import java.io.Serializable;
import java.util.Date;

public class DeviceStatus extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_status);
        ListView NewsListView = findViewById(R.id.listOfDevices);
        Date date = new Date();
        date.getTime();
        Date date1 = new Date();
        date.setTime(20102020);
        Devices devices1 = new Devices(0,1,1,"Device",date,date1,true);
        Devices devices2 = new Devices(0,1,1,"Device",date,date1,true);
        Devices devices3 = new Devices(0,1,1,"Device",date,date1,true);
        Devices devices4 = new Devices(0,1,1,"Device",date,date1,true);
        Devices devices5 = new Devices(0,1,1,"Device",date,date1,true);
        Devices devices6 =new Devices(0,1,1,"Device",date,date1,true);
        Devices devices7 = new Devices(0,1,1,"Device",date,date1,true);
        Devices devices8 = new Devices(0,1,1,"Device",date,date1,true);
        Devices devices9 = new Devices(0,1,1,"Device",date,date1,true);
        Devices devices10 = new Devices(0,1,1,"Device",date,date1,true);
        Devices devices11 = new Devices(0,1,1,"Device",date,date1,true);
        Devices devices12 = new Devices(0,1,1,"Device",date,date1,true);
        Devices devices13 = new Devices(0,1,1,"Device",date,date1,true);
        Devices devices14 = new Devices(0,1,1,"Device",date,date1,true);
        Devices devices15 = new Devices(0,1,1,"Device",date,date1,true);

        Devices[] devicesTotal = new Devices[16];
        devicesTotal[0] = devices1;
        devicesTotal[1] = devices2;
        devicesTotal[2] = devices3;
        devicesTotal[3] = devices4;
        devicesTotal[4] = devices5;
        devicesTotal[5] = devices6;
        devicesTotal[6] = devices7;
        devicesTotal[7] = devices8;
        devicesTotal[8] = devices8;
        devicesTotal[9] = devices9;
        devicesTotal[10] = devices10;
        devicesTotal[11] = devices11;
        devicesTotal[12] = devices12;
        devicesTotal[13] = devices13;
        devicesTotal[14] = devices14;
        devicesTotal[15] = devices15;

        //Devices[] devices1 = (Devices[]) getIntent().getSerializableExtra("extra");
        ListAdapter myAdapter = new DevicesAdapter(this,0, devicesTotal) ;
        NewsListView.setAdapter(myAdapter);

        //applying listener that transfers us to a new activity (DeviceView)
        NewsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO: To apply a better activity-transfer (in the future)...
                Intent intent = new Intent(getApplicationContext(), DeviceView.class);
                startActivity(intent);
            }
        });
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