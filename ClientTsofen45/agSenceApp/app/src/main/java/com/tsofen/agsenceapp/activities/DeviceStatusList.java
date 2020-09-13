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
import com.tsofen.agsenceapp.adapters.DeviceLastMessageAdapter;
import com.tsofen.agsenceapp.entities.DeviceLastMessage;
import com.tsofen.agsenceapp.entities.UserMap;

import java.util.ArrayList;

public class DeviceStatusList extends BackBaseActivity {
    UserMap userMap = new UserMap("Map");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_status_list);


        //setting the messages-adapter..
        final ArrayList<DeviceLastMessage> lastMessages = new ArrayList<DeviceLastMessage>();
        //generic data... // TODO: below...
        for(int i=0; i<12; i++){
            if(i==5){ // for generic data aswell.. // TODO: EXCHANGE GENERIC DATA WITH REAL DATA..
                DeviceLastMessage dlm = new DeviceLastMessage("device"+100,Integer.toString(100),Integer.toString(100));
                lastMessages.add(dlm);
            }
            DeviceLastMessage dlm = new DeviceLastMessage("device"+i,Integer.toString(i),Integer.toString(i));
            lastMessages.add(dlm);
        }

        final ListView lastMessagesListView = findViewById(R.id.last_messages_listview);
        final DeviceLastMessageAdapter myAdapter = new DeviceLastMessageAdapter(this,lastMessages);
        lastMessagesListView.setAdapter(myAdapter);

        //Applying listeners for the sorting arrows on the lastMessages table:
        lastMessagesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) { //position = item position within the listview..

                    //setting another onClick on the Update-Time down-arrow-sorting..
                    ImageView image = (ImageView)view.findViewById(R.id.update_time_sort_down);
                    image.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.N) // to be able to use sort()  (api>=24)
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getApplicationContext(), "Sorting...", Toast.LENGTH_SHORT).show();
                            lastMessages.sort(DeviceLastMessage.DLMUpdateTimeComparator);
                            myAdapter.notifyDataSetChanged();
                        }
                    });

                    //setting another onClick on the Ellapsed Time-Ascending up-arrow-sorting..
                    ImageView image2 = (ImageView)view.findViewById(R.id.time_ellapsed_sort_up);
                    image2.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.N) // to be able to use sort()  (api>=24)
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getApplicationContext(), "Sorting...", Toast.LENGTH_SHORT).show();
                            lastMessages.sort(DeviceLastMessage.DLMEllapsedTimeDescendingComparator);
                            myAdapter.notifyDataSetChanged();
                        }
                    });

                    //setting another onClick on the Ellapsed Time-Ascending up-arrow-sorting..
                    ImageView image3 = (ImageView)view.findViewById(R.id.time_ellapsed_sort_down);
                    image3.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.N) // to be able to use sort()  (api>=24)
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getApplicationContext(), "Sorting...", Toast.LENGTH_SHORT).show();
                            lastMessages.sort(DeviceLastMessage.DLMEllapsedTimeAscendingComparator);
                            myAdapter.notifyDataSetChanged();
                        }
                    });


                }
            }
        });
    }

    public void map(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("user_map", userMap);
        intent.putExtra("flag", true);
        startActivity(intent);
    }



}