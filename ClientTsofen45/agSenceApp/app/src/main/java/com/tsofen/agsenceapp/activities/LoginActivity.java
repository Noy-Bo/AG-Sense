package com.tsofen.agsenceapp.activities;



import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ProcessLifecycleOwner;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tsofen.agsenceapp.BackgroundServices.AppLifecycleObserver;
import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.dataServices.OnLogin;
import com.tsofen.agsenceapp.adaptersInterfaces.onUserLoginHandler;
import com.tsofen.agsenceapp.dataAdapters.UserDataAdapter;
import com.tsofen.agsenceapp.dataServices.ServicesName;
import com.tsofen.agsenceapp.dataServices.UrlConnectionMaker;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.User;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    public CacheMgr cacheMgr = CacheMgr.getInstance();
    public static User user ;

    @RequiresApi(api = Build.VERSION_CODES.O)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // observer registeration for onforeground. -- read AppLifeCycleObserver.
        AppLifecycleObserver appLifecycleObserver = new AppLifecycleObserver();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(appLifecycleObserver);


    }




    public void login(View view) {
        EditText editText = (EditText) findViewById(R.id.usernameTxt);
        final String username = editText.getText().toString();
//
//        if (username != null && username.equals("Admin")) {
//            Intent intent = new Intent(this, AdminDashboardActivity.class);
//            AppBaseActivity.setUserType(username);
//            startActivity(intent);
//        }
//
//        else if(username != null && username.equals("Account")) // Noy - added 'else' here so it will not load 2 screens when logging in as admin.
//        {
//            Intent intent = new Intent(this, AccountDashboardActivity.class);
//            AppBaseActivity.setUserType(username);
//            startActivity(intent);
//        }else{
//            Toast.makeText(this,"Please enter a valid username",Toast.LENGTH_LONG).show();
//        }

        UserDataAdapter.userLogin(username, "", new onUserLoginHandler() {
            @Override
            public void onAdminLoginSuccess(Admin user) {
                Intent intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                AppBaseActivity.setUserType(username);
                startActivity(intent);
            }

            @Override
            public void onAccountLoginSuccess(Account user) {

            }

            @Override
            public void onUserLoginFailed() {

            }
        });
        Map<String,String> params = new HashMap<>();
        params.put("username","Admin");
        params.put("password","1234");
        String url = UrlConnectionMaker.ctreatUrl(ServicesName.Login,params);
    }
}