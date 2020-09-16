package com.tsofen.agsenceapp;

import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.User;

import java.util.ArrayList;
import java.util.List;

public class CacheManager extends Thread{

    private static CacheManager cacheManager;
    private CacheManager(){}

    onDownloadFinishedHandler downloadFinishedHandler;
    onUserLoginFinishedHandler userLoginHandler;

    public static CacheManager getInstance(){
        if(cacheManager == null)
            cacheManager = new CacheManager();
        return cacheManager;
    }

    public List<Devices> getAllDevices(){
        return new ArrayList<Devices>();
    }

    public void setOnDownloadFinishedHandler(onDownloadFinishedHandler handler) {
        this.downloadFinishedHandler = handler;
    }

    public void setUserLoginHandler(onUserLoginFinishedHandler userLoginHandler) {
        this.userLoginHandler = userLoginHandler;
    }

    public interface onDownloadFinishedHandler{
        void onDownloadFinished(List<Devices> devices);
    }
    public interface onUserLoginFinishedHandler{
        void onLoginSuccess(User user);
        void onLoginFailed();
    }

    @Override
    public void run() {
//        if(downloadFinishedHandler != null){
//            downloadFinishedHandler.onDownloadFinished(new ArrayList<Devices>());
//        }

    }
}
