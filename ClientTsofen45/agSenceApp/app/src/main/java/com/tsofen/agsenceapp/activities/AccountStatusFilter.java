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
import com.tsofen.agsenceapp.adapters.AccountsAdapter;
import com.tsofen.agsenceapp.adapters.DevicesAdapter;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.User;

import java.io.Serializable;

public class AccountStatusFilter extends AppCompatActivity implements Serializable {

    boolean displayFaultyDevice = true;
    boolean displayHealthyDevice = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountstatusfilter);

        ListView NewsListView = findViewById(R.id.listofaccounts);
        User account1 = new Account(10,"Tsofen","Tsofen@Tsofen.Tsofen","Admin");
        User account2 = new Account(10,"Tsofen","Tsofen@Tsofen.Tsofen","Admin");
        User account3 = new Account(10,"Tsofen","Tsofen@Tsofen.Tsofen","Admin");
        User account4 = new Account(10,"Tsofen","Tsofen@Tsofen.Tsofen","Admin");
        User account5 = new Account(10,"Tsofen","Tsofen@Tsofen.Tsofen","Admin");
        User account6 = new Account(10,"Tsofen","Tsofen@Tsofen.Tsofen","Admin");
        User account7 = new Account(10,"Tsofen","Tsofen@Tsofen.Tsofen","Admin");
        User account8 = new Account(10,"Tsofen","Tsofen@Tsofen.Tsofen","Admin");
        User account9 = new Account(10,"Tsofen","Tsofen@Tsofen.Tsofen","Admin");
        User[] accounts = new User[9];
        accounts[0] = account1;
        accounts[1] = account2;
        accounts[2] = account3;
        accounts[3] = account4;
        accounts[4] = account5;
        accounts[5] = account6;
        accounts[6] = account7;
        accounts[7] = account8;
        accounts[8] = account9;




        ListAdapter myAdapter = new AccountsAdapter(this,0, accounts) ;
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