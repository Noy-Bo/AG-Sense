package com.tsofen.agsenceapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.utils.AlertFlag;

public class UserProfile extends BackBaseActivity {

    EditText newUsernameTxt, oldPassTxt, newPassTxt, verifyPassTxt;
    Switch notificationsSwitch;
    boolean currNotificationFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        newUsernameTxt = findViewById(R.id.user_profile_username);
        newPassTxt = findViewById(R.id.user_profile_new_password);
        verifyPassTxt = findViewById(R.id.user_profile_verify_password);
        oldPassTxt = findViewById(R.id.user_profile_old_password);
        notificationsSwitch = findViewById(R.id.user_profile_notifications_switch);

        // should init the notification's receiving flag

    }

    public void changeUsername(View view) {
        String username = newUsernameTxt.getText().toString();
        if (username.equals("")) {
            showAlertBox(UserProfile.this, AlertFlag.FAILURE, "You must enter the new username");
            return;
        }
        // change username data adapter method activation
    }

    public void changePassword(View view) {
        String oldPass = oldPassTxt.getText().toString(), newPass = newPassTxt.getText().toString(), verifyPass = verifyPassTxt.getText().toString();
        if (oldPass.equals("") || newPass.equals("") || verifyPass.equals("")){
            showAlertBox(UserProfile.this, AlertFlag.FAILURE, "One or more fields are empty");
            return;
        }

        // old password data adapter method activation

        if(!validatePassword(newPass)){
            showAlertBox(UserProfile.this, AlertFlag.FAILURE, "Illegal new password");
            return;
        }

        if(!newPass.equals(verifyPass)){
            showAlertBox(UserProfile.this, AlertFlag.FAILURE, "The new password and the verification password are not the same");
            return;
        }

        // change password data adapter method activation
    }

    public void changeNotificationsReceiving(View view) {
        boolean newFlag = notificationsSwitch.isSelected();
        if(newFlag == currNotificationFlag)
            return;

        // change notifications receiving status activation
    }
}