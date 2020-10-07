package com.tsofen.agsenceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

    public void GoToPhoneSending(View view) {
//        ForgetPasswordDataAdapter.getInstance().phonePickedConfirmed(username, new PhonePickedConfirmedDataRequestHandler() {
//            @Override
//            public void onUserPhonePickedSuccess() {
//                Intent intent = new Intent(MakeSelection.this, VerifyOTP.class);
//                intent.putExtra("username",username);
//                startActivity(intent);
//            }
//
//            @Override
//            public void onUserPhonePickedFailure() {
//                Toast.makeText(MakeSelection.this, "Server Couldn't send a message to your phone, try again later.", Toast.LENGTH_SHORT).show();
//            }
//        });
//        Intent intent = new Intent(this, VerifyOTP.class);
//
//        startActivity(intent);

        Toast.makeText(this, "Unavailable Feature, Coming soon !", Toast.LENGTH_SHORT).show();
    }

    public void GoToEmailSending(View view) {
        ForgetPasswordDataAdapter.getInstance().emailPickedConfirmed(username, new EmailPickedConfirmedDataRequestHandler() {
            @Override
            public void onUserEmailPickedSuccess() {
                Intent intent = new Intent(MakeSelection.this, VerifyOTP.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }

            @Override
            public void onUserEmailPickedFailure() {
                Toast.makeText(MakeSelection.this, "Server Couldn't send a message to your email, try again later.", Toast.LENGTH_SHORT).show();
            }


        });

    }
}