package com.tsofen.agsenceapp.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Switch;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adaptersInterfaces.EditDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.AccountsDataAdapter;
import com.tsofen.agsenceapp.dataAdapters.EditDataAdapter;
import com.tsofen.agsenceapp.dataServices.AccountsHandler;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.utils.AlertFlag;

import java.util.ArrayList;
import java.util.List;

public class EditUser extends BackBaseActivity {
    SearchableSpinner userSpinner;
    EditText newEmailTxt, newPhoneTxt;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch notificationsSwitch;
    List<Account> allAccounts;
    List<String> allMails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        userSpinner = findViewById(R.id.edit_user_email_spinner);
        newEmailTxt = findViewById(R.id.edit_user_new_email);
        newPhoneTxt = findViewById(R.id.edit_user_new_phone_number);
        notificationsSwitch = findViewById(R.id.edit_user_notifications_switch);
        AccountsDataAdapter.getInstance().getAllAccounts(false,new AccountsHandler() {
            @Override
            public void onAccountsDownloadFinished(List<Account> accounts) {
                allAccounts=new ArrayList<>(accounts);
                allMails = new ArrayList<>();
                for(Account account : allAccounts)
                    allMails.add(account.getEmail());
                ArrayAdapter<String> dataAdapter;
                dataAdapter = new ArrayAdapter<>(EditUser.this, android.R.layout.simple_spinner_item, allMails);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                EditUser.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        userSpinner.setAdapter(dataAdapter);
                        userSpinner.setGravity(Gravity.RIGHT);
                    }
                });
            }
        });

    }

    public void UpdateUserDetails(View view) {
        int index = userSpinner.getSelectedItemPosition();
        String newPhone = newPhoneTxt.getText().toString();
        String newEmail = newEmailTxt.getText().toString();
        if(index == -1){
            showAlertBox(EditUser.this, AlertFlag.FAILURE, "You must select the user you want to edit");
            return;
        }
        if(!newEmail.equals("") && !validateEmail(newEmail)){
            showAlertBox(EditUser.this, AlertFlag.FAILURE, "Illegal email address");
            return;
        }
        if(!newPhone.equals("") && validatePhoneNumber(newPhone)){
            showAlertBox(EditUser.this, AlertFlag.FAILURE, "Illegal phone number");
            return;
        }
        if(newEmail.equals("") && newPhone.equals("")){
            showAlertBox(EditUser.this, AlertFlag.FAILURE, "You must fill at least one field to edit");
            return;
        }

        Account account = allAccounts.get(index);
        EditDataAdapter.getInstance().editUser(account.getId(), newEmail, newPhone, new EditDataRequestHandler() {
            @Override
            public void onDataEditedSuccess() {
                showAlertBox(EditUser.this, AlertFlag.SUCCESS, "Edited user details successfully");
                clearView();
            }

            @Override
            public void onDataEditedFailure() {
                showAlertBox(EditUser.this, AlertFlag.FAILURE, "Failed to edit user details");
            }
        });
    }

    private void clearView() {
        newPhoneTxt.setText("");
        newEmailTxt.setText("");
        userSpinner.setSelection(-1);
    }
}