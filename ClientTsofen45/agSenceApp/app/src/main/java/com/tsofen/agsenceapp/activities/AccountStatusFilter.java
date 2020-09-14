package com.tsofen.agsenceapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.AccountsAdapter;
import com.tsofen.agsenceapp.entities.User;

import java.io.Serializable;
import java.util.ArrayList;

public class AccountStatusFilter extends AppBaseActivity implements Serializable {
    boolean displayFaultyDevice = true;
    boolean displayHealthyDevice = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_accountstatusfilter, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_accounts_status);
        ListView NewsListView = findViewById(R.id.listofaccounts);
       /* Account user = new Account (10,"Tsofen","Tsofen@Tsofen.Tsofen",true,1);
        Account user1 = new Account (10,"Tsofen","Tsofen@Tsofen.Tsofen",true,1);
        Account user2 = new Account (10,"Tsofen","Tsofen@Tsofen.Tsofen",true,1);
        Account user3 = new Account (10,"Tsofen","Tsofen@Tsofen.Tsofen",true,1);
        Account user4 = new Account (10,"Tsofen","Tsofen@Tsofen.Tsofen",true,1);
        Account user5 = new Account (10,"Tsofen","Tsofen@Tsofen.Tsofen",true,1);
        Account user6 = new Account(10,"Tsofen","Tsofen@Tsofen.Tsofen",true,1);
        Account user7 = new Account (10,"Tsofen","Tsofen@Tsofen.Tsofen",true,1);
        Account user8 = new Account (10,"Tsofen","Tsofen@Tsofen.Tsofen",true,1);
        Account[] Accounts = new Account[9];
        Accounts[0] = user;
        Accounts[1] = user1;
        Accounts[2] = user2;
        Accounts[3] = user3;
        Accounts[4] = user4;
        Accounts[5] = user5;
        Accounts[6] = user6;
        Accounts[7] = user7;
        Accounts[8] = user8;
        ListAdapter myAdapter = new AccountsAdapter(this,0, Accounts) ;
        NewsListView.setAdapter(myAdapter);*/




        ArrayList<User> accounts = (ArrayList<User>) getIntent().getSerializableExtra("accounts");
        System.out.println(accounts);

       // ListView accountlist = contentView.findViewById(R.id.listofaccounts);
       // User[] accounts1 = (User[]) getIntent().getSerializableExtra("faulty");
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
    public void createAccount(View view) {
        Intent intent = new Intent(this, NewAccount.class);
        startActivity(intent);
    }
}