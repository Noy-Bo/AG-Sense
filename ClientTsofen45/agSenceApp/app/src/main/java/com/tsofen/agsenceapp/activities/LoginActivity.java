package com.tsofen.agsenceapp.activities;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.work.WorkManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.tsofen.agsenceapp.BackgroundServices.AppLifecycleObserver;
import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.dataServices.OnLogin;
import com.tsofen.agsenceapp.entities.User;

public class LoginActivity extends AppCompatActivity {
    public CacheMgr cacheMgr = CacheMgr.getInstance();
    public static User user = new User(10, "Tsofen", "Tsofen@Tsofen.Tsofen", "Admin");
    private WorkManager workManager = WorkManager.getInstance(this);

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // observer registeration for onforeground. -- read AppLifeCycleObserver.
        AppLifecycleObserver appLifecycleObserver = new AppLifecycleObserver();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(appLifecycleObserver);


    }

    public void loginButton(View view) {
        EditText editText = (EditText) findViewById(R.id.usernameTxt);
        String username = editText.getText().toString();

        if (username != null && username.equals("Admin")) {
            Intent intent = new Intent(this, AdminDashboardActivity.class);

            startActivity(intent);
        } else {
            Intent intent = new Intent(this, AccountDashboardActivity.class);
            startActivity(intent);
        }

    }
}