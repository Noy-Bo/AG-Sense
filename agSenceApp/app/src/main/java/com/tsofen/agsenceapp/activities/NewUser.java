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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewUser extends BackBaseActivity {

    EditText account_name_edit,EmailEditText;

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

    public void UpdateNewUserDetails(View view) {
        EmailEditText = findViewById(R.id.EmailEditText);
        if (emailValidator(EmailEditText.getText().toString())) {
            System.out.println("The email address " + EmailEditText.getText().toString() + " is valid");
        }
        else {
            EmailEditText.setError("Invalid Email");
        }
    }

    private boolean emailValidator(String toString) {
        String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

        Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
        if (toString == null) {
            return false;
        }

        Matcher matcher = EMAIL_PATTERN.matcher(toString);
        return matcher.matches();
    }
}