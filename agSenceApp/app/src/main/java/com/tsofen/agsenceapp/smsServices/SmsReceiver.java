package com.tsofen.agsenceapp.smsServices;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.tsofen.agsenceapp.activities.DeviceSetting;

public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = SmsReceiver.class.getSimpleName();
    public static final String pdu_type = "pdus";
    private String authentication_str;
    private boolean result = false;
    private Intent intent;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        // Get the SMS message.
        while (result == false) {

            SmsMessage[] msgs;
            SmsMessage[] phonenum ;
            String strMessage = "";
            String PhoneNUMBER ="";
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
                    strMessage += "SMS from " + msgs[i].getOriginatingAddress();
                    strMessage += " :" + msgs[i].getMessageBody() + "\n";

                    //---retrieve the SMS senders number ---
                    phonenum = new SmsMessage[pdus.length];
                    for (i = 0; i < phonenum.length; i++) {
                        phonenum[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
                        PhoneNUMBER += phonenum[i].getOriginatingAddress();
                    }
                    //---display the new SMS message---
                    Toast.makeText(context, strMessage, Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, PhoneNUMBER, Toast.LENGTH_LONG).show();

                    // Log and display the SMS message.
                    //Log.d(TAG, "onReceive: " + strMessage);
                    boolean result = SmsMgr.getInstance().checkSms(strMessage,PhoneNUMBER);
                   /* if (strMessage == this.authentication_str)
                        Toast.makeText(context, strMessage + "hola", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(context, "not what i wanted", Toast.LENGTH_LONG).show();*/

                }
            }
        }
    }
}