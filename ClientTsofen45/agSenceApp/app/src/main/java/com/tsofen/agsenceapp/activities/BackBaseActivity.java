package com.tsofen.agsenceapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tsofen.agsenceapp.R;

public class BackBaseActivity extends AppCompatActivity {

    protected TextView backActivityTitle;
    protected Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_base);
        backActivityTitle = findViewById(R.id.back_activity_back);
        toolbar = (Toolbar) findViewById(R.id.back_toolbar);
        setSupportActionBar(toolbar);
    }

    public void goBack(View view) {
        finish();
    }
}