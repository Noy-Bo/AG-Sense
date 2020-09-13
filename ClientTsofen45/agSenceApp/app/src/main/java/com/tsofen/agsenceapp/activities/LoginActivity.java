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
import com.tsofen.agsenceapp.adaptersInterfaces.onUserLoginHandler;
import com.tsofen.agsenceapp.dataAdapters.UserDataAdapter;
import com.tsofen.agsenceapp.dataServices.ServicesName;
import com.tsofen.agsenceapp.dataServices.UrlConnectionMaker;
import com.tsofen.agsenceapp.entities.User;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    public static User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Map<String,String> params = new HashMap<>();
        params.put("username","Admin");
        params.put("password","1234");
        String url = UrlConnectionMaker.ctreatUrl(ServicesName.Login,params);

    }


    public void login(View view) {

        EditText usernametext = (EditText) findViewById(R.id.usernameTxt);
        final String username = usernametext.getText().toString();
        ProgressBar progressBar = (ProgressBar) findViewById((R.id.progressBar));
        EditText password = (EditText) findViewById(R.id.passTxt);
final String pass = password.getText().toString();


        progressBar.setVisibility(View.VISIBLE);

        hideKeyboard(this);


        UserDataAdapter.userLogin(username, pass, new onUserLoginHandler() {
            @Override
            public void onAdminLoginSuccess(User user) {
                Intent intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                AppBaseActivity.setUserType(username);
                startActivity(intent);
            }

            @Override
            public void onAccountLoginSuccess(User user) {
                Intent intent = new Intent(LoginActivity.this, AccountDashboardActivity.class);
                AppBaseActivity.setUserType(username);
                startActivity(intent);
            }

            @Override
            public void onUserLoginFailed() {
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