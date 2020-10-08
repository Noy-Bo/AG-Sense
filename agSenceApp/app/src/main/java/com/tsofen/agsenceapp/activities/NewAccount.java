package com.tsofen.agsenceapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adaptersInterfaces.AddNewDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.AddNewDataAdapter;
import com.tsofen.agsenceapp.utils.AlertFlag;
import com.tsofen.agsenceapp.utils.GeneralProgressBar;

public class NewAccount extends BackBaseActivity {
    EditText accountName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
    }

    public void AddNewAccount(View view) {
        accountName = findViewById(R.id.new_account_name);
        String name = accountName.getText().toString();
        if (name.equals("")){
            showAlertBox(NewAccount.this, AlertFlag.FAILURE, "You must enter the account name");
            return;
        }
        view.setEnabled(false);
        AddNewDataAdapter.getInstance().addNewAccount(name, new AddNewDataRequestHandler() {
            @Override
            public void onNewDataAddedSuccess() {
                showAlertBox(NewAccount.this,AlertFlag.SUCCESS, "New account had been added successfully");
            }

            @Override
            public void onNewDataAddedFailure() {
                showAlertBox(NewAccount.this,AlertFlag.FAILURE, "Failed to add new account");
                return;
            }
        });
    }
}