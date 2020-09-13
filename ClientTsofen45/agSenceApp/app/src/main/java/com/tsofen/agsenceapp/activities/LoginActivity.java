package com.tsofen.agsenceapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.LoginCallBack;
import com.tsofen.agsenceapp.adapters.TestAdapter;
import com.tsofen.agsenceapp.entities.User;

public class LoginActivity extends AppCompatActivity {
    public static User user = new User(10, "Tsofen", "Tsofen@Tsofen.Tsofen", "Admin");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void login(View view) {
        EditText usernametext = (EditText) findViewById(R.id.usernameTxt);
        final String username = usernametext.getText().toString();
        ProgressBar progressBar = (ProgressBar) findViewById((R.id.progressBar));
        EditText password = (EditText) findViewById(R.id.passTxt);


       /*if (username != null && username.equals("Admin")) {
            Intent intent = new Intent(this, AdminDashboardActivity.class);
            AppBaseActivity.setUserType(username);
            startActivity(intent);
        }

        else if(username != null && username.equals("Account")) // Noy - added 'else' here so it will not load 2 screens when logging in as admin.
        {
            Intent intent = new Intent(this, AccountDashboardActivity.class);
            AppBaseActivity.setUserType(username);
            startActivity(intent);
        }else{
            Toast.makeText(this,"Please enter a valid username",Toast.LENGTH_LONG).show();
        }*/

        final TestAdapter adapter = new TestAdapter();
        progressBar.setVisibility(View.VISIBLE);

        hideKeyboard(this);

        adapter.login(username, "1111", new LoginCallBack() {
            @Override
            public void onAdminLoginSuccess() {
                Intent intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                AppBaseActivity.setUserType(username);
                startActivity(intent);
            }
            @Override
            public void onUserLoginSuccess() {
                Intent intent = new Intent(LoginActivity.this, AccountDashboardActivity.class);
                AppBaseActivity.setUserType(username);
                startActivity(intent);
            }

            @Override
            public void onLoginFail() {
                Toast.makeText(LoginActivity.this,"Please enter a valid username",Toast.LENGTH_LONG).show();
            }
        });


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