package com.tsofen.agsenceapp.dataServices;

import java.util.List;

public interface CompaniesNameHandler extends BaseHandler {
    void onCompaniesNameReady(List<String> companiesName);
}
