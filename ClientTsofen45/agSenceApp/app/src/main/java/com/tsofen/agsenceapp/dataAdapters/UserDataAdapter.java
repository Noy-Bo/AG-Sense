package com.tsofen.agsenceapp.dataAdapters;


import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.adaptersInterfaces.onUserLoginHandler;
import com.tsofen.agsenceapp.dataServices.OnLogin;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.User;

import java.util.List;

public class UserDataAdapter {

    public static void userLogin(String username, String password , final onUserLoginHandler handler){
        CacheMgr cacheManager = CacheMgr.getInstance();
        cacheManager.loginJob(new OnLogin() {
            @Override
            public void onLoginSuccess(User user) {
                if(user instanceof Admin)
                    handler.onAdminLoginSuccess((Admin)user);
                else
                    handler.onAccountLoginSuccess((Account)user);
            }

            @Override
            public void onLoginFailure() {
                handler.onUserLoginFailed();
            }
        } );
    }

}
