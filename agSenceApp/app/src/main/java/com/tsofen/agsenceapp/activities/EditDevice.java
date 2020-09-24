package com.tsofen.agsenceapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.DevicesAdapter;
import com.tsofen.agsenceapp.adaptersInterfaces.DeviceDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.DeviceDataAdapter;
import com.tsofen.agsenceapp.dataServices.DevicesHandler;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Devices;

import java.util.ArrayList;
import java.util.List;

public class EditDevice extends BackBaseActivity {
   protected EditText DeviceNewPhoneNumberEdit,DeviceNewPassword,DeviceVerifyPassword,NewAccountNameEdit;
    final List<String> devicesIEMI = new ArrayList<>();
    final List<Devices> _devices = new ArrayList<>();
protected Spinner DeviceIEMISpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_device);
//devicesIEMI.add(0,"Choose Device");
UpdateDevicesList();
DeviceIEMISpinner = findViewById(R.id.DeviceIEMISpinner);
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, devicesIEMI);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DeviceIEMISpinner.setAdapter(dataAdapter);
    }

    private void UpdateDevicesList() {
        DeviceDataAdapter.getInstance().getAllDevices(0,0, new DeviceDataRequestHandler() {
            @Override
            public void onDeviceDataLoaded(List<Devices> devices) {


                for (Devices devices1 : devices)
                {
                    devicesIEMI.add(devices1.getImei().toString());
                }
            }
        });
    }


    public void UpdateDevice(View view) {
        DeviceNewPhoneNumberEdit = findViewById(R.id.DeviceNewPhoneNumberEdit);
        DeviceNewPassword = findViewById(R.id.DeviceNewPassword);
        DeviceVerifyPassword = findViewById(R.id.DeviceVerifyPassword);
        NewAccountNameEdit = findViewById(R.id.NewAccountNameEdit);
String Ragex = "^05\\d{8}";

if(!DeviceNewPhoneNumberEdit.getText().toString().matches(Ragex))
{
    DeviceNewPhoneNumberEdit.setError("Invalid Phone number");
}
        if(!DeviceNewPassword.getText().toString().equals(DeviceVerifyPassword.getText().toString()))
        {
            DeviceVerifyPassword.setError("Password Doesn't Match");
        }
        if(!CheckPassword(DeviceNewPassword.getText().toString()))
        {
            DeviceNewPassword.setError("Password is weak");
        }






    }

    private boolean CheckPassword(String toString) {

        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        if(DeviceNewPassword.getText().toString().length() >= 8)
        {
            for(int i = 0; i<DeviceNewPassword.getText().toString().length(); i++)
            {
                char temp = DeviceNewPassword.getText().toString().charAt(i);
                if(Character.isUpperCase(temp))
                hasUpperCase = true;
               else if(Character.isLowerCase(temp))
                    hasLowerCase = true;
               else if(Character.isDigit(temp))
                    hasDigit = true;

               if(hasDigit && hasLowerCase && hasUpperCase)
               {
                  return true;
               }
            }
        }
        return false;
    }


}