package com.tsofen.agsenceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tsofen.agsenceapp.adaptersInterfaces.EmailPickedConfirmedDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.ForgetPasswordDataAdapter;
import com.tsofen.agsenceapp.entities.Account;

public class MakeSelection extends AppCompatActivity {
protected TextView mail_des;
protected TextView mobile_des;
protected Account account;
protected String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_selection);
        mail_des = findViewById(R.id.mail_des);
        mobile_des = findViewById(R.id.mobile_des);
        account = (Account) getIntent().getSerializableExtra("account");
        username = getIntent().getStringExtra("username");
        assert account != null;
        mail_des.setText(account.getEmail());
        mobile_des.setText(account.getPhoneNumber());


    }

    public void callBackScreenFromMakeSelection(View view) {

    }

    public void callOTPScreenFromMakeSelection(View view) {
        Intent intent = new Intent(this, VerifyOTP.class);

        startActivity(intent);
    }

    public void GoToEmailSending(View view) {
        ForgetPasswordDataAdapter.getInstance().emailPickedConfirmed(username, new EmailPickedConfirmedDataRequestHandler() {
            @Override
            public void onUserEmailPicked(boolean confirmed) {
                Intent intent = new Intent(MakeSelection.this, VerifyOTP.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

    }
}