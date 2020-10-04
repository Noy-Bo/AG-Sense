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
    public void getAllAccounts(boolean reqeustLatestData,final AccountsHandler handler) {
        if (reqeustLatestData == true)
        {
            cacheManager.getLatestAccountsJob(0, 0, new AccountsHandler() {
                @Override
                public void onAccountsDownloadFinished(List<Account> accounts) {
                    handler.onAccountsDownloadFinished(accounts);
                }
            });
        }
        else
        {
            cacheManager.getAccountsJob(0, 0, new AccountsHandler() {
                @Override
                public void onAccountsDownloadFinished(List<Account> accounts) {
                    handler.onAccountsDownloadFinished(accounts);
                }
            });
        }

    }

    @Override
    public void getFaultyAccounts(final AccountsHandler handler) {
        cacheManager.getAccountsJob(0, 0, new AccountsHandler() {
            @Override
            public void onAccountsDownloadFinished(List<Account> accounts) {
                List<Account> toReturn = new ArrayList<>();
                for(Account account : accounts) {
                    if (account.getFaultyAccount())
                        toReturn.add(account);
                }
                handler.onAccountsDownloadFinished(toReturn);

            }
        });
    }

    @Override
    public void getHealthyAccounts(final AccountsHandler handler) {
        cacheManager.getAccountsJob(0, 0, new AccountsHandler() {
            @Override
            public void onAccountsDownloadFinished(List<Account> accounts) {
                List<Account> toReturn = new ArrayList<>();
                for(Account account : accounts) {
                    if (!account.getFaultyAccount())
                        toReturn.add(account);
                }
                handler.onAccountsDownloadFinished(toReturn);
            }
        });
    }
}
