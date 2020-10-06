package com.tsofen.agsenceapp.dataServices;

import com.tsofen.agsenceapp.entities.User;

public interface LoginHandler extends BaseHandler {
    void onLoginSuccess(User user);
    void onLoginFailure();
}
