package com.tsofen.agsenceapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.DeviceDataAdapter;
import com.tsofen.agsenceapp.entities.Devices;

import java.util.ArrayList;
import java.util.List;

public class AdminDashboardActivity extends SearchBaseActivity {

    private  long backPressedTime;
    private Toast backtoast;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_admin_dashboard, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_admin_dashboard);
    }

    public void accountNotification(View view) {
        Intent intent = new Intent(this, AdminNotification.class);
        startActivity(intent);
    }

    public void GoToAccountStatus(View view) {
        Intent intent = new Intent(this, AccountStatusFilter.class);
        startActivity(intent);
    }

    public void goToHealthyDevices(View view) {
        final ArrayList<Devices> healthy = new ArrayList<>();
        DeviceDataAdapter.getInstance().getHealthyDevices(new DeviceDataRequestHandler() {
            @Override
            public void onDeviceDataLoaded(List<Devices> devices) {
                healthy.addAll(devices);
            }
        });
        Intent intent = new Intent(this, DeviceStatus.class);
        intent.putExtra("devices",healthy);
        startActivity(intent);
    }

    public void goToFaultyDevices(View view) {
        final ArrayList<Devices> faulty = new ArrayList<>();
        DeviceDataAdapter.getInstance().getFaultyDevices(new DeviceDataRequestHandler() {
            @Override
            public void onDeviceDataLoaded(List<Devices> devices) {
                faulty.addAll(devices);
                System.out.println("Faulty devices: "+faulty);
            }
        });
        Intent intent = new Intent(this, DeviceStatus.class);
        intent.putExtra("devices",faulty);
        startActivity(intent);
    }


    public void GoToAccountSettings(View view) {
        Intent intent = new Intent(this,DeviceSetting.class);
        startActivity(intent);
    }

    public void onBackPressed(){
        if(backPressedTime+2000>System.currentTimeMillis()){
            backtoast.cancel();
            super.finishAffinity();
            return;
        }else{
            backtoast = Toast.makeText(getBaseContext(), "press back again to exit", Toast.LENGTH_SHORT);
            backtoast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }



}