package com.tsofen.agsenceapp.dataServices;

import com.tsofen.agsenceapp.entities.User;

public interface OnLogin {
    void onLoginSuccess(User user);
    void onLoginFailure();
}
