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
}