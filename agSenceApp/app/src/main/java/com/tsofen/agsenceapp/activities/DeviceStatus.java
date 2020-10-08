package com.tsofen.agsenceapp.activities;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListAdapter;
import android.widget.ListView;

import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.DevicesAdapter;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.DeviceDataAdapter;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Admin;
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
    CacheMgr cacheMgr = CacheMgr.getInstance();
    String filterStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.activity_device_status, null, false);
        pd = GeneralProgressBar.displayProgressDialog(this, "loading devices...");
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //devicesArr.clear(); // do not use this!. down below you take Devices from cache and assign it directly to this variable, therefore controlling cache from here. this is bad
                // if u still need to use it. do not take direct refrence from cache!.
                filteredDevices.clear();

                ((ArrayAdapter)devicesList.getAdapter()).notifyDataSetChanged();
                getAllDevicesFromCache(true);



            }
        });
        devicesList = contentView.findViewById(R.id.listOfDevices);
        searchView = (AutoCompleteTextView) contentView.findViewById(R.id.search_text_view);
        searchView.setHint(R.string.search_device_hint);
        DeviceDataAdapter.getInstance().getAllDevices(0, 0,false, new DeviceDataRequestHandler() {
            @Override
            public void onDeviceDataLoaded(final List<Devices> devices) {
                DeviceStatus.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        searchView.setAdapter(new DevicesAdapter<Devices>(DeviceStatus.this, devices));
                    }
                });

            }
        });
        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DeviceStatus.this, DeviceView.class);
                Devices device = (Devices) searchView.getAdapter().getItem(i);
                intent.putExtra("device", device);
                startActivity(intent);
            }
        });


        getAllDevicesFromCache(false);

        devicesList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int firstVisibleCount, int totalItemCount) {
                int topRowVerticalPosition = (devicesList == null || devicesList.getChildCount() == 0) ? 0 : devicesList.getChildAt(0).getTop();
                swipeRefreshLayout.setEnabled(firstVisibleItem == 0 && topRowVerticalPosition >= 0);
            }
        });

        //applying listener that transfers us to a new activity (DeviceView)
        devicesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (swipeRefreshLayout.isRefreshing())
                    return;

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
        boolean gpsType = true;
        boolean bankType = true;
        boolean LequidType = true;
        boolean healthyDevices = true;
        boolean faultyDevices = true;
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
        if(faultyDevices)
            filterStr = "faulty";
        else if(healthyDevices)
            filterStr = "healthy";
        else
            filterStr = "all";
        //filtering
        for (Devices device : devicesArr) {
            if (((device.getFaulty() && faultyDevices) ||
                    (!device.getFaulty() && healthyDevices))
                    && ((device.getType().equals(Devices.DeviceType.GPS.toString()) && gpsType) ||
                    (device.getType().equals(Devices.DeviceType.BANKS.toString()) && bankType) ||
                    (device.getType().equals(Devices.DeviceType.LIQUID.toString()) && LequidType))) {
                filteredDevices.add(device);
            }
        }
        //
        final ListAdapter myAdapter = new DevicesAdapter<Devices>(this, filteredDevices);
        //Ends here
        devicesList.setAdapter(myAdapter);
    }

    private void updatingUI() {
        filteredDevices = new ArrayList<>();

        filterStr = getIntent().getStringExtra("filter");
        if (filterStr != null) {
            if (filterStr.equals("faulty")) {
                for (Devices device : devicesArr) {
                    if (device.getFaulty()) {
                        filteredDevices.add(device);
                    }
                }
            } else if (filterStr.equals("healthy")) {
                for (Devices device : devicesArr) {
                    if (!device.getFaulty()) {
                        filteredDevices.add(device);
                    }
                }
            } else
                filteredDevices.addAll(devicesArr);

        }
        final ListAdapter myAdapter = new DevicesAdapter<Devices>(DeviceStatus.this, filteredDevices);
        devicesList.setAdapter(myAdapter);
        GeneralProgressBar.removeProgressDialog(pd);
        swipeRefreshLayout.setRefreshing(false);
    }

    public void openMap(View view) {
        if (filteredDevices == null || filteredDevices.size() == 0) {
            Toast.makeText(this, "No devices to display", Toast.LENGTH_LONG).show();
        } else {
            userMap.getPlaces().clear();
            for (Devices device : filteredDevices) {
                if (device.getLatitude() != null && device.getLogitude() != null) {
                    Place newPlace = new Place(Float.parseFloat(device.getLatitude()), Float.parseFloat(device.getLogitude()));
                    if (device.getName() != null) {
                        newPlace.setTitle(device.getName());
                    }
                    if (device.getLastUpdate() != null) {
                        newPlace.setSnippet(device.getLastUpdate().toString());
                    }
                    userMap.addPlace(newPlace);
                }
            }
            Intent intent = new Intent(this, MapsActivity.class);
            intent.putExtra("user_map", userMap);
            intent.putExtra("opcode", 1);
            intent.putExtra("filter", filterStr);
            startActivity(intent);
        }
    }


    public void getAllDevicesFromCache(boolean requestLatestData)
    {

        DeviceDataAdapter.getInstance().getAllDevices(0, 0,requestLatestData, new DeviceDataRequestHandler() {

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

    }
}