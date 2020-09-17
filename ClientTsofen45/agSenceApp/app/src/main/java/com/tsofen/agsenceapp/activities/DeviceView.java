package com.tsofen.agsenceapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.SliderAdapter;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceInfoDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.DeviceDataAdapter;
import com.tsofen.agsenceapp.entities.DeviceData;
import com.tsofen.agsenceapp.entities.Devices;

import java.util.List;

public class DeviceView extends AppBaseActivity {

    private ViewPager sliderViewPager;
    private LinearLayout dotslinearLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_device_view, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_device_status);

        sliderViewPager = (ViewPager) findViewById(R.id.viewPager);
        dotslinearLayout = (LinearLayout) findViewById(R.id.sliderDotsLayout);
        sliderAdapter = new SliderAdapter(this);

        //applying the adapter onto the viewpager!
        sliderViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);

        sliderViewPager.addOnPageChangeListener(viewListener);

        //applying logic to Status_list button (transfers to DeviceStatusListActivity)
        final TextView status_list_button = findViewById(R.id.status_list_textview);
        status_list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeviceStatusList.class);
                startActivity(intent);
            }
        });

        final TextView notification_button = findViewById(R.id.deviceStatusActivity_notification_button);
        notification_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: CHANGE INTENT PARAMTERS TO NotificationActivity!!!
                Intent intent = new Intent(getApplicationContext(), DeviceStatusList.class);
                startActivity(intent);
            }
        });
        final Devices device = (Devices) getIntent().getSerializableExtra("device");
        DeviceDataAdapter.getInstance().getDeviceDataList(device.getId(), new DeviceInfoDataRequestHandler() {
            @Override
            public void getDeviceDataInfo(List<DeviceData> deviceDataList) {
                TextView status = findViewById(R.id.device_view_status);
                TextView lastUpdate = findViewById(R.id.device_view_last_update);
                TextView coordinations = findViewById(R.id.device_view_coordination);
                TextView isMoving = findViewById(R.id.device_view_is_moving);
                DeviceData deviceData = device.getDeviceData().get(0);
                status.setText(String.format("Device Status: %s",device.getFaulty()?"healthy":"faulty"));
                lastUpdate.setText("last updated: "+device.getLastUpdate());
                coordinations.setText(String.format("Lat: %d Long: %d ",deviceData.getLat(),deviceData.getLon())); // no height
                isMoving.setText(String.format("Moving: %s",((deviceData.isMoving())?"Yes":"No")));
            }
        });

    }

    //for indentifying the current-dot (in the dot-scroller) we're positioned on..
    public void addDotsIndicator(int position) {
        mDots = new TextView[3];
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
}