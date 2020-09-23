package com.tsofen.agsenceapp.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.tsofen.agsenceapp.R;

import java.util.ArrayList;
import java.util.List;
import android.os.Build;
import android.Manifest;

import static java.lang.Float.parseFloat;
public class AuthorizationNumberSetting extends BackBaseActivity {
    EditText editAdminNumber, editauthorizednum1, editauthorizednum2, editauthorizednum3;
    Button  AuthorizationNumberButtonUpdate;
    Spinner FenceTypeSpinner;
    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_setting);
        Spinner spinner = (Spinner) findViewById(R.id.FenceTypeSpinner);
        List<String> type = new ArrayList<>();
        type.add(0, "Choose Type");
        type.add("Out of the fence");
        type.add("In the fence");
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


        //Authorization Number starts here

        editAdminNumber = (EditText) findViewById(R.id.editAdminNumber);
        editauthorizednum1 = (EditText) findViewById(R.id.editauthorizednum1);
        editauthorizednum2 = (EditText) findViewById(R.id.editauthorizednum2);
        editauthorizednum3 = (EditText) findViewById(R.id.editauthorizednum3);
        //Ends here

    }
    public void AuthorizationNumberUpdate(View view) {
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
            }

            else
            {
                Toast.makeText(this, "One or more Argument is invalid", Toast.LENGTH_SHORT).show();
            }



        }
    }
    public void sendMsg(String phoneNumber, String message) {
        SmsManager smsMgr;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {  //settings check
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS) == getPackageManager().PERMISSION_GRANTED)
            {
                try {

                    smsMgr = SmsManager.getDefault();
                    smsMgr.sendTextMessage(phoneNumber, null, message, null, null);
                    Toast.makeText(this,R.string.msg_sent, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this,R.string.error_send_msg, Toast.LENGTH_SHORT).show();
                }
            } else {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                Toast.makeText(this,R.string.send_msg_again, Toast.LENGTH_SHORT).show();
            }

        }

    }

}