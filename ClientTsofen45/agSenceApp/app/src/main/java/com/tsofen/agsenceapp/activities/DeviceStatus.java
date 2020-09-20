package com.tsofen.agsenceapp.activities;

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
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.DeviceDataAdapter;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.UserMap;

import java.util.ArrayList;
import java.util.List;


public class DeviceStatus extends SearchBaseActivity {
    UserMap userMap = new UserMap("Map");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_device_status, null, false);
        final ListView devicesList = contentView.findViewById(R.id.listOfDevices);

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

        DeviceDataAdapter.getInstance().getAllDevices(0, 0, new DeviceDataRequestHandler() {
            @Override
            public void onDeviceDataLoaded(final List<Devices> devices) {
                DeviceStatus.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final ListAdapter myAdapter = new DevicesAdapter(DeviceStatus.this,0, devices) ;
                        devicesList.setAdapter(myAdapter);
                    }
                });

            }
        });


        //applying listener that transfers us to a new activity (DeviceView)
        devicesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO: To apply a better activity-transfer (in the future)...
                Intent intent = new Intent(getApplicationContext(), DeviceView.class);
                Devices device = (Devices) (devicesList.getAdapter()).getItem(i);
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
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 123 &&
                resultCode == RESULT_OK) {
            boolean tm1 = intent.getBooleanExtra("type1" ,false);
            boolean tm2 = intent.getBooleanExtra("type2" ,false);
            boolean tm3 = intent.getBooleanExtra("type3" ,false);
            boolean tm4 = intent.getBooleanExtra("healthyDevices" ,false);
            boolean tm5 =  intent.getBooleanExtra("faultyDevices" ,false);
        }
    }
}