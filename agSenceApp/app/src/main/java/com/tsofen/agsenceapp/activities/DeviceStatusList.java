package com.tsofen.agsenceapp.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.DeviceDataListAdapter;
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

        //Applying listeners for the sorting arrows on the lastMessages table:
//        lastMessagesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                if (position == 0) { //position = item position within the listview..
//
//                    //setting another onClick on the Update-Time down-arrow-sorting..
//                    ImageView image = (ImageView) view.findViewById(R.id.update_time_sort_down);
//                    image.setOnClickListener(new View.OnClickListener() {
//                        @RequiresApi(api = Build.VERSION_CODES.N)
//                        // to be able to use sort()  (api>=24)
//                        @Override
//                        public void onClick(View view) {
//                            Toast.makeText(getApplicationContext(), "Sorting...", Toast.LENGTH_SHORT).show();
//                            deviceData.sort(DeviceLastMessage.DLMUpdateTimeComparator);
//                            myAdapter.notifyDataSetChanged();
//                        }
//                    });
//
//                    //setting another onClick on the Ellapsed Time-Ascending up-arrow-sorting..
//                    ImageView image2 = (ImageView) view.findViewById(R.id.time_ellapsed_sort_up);
//                    image2.setOnClickListener(new View.OnClickListener() {
//                        @RequiresApi(api = Build.VERSION_CODES.N)
//                        // to be able to use sort()  (api>=24)
//                        @Override
//                        public void onClick(View view) {
//                            Toast.makeText(getApplicationContext(), "Sorting...", Toast.LENGTH_SHORT).show();
//                            deviceDataArrayList.sort(DeviceLastMessage.DLMEllapsedTimeDescendingComparator);
//                            myAdapter.notifyDataSetChanged();
//                        }
//                    });
//
//                    //setting another onClick on the Ellapsed Time-Ascending up-arrow-sorting..
//                    ImageView image3 = (ImageView) view.findViewById(R.id.time_ellapsed_sort_down);
//                    image3.setOnClickListener(new View.OnClickListener() {
//                        @RequiresApi(api = Build.VERSION_CODES.N)
//                        // to be able to use sort()  (api>=24)
//                        @Override
//                        public void onClick(View view) {
//                            Toast.makeText(getApplicationContext(), "Sorting...", Toast.LENGTH_SHORT).show();
//                            deviceDataArrayList.sort(DeviceLastMessage.DLMEllapsedTimeAscendingComparator);
//                            myAdapter.notifyDataSetChanged();
//                        }
//                    });
//
//
//                }
//            }
//        });
    }

    public void openMap(View view) {
        if (deviceData == null || deviceData.size() == 0) {
            Toast.makeText(this, "No devices to display", Toast.LENGTH_LONG).show();
        } else {
            for (DeviceData deviceData : deviceData) {
                userMap.addPlace(new Place(deviceData.getDateAndTime().toString(), deviceData.getLat(), deviceData.getLon()));
            }
            Intent intent = new Intent(this, MapsActivity.class);
            intent.putExtra("flag", true);
            intent.putExtra("user_map", userMap);
            startActivity(intent);
        }
    }

//    public void openMap(View view) {
//        if (deviceData.size() == 0) {
//            Toast.makeText(this, "No devices to display", Toast.LENGTH_LONG).show();
//        } else {
//            ArrayList<Devices> devicesArr = new ArrayList<>();
//            for(DeviceData device : deviceData) {
//                deviceData.add(new Place(device.getLatitude(), device.getLogitude()));
//            }
//            Intent intent = new Intent(this, MapsActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("devices", devicesArr);
//            intent.putExtras(bundle);
//            startActivity(intent);
//        }
//    }

}