package com.tsofen.agsenceapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adaptersInterfaces.AddNewDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.AccountsDataAdapter;
import com.tsofen.agsenceapp.dataAdapters.AddNewDataAdapter;
import com.tsofen.agsenceapp.dataServices.CompaniesNameHandler;
import com.tsofen.agsenceapp.entities.AccountCompany;
import com.tsofen.agsenceapp.utils.AlertFlag;

import java.util.ArrayList;
import java.util.List;

public class NewUser extends BackBaseActivity {

    EditText EmailEditText, accountUsername, phoneNumberTxt;
    Spinner spin, accountCompanyName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        accountCompanyName = findViewById(R.id.account_company_name_edit);
        AccountsDataAdapter.getInstance().getAllCompaniesName(new CompaniesNameHandler() {
            @Override
            public void onCompaniesNameReady(List<AccountCompany> companiesName) {
                ArrayAdapter<AccountCompany> dataAdapter = new ArrayAdapter<>(NewUser.this, android.R.layout.simple_spinner_item, companiesName);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                NewUser.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        accountCompanyName.setAdapter(dataAdapter);
                    }
                });
            }
        });


        String[] users = {"Admin", "Account", "Support"};
        spin = (Spinner) findViewById(R.id.UserType);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        EmailEditText = findViewById(R.id.EmailEditText);
        accountUsername = findViewById(R.id.new_user_username);
        phoneNumberTxt = findViewById(R.id.new_user_phone_number);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (spin.getSelectedItem().toString().equals("Account") || spin.getSelectedItem().toString().equals("Support"))
                    accountCompanyName.setVisibility(View.VISIBLE);
                else if (spin.getSelectedItem().toString().equals("Admin")) {
                    accountCompanyName.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Switch simpleSwitch = (Switch) findViewById(R.id.NotificationsNewSwitch); // initiate Switch

        simpleSwitch.setTextOn("On"); // displayed text of the Switch whenever it is in checked or on state
        simpleSwitch.setTextOff("Off"); // displayed text of the Switch whenever it is in unchecked i.e. off state
    }

    public void addNewUser(View view) {
        boolean legal = true;
        String username = accountUsername.getText().toString(), phoneNumber = phoneNumberTxt.getText().toString();
        String userType = (String) spin.getSelectedItem(), companyName = ((AccountCompany) accountCompanyName.getSelectedItem()).getName(), email = EmailEditText.getText().toString();
        boolean receiveNotifications = false;

        if (!validateEmail(email)) {
            EmailEditText.setError("Invalid Email");
            legal = false;
        }
        if (!userType.equals("Account"))
            companyName = null;
        if (userType.equals("") || username.equals(""))
            legal = false;
        if (phoneNumber.equals("") && !validatePhoneNumber(phoneNumber))
            legal = false;
        if (!legal) {
            showAlertBox(NewUser.this, AlertFlag.FAILURE, "Some details are missing or illegal");
            return;
        }

        AddNewDataAdapter.getInstance().addNewUser(username, email, userType, phoneNumber, companyName, new AddNewDataRequestHandler() {
            @Override
            public void onNewDataAddedSuccess() {
                showAlertBox(NewUser.this, AlertFlag.SUCCESS, "Added new user successfully");
            }

            @Override
            public void onNewDataAddedFailure() {
                showAlertBox(NewUser.this, AlertFlag.FAILURE, "Failed to add new user");
            }
        });
    }

}