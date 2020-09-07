package com.tsofen.agsenceapp.activities;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.AccountsAdapter;
import com.tsofen.agsenceapp.adapters.DevicesAdapter;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.User;

import java.io.Serializable;

public class AccountStatusFilter extends AppBaseActivity implements Serializable {

    boolean displayFaultyDevice = true;
    boolean displayHealthyDevice = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_accountstatusfilter, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_account_dashboard);
        ListView NewsListView = findViewById(R.id.listofaccounts);

        User user = new User (10,"Tsofen","Tsofen@Tsofen.Tsofen","Admin");
        User user1 = new User (10,"Tsofen","Tsofen@Tsofen.Tsofen","Admin");
        User user2 = new User (10,"Tsofen","Tsofen@Tsofen.Tsofen","Admin");
        User user3 = new User (10,"Tsofen","Tsofen@Tsofen.Tsofen","Admin");
        User user4 = new User (10,"Tsofen","Tsofen@Tsofen.Tsofen","Admin");
        User user5 = new User (10,"Tsofen","Tsofen@Tsofen.Tsofen","Admin");
        User user6 = new User (10,"Tsofen","Tsofen@Tsofen.Tsofen","Admin");
        User user7 = new User (10,"Tsofen","Tsofen@Tsofen.Tsofen","Admin");
        User user8 = new User (10,"Tsofen","Tsofen@Tsofen.Tsofen","Admin");
        User[] users = new User[9];
        users[0] = user;
        users[1] = user1;
        users[2] = user2;
        users[3] = user3;
        users[4] = user4;
        users[5] = user5;
        users[6] = user6;
        users[7] = user7;
        users[8] = user8;

        ListAdapter myAdapter = new AccountsAdapter(this,0, users) ;
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