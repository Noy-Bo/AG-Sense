package com.tsofen.agsenceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SetNewPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);
    }

    public void goToHomeFromSetNewPassword(View view) {
    }

    public void setNewPasswordBtn(View view) {
        Intent intent = new Intent(this, ForgetPasswordSuccessMessage.class);

        startActivity(intent);
    }
}