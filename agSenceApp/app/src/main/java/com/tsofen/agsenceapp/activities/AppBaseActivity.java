package com.tsofen.agsenceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.navigation.NavigationView;
import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.CacheManagerAPI;
import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.dataAdapters.AccountsDataAdapter;
import com.tsofen.agsenceapp.dataServices.AccountsHandler;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.User;

import java.util.ArrayList;
import java.util.List;

public class AppBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    protected DrawerLayout drawer;
    protected NavigationView navigationView;
    protected Toolbar toolbar;
    static User user;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_base);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //disable for all,  each activity enable for her own.
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setEnabled(false);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(user instanceof Admin)
            hideAccountOptions();
        else
            hideAdminOptions();

        TextView usernameHeader = (TextView)findViewById(R.id.header_username);
        TextView userEmailHeader = (TextView)findViewById(R.id.header_username);
        userEmailHeader.setText(user.getEmail());
        usernameHeader.setText(user.getUsername());
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.isChecked())
            return true;
        int id = item.getItemId();
        if (id == R.id.nav_admin_dashboard) {
            startActivity(new Intent(getApplicationContext(), AdminDashboardActivity.class));
        } else if (id == R.id.nav_account_dashboard) {
            startActivity(new Intent(getApplicationContext(), AccountDashboardActivity.class));
        } else if (id == R.id.nav_accounts_status) {
            Intent intent = new Intent(this, AccountStatusFilter.class);
            intent.putExtra("filter","all");
            startActivity(intent);
        } else if (id == R.id.nav_account_devices_status) {
            Intent intent = new Intent(this, AccountDevicesStatus.class);
            intent.putExtra("account",user);
            intent.putExtra("filter","all");
            startActivity(intent);
        }  else if (id == R.id.nav_admin_notifications) {
            Intent intent = new Intent(this, NotificationsActivity.class);
            intent.putExtra("obj",(Admin)user);
            startActivity(intent);
        }   else if (id == R.id.nav_device_status) {
            Intent intent = new Intent(this, DeviceStatus.class);
            intent.putExtra("filter", "all");
            startActivity(intent);
        }else if(id == R.id.nav_other){
            Intent intent = new Intent(this, OthersActivity.class);
            startActivity(intent);
        }else if (id==R.id.nav_device_settings){
            Intent intent = new Intent(this,DeviceSettings.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            CacheMgr.getInstance().clearCache();
            finishAffinity();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void hideAdminOptions() {
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_accounts_status).setVisible(false);
        nav_Menu.findItem(R.id.nav_admin_dashboard).setVisible(false);
        nav_Menu.findItem(R.id.nav_admin_notifications).setVisible(false);
        nav_Menu.findItem(R.id.nav_device_status).setVisible(false);
        nav_Menu.findItem(R.id.nav_other).setVisible(false);
        nav_Menu.findItem(R.id.nav_device_settings).setVisible(false);
    }

    public void hideAccountOptions() {
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_account_devices_status).setVisible(false);
        nav_Menu.findItem(R.id.nav_account_dashboard).setVisible(false);
        nav_Menu.findItem(R.id.nav_account_devices_status).setVisible(false);
    }

    public static void setUser(User user) {
        AppBaseActivity.user = user;
    }

    public static User getUser(){return user; }
}