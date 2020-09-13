package com.tsofen.agsenceapp.dataServices;

import com.tsofen.agsenceapp.entities.User;

public interface OnLogin {
    void OnLoginSuccess(User user);
    void OnLoginFailure();
}
