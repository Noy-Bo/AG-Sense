package com.tsofen.agsenceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tsofen.agsenceapp.R;

public class AdminDashboardActivity extends AppCompatActivity {
    private  long backPressedTime;
    private Toast backtoast;
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
    public void accountNotification(View view) {
        Intent intent = new Intent(this, AdminNotification.class);
        startActivity(intent);
    }
    public void onBackPressed(){
        if(backPressedTime+2000>System.currentTimeMillis()){
            backtoast.cancel();
            super.onBackPressed();
            return;
        }else{
            backtoast = Toast.makeText(getBaseContext(), "press back again to exit", Toast.LENGTH_SHORT);
            backtoast.show();
        }
        backPressedTime=System.currentTimeMillis();
    }
}