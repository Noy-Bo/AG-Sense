package com.tsofen.agsenceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.tsofen.agsenceapp.adaptersInterfaces.ConfirmPasswordDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.ForgetPasswordDataAdapter;

public class SetNewPassword extends AppCompatActivity {
    protected EditText new_password, confirm_password;
    String code;
protected String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);
        new_password = findViewById(R.id.new_password);
        username = getIntent().getStringExtra("username");
        confirm_password = findViewById(R.id.confirm_password);
        code = getIntent().getStringExtra("code");
    }


    private boolean CheckPassword(String toString) {
        new_password = findViewById(R.id.new_password);
        confirm_password = findViewById(R.id.confirm_password);
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        if (new_password.getText().toString().length() >= 8) {
            for (int i = 0; i < new_password.getText().toString().length(); i++) {
                char temp = new_password.getText().toString().charAt(i);
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

    public void goToHomeFromSetNewPassword(View view) {
    }

    public void setNewPasswordBtn(View view) {

        boolean type1 = true;
        boolean type2 = true;


        if (!new_password.getText().toString().equals(confirm_password.getText().toString())) {
            confirm_password.setError("Passwords Doesn't Match");
            type1 = false;
        }
        if (!CheckPassword(new_password.getText().toString())) {
            new_password.setError("Password is weak");
            type2= false;
        }
        if (type1 && type2) {


            ForgetPasswordDataAdapter.getInstance().confirmUserPassword(username, code,new_password.getText().toString(), new ConfirmPasswordDataRequestHandler() {
                @Override
                public void onUserConfirmPasswordSuccess() {

                    Intent intent = new Intent(SetNewPassword.this, ForgetPasswordSuccessMessage.class);
                    startActivity(intent);
                }

                @Override
                public void onUserConfirmPasswordFailure() {
                   // Toast.makeText(SetNewPassword.this, "Failed to Update the password", Toast.LENGTH_SHORT).show();
                }


            });

        }
    }
}