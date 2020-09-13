package com.tsofen.agsenceapp.dataAdapters;

import com.tsofen.agsenceapp.CacheManager;
import com.tsofen.agsenceapp.adaptersInterfaces.onUserLoginHandler;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.User;

public class UserDataAdapter {

    public static void userLogin(String username, String password , final onUserLoginHandler handler){
        CacheManager cacheManager = CacheManager.getInstance();
        cacheManager.setUserLoginHandler(new CacheManager.onUserLoginFinishedHandler() {
            @Override
            public void onLoginSuccess(User user) {
                if(user instanceof Admin)
                    handler.onAdminLoginSuccess((Admin)user);
                else
                    handler.onAccountLoginSuccess((Account)user);
            }

            @Override
            public void onLoginFailed() {
                handler.onUserLoginFailed();
            }
        });
    }

}
