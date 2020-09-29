package com.tsofen.agsenceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MakeSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_selection);
    }

    public void callBackScreenFromMakeSelection(View view) {

    }

    public void callOTPScreenFromMakeSelection(View view) {
        Intent intent = new Intent(this, VerifyOTP.class);

        startActivity(intent);
    }

    public void GoToEmailSending(View view) {
        Intent intent = new Intent(this, VerifyOTP.class);

        startActivity(intent);
    }
}