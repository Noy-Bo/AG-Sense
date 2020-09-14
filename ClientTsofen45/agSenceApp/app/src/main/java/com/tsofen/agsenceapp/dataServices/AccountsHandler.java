package com.tsofen.agsenceapp.dataServices;

import com.tsofen.agsenceapp.entities.Account;

import java.util.ArrayList;

public interface AccountsHandler {

    void onAccountsDownloadFinished(ArrayList<Account> accounts);
}
