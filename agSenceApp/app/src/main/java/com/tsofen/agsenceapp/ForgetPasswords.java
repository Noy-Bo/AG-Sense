package com.tsofen.agsenceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tsofen.agsenceapp.adaptersInterfaces.ForgetPasswordDataRequestHandler;
import com.tsofen.agsenceapp.dataAdapters.ForgetPasswordDataAdapter;
import com.tsofen.agsenceapp.entities.Account;

import java.util.ArrayList;

public class ForgetPasswords extends AppCompatActivity {
protected boolean EmailCheck;
protected Account account;
protected EditText editText;
protected Account tempaccount;
protected ArrayList<Account> __accounts;
protected ProgressBar prog;
protected EditText forget_password_email_address;
   protected Boolean checking = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_passwords);
        forget_password_email_address = findViewById(R.id.forgot_password);
    }

    public void callBackScreenFromForgetPassword(View view) {
    }
    public static boolean emailValidator(String email) {
//        String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
//
//        Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
//        if (email == null) {
//            return false;
//        }
//
//        Matcher matcher = EMAIL_PATTERN.matcher(email);
//        return matcher.matches();

        return true;
    }
    public void verifyEmailAddress(View view) {

        forget_password_email_address = findViewById(R.id.forgot_password);
       // if (emailValidator(forget_password_email_address.getText().toString())) {
          //  System.out.println("The email address " + forget_password_email_address.getText().toString() + " is valid");
            prog = findViewById(R.id.prog);
            prog.setVisibility(View.VISIBLE);
            //forget_password_email_address = findViewById(R.id.forgot_password);
editText = findViewById(R.id.forgot_password);


            ForgetPasswordDataAdapter.getInstance().getUserDetails(editText.getText().toString(), new ForgetPasswordDataRequestHandler() {
                @Override
                public void onUserDetailsReceived(Account account) {
                    Intent intent = new Intent(ForgetPasswords.this, MakeSelection.class);
                    intent.putExtra("account", account);
intent.putExtra("username",editText.getText().toString());
                    startActivity(intent);
                }
            });


//            AccountsDataAdapter.getInstance().getAllAccounts(new AccountsHandler() {
//                @Override
//                public void onAccountsDownloadFinished(List<Account> accounts) {
//                    for (Account account1 : accounts) {
//                        if (account1.getUsername().equals(forget_password_email_address.getText().toString())) {
//                            checking = true;
//                            prog.setVisibility(View.INVISIBLE);
//tempaccount = account1;
//                          getdetails(tempaccount);
//
//
//
//
//
//
//                        }
//                    }
//
//
//                }
//            });

            if (!checking) {
                prog.setVisibility(View.INVISIBLE);

                Toast.makeText(ForgetPasswords.this, "Username you entered is not registered.", Toast.LENGTH_SHORT).show();
            }
      //  }
     //   else {
        //    forget_password_email_address.setError("Invalid Username");
     //   }






     //   System.out.println(__accounts.get(1).getEmail());


/*if(account!=null)
{
    if(account.getEmail().equals(forget_password_email_address.getText().toString()))
    {
        prog.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(this, MakeSelection.class);
        startActivity(intent);
    }
    else
    {
        prog.setVisibility(View.INVISIBLE);

        Toast.makeText(this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
    }
}


*/
    }



    private void getdata() {

    }
}