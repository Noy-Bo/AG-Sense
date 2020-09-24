package com.tsofen.agsenceapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.AccountsAdapter;
import com.tsofen.agsenceapp.dataAdapters.AccountsDataAdapter;
import com.tsofen.agsenceapp.dataServices.AccountsHandler;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.utils.GeneralProgressBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AccountStatusFilter extends SearchBaseActivity implements Serializable {
    boolean displayFaultyAccounts = true;
    boolean displayHealthyAccounts = true;
    ListView accountsList;
    ArrayList<Account> accountsArr = new ArrayList<>();
    ProgressDialog pd ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_accounts_status_filter, null, false);
        pd = GeneralProgressBar.displayProgressDialog(this,"loading accounts...");
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_accounts_status);
        accountsList = findViewById(R.id.accounts_list);


        //update buttons color according to filter
        String filter = getIntent().getExtras().getString("filter");
        if (filter != null) {
            if (filter.equals("faulty")) {
                TextView displayHealthyBox = findViewById(R.id.display_healthy_button);
                displayHealthyBox.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
                displayHealthyBox.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
            } else if (filter.equals("healthy")) {
                TextView displayFaultyBox = findViewById(R.id.display_faulty_button);
                displayFaultyBox.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
                displayFaultyBox.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
            } else {
                TextView displayHealthyBox = findViewById(R.id.display_healthy_button);
                displayHealthyBox.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
                displayHealthyBox.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
                TextView displayFaultyBox = findViewById(R.id.display_faulty_button);
                displayFaultyBox.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
                displayFaultyBox.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
            }
        }

        AccountsDataAdapter.getInstance().getAllAccounts(new AccountsHandler() {
            @Override
            public void onAccountsDownloadFinished(final List<Account> accounts) {
                accountsArr = (ArrayList<Account>) accounts;
                AccountStatusFilter.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ListAdapter myAdapter = new AccountsAdapter(AccountStatusFilter.this, 0, accountsArr);
                        accountsList.setAdapter(myAdapter);

                        //update Listview using the filter given
                        String filter = getIntent().getExtras().getString("filter");
                        if (filter != null) {
                            if (filter.equals("faulty")) {
                                displayFaultyAccounts = true;
                                displayHealthyAccounts = false;

                            } else if (filter.equals("healthy")) {
                                displayFaultyAccounts = false;
                                displayHealthyAccounts = true;
                            } else {
                                displayFaultyAccounts = true;
                                displayHealthyAccounts = true;
                            }
                            updateList();
                        }
                    }
                });

            }
        });


    }

    public void displayFaultyClicked(View view) {
        TextView displayFaultyBox = view.findViewById(R.id.display_faulty_button);
        if (displayFaultyAccounts == true) // do not display faulty devices.
        {
            displayFaultyBox.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
            displayFaultyBox.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
            displayFaultyAccounts = false;

        } else if (displayFaultyAccounts == false) // displaying the faulty device.
        {
            displayFaultyBox.setBackground(ContextCompat.getDrawable(this, R.drawable.white_shape_squares));
            displayFaultyBox.setTextColor(ContextCompat.getColor(this, R.color.white));
            displayFaultyAccounts = true;
        }
        updateList();
    }

    public void displayHealthyClicked(View view) {
        TextView displayHealthyBox = view.findViewById(R.id.display_healthy_button);
        if (displayHealthyAccounts == true) // do not display healthy devices.
        {
            displayHealthyBox.setBackground(ContextCompat.getDrawable(this, R.drawable.blue_shape_squares));
            displayHealthyBox.setTextColor(ContextCompat.getColor(this, R.color.dark_blue));
            displayHealthyAccounts = false;
        } else if (displayHealthyAccounts == false) // displaying the healthy device.
        {
            displayHealthyBox.setBackground(ContextCompat.getDrawable(this, R.drawable.white_shape_squares));
            displayHealthyBox.setTextColor(ContextCompat.getColor(this, R.color.white));
            displayHealthyAccounts = true;
        }
        updateList();
    }

    public void createAccount(View view) {
        Intent intent = new Intent(this, NewAccount.class);
        startActivity(intent);
    }

    public void updateList() {

        ArrayList<Account> filteredAccounts = new ArrayList<>();
        for (Account account : accountsArr) {
            if ((displayFaultyAccounts && account.isFaulty() == true) ||
                    (displayHealthyAccounts && account.isFaulty() == false)) {
                filteredAccounts.add(account);
            }
        }
        ListAdapter myAdapter = new AccountsAdapter(AccountStatusFilter.this, 0, filteredAccounts);
        accountsList.setAdapter(myAdapter);
        GeneralProgressBar.removeProgressDialog(pd);
    }
}