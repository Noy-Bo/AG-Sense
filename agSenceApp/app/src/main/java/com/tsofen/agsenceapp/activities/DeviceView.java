package com.tsofen.agsenceapp.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.SliderAdapter;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceInfoDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.DeviceDataAdapter;
import com.tsofen.agsenceapp.entities.DeviceData;
import com.tsofen.agsenceapp.entities.Devices;

import java.util.ArrayList;
import java.util.List;

public class DeviceView extends AppBaseActivity {

    private ViewPager sliderViewPager;
    private LinearLayout dotslinearLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    Devices device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_device_view, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_device_status);

        sliderViewPager = (ViewPager) findViewById(R.id.viewPager);
        dotslinearLayout = (LinearLayout) findViewById(R.id.sliderDotsLayout);

        sliderViewPager.addOnPageChangeListener(viewListener);
        device = (Devices) getIntent().getSerializableExtra("device");

        setTitle("Device '" + device.getName() + "' view");

        DeviceDataAdapter.getInstance().getDeviceDataList(device.getId(), new DeviceInfoDataRequestHandler() {
            @SuppressLint("DefaultLocale")
<<<<<<< HEAD:ClientTsofen45/agSenceApp/app/src/main/java/com/tsofen/agsenceapp/activities/DeviceView.java
            @Override //applying logic to the handler once Data is received by the thread...
            public void getDeviceDataInfo(List<DeviceData> deviceDataList) {
                TextView status = findViewById(R.id.device_view_status);
                TextView lastUpdate = findViewById(R.id.device_view_last_update);
                TextView coordinations = findViewById(R.id.device_view_coordination);
                TextView isMoving = findViewById(R.id.device_view_is_moving);
                if(deviceDataList.size() == 0){
                    status.setText("Device Status: ----");
                    lastUpdate.setText("last updated: ----");
                    coordinations.setText("Lat: ---- Long: ---- "); // no height
                    isMoving.setText("Moving: ----");
                    return;
                } /////
                final  DeviceData deviceData = deviceDataList.get(0);
                device.setDeviceData(deviceDataList);
                status.setText(String.format("Device Status: %s", device.getFaulty() ? "faulty" : "healthy"));
                lastUpdate.setText("last updated: " + device.getLastUpdate());
                coordinations.setText(String.format("Lat: %f Long: %f ", deviceData.getLat(), deviceData.getLon())); // no height
                isMoving.setText(String.format("Moving: %s", ((deviceData.getMoveAlertActive()) ? "Yes" : "No")));

                //----------
                DeviceView.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        sliderAdapter = new SliderAdapter(DeviceView.this, deviceData);
                        //applying the adapter onto the viewpager!
                        sliderViewPager.setAdapter(sliderAdapter);
                        addDotsIndicator(0);
                    }
                });


=======
            @Override
            public void getDeviceDataInfo(final List<DeviceData> deviceDataList) {
                DeviceView.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView status = findViewById(R.id.device_view_status);
                        TextView lastUpdate = findViewById(R.id.device_view_last_update);
                        TextView coordinations = findViewById(R.id.device_view_coordination);
                        TextView isMoving = findViewById(R.id.device_view_is_moving);
                        if(deviceDataList.size() == 0){
                            status.setText("Device Status: ----");
                            lastUpdate.setText("last updated: ----");
                            coordinations.setText("Lat: ---- Long: ---- "); // no height
                            isMoving.setText("Moving: ----");
                            return;
                        }
                        DeviceData deviceData = deviceDataList.get(0);
                        device.setDeviceData(deviceDataList);
                        status.setText(String.format("Device Status: %s", device.getFaulty() ? "faulty" : "healthy"));
                        lastUpdate.setText("last updated: " + device.getLastUpdate());
                        coordinations.setText(String.format("Lat: %f Long: %f ", deviceData.getLat(), deviceData.getLon())); // no height
                        isMoving.setText(String.format("Moving: %s", ((deviceData.getMoveAlertActive()) ? "Yes" : "No")));
                    }
                });
>>>>>>> master:agSenceApp/app/src/main/java/com/tsofen/agsenceapp/activities/DeviceView.java
            }
        });

    }

    //for indentifying the current-dot (in the dot-scroller) we're positioned on..
    public void addDotsIndicator(int position) {
        mDots = new TextView[10]; //TO CHANGE
        dotslinearLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            // HTML = This class processes HTML strings into displayable styled text.
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(40);
            mDots[i].setTextColor(getResources().getColor(R.color.very_light_grey));
            dotslinearLayout.addView(mDots[i]);
        }

        if (mDots.length > 0) { //changes Dot color!
            mDots[position].setTextColor(getResources().getColor(R.color.orange));
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        //This method will be invoked when the current page is scrolled, either as part of a programmatically initiated smooth scroll or a user initiated touch scroll.
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        //This method will be invoked when a new page becomes selected. Animation is not necessarily complete
        public void onPageSelected(int i) {
            addDotsIndicator(i);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    public void GoToSettingsPage(View view) {
        Intent intent = new Intent(this, DeviceSetting.class);
        startActivity(intent);
    }

    //applying logic to Status_list button (transfers to DeviceStatusListActivity)

    public void openStatusListActivity(View view) {
        Intent intent = new Intent(this, DeviceStatusList.class);
        intent.putExtra("device",device);
        startActivity(intent);
    }


    public void openNotificationsActivity(View view) {
        Intent intent = new Intent(this, NotificationsActivity.class);
        intent.putExtra("obj",device);
        startActivity(intent);

    }
}