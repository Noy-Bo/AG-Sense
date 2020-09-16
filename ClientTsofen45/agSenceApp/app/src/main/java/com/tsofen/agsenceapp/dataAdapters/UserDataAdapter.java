package com.tsofen.agsenceapp.dataAdapters;


import android.util.Log;

import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.adaptersInterfaces.UserDataAdapterAPI;
import com.tsofen.agsenceapp.adaptersInterfaces.onUserLoginHandler;
import com.tsofen.agsenceapp.dataServices.LoginHandler;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.User;

public class UserDataAdapter extends BaseDataAdapter implements UserDataAdapterAPI {

    private static  UserDataAdapter instance;
    private UserDataAdapter(){}
    public static UserDataAdapter getInstance(){
        if(instance == null)
            instance = new UserDataAdapter();
        return  instance;
    }

    @Override
    public void userLogin(String username, String password , final onUserLoginHandler handler){
        cacheManager.loginJob(username, password , new LoginHandler() {
            @Override
            public void onLoginSuccess(User user) {
                if(user instanceof Admin)
                    handler.onAdminLoginSuccess((Admin) user);
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
