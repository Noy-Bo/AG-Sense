package com.tsofen.agsenceapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tsofen.agsenceapp.R;

public class BackBaseActivity extends AppCompatActivity {

    protected TextView backActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_base);
        backActivityTitle = findViewById(R.id.back_activity_back);
    }

    public void goBack(View view) {
        finish();
    }
}