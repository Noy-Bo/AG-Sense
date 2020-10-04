package com.tsofen.agsenceapp.activities;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adaptersInterfaces.AddNewDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.AccountsDataAdapter;
import com.tsofen.agsenceapp.dataAdapters.AddNewDataAdapter;
import com.tsofen.agsenceapp.dataServices.CompaniesNameHandler;
import com.tsofen.agsenceapp.utils.AlertFlag;

import java.util.ArrayList;
import java.util.List;

public class NewDevice extends BackBaseActivity {
    EditText IEMIEdit, DevicePhoneNumberEdit, DevicePasswordEdit;
    Spinner DeviceTypeSpinner, AccountNameSpinner;
    final List<String> _accountsnames = new ArrayList<>();

    /**/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_device);
//      _accountsnames.add(0,"Choose Type");
        UpdateAccountsName();
        AccountNameSpinner = (Spinner) findViewById(R.id.AccountNameSpinner);
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, _accountsnames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AccountNameSpinner.setAdapter(dataAdapter);
        AccountNameSpinner.setGravity(Gravity.RIGHT);
        /*UpdateAccount included
         * these lines can be removed as it is Account name spinner, should be changed after we have the new API*/


        DeviceTypeSpinner = (Spinner) findViewById(R.id.DeviceTypeSpinner);
        DeviceTypeSpinner.setGravity(Gravity.RIGHT);
        List<String> DeviceSpinner_type = new ArrayList<>();
        //DeviceSpinner_type.add(0, "Choose Type");
        DeviceSpinner_type.add("GpsForPersonal");
        DeviceSpinner_type.add("SensorForBanks");
        DeviceSpinner_type.add("lequidHeightForTanks");
        ArrayAdapter<String> DeviceSpinner_dataAdapter;
        DeviceSpinner_dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, DeviceSpinner_type);
        DeviceSpinner_dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DeviceTypeSpinner.setAdapter(DeviceSpinner_dataAdapter);
    }

    private void UpdateAccountsName() {
        AccountsDataAdapter.getInstance().getAllCompaniesName(new CompaniesNameHandler() {
            @Override
            public void onCompaniesNameReady(List<String> companiesName) {
                _accountsnames.addAll(companiesName);

            }
        });
    }

    public void addNewDevice(View view) {
        DevicePasswordEdit = findViewById(R.id.DevicePasswordEdit);
        DevicePhoneNumberEdit = findViewById(R.id.DevicePhoneNumberEdit);
        IEMIEdit = findViewById(R.id.IEMIEdit);

        String iemiRegex = "[0-9]+";
        boolean legal = true;
        if (!validatePhoneNumber(DevicePhoneNumberEdit.getText().toString())) {
            DevicePhoneNumberEdit.setError("Invalid Phone number");
            legal = false;
        }
        if (!validatePassword(DevicePasswordEdit.getText().toString())) {
            DevicePasswordEdit.setError("Password is weak");
            legal = false;
        }
        if (!IEMIEdit.getText().toString().matches(iemiRegex)) {
            IEMIEdit.setError("Illegal IEMI");
            legal = false;
        }
        if (DeviceTypeSpinner.getSelectedItem() == null || AccountNameSpinner.getSelectedItem() == null) {
            showAlertBox(NewDevice.this, AlertFlag.FAILURE, "Some details are missing");
            legal = false;
        }
        if (!legal)
            return;
        Long iemi = Long.parseLong(IEMIEdit.getText().toString());
        String accountName = (String) AccountNameSpinner.getSelectedItem();
        String phoneNumber = DevicePhoneNumberEdit.getText().toString();
        String password = DevicePasswordEdit.getText().toString();
        String type = (String) DeviceTypeSpinner.getSelectedItem().toString().replace(" ","");
        AddNewDataAdapter.getInstance().addNewDevice(iemi, type, accountName, phoneNumber, password, new AddNewDataRequestHandler() {
            @Override
            public void onNewDataAddedSuccess() {
                showAlertBox(NewDevice.this, AlertFlag.SUCCESS, "Added new device successfully");
            }

            @Override
            public void onNewDataAddedFailure() {
                showAlertBox(NewDevice.this, AlertFlag.FAILURE, "Failed to add new device"); }
        });
    }
}

