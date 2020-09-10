package com.tsofen.agsenceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.dataServices.UrlConnectionMaker;
import com.tsofen.agsenceapp.dataServices.Opcodes;

import com.tsofen.agsenceapp.entities.User;

public class LoginActivity extends AppCompatActivity {
    public static User user = new User(10, "Tsofen", "Tsofen@Tsofen.Tsofen", "Admin");
   public  Bundle inputDataContainer = new Bundle() ;
   public  Bundle outputDataContainer = new Bundle() ;
   public UrlConnectionMaker urlconnection= new UrlConnectionMaker();
   public Opcodes myOpcodes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void login(View view) {
        EditText editTextUserName = (EditText) findViewById(R.id.usernameTxt);
        String username = editTextUserName.getText().toString();
        inputDataContainer.putString("userName", username);
        EditText editTextPassword = (EditText) findViewById(R.id.passTxt);
        String password = editTextPassword.getText().toString();
        inputDataContainer.putString("password",password );
        inputDataContainer.putInt("opCode",Opcodes.Login.ordinal());
       // urlconnection.getParameters(inputDataContainer);
        if (username != null && username.equals("Admin")) {
            Intent intent = new Intent(this, AdminDashboardActivity.class);
            AppBaseActivity.setUserType(username);
            startActivity(intent);
        }

        else if(username != null && username.equals("Account")) // Noy - added 'else' here so it will not load 2 screens when logging in as admin.
        {
            Intent intent = new Intent(this, AccountDashboardActivity.class);
            AppBaseActivity.setUserType(username);
            startActivity(intent);

        }else{
            Toast.makeText(this,"Please enter a valid username",Toast.LENGTH_LONG).show();
        }


    }
}