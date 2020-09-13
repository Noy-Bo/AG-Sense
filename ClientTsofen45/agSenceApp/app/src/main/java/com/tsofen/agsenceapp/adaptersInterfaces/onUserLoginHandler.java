package com.tsofen.agsenceapp.adaptersInterfaces;

import com.tsofen.agsenceapp.entities.User;

public interface onUserLoginHandler {
    void onAdminLoginSuccess(User user);
    void onAccountLoginSuccess(User user);
    void onUserLoginFailed();

}
