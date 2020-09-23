package com.tsofen.agsenceapp.activities;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ProcessLifecycleOwner;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.tsofen.agsenceapp.BackgroundServices.AppLifecycleObserver;
import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adaptersInterfaces.onUserLoginHandler;
import com.tsofen.agsenceapp.dataAdapters.UserDataAdapter;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.User;
import com.tsofen.agsenceapp.notifications.TokenRegistrationHandler;
import com.tsofen.agsenceapp.utils.FailedLogin;


public class LoginActivity extends AppCompatActivity implements FailedLogin {

    public CacheMgr cacheMgr = CacheMgr.getInstance();

    @RequiresApi(api = Build.VERSION_CODES.O)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // observer registeration for onforeground. -- read AppLifeCycleObserver.
        AppLifecycleObserver appLifecycleObserver = new AppLifecycleObserver();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(appLifecycleObserver);

    }


    public void login(View view) {

        EditText usernametext = (EditText) findViewById(R.id.usernameTxt);
        final String username = usernametext.getText().toString();
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this,
                new OnSuccessListener<InstanceIdResult>() {
                    @Override
                    public void onSuccess(InstanceIdResult instanceIdResult) {
                        String deviceToken = instanceIdResult.getToken();
                        Log.e("newToken", deviceToken);
                        TokenRegistrationHandler.registerToken("barakg", deviceToken);
                    }
                });
        EditText password = (EditText) findViewById(R.id.passTxt);
        final String pass = password.getText().toString();

        ProgressBar progressBar = (ProgressBar) findViewById((R.id.progressBar));
        progressBar.setVisibility(View.VISIBLE);

        hideKeyboard(this);

        UserDataAdapter.getInstance().setContext(this);
        UserDataAdapter.getInstance().setCallback(this);
        UserDataAdapter.getInstance().userLogin(username, pass, new onUserLoginHandler() {
            @Override
            public void onAdminLoginSuccess(Admin user) {
                AppBaseActivity.setUser(user);
                Intent intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                finishAffinity();
                startActivity(intent);



            }

            @Override
            public void onAccountLoginSuccess(Account user) {
                AppBaseActivity.setUser(user);
                Intent intent = new Intent(LoginActivity.this, AccountDashboardActivity.class);
                finishAffinity();
                startActivity(intent);
            }

            @Override
            public void onUserLoginFailed() {
                Toast.makeText(LoginActivity.this, "Please enter a valid username", Toast.LENGTH_LONG).show();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        UserDataAdapter.getInstance().getCallback().Failed();
                    }
                });
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





    @Override
    public void Failed() {
        ProgressBar progressBar = (ProgressBar) findViewById((R.id.progressBar));
        progressBar.setVisibility(View.INVISIBLE);
    }


    public void messageAuthentication() // in developement.
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == getPackageManager().PERMISSION_GRANTED) {
            Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);

            if (cursor.moveToFirst()) { // must check the result to prevent exception
                do {
                    String msgData = "";
                    for (int index = 0; index < cursor.getColumnCount(); index++) {
                        msgData += " " + cursor.getColumnName(index) + ":" + cursor.getString(index);
                    }
                    Log.d("sms", msgData);
                } while (cursor.moveToNext()); // how many messages.
            } else {
                // empty box, no SMS
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
