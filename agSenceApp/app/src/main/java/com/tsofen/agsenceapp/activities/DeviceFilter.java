package com.tsofen.agsenceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.tsofen.agsenceapp.R;

public class DeviceFilter extends BackBaseActivity {
    boolean displayFaultyDevice;
    boolean displayHealthyDevice;
    boolean type1Toggle;
    boolean type2Toggle;
    boolean type3Toggle;
    private int help;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_filter);
        displayFaultyDevice = true;
        displayHealthyDevice = true;
        type1Toggle = false;
        type2Toggle = false;
        type3Toggle = false;
    }


    public void switchButton(View view) {
        TextView typeBox = (TextView) view;

        if (typeBox.getId() == R.id.type1_button) {
            switchColor(typeBox, type1Toggle);
            if (type1Toggle) {
                type1Toggle = false;
            } else {
                type1Toggle = true;
            }

        } else if (typeBox.getId() == R.id.type2_button) {
            switchColor(typeBox, type2Toggle);
            if (type2Toggle) {
                type2Toggle = false;
            } else {
                type2Toggle = true;
            }

        } else if (typeBox.getId() == R.id.type3_button) {
            switchColor(typeBox, type3Toggle);
            if (type3Toggle) {
                type3Toggle = false;
            } else {
                type3Toggle = true;
            }

        } else if (typeBox.getId() == R.id.display_faulty_button) {
            switchColor(typeBox, displayFaultyDevice);
            if (displayFaultyDevice) {
                displayFaultyDevice = false;
            } else {
                displayFaultyDevice = true;
            }

        } else if (typeBox.getId() == R.id.display_healthy_button) {
            switchColor(typeBox, displayHealthyDevice);
            if (displayHealthyDevice)
                displayHealthyDevice = false;
            else
                displayHealthyDevice = true;
        }
    }

    private void switchColor(TextView typeBox, boolean isPressed) {
        if (isPressed) {
            typeBox.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
            typeBox.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
        } else {
            typeBox.setBackground(ContextCompat.getDrawable(this, R.drawable.white_shape_squares));
            typeBox.setTextColor(ContextCompat.getColor(this, R.color.white));
        }
    }


    public void resetFilterDevices(View view) {

        TextView displayfaultybutton = findViewById(R.id.display_faulty_button);

        displayfaultybutton.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
        displayfaultybutton.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
        displayFaultyDevice = false;


        TextView displayhealthybutton = findViewById(R.id.display_healthy_button);

        displayhealthybutton.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
        displayhealthybutton.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
        displayHealthyDevice = false;


        TextView type1Box = findViewById(R.id.type1_button);

        type1Box.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
        type1Box.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));

        type1Toggle = false;


        TextView type2Box = findViewById(R.id.type2_button);

        type2Box.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
        type2Box.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));

        type2Toggle = false;
        TextView type3Box = findViewById(R.id.type3_button);

        type3Box.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
        type3Box.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));

        type3Toggle = false;


    }

    public void returnResults(View view) {
        returnResults();
    }

    @Override
    public void goBack(View view) {
        type1Toggle = true;
        type2Toggle = true;
        type3Toggle = true;
        displayHealthyDevice = true;
        displayFaultyDevice = true;
        returnResults();
    }

    private void returnResults() {
        Intent intent = new Intent();
        intent.putExtra("GpsForPersonal", type1Toggle);
        intent.putExtra("SensorForBanks", type2Toggle);
        intent.putExtra("lequidHeightForTanks", type3Toggle);
        intent.putExtra("faultyDevices", displayFaultyDevice);
        intent.putExtra("healthyDevices", displayHealthyDevice);
        setResult(RESULT_OK, intent);
        finish();
    }
}