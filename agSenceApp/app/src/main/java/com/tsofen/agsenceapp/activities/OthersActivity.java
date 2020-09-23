package com.tsofen.agsenceapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.tsofen.agsenceapp.R;

public class OthersActivity extends AppBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_others, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_other);
    }

    public void goToEditDevice(View view) {
        Intent intent = new Intent(this,EditDevice.class);
        startActivity(intent);
    }

    public void goToEditAccount(View view) {
        Intent intent = new Intent(this,EditAccount.class);
        startActivity(intent);
    }

    public void goToAddNewAccount(View view) {
        Intent intent = new Intent(this,NewAccount.class);
        startActivity(intent);
    }

    public void goToAddNewUser(View view) {
        Intent intent = new Intent(this,NewUser.class);
        startActivity(intent);
    }

    public void goToAddNewDevice(View view) {
        Intent intent = new Intent(this,NewDevice.class);
        startActivity(intent);
    }
}