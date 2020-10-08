package com.tsofen.agsenceapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.tsofen.agsenceapp.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgetPassword extends BackBaseActivity {
protected EditText VerifyNewPassword,NewPassword,EmailEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VerifyNewPassword = findViewById(R.id.VerifyNewPassword);
        NewPassword = findViewById(R.id.NewPassword);
        EmailEdit = findViewById(R.id.EmailEdit);
        setContentView(R.layout.activity_forget_password);
    }

    public void SendCodeToEmail(View view) {
        EmailEdit = findViewById(R.id.EmailEdit);
        if (validateEmail(EmailEdit.getText().toString())) {
            System.out.println("The email address " + EmailEdit.getText().toString() + " is valid");
        }
        else {
            EmailEdit.setError("Invalid Email");
        }
    }

    public void UpdateForgetPassword(View view) {

        if(!NewPassword.getText().toString().equals(VerifyNewPassword.getText().toString()))
        {
            VerifyNewPassword.setError("Password Doesn't Match");
        }
        if(!validatePassword(NewPassword.getText().toString()))
        {
            NewPassword.setError("Password is weak");
        }
    }

}