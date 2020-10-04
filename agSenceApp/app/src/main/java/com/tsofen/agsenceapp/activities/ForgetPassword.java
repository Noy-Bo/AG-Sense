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


//    private boolean validatePassword(String toString) {
//        boolean hasUpperCase = false;
//        boolean hasLowerCase = false;
//        boolean hasDigit = false;
//        if(NewPassword.getText().toString().length() >= 8)
//        {
//            for(int i = 0; i<NewPassword.getText().toString().length(); i++)
//            {
//                char temp = NewPassword.getText().toString().charAt(i);
//                if(Character.isUpperCase(temp))
//                    hasUpperCase = true;
//                else if(Character.isLowerCase(temp))
//                    hasLowerCase = true;
//                else if(Character.isDigit(temp))
//                    hasDigit = true;
//
//                if(hasDigit && hasLowerCase && hasUpperCase)
//                {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//
//    public static boolean emailValidator(String email) {
//        String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
//
//        Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
//        if (email == null) {
//            return false;
//        }
//
//        Matcher matcher = EMAIL_PATTERN.matcher(email);
//        return matcher.matches();
//    }
}