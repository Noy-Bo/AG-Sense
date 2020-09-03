package com.tsofen.agsenceapp.activities;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.AccountsAdapter;
import com.tsofen.agsenceapp.adapters.DevicesAdapter;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.User;

import java.io.Serializable;

public class AccountStatusFilter extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountstatusfilter);

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
}