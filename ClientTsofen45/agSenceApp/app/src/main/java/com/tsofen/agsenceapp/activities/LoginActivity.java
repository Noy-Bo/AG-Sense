package com.tsofen.agsenceapp.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ProcessLifecycleOwner;


import com.tsofen.agsenceapp.BackgroundServices.AppLifecycleObserver;
import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adaptersInterfaces.onUserLoginHandler;
import com.tsofen.agsenceapp.dataAdapters.UserDataAdapter;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.User;

public class LoginActivity extends AppCompatActivity {

    public CacheMgr cacheMgr = CacheMgr.getInstance();
    public static Admin admin;
    public static Account account;

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

        EditText usernametext = (EditText) findViewById(R.id.usernameTxt);
        final String username = usernametext.getText().toString();
        ProgressBar progressBar = (ProgressBar) findViewById((R.id.progressBar));
        EditText password = (EditText) findViewById(R.id.passTxt);
        final String pass = password.getText().toString();


        progressBar.setVisibility(View.VISIBLE);

        hideKeyboard(this);


        UserDataAdapter.getInstance().userLogin(username, pass, new onUserLoginHandler() {
            @Override
            public void onAdminLoginSuccess(Admin user) {
                setAdmin(user);
                Intent intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                AppBaseActivity.setUserType("Admin");
                startActivity(intent);
            }

            @Override
            public void onAccountLoginSuccess(Account user) {
                setAccount(user);
                Intent intent = new Intent(LoginActivity.this, AccountDashboardActivity.class);
                AppBaseActivity.setUserType("Account");
                startActivity(intent);
            }

            @Override
            public void onUserLoginFailed() {
                Toast.makeText(LoginActivity.this, "Please enter a valid username", Toast.LENGTH_LONG).show();
            }

        });


    }

    public static void setAdmin(Admin admin) {
        LoginActivity.admin = admin;
    }

    public static void setAccount(Account account) {
        LoginActivity.account = account;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
