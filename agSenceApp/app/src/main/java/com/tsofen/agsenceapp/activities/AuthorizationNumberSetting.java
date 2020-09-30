package com.tsofen.agsenceapp.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.smsServices.SmsMgr;

import java.util.ArrayList;
import java.util.List;
import android.os.Build;
import android.Manifest;

import static java.lang.Float.parseFloat;
public class AuthorizationNumberSetting extends BackBaseActivity {
    EditText editAdminNumber, editauthorizednum1, editauthorizednum2, editauthorizednum3,editauthorizednumber;
    Button  AuthorizationNumberButtonUpdate;
    //Spinner FenceTypeSpinner;
    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View contentView = inflater.inflate(R.layout.activity_authorization_number_setting, null, false);
        setContentView(R.layout.activity_authorization_number_setting);
       // editauthorizednumber = (EditText) findViewById(R.id.editauthorizednumber);
    }
    public void AuthorizationNumberUpdate(View view) {
        if(SmsMgr.getInstance().getTracking().containsKey("phone number"))
        {
            Toast.makeText(this, "an update request is being proccessed,please wait till finish", Toast.LENGTH_SHORT).show();
            return;
        }
        ProgressBar progressBar = view.findViewById(R.id.authorizationSettingsProgressBar);
        progressBar.setVisibility(View.VISIBLE);
        if (editAdminNumber.getText().toString().equals("") || editauthorizednum1.getText().toString().equals("")) {
            Toast.makeText(this, "One or more Argument is invalid", Toast.LENGTH_SHORT).show();
        } else {
            if ((editAdminNumber.getText().toString().length()) != 10 && !editAdminNumber.getText().toString().startsWith("05")) {
                editAdminNumber.setError("Invalid Number");
            }
            if ((editauthorizednum1.getText().toString().length()) !=10 && !editAdminNumber.getText().toString().startsWith("05")) {
                editauthorizednum1.setError("Invalid Number");
            }

            if(editauthorizednum1.getText().toString().length() == 10 && editAdminNumber.getText().toString().length() == 10
                    && editAdminNumber.getText().toString().startsWith("05") && editauthorizednum1.getText().toString().startsWith("05")
                    &&(editauthorizednum2.getText().toString().length() == 10 || editauthorizednum2.getText().toString().length() == 0)
                    &&(editauthorizednum3.getText().toString().length() == 10 || editauthorizednum3.getText().toString().length() == 0))
            {
                Toast toast = Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT);
                toast.show();
            //    DeviceSettings.sendMsg("phone number","sms");
                progressBar.setVisibility(View.INVISIBLE);

            }

            else
            {
                Toast.makeText(this, "One or more Argument is invalid", Toast.LENGTH_SHORT).show();
            }

        }
    }

}