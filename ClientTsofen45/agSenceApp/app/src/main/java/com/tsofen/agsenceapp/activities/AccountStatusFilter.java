package com.tsofen.agsenceapp.activities;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.DevicesAdapter;
import com.tsofen.agsenceapp.entities.Devices;

public class AccountStatusFilter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountstatusfilter);

        ListView NewsListView = findViewById(R.id.listofaccounts);

        Devices[] devices1 = (Devices[]) getIntent().getSerializableExtra("extra");
        ListAdapter myAdapter = new DevicesAdapter(this,0, devices1) ;
        NewsListView.setAdapter(myAdapter);
    }
}