package com.tsofen.agsenceapp.activities;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import android.widget.ProgressBar;
import android.widget.Toast;


import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.DevicesAdapter;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.DeviceDataAdapter;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.Place;
import com.tsofen.agsenceapp.entities.UserMap;
import com.tsofen.agsenceapp.utils.GeneralProgressBar;

import java.util.ArrayList;
import java.util.List;


public class DeviceStatus extends SearchBaseActivity {
    UserMap userMap = new UserMap();
    ArrayList<Devices> devicesArr = new ArrayList<>();
    ArrayList<Devices> filteredDevices = new ArrayList<>();
    LayoutInflater inflater;
    View contentView;
    ListView devicesList;
    ProgressDialog pd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_device_status, null, false);
        devicesList = contentView.findViewById(R.id.listOfDevices);
        pd = GeneralProgressBar.displayProgressDialog(this,"loading devices...");
        searchView = contentView.findViewById(R.id.search_text_view);
        searchView.setQueryHint("search bar here");




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
                        devicesArr = (ArrayList<Devices>) devices;
                        filteredDevices = devicesArr;
                        updatingUI();
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
                intent.putExtra("device", device);
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
        boolean gpsType = false;
        boolean bankType = false;
        boolean LequidType = false;
        boolean healthyDevices = false;
        boolean faultyDevices = false;
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 123 &&
                resultCode == RESULT_OK) {
            gpsType = intent.getBooleanExtra("GpsForPersonal", false);
            bankType = intent.getBooleanExtra("SensorForBanks", false);
            LequidType = intent.getBooleanExtra("lequidHeightForTanks", false);
            healthyDevices = intent.getBooleanExtra("healthyDevices", false);
            faultyDevices = intent.getBooleanExtra("faultyDevices", false);
        }
        filteredDevices = new ArrayList<>();
        //filtering
        for (Devices device : devicesArr) {
            if (((device.getFaulty() == true && faultyDevices) ||
                    (device.getFaulty() == false && healthyDevices))
                    && ((device.getType().equals(Devices.DeviceType.GPS.toString()) && gpsType) ||
                    (device.getType().equals(Devices.DeviceType.BANKS.toString()) && bankType) ||
                    (device.getType().equals(Devices.DeviceType.LIQUID.toString()) && LequidType))) {
                filteredDevices.add(device);
            }
        }
        //
        final ListAdapter myAdapter = new DevicesAdapter(this, 0, filteredDevices);
        //Ends here
        devicesList.setAdapter(myAdapter);
    }

    private void updatingUI() {

        filteredDevices = new ArrayList<>();

        String filter = getIntent().getExtras().getString("filter");
        if (filter != null) {
            if (filter.equals("faulty") ) {
                for (Devices device : devicesArr) {
                    if (device.getFaulty() == true){
                        filteredDevices.add(device);
                    }
                }
            }else{
                for (Devices device : devicesArr) {
                    if (device.getFaulty() == false){
                        filteredDevices.add(device);
                    }
                }
            }

        }
        final ListAdapter myAdapter = new DevicesAdapter(DeviceStatus.this, 0, filteredDevices);
        devicesList.setAdapter(myAdapter);
        GeneralProgressBar.removeProgressDialog(pd);
    }

    public void openMap(View view) {
        if (filteredDevices == null || filteredDevices.size() == 0) {
            Toast.makeText(this, "No devices to display", Toast.LENGTH_LONG).show();
        } else {
            for (Devices device :  filteredDevices) {
                Place newPlace = new Place((float) device.getLatitude(), (float) device.getLogitude());
                if(device.getName()!=null) {
                    newPlace.setTitle(device.getName());
                }
                if(device.getLastUpdate()!=null) {
                    newPlace.setSnippet(device.getLastUpdate().toString());
                }
                userMap.addPlace(newPlace);
            }
            Intent intent = new Intent(this, MapsActivity.class);
            intent.putExtra("user_map", userMap);
            startActivity(intent);
        }
    }
}