package com.tsofen.agsenceapp.dataAdapters;

import com.tsofen.agsenceapp.adaptersInterfaces.AccountsDataAdapterAPI;
import com.tsofen.agsenceapp.dataServices.AccountsHandler;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Devices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountsDataAdapter extends BaseDataAdapter implements AccountsDataAdapterAPI {

    private static AccountsDataAdapter instance;
    private AccountsDataAdapter(){}
    public static AccountsDataAdapter getInstance(){
        if(instance == null)
            instance = new AccountsDataAdapter();
        return instance;
    }

    @Override
    public void getAllAccounts(final AccountsHandler handler) {
        cacheManager.getAccountsJob(0, 0, new AccountsHandler() {
            @Override
            public void onAccountsDownloadFinished(List<Account> accounts) {
//                handler.onAccountsDownloadFinished(accounts);

                List<Account> newData = new ArrayList<>();
                newData.add(new Account(1,"Lama Ghantous", "lama@gmail.com",true, 1));
                newData.add(new Account(1,"Ayat Taha", "ayat@gmail.com",false, 2));

                handler.onAccountsDownloadFinished(newData);
            }
        });
    }

    @Override
    public void getFaultyAccounts(final AccountsHandler handler) {
        cacheManager.getAccountsJob(0, 0, new AccountsHandler() {
            @Override
            public void onAccountsDownloadFinished(List<Account> accounts) {
                List<Account> newData = new ArrayList<>();
                newData.add(new Account(1,"Lama Ghantous", "lama@gmail.com",true, 1));

                handler.onAccountsDownloadFinished(newData);
            }
        });
    }

    @Override
    public void getHealthyAccounts(final AccountsHandler handler) {
        cacheManager.getAccountsJob(0, 0, new AccountsHandler() {
            @Override
            public void onAccountsDownloadFinished(List<Account> accounts) {
                List<Account> newData = new ArrayList<>();
                newData.add(new Account(1,"Ayat Taha", "ayat@gmail.com",false, 2));

                handler.onAccountsDownloadFinished(newData);
            }
        });
    }
}
