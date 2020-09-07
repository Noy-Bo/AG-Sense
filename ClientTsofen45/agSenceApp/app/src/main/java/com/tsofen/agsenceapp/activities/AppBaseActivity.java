package com.tsofen.agsenceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;
import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.activities.AccountDashboardActivity;
import com.tsofen.agsenceapp.activities.AdminDashboardActivity;

public class AppBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout drawer;
    protected NavigationView navigationView;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_base);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_admin_dashboard) {
            startAnimatedActivity(new Intent(getApplicationContext(), AdminDashboardActivity.class));
        } else if (id == R.id.nav_account_dashboard) {
            startAnimatedActivity(new Intent(getApplicationContext(), AccountDashboardActivity.class));
        } else if (id == R.id.nav_accounts_status) {
            startAnimatedActivity(new Intent(getApplicationContext(), AccountStatusFilter.class));
        } else if (id == R.id.nav_admin_notifications) {
            startAnimatedActivity(new Intent(getApplicationContext(), AdminNotification.class));
        } else if (id == R.id.nav_account_notifications) {
            startAnimatedActivity(new Intent(getApplicationContext(), AccountDashboardActivity.class));
        } else if (id == R.id.nav_device_status) {
            startAnimatedActivity(new Intent(getApplicationContext(), DeviceStatus.class));
        } else if (id == R.id.nav_logout) {
            startAnimatedActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void startAnimatedActivity(Intent intent) {
        startActivity(intent);
//        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    public void hideAdminOptions() {
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_admin_dashboard).setVisible(false);
        nav_Menu.findItem(R.id.nav_admin_notifications).setVisible(false);
    }

    public void hideAccountOptions() {
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_account_dashboard).setVisible(false);
        nav_Menu.findItem(R.id.nav_account_notifications).setVisible(false);
    }
}