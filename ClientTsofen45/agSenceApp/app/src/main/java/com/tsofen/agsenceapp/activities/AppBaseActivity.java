package com.tsofen.agsenceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.tsofen.agsenceapp.R;

public class AppBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    protected DrawerLayout drawer;
    protected NavigationView navigationView;
    protected Toolbar toolbar;
    static boolean isAdmin;

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

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(isAdmin)
            hideAccountOptions();
        else
            hideAdminOptions();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_admin_dashboard) {
            startActivity(new Intent(getApplicationContext(), AdminDashboardActivity.class));
        } else if (id == R.id.nav_account_dashboard) {
            startActivity(new Intent(getApplicationContext(), AccountDashboardActivity.class));
        } else if (id == R.id.nav_accounts_status) {
            startActivity(new Intent(getApplicationContext(), AccountStatusFilter.class));
        } else if (id == R.id.nav_admin_notifications) {
            startActivity(new Intent(getApplicationContext(), AdminNotification.class));
        }   else if (id == R.id.nav_device_status) {
            startActivity(new Intent(getApplicationContext(), DeviceStatus.class));
        } else if (id == R.id.nav_logout) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void hideAdminOptions() {
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_admin_dashboard).setVisible(false);
        nav_Menu.findItem(R.id.nav_admin_notifications).setVisible(false);
    }

    public void hideAccountOptions() {
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_account_dashboard).setVisible(false);
    }

    public static void setUserType(String userType){
        if(userType.equals("Admin"))
            isAdmin = true;
        else
            isAdmin = false;

    }
}