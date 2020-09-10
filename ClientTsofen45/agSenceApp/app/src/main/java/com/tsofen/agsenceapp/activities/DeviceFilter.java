package com.tsofen.agsenceapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.tsofen.agsenceapp.R;

public class DeviceFilter extends AppCompatActivity {
    boolean displayFaultyDevice = true;
    boolean displayHealthyDevice = true;
    boolean type1Toggle = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_filter);
    }
    public void finishTask(View view) {
        finish();
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
    public void typeToggle(View view) {
        TextView type1Box = view.findViewById(R.id.type1_button);


        if (type1Toggle == true) // do not display healthy devices.
        {
            type1Box.setBackground(ContextCompat.getDrawable(this,R.drawable.blue_shape_squares));
            type1Box.setTextColor(ContextCompat.getColor(this,R.color.dark_blue));

            type1Toggle = false;
        }
        else if (type1Toggle == false) // displaying the healthy device.
        {
            type1Box.setBackground(ContextCompat.getDrawable(this,R.drawable.white_shape_squares));
            type1Box.setTextColor(ContextCompat.getColor(this,R.color.white));

            type1Toggle = true;
        }
    }
}