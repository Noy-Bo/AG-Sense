package com.tsofen.agsenceapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adaptersInterfaces.EditDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.AccountsDataAdapter;
import com.tsofen.agsenceapp.dataAdapters.EditDataAdapter;
import com.tsofen.agsenceapp.dataServices.CompaniesNameHandler;
import com.tsofen.agsenceapp.entities.AccountCompany;
import com.tsofen.agsenceapp.utils.AlertFlag;

import java.util.ArrayList;
import java.util.List;

public class EditAccount extends BackBaseActivity {

    EditText newNameTxt;
    Spinner prevNameTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        prevNameTxt = findViewById(R.id.PreviousAccountNameSpinner);
        AccountsDataAdapter.getInstance().getAllCompaniesName(new CompaniesNameHandler() {
            @Override
            public void onCompaniesNameReady(List<AccountCompany> companiesName) {

                ArrayAdapter<AccountCompany> dataAdapter = new ArrayAdapter<>(EditAccount.this, android.R.layout.simple_spinner_item, companiesName);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                EditAccount.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        prevNameTxt.setAdapter(dataAdapter);
                    }
                });
            }
        });


        newNameTxt = findViewById(R.id.edit_account_new_name);
    }

    public void UpdateAccount(View view) {
        AccountCompany selectedCompany = ((AccountCompany) prevNameTxt.getSelectedItem());
        String newName = newNameTxt.getText().toString();
        if (selectedCompany == null || newName.equals("")) {
            showAlertBox(EditAccount.this, AlertFlag.FAILURE, "Some details are missing");
            return;
        }

        String prevName = selectedCompany.getName() ;
        EditDataAdapter.getInstance().editAccount(prevName, newName, new EditDataRequestHandler() {
            @Override
            public void onDataEditedSuccess() {
                showAlertBox(EditAccount.this, AlertFlag.SUCCESS, "Edited account successfully");
                clearView();
            }

            @Override
            public void onDataEditedFailure() {
                showAlertBox(EditAccount.this, AlertFlag.FAILURE, "Failed to edit account");
            }
        });
    }

    private void clearView() {
        prevNameTxt.setSelection(-1);
        newNameTxt.setText("");
    }
}
