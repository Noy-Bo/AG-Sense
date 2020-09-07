package com.tsofen.agsenceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.entities.User;

public class LoginActivity extends AppCompatActivity {
    public static User user = new User (10,"Tsofen","Tsofen@Tsofen.Tsofen","Admin");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_setting);
    }

    public void login(View view) {
        EditText editText = (EditText) findViewById(R.id.usernameTxt);
        String username = editText.getText().toString();

        if (username != null && username.equals("Admin")) {
            Intent intent = new Intent(this, AdminDashboardActivity.class);
            startActivity(intent);
        }

        else // Noy - added 'else' here so it will not load 2 screens when logging in as admin.
        {
            Intent intent = new Intent(this, AccountDashboardActivity.class);
            startActivity(intent);
        }

    }
}