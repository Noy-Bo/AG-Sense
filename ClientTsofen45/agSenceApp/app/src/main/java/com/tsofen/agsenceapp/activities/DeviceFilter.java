package com.tsofen.agsenceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.tsofen.agsenceapp.R;

public class DeviceFilter extends BackBaseActivity {
    boolean displayFaultyDevice = true;
    boolean displayHealthyDevice = true;
    boolean type1Toggle = false;
    boolean type2Toggle = false;
    boolean type3Toggle = false;
    private int help;



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


    public void type2Toggle(View view) {
        TextView type2Box = view.findViewById(R.id.type2_button);
        if (type2Toggle == true) // do not display healthy devices.
        {
            type2Box.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
            type2Box.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));

            type2Toggle = false;
        } else if (type2Toggle == false) // displaying the healthy device.
        {
            type2Box.setBackground(ContextCompat.getDrawable(this, R.drawable.white_shape_squares));
            type2Box.setTextColor(ContextCompat.getColor(this, R.color.white));

            type2Toggle = true;
        }
    }




    public void type3Toggle(View view) {
        TextView type3Box = view.findViewById(R.id.type3_button);

        if (type3Toggle == true) // do not display healthy devices.
        {
            type3Box.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
            type3Box.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));

            type3Toggle = false;
        } else if (type3Toggle == false) // displaying the healthy device.
        {
            type3Box.setBackground(ContextCompat.getDrawable(this, R.drawable.white_shape_squares));
            type3Box.setTextColor(ContextCompat.getColor(this, R.color.white));

            type3Toggle = true;
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
    Intent intent = new Intent();
    intent.putExtra("type1",type1Toggle );
    intent.putExtra("type2",type2Toggle );
    intent.putExtra("type3",type3Toggle );
    intent.putExtra("faultyDevices",displayFaultyDevice );
    intent.putExtra("healthyDevices",displayHealthyDevice );
    setResult(RESULT_OK, intent);
    finish();
    }


}