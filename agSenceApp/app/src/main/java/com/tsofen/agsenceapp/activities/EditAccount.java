package com.tsofen.agsenceapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adaptersInterfaces.EditDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.EditDataAdapter;
import com.tsofen.agsenceapp.utils.AlertFlag;

public class EditAccount extends BackBaseActivity  {

    EditText prevNameTxt, newNameTxt;
    Switch notificationsSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        prevNameTxt = findViewById(R.id.account_company_name_edit);
        notificationsSwitch = findViewById(R.id.NotifiationsEditSwitch);
        newNameTxt = findViewById(R.id.edit_account_new_name);
    }

    public void UpdateAccount(View view) {
        String prevName = prevNameTxt.getText().toString();
        String newName = newNameTxt.getText().toString();
        boolean receive = notificationsSwitch.isActivated();
        if(prevName.equals("")||newName.equals("")){
            showAlertBox(EditAccount.this, AlertFlag.FAILURE, "Some details are missing");
            return;
        }

        EditDataAdapter.getInstance().editAccount(prevName, newName, new EditDataRequestHandler() {
            @Override
            public void onDataEditedSuccess() {
                showAlertBox(EditAccount.this, AlertFlag.SUCCESS, "Edited account successfully");
                return;
            }

            @Override
            public void onDataEditedFailure() {
                showAlertBox(EditAccount.this, AlertFlag.FAILURE, "Failed to edit account");
                return;
            }
        });
    }
}
