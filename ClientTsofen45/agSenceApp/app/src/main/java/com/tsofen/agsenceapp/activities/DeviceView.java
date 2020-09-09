package com.tsofen.agsenceapp.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.SliderAdapter;

import java.util.ArrayList;

public class DeviceView extends AppCompatActivity {


    private ViewPager sliderViewPager;
    private LinearLayout dotslinearLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_view);

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

    }

    //for indentifying the current-dot (in the dot-scroller) we're positioned on..
    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        dotslinearLayout.removeAllViews();

        for(int i =0; i<mDots.length; i++){
            mDots[i] = new TextView(this);
            // HTML = This class processes HTML strings into displayable styled text.
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(40);
            mDots[i].setTextColor(getResources().getColor(R.color.very_light_grey));
            dotslinearLayout.addView(mDots[i]);
        }

        if(mDots.length > 0){ //changes Dot color!
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




}