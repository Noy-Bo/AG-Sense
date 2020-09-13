package com.tsofen.agsenceapp.adaptersInterfaces;

import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.User;

public interface onUserLoginHandler {
    void onAdminLoginSuccess(Admin user);
    void onAccountLoginSuccess(Account user);
    void onUserLoginFailed();

}
