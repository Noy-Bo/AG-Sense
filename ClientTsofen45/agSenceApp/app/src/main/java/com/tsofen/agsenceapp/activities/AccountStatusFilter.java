package com.tsofen.agsenceapp.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.DevicesAdapter;
import com.tsofen.agsenceapp.entities.Devices;

public class AccountStatusFilter extends AppCompatActivity {

    boolean displayFaultyDevice = true;
    boolean displayHealthyDevice = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountstatusfilter);

        ListView NewsListView = findViewById(R.id.listofaccounts);

        Devices[] devices1 = (Devices[]) getIntent().getSerializableExtra("extra");
        ListAdapter myAdapter = new DevicesAdapter(this,0, devices1) ;
        NewsListView.setAdapter(myAdapter);
    }

    public void displayFaultyClicked(View view) {
        TextView displayFaultyBox = view.findViewById(R.id.display_faulty_button);

        if (displayFaultyDevice == true) // do not display faulty devices.
        {
            displayFaultyBox.setBackground(ContextCompat.getDrawable(this,R.drawable.blue_shape_squares));
            displayFaultyBox.setTextColor(ContextCompat.getColor(this,R.color.dark_blue));
            displayFaultyDevice = false;
        }
        else if (displayFaultyDevice == false) // displaying the faulty device.
        {
            displayFaultyBox.setBackground(ContextCompat.getDrawable(this,R.drawable.white_shape_squares));
            displayFaultyBox.setTextColor(ContextCompat.getColor(this,R.color.white));
            displayFaultyDevice = true;
        }
    }

    public void displayHealthyClicked(View view) {
        TextView displayHealthyBox = view.findViewById(R.id.display_healthy_button);

        if (displayHealthyDevice == true) // do not display healthy devices.
        {
            displayHealthyBox.setBackground(ContextCompat.getDrawable(this,R.drawable.blue_shape_squares));
            displayHealthyBox.setTextColor(ContextCompat.getColor(this,R.color.dark_blue));
            displayHealthyDevice = false;
        }
        else if (displayHealthyDevice == false) // displaying the healthy device.
        {
            displayHealthyBox.setBackground(ContextCompat.getDrawable(this,R.drawable.white_shape_squares));
            displayHealthyBox.setTextColor(ContextCompat.getColor(this,R.color.white));
            displayHealthyDevice = true;
        }

    }
}