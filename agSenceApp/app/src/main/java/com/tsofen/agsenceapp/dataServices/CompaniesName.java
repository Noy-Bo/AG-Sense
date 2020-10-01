package com.tsofen.agsenceapp.dataServices;

import java.util.List;

public interface CompaniesName extends BaseHandler {
    void onCompaniesNameReady(List<String> companiesName);
}
