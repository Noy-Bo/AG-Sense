package com.tsofen.agsenceapp.dataServices;

import com.tsofen.agsenceapp.entities.AccountCompany;

import java.util.List;

public interface CompaniesNameHandler extends BaseHandler {
    void onCompaniesNameReady(List<AccountCompany> companiesName);
}
