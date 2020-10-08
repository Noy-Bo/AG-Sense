package com.tsofen.agsenceapp.activities;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.utils.AlertFlag;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BackBaseActivity extends AppCompatActivity {


    protected TextView backActivityTitle;
    protected Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_base);
        backActivityTitle = findViewById(R.id.back_activity_back);
        toolbar = (Toolbar) findViewById(R.id.back_toolbar);
        setSupportActionBar(toolbar);
    }

    public void goBack(View view) {
        finish();
    }

    public void showAlertBox(final Context context, final AlertFlag flag, final String text){
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String title;
                int iconSrc;
                if(flag.equals(AlertFlag.SUCCESS)){
                    title = "Success";
                    iconSrc = R.drawable.success_icon;
                }else{
                    title = "Failure";
                    iconSrc = R.drawable.failure_icon;
                }

                new AlertDialog.Builder(context)
                        .setTitle(title)
                        .setMessage(text)
                        .setPositiveButton("Ok", null)
                        .setIcon(iconSrc)
                        .show();
            }
        });

    }

    public boolean validateEmail(String mail) {
        String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

        Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
        if (mail == null) {
            return false;
        }

        Matcher matcher = EMAIL_PATTERN.matcher(mail);
        return matcher.matches();
    }

    public boolean validatePassword(String pass) {

        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        if (pass.length() >= 8) {
            for (int i = 0; i < pass.length(); i++) {
                char temp = pass.charAt(i);
                if (Character.isUpperCase(temp))
                    hasUpperCase = true;
                else if (Character.isLowerCase(temp))
                    hasLowerCase = true;
                else if (Character.isDigit(temp))
                    hasDigit = true;

                if (hasDigit && hasLowerCase && hasUpperCase) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean validatePhoneNumber(String number){
        String phoneNumberRegex = "^05\\d{8}";
        return number.matches(phoneNumberRegex);
    }
}