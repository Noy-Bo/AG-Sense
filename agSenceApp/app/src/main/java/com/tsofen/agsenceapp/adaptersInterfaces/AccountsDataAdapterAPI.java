package com.tsofen.agsenceapp.adaptersInterfaces;

import com.tsofen.agsenceapp.dataServices.AccountsHandler;
import com.tsofen.agsenceapp.dataServices.CompaniesNameHandler;

public interface AccountsDataAdapterAPI {
    void getAllAccounts(AccountsHandler handler);
    void getFaultyAccounts(AccountsHandler handler);
    void getHealthyAccounts(AccountsHandler handler);
    void getAllCompaniesName(CompaniesNameHandler handler);

}
