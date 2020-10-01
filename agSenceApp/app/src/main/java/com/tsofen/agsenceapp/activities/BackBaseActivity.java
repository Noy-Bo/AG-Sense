package com.tsofen.agsenceapp.activities;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.utils.AlertFlag;

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

    public void showAlertBox(Context context, AlertFlag flag, String text){
        String title;
        int iconSrc;
        if(flag.equals(AlertFlag.SUCCESS)){
            title = "Success";
            iconSrc = R.drawable.success_icon;
        }else{
            title = "Failure";
            iconSrc = R.drawable.failure_icon;
        }

        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(text)
                .setPositiveButton("Ok", null)
                .setIcon(iconSrc)
                .show();
    }
}