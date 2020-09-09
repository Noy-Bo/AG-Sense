package com.tsofen.agsenceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.tsofen.agsenceapp.R;

public class AdminDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
    }

    public void GoToAccountStatus(View view) {

        Intent intent = new Intent(this, AccountStatusFilter.class);
        startActivity(intent);
    }

    public void goToHealthyDevices(View view) {
        Intent intent = new Intent(this, DeviceStatus.class);
        startActivity(intent);
    }

    public void goToFaultyDevices(View view) { //TODO: CHANGE ACTIVITY TO TRANSFER TO
        Intent intent = new Intent(this, DeviceStatus.class);
        startActivity(intent);
    }

    public void GoToAccountSettings(View view) {
        Intent intent = new Intent(this,DeviceSetting.class);
        startActivity(intent);
    }
}