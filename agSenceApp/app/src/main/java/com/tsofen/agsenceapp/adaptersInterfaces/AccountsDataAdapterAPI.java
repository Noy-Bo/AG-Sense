package com.tsofen.agsenceapp.adaptersInterfaces;

import com.tsofen.agsenceapp.dataServices.AccountsHandler;

public interface AccountsDataAdapterAPI {
    void getAllAccounts(boolean requestLatestData,AccountsHandler handler);
    void getFaultyAccounts(AccountsHandler handler);
    void getHealthyAccounts(AccountsHandler handler);


}
