package com.tsofen.agsenceapp.dataServices;

import com.tsofen.agsenceapp.entities.Account;

import java.util.List;

public interface AccountsHandler {

    void onAccountsDownloadFinished(List<Account> accounts);
}
