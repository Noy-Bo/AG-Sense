package com.tsofen.agsenceapp.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.dataServices.DeviceSmsInfoHandler;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.smsServices.OnAllSmsRecievedHandler;
import com.tsofen.agsenceapp.smsServices.SmsMgr;

import java.security.Permission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.os.Build;
import android.Manifest;

import static java.lang.Float.parseFloat;
public class AuthorizationNumberSetting extends BackBaseActivity {
    EditText editAdminNumber, editauthorizednum1, editauthorizednum2, editauthorizednum3,editauthorizednumber;
    Devices device = null;
    Button  AuthorizationNumberButtonUpdate;
    //Spinner FenceTypeSpinner;
    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization_number_setting);
        device = (Devices) getIntent().getSerializableExtra("device");
    }
    public void AuthorizationNumberUpdate(final View view) {

        TextView number = findViewById(R.id.editAdminNumber);
        final String adminNumber = number.getText().toString();
        number.setText("");
        if (adminNumber.equals("")) {
            Toast.makeText(this, "One or more Argument is invalid", Toast.LENGTH_SHORT).show();
        } else {
            if ((adminNumber.length()) != 12 && !adminNumber.startsWith("9725")) {
                Toast.makeText(this, "invalid number , must be 12 (international number) digits long and starts with 9725", Toast.LENGTH_LONG).show();
                return;
            }

            CacheMgr.getInstance().getDeviceSmsinfoJob(String.valueOf(device.getImei()),  new DeviceSmsInfoHandler() {
                            @Override
                public void onDeviceSmsInfoReceived(String devicePasswordAndPhoneNumber) {
                   Log.d("testing sms","displaying results: " + devicePasswordAndPhoneNumber);
                   String[] SmsPassPhone = devicePasswordAndPhoneNumber.split(",");
                    if(SmsPassPhone.length!=2){
                        Log.d("ERROR", "Server response not in right format");
                        return;
                    }
                   String devicePass =SmsPassPhone[0];
                   String devicePhone =SmsPassPhone[1];

                    if ((devicePhone.length()) != 13 && !devicePhone.startsWith("+9725")) {
                        Log.d("ERROR", "Wrong number format");
                        return;
                    }

                    if(SmsMgr.getInstance().getAuthorization().containsKey(devicePhone))
                    {
                        Log.d("ERROR", "same request is being processed");
                        return;
                    }
                    sendSMS(view,devicePhone,
                            String.format(SmsMgr.SendFormat.ADD_ADMIN_NUM.getUrl(),devicePass,adminNumber));
                }
            });


        }
    }

    public void sendSMS(View view , final String phoneNumber, final String SMS ) {
        if (checkSelfPermission(Manifest.permission.RECEIVE_SMS) != getPackageManager().PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale( Manifest.permission.RECEIVE_SMS)) {
                //user denied.
                ;
            } else {
                //pop up for permission.
                requestPermissions( new String[]{Manifest.permission.RECEIVE_SMS}, SmsMgr.MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
            }
        }
        sendMsg(phoneNumber,SMS);
        ArrayList arr= new ArrayList<SmsMgr.Response>(Arrays.asList(SmsMgr.Response.ADD_ADMIN_NUM));
        SmsMgr.getInstance().createTracker(phoneNumber,arr, SmsMgr.settingType.AUTHORIZATION, new OnAllSmsRecievedHandler() {
            @Override
            public void onAllSmsReceivedHandler() {
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       Toast.makeText(getApplicationContext(), "setting updated", Toast.LENGTH_LONG).show();
                   }
               });

            }
        });

    }

    public void sendMsg(String phoneNumber, String message) {
        SmsManager smsMgr;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != getPackageManager().PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale( Manifest.permission.RECEIVE_SMS)) {
                //user denied.
                ;
            } else {
                //pop up for permission.
                requestPermissions( new String[]{Manifest.permission.RECEIVE_SMS}, SmsMgr.MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {  //settings check
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == getPackageManager().PERMISSION_GRANTED) {
                try {

                    smsMgr = SmsManager.getDefault();
                    smsMgr.sendTextMessage(phoneNumber, null, message, null, null);

                } catch (Exception e) {
                    e.printStackTrace();

                }
            } else {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
            }

        }

    }
}