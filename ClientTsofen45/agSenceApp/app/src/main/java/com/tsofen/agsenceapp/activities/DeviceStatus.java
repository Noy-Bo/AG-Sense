package com.tsofen.agsenceapp.activities;

import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.DevicesAdapter;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.UserMap;

import java.util.ArrayList;


public class DeviceStatus extends SearchBaseActivity {
    UserMap userMap = new UserMap("Map");
    ArrayList<Devices> devices = new ArrayList<>();
    LayoutInflater inflater ;
    View contentView;
    ListView devicesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.activity_device_status, null, false);
        devicesList = contentView.findViewById(R.id.listOfDevices);
        devices = (ArrayList<Devices>) getIntent().getSerializableExtra("devices");

   /*     String filterString = getIntent().getStringExtra("filter");
        ArrayList<Devices> toShow = new ArrayList<>();
        if (filterString.equals("faulty")) {
            for (Devices device : devices) {
                if (device.getFaulty())
                    toShow.add(device);
            }
        } else {
            for (Devices device : devices) {
                if (!device.getFaulty())
                    toShow.add(device);
            }
        }
          ListAdapter myAdapter = new DevicesAdapter(this, 0, toShow);
          */ // This part of code is not working, Couldn't find where 'filter' has been sent as extra in intent therefore removed it- Ameer
        //if its unfinished code, I'll simply add where my code started and ended --- 16-09-2020
       //My code starts here
        System.out.println(devices);


        final ListAdapter myAdapter = new DevicesAdapter(this,0, devices) ;
        //Ends here

        devicesList.setAdapter(myAdapter);

        //applying listener that transfers us to a new activity (DeviceView)
        devicesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO: To apply a better activity-transfer (in the future)...
                Intent intent = new Intent(getApplicationContext(), DeviceView.class);
                Devices device = (Devices) myAdapter.getItem(i);
                intent.putExtra("device",device);
                startActivity(intent);
            }
        });

        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_device_status);
    }

    public void filterDevices(View view) {
        Intent intent = new Intent(this, DeviceFilter.class);
        startActivityForResult(intent, 123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        boolean type1 = false;
        boolean type3 = false;
        boolean type2 = false;
        boolean healthyDevices = false;
        boolean faultyDevices = false;
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 123 &&
                resultCode == RESULT_OK) {
            type1 = intent.getBooleanExtra("type1" ,false);
            type2 = intent.getBooleanExtra("type2" ,false);
            type3 = intent.getBooleanExtra("type3" ,false);
            healthyDevices = intent.getBooleanExtra("healthyDevices" ,false);
            faultyDevices =  intent.getBooleanExtra("faultyDevices" ,false);
        }
        ArrayList<Devices> filteredDevices= new ArrayList<>();
        //filtering
        for (Devices device :  devices)
        {
           if((    (device.getFaulty()==true && faultyDevices)                              ||
               (device.getFaulty()==false && healthyDevices)                            )
                   &&((device.getType().equals(Devices.DeviceType.GPS.toString())&& type1)        ||
                       (device.getType().equals(Devices.DeviceType.STRING_TWO.toString()) && type2)    ||
                       (device.getType().equals(Devices.DeviceType.STRING_THREE.toString()) && type3)   )  ){
               filteredDevices.add(device);
           }
        }
        //
        final ListAdapter myAdapter = new DevicesAdapter(this,0, filteredDevices) ;
        //Ends here
        devicesList.setAdapter(myAdapter);
    }
}