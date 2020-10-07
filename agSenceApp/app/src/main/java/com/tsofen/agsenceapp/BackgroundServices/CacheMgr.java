package com.tsofen.agsenceapp.BackgroundServices;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.util.Log;


import com.tsofen.agsenceapp.activities.AppBaseActivity;
import com.tsofen.agsenceapp.dataServices.AccountDevicesHandler;
import com.tsofen.agsenceapp.dataServices.BaseHandler;
import com.tsofen.agsenceapp.dataServices.AccountNotificationsHandler;

import com.tsofen.agsenceapp.dataServices.AccountsHandler;

import com.tsofen.agsenceapp.dataServices.CompaniesNameHandler;
import com.tsofen.agsenceapp.dataServices.DeviceDataHandler;
import com.tsofen.agsenceapp.dataServices.DeviceNotificationsHandler;
import com.tsofen.agsenceapp.dataServices.EditAccountHandler;
import com.tsofen.agsenceapp.dataServices.EditDeviceHandler;
import com.tsofen.agsenceapp.dataServices.NewCompanyHandler;
import com.tsofen.agsenceapp.dataServices.NewDeviceAddedHandler;
import com.tsofen.agsenceapp.dataServices.NewUserAddedHandler;
import com.tsofen.agsenceapp.dataServices.NotificationsHandler;
import com.tsofen.agsenceapp.dataServices.OnDataReadyHandler;

import com.tsofen.agsenceapp.dataServices.DevicesHandler;
import com.tsofen.agsenceapp.dataServices.LoginHandler;

import com.tsofen.agsenceapp.dataServices.PasswordSetHandler;
import com.tsofen.agsenceapp.dataServices.ServicesName;
import com.tsofen.agsenceapp.dataServices.TextDownloader;
import com.tsofen.agsenceapp.dataServices.UrlConnectionMaker;
import com.tsofen.agsenceapp.dataServices.UserDetailsForgetPasswordHandler;
import com.tsofen.agsenceapp.dataServices.UserPasswordChangeHandler;
import com.tsofen.agsenceapp.dataServices.VerificationCodeCheckHandler;
import com.tsofen.agsenceapp.dataServices.VerificationCodeSentHandler;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Notification;

import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.Devices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

public class CacheMgr implements CacheManagerAPI {

    private static CacheMgr cacheMgr=null;
    private List<Notification> notifications;
    private List<Account> accounts;
    private List<Devices> devices;

    private final static int waitInterval = 60000;
    private boolean stopGetDevicesPeriodic = false;
    private HandlerThread handlerThreadForGetDevicesPeriodic = new HandlerThread("serverPeriodicJobHandler");
    private Handler threadHandlerForGetDevicesPeriodic;

    private TextDownloader downloader = TextDownloader.getInstance();

    // ==================================================================================
    // ---------------------------- CTOR Getters / Setters -------------------------------
    // ==================================================================================


