package com.tsofen.agsenceapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.R;
import com.tsofen.agsenceapp.adapters.AccountsAdapter;
import com.tsofen.agsenceapp.dataAdapters.AccountsDataAdapter;
import com.tsofen.agsenceapp.dataServices.AccountsHandler;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.User;
import com.tsofen.agsenceapp.utils.GeneralProgressBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AccountStatusFilter extends SearchBaseActivity implements Serializable {
    boolean displayFaultyAccounts = true;
    boolean displayHealthyAccounts = true;
    ListView accountsList;
    String company = "";
    ArrayList<Account> accountsArr = new ArrayList<>();
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_accounts_status_filter, null, false);
        pd = GeneralProgressBar.displayProgressDialog(this, "loading accounts...");

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //accountsArr.clear(); // do not use this!. down below you take Accounts from cache and assign it directly to this variable, therefore controlling cache from here. this is bad
                // if u still need to use it. do not take direct refrence from cache!.
                ((ArrayAdapter)accountsList.getAdapter()).notifyDataSetChanged();
                getAccountsFromCacheManager(true);

            }
        });


        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_accounts_status);
        accountsList = findViewById(R.id.accounts_list);



        company = getIntent().getExtras().getString("company");
        searchView = (AutoCompleteTextView) contentView.findViewById(R.id.search_text_view);
        searchView.setHint(R.string.search_account_hint);


        getAccountsFromCacheManager(false);

        accountsList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int firstVisibleCount, int totalItemCount) {
                int topRowVerticalPosition = (accountsList == null || accountsList.getChildCount() == 0) ? 0 : accountsList.getChildAt(0).getTop();
                swipeRefreshLayout.setEnabled(firstVisibleItem == 0 && topRowVerticalPosition >= 0);
            }
        });

        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AccountStatusFilter.this, AccountDashboardActivity.class);
                Account account = (Account) searchView.getAdapter().getItem(i);
                intent.putExtra("account", account);
                startActivity(intent);
            }
        });
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
        ArrayAdapter myAdapter = new AccountsAdapter<Account>(AccountStatusFilter.this, filteredAccounts);
        accountsList.setAdapter(myAdapter);
        GeneralProgressBar.removeProgressDialog(pd);
        swipeRefreshLayout.setRefreshing(false);
    }


    public void getAccountsFromCacheManager(boolean requestLatestData) {
        AccountsDataAdapter.getInstance().getAllAccounts(requestLatestData,new AccountsHandler() {
            @Override
            public void onAccountsDownloadFinished(final List<Account> accounts) {
                accountsArr = new ArrayList<>();
                for (Account account: accounts) {
                    if(account.getCompanyName().equals(company)){
                        accountsArr.add(account);
                    }
                }
                AccountStatusFilter.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ListAdapter myAdapter = new AccountsAdapter<User>(AccountStatusFilter.this, accountsArr);
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
                        searchView.setAdapter(new AccountsAdapter<Account>(AccountStatusFilter.this, accountsArr));

                    }
                });

            }
        });
    }
}