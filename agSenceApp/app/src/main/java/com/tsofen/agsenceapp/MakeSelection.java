package com.tsofen.agsenceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tsofen.agsenceapp.entities.Account;

public class MakeSelection extends AppCompatActivity {
protected TextView mail_des;
protected Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_selection);
        mail_des = findViewById(R.id.mail_des);

        account = (Account) getIntent().getSerializableExtra("accounts");
        assert account != null;
        mail_des.setText(account.getEmail());

    }

    public void callBackScreenFromMakeSelection(View view) {

    }

    public void callOTPScreenFromMakeSelection(View view) {
        Intent intent = new Intent(this, VerifyOTP.class);

        startActivity(intent);
    }

    public void GoToEmailSending(View view) {
        Intent intent = new Intent(this, VerifyOTP.class);
        intent.putExtra("account",account);
        startActivity(intent);
    }
}