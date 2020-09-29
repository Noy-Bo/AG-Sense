package com.tsofen.agsenceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ForgetPasswords extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_passwords);
    }

    public void callBackScreenFromForgetPassword(View view) {
    }

    public void verifyPhoneNumber(View view) {
        Intent intent = new Intent(this, MakeSelection.class);

        startActivity(intent);
    }
}