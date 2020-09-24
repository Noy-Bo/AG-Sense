package com.tsofen.agsenceapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.ProcessLifecycleOwner;

import com.tsofen.agsenceapp.BackgroundServices.AppLifecycleObserver;
import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adaptersInterfaces.NotificationsDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.AccountsDataAdapter;
import com.tsofen.agsenceapp.dataAdapters.NotificationsDataAdapter;
import com.tsofen.agsenceapp.dataServices.AccountsHandler;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.Notification;

import java.util.ArrayList;
import java.util.List;

public class AdminDashboardActivity extends SearchBaseActivity {

    private long backPressedTime;
    private Toast backtoast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_admin_dashboard, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_admin_dashboard);

        // observer registeration for onforeground. -- read AppLifeCycleObserver.
        AppLifecycleObserver appLifecycleObserver = new AppLifecycleObserver();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(appLifecycleObserver);
    }

    public void goToNotifications(View view) {
        ProgressBar progressBar = (ProgressBar) findViewById((R.id.adminProgressBar));
        progressBar.setVisibility(View.VISIBLE);
        NotificationsDataAdapter.getInstance().getAllNotifications(0, 20, new NotificationsDataRequestHandler() {
            @Override
            public void onNotificationsReceived(List<Notification> notifications) {
                Intent intent = new Intent(AdminDashboardActivity.this, NotificationsActivity.class);
                intent.putExtra("obj", (Admin)AppBaseActivity.user);
                startActivity(intent);
            }
        });

    }

    public void goToFaultyAccounts(View view) {
        ProgressBar progressBar = (ProgressBar) findViewById((R.id.adminProgressBar));
        progressBar.setVisibility(View.VISIBLE);

        Intent intent = new Intent(AdminDashboardActivity.this, AccountStatusFilter.class);
        intent.putExtra("filter", "faulty");
        startActivity(intent);

    }

    public void goToHealthyAccounts(View view) {
        ProgressBar progressBar = (ProgressBar) findViewById((R.id.adminProgressBar));
        progressBar.setVisibility(View.VISIBLE);

        Intent intent = new Intent(AdminDashboardActivity.this, AccountStatusFilter.class);
        intent.putExtra("filter", "healthy");
        startActivity(intent);


    }

    public void goToHealthyDevices(View view) {
        ProgressBar progressBar = (ProgressBar) findViewById((R.id.adminProgressBar));
        progressBar.setVisibility(View.VISIBLE);

        Intent intent = new Intent(AdminDashboardActivity.this, DeviceStatus.class);
        intent.putExtra("filter", "healthy");
        startActivity(intent);


    }

    public void goToFaultyDevices(View view) {

        ProgressBar progressBar = (ProgressBar) findViewById((R.id.adminProgressBar));
        progressBar.setVisibility(View.VISIBLE);

        Intent intent = new Intent(AdminDashboardActivity.this, DeviceStatus.class);
        intent.putExtra("filter", "faulty");
        startActivity(intent);

    }


    public void GoToAccountSettings(View view) {
        Intent intent = new Intent(this, DeviceSettings.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backtoast.cancel();
            super.finishAffinity();
            return;
        } else {
            backtoast = Toast.makeText(getBaseContext(), "press back again to exit", Toast.LENGTH_SHORT);
            backtoast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }



    public void GoToOther(View view) {
        final ArrayList<Account> _accounts = new ArrayList<>();
        Intent intent = new Intent(this, OthersActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ProgressBar progressBar = (ProgressBar) findViewById((R.id.adminProgressBar));
        progressBar.setVisibility(View.INVISIBLE);
    }


}