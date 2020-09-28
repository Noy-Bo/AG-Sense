package com.tsofen.agsenceapp.smsServices;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.tsofen.agsenceapp.activities.DeviceSetting;

import java.util.ArrayList;

public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = SmsReceiver.class.getSimpleName();
    public static final String pdu_type = "pdus";
    private String authentication_str;
    private Intent intent;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        SmsMessage[] msgs;
        SmsMessage[] phonenum ;
        String strMessage = "";
        String format = bundle.getString("format");
        // Retrieve the SMS message received.
        Object[] pdus = (Object[]) bundle.get(pdu_type);
        if (pdus != null) {
            // Check the Android version.
            boolean isVersionM =
                    (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
            // Fill the msgs array.
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                // Check Android version and use appropriate createFromPdu.
                if (isVersionM) {
                    // If Android version M or newer:
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                } else {
                    // If Android version L or older:
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                // Build the message to show.
                String phomeNumber = msgs[i].getOriginatingAddress();
                String SMS = msgs[i].getMessageBody();
                checkSMS(phomeNumber, SMS);

            }
        }

    }

    private void checkSMS(String phoneNumber, String message){
        SmsMgr.Response response = SmsMgr.Response.contains(message);

        if(response == null){
            return;
        }

        if(SmsMgr.getTrackingResponse().contains(response) &&  SmsMgr.getTracking().containsKey(phoneNumber)){
            SmsMgr.getTracking().get(phoneNumber).deleteCommand(response);

        }else if(SmsMgr.getAuthorizationResponse().contains(response) &&  SmsMgr.getAuthorization().containsKey(phoneNumber)){
            SmsMgr.getAuthorization().get(phoneNumber).deleteCommand(response);

        }else if(SmsMgr.getSpeedingAlertResponse().contains(response) &&  SmsMgr.getSpeedingAlert().containsKey(phoneNumber)){
            SmsMgr.getSpeedingAlert().get(phoneNumber).deleteCommand(response);
        }
    }
}