package com.tsofen.agsenceapp.dataServices;

import com.tsofen.agsenceapp.entities.Account;

public interface UserDetailsForgetPasswordHandler extends BaseHandler {
    void onUserDetails(Account account);


}
