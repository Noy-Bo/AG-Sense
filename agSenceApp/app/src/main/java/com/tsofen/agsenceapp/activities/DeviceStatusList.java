package com.tsofen.agsenceapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.DeviceDataListAdapter;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceInfoDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.DeviceDataAdapter;
import com.tsofen.agsenceapp.entities.DeviceData;
import com.tsofen.agsenceapp.entities.DeviceLastMessage;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.Place;
import com.tsofen.agsenceapp.entities.UserMap;

import java.util.ArrayList;
import java.util.List;

public class DeviceStatusList extends BackBaseActivity {
    UserMap userMap = new UserMap();
    List<DeviceData> deviceData;
    Devices device;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_status_list);
        device = (Devices) getIntent().getSerializableExtra("device");
        deviceData = device.getDeviceData();
        if (deviceData == null) {
            Toast.makeText(this, "No device data to show", Toast.LENGTH_SHORT).show();
            return;
        }
        final ListView lastMessagesListView = findViewById(R.id.device_data_list_view);
        final DeviceDataListAdapter myAdapter = new DeviceDataListAdapter(this, deviceData);
        lastMessagesListView.setAdapter(myAdapter);

        swipeRefreshLayout = findViewById(R.id.device_status_list_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                DeviceDataAdapter.getInstance().getDeviceDataList(device.getId(), new DeviceInfoDataRequestHandler() {
                    @Override
                    public void getDeviceDataInfo(List<DeviceData> deviceDataList) {
                        device.setDeviceData(deviceDataList);
                        deviceData = device.getDeviceData();
                        DeviceStatusList.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((DeviceDataListAdapter)lastMessagesListView.getAdapter()).notifyDataSetChanged();
                                swipeRefreshLayout.setEnabled(false);
                            }
                        });
                    }
                });



            }
        });

       lastMessagesListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int firstVisibleCount, int totalItemCount) {
                int topRowVerticalPosition = (lastMessagesListView == null || lastMessagesListView.getChildCount() == 0) ? 0 : lastMessagesListView.getChildAt(0).getTop();
                swipeRefreshLayout.setEnabled(firstVisibleItem == 0 && topRowVerticalPosition >= 0);
            }
        });

    }

    public void openMap(View view) {
        if (deviceData == null || deviceData.size() == 0) {
            Toast.makeText(this, "No devices to display", Toast.LENGTH_LONG).show();
        } else {
            for (DeviceData deviceData : deviceData) {
                if (device.getLatitude() != null && device.getLogitude() != null) {
                    Place newPlace = new Place(deviceData.getDateAndTime().toString(), deviceData.getLat(), deviceData.getLon());
                    if (deviceData.getId() != null) {
                        newPlace.setTitle(device.getName());
                    }
                    if (deviceData.getDateAndTime() != null) {
                        newPlace.setSnippet(deviceData.getDateAndTime().toString());
                    }
//                    if (userMap.getPlaces().size() == 0 || (!userMap.getPlaces().contains(newPlace) && checkDist(newPlace, userMap.getPlaces().get(userMap.getPlaces().size() - 1))))
                        userMap.addPlace(newPlace);
                }
            }
            Intent intent = new Intent(this, MapsActivity.class);
            intent.putExtra("opcode", 2);
            intent.putExtra("user_map", userMap);
            startActivity(intent);
        }
    }

    private boolean checkDist(Place newPlace, Place place) {
        Double latDist = newPlace.getLocation().latitude - place.getLocation().latitude;
        Double lonDist = newPlace.getLocation().longitude - place.getLocation().longitude;
        Double squareSum = latDist * latDist + lonDist * lonDist;

        return (Math.sqrt(squareSum) >= 0.0001);
    }
}