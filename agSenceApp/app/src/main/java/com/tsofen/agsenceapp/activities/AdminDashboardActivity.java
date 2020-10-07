package com.tsofen.agsenceapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.tsofen.agsenceapp.BackgroundServices.AppLifecycleObserver;
import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.AccountsAdapter;
import com.tsofen.agsenceapp.adaptersInterfaces.NotificationsDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.AccountsDataAdapter;
import com.tsofen.agsenceapp.dataServices.AccountsHandler;
import com.tsofen.agsenceapp.dataServices.AdminDashboardInfoHandler;
import com.tsofen.agsenceapp.dataServices.DeviceSmsInfoHandler;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.Notification;
import com.tsofen.agsenceapp.utils.AdminDashboardInfo;
import com.tsofen.agsenceapp.utils.GeneralProgressBar;

import java.util.ArrayList;
import java.util.List;

public class AdminDashboardActivity extends SearchBaseActivity {

    private long backPressedTime;
    private Toast backtoast;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_admin_dashboard, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_admin_dashboard);
        searchView = (AutoCompleteTextView) contentView.findViewById(R.id.search_text_view);
        searchView.setHint(R.string.search_account_hint);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initDashboard();
                swipeRefreshLayout.setRefreshing(false);

            }
        });

        AccountsDataAdapter.getInstance().getAllAccounts(false, new AccountsHandler() {
            @Override
            public void onAccountsDownloadFinished(final List<Account> accounts) {
                AdminDashboardActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        searchView.setAdapter(new AccountsAdapter<Account>(AdminDashboardActivity.this, accounts));
                    }
                });

            }
        });
        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AdminDashboardActivity.this, AccountDashboardActivity.class);
                Account account = (Account) searchView.getAdapter().getItem(i);
                intent.putExtra("account", account);
                startActivity(intent);
            }
        });

        // observer registeration for onforeground. -- read AppLifeCycleObserver.
        AppLifecycleObserver appLifecycleObserver = new AppLifecycleObserver();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(appLifecycleObserver);
        initDashboard();

        CacheMgr.getInstance().getDeviceSmsinfoJob("864403044179316", new DeviceSmsInfoHandler() {
            @Override
            public void onDeviceSmsInfoReceived(String devicePasswordAndPhoneNumber) {
               Log.d("testing sms","displaying results: " + devicePasswordAndPhoneNumber);
            }
        });
    }

    public void goToNotifications(View view) {
        Intent intent = new Intent(AdminDashboardActivity.this, NotificationsActivity.class);
        intent.putExtra("obj", (Admin) AppBaseActivity.getUser());
        startActivity(intent);
    }

    public void goToFaultyAccounts(View view) {

        pd = GeneralProgressBar.displayProgressDialog(this, "loading...");

        Intent intent = new Intent(AdminDashboardActivity.this, CompanyStatus.class);
        intent.putExtra("filter", "faulty");
        startActivity(intent);

    }

    public void goToHealthyAccounts(View view) {
        pd = GeneralProgressBar.displayProgressDialog(this, "loading...");

        Intent intent = new Intent(AdminDashboardActivity.this, CompanyStatus.class);
        intent.putExtra("filter", "healthy");
        startActivity(intent);

    }

    public void goToHealthyDevices(View view) {
        pd = GeneralProgressBar.displayProgressDialog(this, "loading...");

        Intent intent = new Intent(AdminDashboardActivity.this, DeviceStatus.class);
        intent.putExtra("filter", "healthy");
        startActivity(intent);

    }

    public void goToFaultyDevices(View view) {

        pd = GeneralProgressBar.displayProgressDialog(this, "loading...");

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
            CacheMgr.getInstance().clearCache();
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
        GeneralProgressBar.removeProgressDialog(pd);
        initDashboard();
    }

    private void initDashboard() {
        CacheMgr.getInstance().getAdminDashboardInfoJob(AppBaseActivity.getUser().getId(), new AdminDashboardInfoHandler() {
            @Override
            public void onAdminDashboardInfoReceived(AdminDashboardInfo adminDashboardInfo) {
                TextView faultyAccountsCount = findViewById(R.id.faulty_accounts_count);
                TextView healthyAccountsCount = findViewById(R.id.healthy_accounts_count);
                TextView faultyDevicesCount = findViewById(R.id.faulty_device_count);
                TextView healthyDevicesCount = findViewById(R.id.healthy_devices_count);
                TextView unreadNotificationsCount = findViewById(R.id.unread_notifications_count);
                AdminDashboardActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        faultyAccountsCount.setText(adminDashboardInfo.getFaultyAccountsNumber());
                        healthyAccountsCount.setText(adminDashboardInfo.getHealtyAccountsNumber());
                        faultyDevicesCount.setText(adminDashboardInfo.getFaultyDevicesNumber());
                        healthyDevicesCount.setText(adminDashboardInfo.getHealtyDevicesNumber());
                        unreadNotificationsCount.setText(adminDashboardInfo.getUnreadNotificationsNumber());
                    }
                });
            }
        });
    }

}