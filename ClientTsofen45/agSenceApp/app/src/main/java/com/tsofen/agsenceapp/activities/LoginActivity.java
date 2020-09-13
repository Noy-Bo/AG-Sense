package com.tsofen.agsenceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.dataServices.ServicesName;
import com.tsofen.agsenceapp.dataServices.UrlConnectionMaker;
import com.tsofen.agsenceapp.entities.User;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    public static User user = new User (10,"Tsofen","Tsofen@Tsofen.Tsofen","Admin");
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
        EditText editText = (EditText) findViewById(R.id.usernameTxt);
        String username = editText.getText().toString();

        if (username != null && username.equals("Admin")) {
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
        }

    }
}