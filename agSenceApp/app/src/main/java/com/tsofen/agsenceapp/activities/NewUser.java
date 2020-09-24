package com.tsofen.agsenceapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.tsofen.agsenceapp.R;

public class NewUser extends BackBaseActivity {

    EditText account_name_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
account_name_edit = findViewById(R.id.account_name_edit);
        String[] users = { "Admin", "Account", "Support"};
        final Spinner spin = (Spinner) findViewById(R.id.UserType);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(spin.getSelectedItem().toString().equals("Account") || spin.getSelectedItem().toString().equals("Support"))
                account_name_edit.setVisibility(View.VISIBLE);
                else
                if(spin.getSelectedItem().toString().equals("Admin"))
                {
                    account_name_edit.setVisibility(View.INVISIBLE);

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
}