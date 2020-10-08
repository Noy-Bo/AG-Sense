package com.tsofen.agsenceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tsofen.agsenceapp.adaptersInterfaces.ForgetPasswordDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.ForgetPasswordDataAdapter;
import com.tsofen.agsenceapp.entities.Account;

import java.util.ArrayList;

public class ForgetPasswords extends AppCompatActivity {
    protected boolean EmailCheck;
    protected Account account;
    protected EditText editText;
    protected Account tempaccount;
    protected ArrayList<Account> __accounts;
    protected ProgressBar prog;
    protected TextView please_wait_forgetpassword;
    protected EditText forget_password_email_address;
    protected Boolean checking = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_passwords);
        forget_password_email_address = findViewById(R.id.forgot_password);
    }

    public void callBackScreenFromForgetPassword(View view) {
        finish();
    }

    public void verifyEmailAddress(View view) {

        forget_password_email_address = findViewById(R.id.forgot_password);
        // if (emailValidator(forget_password_email_address.getText().toString())) {
        //  System.out.println("The email address " + forget_password_email_address.getText().toString() + " is valid");
        prog = findViewById(R.id.prog);
        prog.setVisibility(View.VISIBLE);
        please_wait_forgetpassword = findViewById(R.id.please_wait_forgetpassword);
        please_wait_forgetpassword.setVisibility(View.VISIBLE);
        //forget_password_email_address = findViewById(R.id.forgot_password);
        editText = findViewById(R.id.forgot_password);
        hideKeyboard(this);

        ForgetPasswordDataAdapter.getInstance().getUserDetails(editText.getText().toString(), new ForgetPasswordDataRequestHandler() {
            @Override
            public void onUserDetailsReceived(Account account) {
                if (account != null) {
                    Intent intent = new Intent(ForgetPasswords.this, MakeSelection.class);
                    intent.putExtra("account", account);
                    intent.putExtra("username", editText.getText().toString());
                    startActivity(intent);
                } else {
                    prog.setVisibility(View.INVISIBLE);

                    Toast.makeText(ForgetPasswords.this, "Username you entered is not registered.", Toast.LENGTH_SHORT).show();
                }
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