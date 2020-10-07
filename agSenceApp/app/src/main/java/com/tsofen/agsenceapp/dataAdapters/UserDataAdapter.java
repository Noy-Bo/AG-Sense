package com.tsofen.agsenceapp.dataAdapters;


import android.content.Context;
import android.util.Log;

import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.adaptersInterfaces.AdminDashboardInfoHandler;
import com.tsofen.agsenceapp.adaptersInterfaces.UserDataAdapterAPI;
import com.tsofen.agsenceapp.adaptersInterfaces.onUserLoginHandler;
import com.tsofen.agsenceapp.dataServices.LoginHandler;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.User;
import com.tsofen.agsenceapp.utils.FailedLogin;

public class UserDataAdapter extends BaseDataAdapter implements UserDataAdapterAPI {

    private static  UserDataAdapter instance;
    private static Context context = null;
    private FailedLogin callback;
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
                else if (user instanceof Account)
                    handler.onAccountLoginSuccess((Account)user);
            }

            @Override
            public void onLoginFailure() {
                handler.onUserLoginFailed();
            }
        } );
    }

    @Override
    public void getAdminDashboardInfo(int adminId, AdminDashboardInfoHandler handler) {
    }

    public  Context getContext() {
        return context;
    }

    public  void setContext(Context context) {
        this.context = context;
    }

    public FailedLogin getCallback() {
        return callback;
    }

    public void setCallback(FailedLogin callback) {
        this.callback = callback;
    }

}