    private CacheMgr() {
        initializeAllServices();
        devices = new ArrayList<>();
        accounts = new ArrayList<>();
        notifications = new ArrayList<>();
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public  void setNotifications(List<Notification> notifications) { // public setter for firebase notification on recieve
        this.notifications = notifications;
    }
    public void setStopGetDevicesPeriodic(boolean stopGetDevicesPeriodic) {
        this.stopGetDevicesPeriodic = stopGetDevicesPeriodic;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Devices> getDevices() {
        return devices;
    }

    public void setDevices(List<Devices> devices) {
        this.devices = devices;
    }

    public static CacheMgr getInstance() {
        if (cacheMgr == null)
            cacheMgr = new CacheMgr(); // TODO - add synchronized.

        return cacheMgr;
    }


    // periodic GetDevices configs

    public HandlerThread getHandlerThreadForGetDevicesPeriodic(){
        return handlerThreadForGetDevicesPeriodic;
    }
    public Handler getThreadHandlerForGetDevicesPeriodic() {
        return threadHandlerForGetDevicesPeriodic;
    }

    private void initializeAllServices()
    {

        //initializing the handlers
        handlerThreadForGetDevicesPeriodic.start();
        threadHandlerForGetDevicesPeriodic = new Handler(handlerThreadForGetDevicesPeriodic.getLooper());

    }

    // ==================================================================================
    // -------------------------------- Repeated Job Admin ------------------------------
    // ==================================================================================

    private DevicesHandler handlerForRepeatedGetDevicesJob  = new DevicesHandler() {
        @Override
        public void onDevicesDownloadFinished(List<Devices> devices) {
            Log.d("repeated","repeated task completed onDevicesDownloadFinished");
            setDevices(devices);
            SystemClock.sleep(waitInterval);
            cacheMgr.getThreadHandlerForGetDevicesPeriodic().post(cacheMgr.GetDevicesPeriodic);

            if (stopGetDevicesPeriodic == true)
            {
                cacheMgr.getThreadHandlerForGetDevicesPeriodic().removeCallbacksAndMessages(null);
                stopGetDevicesPeriodic = false;
            }
        }
    };
    private  AdminGetDevicesPeriodicRunnable GetDevicesPeriodic = new AdminGetDevicesPeriodicRunnable(handlerForRepeatedGetDevicesJob,new HashMap<String,String>(),ServicesName.getAllDevices);
    public Runnable getGetDevicesPeriodicRunnable() {
        return this.GetDevicesPeriodic;
    }


    // ==================================================================================
    // ---------------------------- Main Generic Async Task. ----------------------------
    // ==================================================================================

    private class BaseAsyncTask<E> extends AsyncTask<Void, Void, Void>
    {
        private BaseHandler handler;
        Map<String, String> params;
        ServicesName serviceName;

        public BaseAsyncTask(BaseHandler handler, Map<String, String> params, ServicesName serviceName) {
            this.handler = handler;
            this.params = params;
            this.serviceName = serviceName;
        }



        @Override
        protected Void doInBackground(Void... voids) {

            UrlConnectionMaker urlConnectionMaker = new UrlConnectionMaker();
            downloader.getText(urlConnectionMaker.createUrl(serviceName, this.params), new OnDataReadyHandler() {
                @Override
                public void onDataDownloadCompleted(String downloadedData) {
                    Log.d("generics","onDataDownloadCompleted");
                    // JSON Parser
                    CacheManagerHandlers.parseDataAndSendCallback(downloadedData,handler);

                }

                @Override
                public void onDownloadError() {
                    if (handler instanceof LoginHandler)
                    {
                        ((LoginHandler)handler).onLoginFailure();
                    }
                }
            });

            return null;
        }
    }




    // ==================================================================================
    // ---------------------------- Jobs API for Adapters.-------------------------------
    // ==================================================================================

    @Override
    public void loginJob(final String username, final String password, final LoginHandler handler) {

        Map<String, String> params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
        BaseAsyncTask<String> asyncGeneric = new BaseAsyncTask<>(handler, params, ServicesName.Login);
        asyncGeneric.execute();



    }

    @Override
    public void getAccountsJob(int start, int num, AccountsHandler handler) {
        if (getAccounts().size() == 0)
        {
            Map<String, String> params = new HashMap<>();
            params.put("num", Integer.toString(num));
            params.put("start", Integer.toString(start));
            BaseAsyncTask<Devices> asyncGeneric = new BaseAsyncTask<>(handler, params, ServicesName.getAllAccounts);
            asyncGeneric.execute();

        }
        else
        {
            handler.onAccountsDownloadFinished(getAccounts());
        }
    }
    @Override
    public void getDevicesJob(int start, int num, DevicesHandler handler) {



       if (getDevices().size() == 0) {
           Map<String, String> params = new HashMap<>();
           //       params.put("num",Integer.toString(num));
           //       params.put("start",Integer.toString(start));
           BaseAsyncTask<Devices> asyncGeneric = new BaseAsyncTask<>(handler, params, ServicesName.getAllDevices);
           asyncGeneric.execute();
       }
       else
       {
           handler.onDevicesDownloadFinished(getDevices());
       }

    }

    @Override
    public void getNotificationsJob(int start, int num, NotificationsHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("num",Integer.toString(num));
        params.put("start",Integer.toString(start));
        BaseAsyncTask<Devices> asyncGeneric = new BaseAsyncTask<>(handler,params,ServicesName.getNotifications);
        asyncGeneric.execute();
    }

    @Override
    public void getDevicesRelatedToAccountJob(int accountId, int start, int num, AccountDevicesHandler handler)
    {
        if (AppBaseActivity.getUser() instanceof Admin)
        {
            Map<String, String> params = new HashMap<>();
            params.put("id", Integer.toString(accountId));
            params.put("num", Integer.toString(num));
            params.put("start", Integer.toString(start));
            BaseAsyncTask<Devices> asyncGeneric = new BaseAsyncTask<>(handler, params, ServicesName.getDeviceRelatedToAccount);
            asyncGeneric.execute();
        }
        else if (AppBaseActivity.getUser() instanceof Account)
        {
            if  ( getDevices().size() == 0)
            {
                Map<String, String> params = new HashMap<>();
                params.put("id", Integer.toString(accountId));
                params.put("num", Integer.toString(num));
                params.put("start", Integer.toString(start));
                BaseAsyncTask<Devices> asyncGeneric = new BaseAsyncTask<>(handler, params, ServicesName.getDeviceRelatedToAccount);
                asyncGeneric.execute();
            }
            else
            {
                handler.onDevicesRelatedToAccountDownloadFinished(getDevices());
            }
        }


    }

    @Override
    public void getNotificationRelatedToDeviceJob(int deviceId, int start, int num, DeviceNotificationsHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("id",Integer.toString(deviceId));
        params.put("num",Integer.toString(num));
        params.put("start",Integer.toString(start));
        BaseAsyncTask<Devices> asyncGeneric = new BaseAsyncTask<>(handler,params,ServicesName.getNotificationRelatedToDevice);
        asyncGeneric.execute();
    }

    @Override
    public void getNotificationRelatedToAccountJob(int accountId, int start, int num, AccountNotificationsHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("id",Integer.toString(accountId));
        params.put("num",Integer.toString(num));
        params.put("start",Integer.toString(start));
        BaseAsyncTask<Devices> asyncGeneric = new BaseAsyncTask<>(handler,params,ServicesName.getNotificationsRelatedToAccount);
        asyncGeneric.execute();
    }

    @Override

    public void getSpecificDeviceDataByIdJob(int deviceId, DeviceDataHandler handler) {

        Map<String, String> params = new HashMap<>();
        params.put("id",Integer.toString(deviceId));
        BaseAsyncTask<Devices> asyncGeneric = new BaseAsyncTask<>(handler,params,ServicesName.getAllDeviceDataById);
        asyncGeneric.execute();

    }



    @Override
    public void addNewUserJob(String username, String emailAddress, String userType, String accountName, NewUserAddedHandler handler) {

        Map<String, String> params = new HashMap<>();
        params.put("accountName",accountName);
        params.put("username",username);
        params.put("userType",userType);
        params.put("email",emailAddress);
        BaseAsyncTask<Devices> asyncGeneric = new BaseAsyncTask<>(handler,params,ServicesName.AddToDb);
        asyncGeneric.execute();

    }

    @Override
    public void addNewCompanyJob(String companyName, NewCompanyHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("accountName",companyName);
        BaseAsyncTask<Devices> asyncGeneric = new BaseAsyncTask<>(handler,params,ServicesName.addAccount);
        asyncGeneric.execute();
    }

    @Override
    public void getAllCompaniesNameJob(CompaniesNameHandler handler) {
        Map<String, String> params = new HashMap<>();
        BaseAsyncTask<Devices> asyncGeneric = new BaseAsyncTask<>(handler,params,ServicesName.getAllAccountsName);
        asyncGeneric.execute();
    }

    @Override
    public void addNewDeviceJob(Long imei, String deviceType, String accountName, String devicePhoneNumber, String devicePassword, NewDeviceAddedHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("accountName",accountName);
        params.put("devicePassword",devicePassword);
        params.put("imei",Long.toString(imei));
        params.put("phoneNumber",devicePhoneNumber);
        params.put("type",deviceType);
        BaseAsyncTask<Devices> asyncGeneric = new BaseAsyncTask<>(handler,params,ServicesName.AddNewDevice);
        asyncGeneric.execute();


    }

    @Override
    public void setPasswordJob(int userId, String password, PasswordSetHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("userId",Long.toString(userId));
        params.put("pass",password);
        BaseAsyncTask<Devices> asyncGeneric = new BaseAsyncTask<>(handler,params,ServicesName.setPass);
        asyncGeneric.execute();
        //setPass("/User/setPassword"),           //userId string, userId integer

    }

    @Override
    public void editAccountJob(String prevName, String newName, EditAccountHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("prevName",prevName);
        params.put("newName",newName);
        BaseAsyncTask<Devices> asyncGeneric = new BaseAsyncTask<>(handler,params,ServicesName.editAccount);
        asyncGeneric.execute();

    }

    @Override
    public void editDeviceJob(Long deviceIMEI, String newPhoneNumber, String newPass, EditDeviceHandler handler) {
        // NO URL FROM SERVER
    }

    @Override
    public void changeUserPasswordJob(String username, String newPass, UserPasswordChangeHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("newPass",newPass);
        params.put("userName",username); // changed to userName by ameer from int Id
        BaseAsyncTask<Devices> asyncGeneric = new BaseAsyncTask<>(handler,params,ServicesName.ConfirmPassword); // changed by ameer from changePass to ConfirmPassword
        asyncGeneric.execute();
    }


    @Override
    public void sendVerificationCodeJob(String email, VerificationCodeSentHandler handler) {
        // NO URL FROM SERVER
    }

    @Override
    public void verifyCodeJob(String username, String verificationCode, VerificationCodeCheckHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("userName",username);
        params.put("Verification",verificationCode);
        BaseAsyncTask<Devices> asyncGeneric = new BaseAsyncTask<>(handler,params,ServicesName.ConfirmCode);
        asyncGeneric.execute();
    }
    @Override
    public void emailConfirmed(String username, VerificationCodeSentHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("userName",username);
        BaseAsyncTask<Devices> asyncGeneric = new BaseAsyncTask<>(handler,params,ServicesName.EmailPicked); // must change when server link is live
        asyncGeneric.execute();
    }

    @Override
    public void userDetailsForgetPassword(String username, UserDetailsForgetPasswordHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("userName",username);
        BaseAsyncTask<Devices> asyncGeneric = new BaseAsyncTask<>(handler,params,ServicesName.usernameForgetPassword);
        asyncGeneric.execute();
    }




    // ==================================================================================
    // --------------------------- Clear Data API for Adatpers --------------------------
    // ==================================================================================

    public void clearCacheDevices(){devices.clear();}
    public void clearCacheAccounts(){accounts.clear();}
    public void clearCacheNotification(){notifications.clear();}

    public  void clearCache(){
        accounts.clear();
        devices.clear();
        notifications.clear();
    }


}

