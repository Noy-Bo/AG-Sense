package com.tsofen.agsenceapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceDataRequestHandler;
import com.tsofen.agsenceapp.adaptersInterfaces.EditDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.DeviceDataAdapter;
import com.tsofen.agsenceapp.dataAdapters.EditDataAdapter;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.utils.AlertFlag;

import java.util.ArrayList;
import java.util.List;

public class EditDevice extends BackBaseActivity {
    protected EditText deviceNewPhoneNumberEdit, deviceNewPassword, deviceVerifyPassword, deviceNewName;
    final List<String> devicesIEMI = new ArrayList<>();
    final List<Devices> _devices = new ArrayList<>();
    protected Spinner deviceIEMISpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_device);
//devicesIEMI.add(0,"Choose Device");
        UpdateDevicesList();
        deviceIEMISpinner = findViewById(R.id.DeviceIEMISpinner);
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, devicesIEMI);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deviceIEMISpinner.setAdapter(dataAdapter);
    }

    private void UpdateDevicesList() {
        DeviceDataAdapter.getInstance().getAllDevices(0, 0, false, new DeviceDataRequestHandler() {
            @Override
            public void onDeviceDataLoaded(List<Devices> devices) {
                for (Devices devices1 : devices) {
                    devicesIEMI.add(devices1.getImei().toString());
                }
            }
        });
    }


    public void updateDevice(View view) {
        deviceNewPhoneNumberEdit = findViewById(R.id.DeviceNewPhoneNumberEdit);
        deviceNewPassword = findViewById(R.id.DeviceNewPassword);
        deviceVerifyPassword = findViewById(R.id.DeviceVerifyPassword);
        deviceNewName = findViewById(R.id.edit_device_name);
        String imei = (String) deviceIEMISpinner.getSelectedItem();
        String regex = "^05\\d{8}";
        boolean legal = true;
        if (!deviceNewPhoneNumberEdit.getText().toString().matches(regex)) {
            deviceNewPhoneNumberEdit.setError("Invalid Phone number");
            legal = false;
        }
        if (!deviceNewPassword.getText().toString().equals(deviceVerifyPassword.getText().toString())) {
            deviceVerifyPassword.setError("Password Doesn't Match");
            legal = false;
        }

        if (imei == null || !deviceNewPassword.getText().toString().equals("") || !legal) {
            showAlertBox(EditDevice.this, AlertFlag.FAILURE, "Some details are missing or illegal");
            return;
        }
        if (deviceNewPhoneNumberEdit.equals(""))
            deviceNewPhoneNumberEdit = null;
        if (deviceNewPassword.equals(""))
            deviceNewPassword = null;
        if (deviceNewName.equals(""))
            deviceNewName = null;

        EditDataAdapter.getInstance().editDevice(Long.parseLong(imei), deviceNewPhoneNumberEdit.getText().toString(), deviceNewPassword.getText().toString(), new EditDataRequestHandler() {
            @Override
            public void onDataEditedSuccess() {
                showAlertBox(EditDevice.this, AlertFlag.SUCCESS, "Edited device successfully");
                clearView();
            }

            @Override
            public void onDataEditedFailure() {
                showAlertBox(EditDevice.this, AlertFlag.FAILURE, "Failed to edit device");

            }
        });


    }

    private void clearView() {
        deviceNewPassword.setText("");
        deviceNewName.setText("");
        deviceNewPhoneNumberEdit.setText("");
        deviceIEMISpinner.setSelection(-1);
        deviceVerifyPassword.setText("");
    }

}