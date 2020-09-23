package com.tsofen.agsenceapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.dataAdapters.AccountsDataAdapter;
import com.tsofen.agsenceapp.dataServices.AccountsHandler;
import com.tsofen.agsenceapp.entities.Account;

import java.util.ArrayList;
import java.util.List;

public class NewDevice extends AppCompatActivity {
EditText IEMIEdit,DevicePhoneNumberEdit,DevicePasswordEdit;
    Spinner DeviceTypeSpinner,AccountNameSpinner;
    final List<Account> _accounts = new ArrayList<>();
    final List<String> _accountsnames = new ArrayList<>();
/**/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_device);
_accountsnames.add(0,"Choose Type");
    UpdateAccounts();
        assert _accounts != null;
        AccountNameSpinner =  (Spinner) findViewById(R.id.AccountNameSpinner);
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, _accountsnames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AccountNameSpinner.setAdapter(dataAdapter);

        /*UpdateAccount included
        * these lines can be removed as it is Account name spinner, should be changed after we have the new API*/



        DeviceTypeSpinner = (Spinner) findViewById(R.id.DeviceTypeSpinner);
        List<String> DeviceSpinner_type = new ArrayList<>();
        DeviceSpinner_type.add(0, "Choose Type");
        DeviceSpinner_type.add("Type 1");
        DeviceSpinner_type.add("Type 2");
        DeviceSpinner_type.add("Type 3");
        ArrayAdapter<String> DeviceSpinner_dataAdapter;
        DeviceSpinner_dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, DeviceSpinner_type);
        DeviceSpinner_dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        DeviceTypeSpinner.setAdapter(DeviceSpinner_dataAdapter);


    }

    private void UpdateAccounts() {
        AccountsDataAdapter.getInstance().getHealthyAccounts(new AccountsHandler() {
            @Override
            public void onAccountsDownloadFinished(List<Account> accounts) {
                _accounts.addAll(accounts);
                for(Account account : _accounts)
                {
                    _accountsnames.add(account.getUsername());
                }
            }
        });
    }

    public void UpdateDevice(View view) {
    }
}

