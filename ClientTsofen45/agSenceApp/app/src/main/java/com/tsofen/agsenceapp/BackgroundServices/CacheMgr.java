package com.tsofen.agsenceapp.BackgroundServices;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.tsofen.agsenceapp.CacheManagerAPI;

import com.tsofen.agsenceapp.dataServices.AccountDevicesHandler;
import com.tsofen.agsenceapp.dataServices.AccountNotificationsHandler;

import com.tsofen.agsenceapp.dataServices.AccountsHandler;
import com.tsofen.agsenceapp.dataServices.BaseHandler;
import com.tsofen.agsenceapp.dataServices.DeviceDataHandler;
import com.tsofen.agsenceapp.dataServices.DeviceNotificationsHandler;
import com.tsofen.agsenceapp.dataServices.NotificationsHandler;
import com.tsofen.agsenceapp.dataServices.OnDataReadyHandler;

import com.tsofen.agsenceapp.dataServices.DevicesHandler;
import com.tsofen.agsenceapp.dataServices.LoginHandler;
import com.tsofen.agsenceapp.dataServices.ServicesName;
import com.tsofen.agsenceapp.dataServices.TextDownloader;
import com.tsofen.agsenceapp.dataServices.UrlConnectionMaker;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Notification;

import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.DeviceData;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.User;
import org.json.JSONObject;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

public class CacheMgr implements CacheManagerAPI {

    private static CacheMgr cacheMgr=null;
    private List<Notification> notifications;
    private List<Account> accounts;
    private List<Devices> devices;




    private CacheMgr() {
        initializeAllServices();
        notifications = new ArrayList<>();
        accounts = new ArrayList<>();
        notifications = new ArrayList<>();
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
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

    // handlers and loopers.

    private HandlerThread handlerThreadServerPeriodic = new HandlerThread("serverPeriodicJobHandler");
    private HandlerThread handlerThreadLogin = new HandlerThread("handlerThreadLogin");
    private HandlerThread handlerThreadGetDevices = new HandlerThread("handlerThreadGetDevices");
    private HandlerThread handlerThreadGetSpecificDeviceDataById = new HandlerThread("handlerThreadGetSpecificDeviceDataById");
    private HandlerThread handlerThreadGetSpecificDevicesByAccount = new HandlerThread("handlerThreadGetSpecificDevicesByAccount");
    private HandlerThread handlerThreadGetSpecificNotificationsByAccount = new HandlerThread("handlerThreadGetSpecificNotificationsByAccount");
    private HandlerThread handlerThreadGetSpecificNotificationsByDevice = new HandlerThread("handlerThreadGetSpecificNotificationsByDevice");
    private HandlerThread handlerThreadGetNotifications = new HandlerThread("handlerThreadGetNotifications");
    private HandlerThread handlerThreadGetAccounts = new HandlerThread("handlerThreadGetAccounts");
    private Handler threadHandlerForGetSpecificDeviceDataById;
    private Handler threadHandlerForGetSpecificDevicesByAccount;
    private Handler threadHandlerForGetSpecificNotificationsByAccount;
    private Handler threadHandlerForGetSpecificNotificationsByDevice;
    private Handler threadHandlerForGetDevices;
    private Handler threadHandlerForGetAccounts;
    private Handler threadHandlerForGetNotifications;
    private Handler threadHandlerForLogin;
    private Handler threadHandlerForServerPeriod;
    private TextDownloader downloader = TextDownloader.getInstance();

    public HandlerThread gethandlerThreadServerPeriodic(){
        return handlerThreadServerPeriodic;
    }
    public Handler getThreadHandlerForServerPeriod() {
        return threadHandlerForServerPeriod;
    }

    private void initializeAllServices() // remove public
    {

        //initializing the handlers
        handlerThreadServerPeriodic.start();
        threadHandlerForServerPeriod = new Handler(handlerThreadServerPeriodic.getLooper());

        handlerThreadLogin.start();
        threadHandlerForLogin = new Handler(handlerThreadLogin.getLooper());

        handlerThreadGetDevices.start();
        threadHandlerForGetDevices = new Handler(handlerThreadGetDevices.getLooper());

        handlerThreadGetSpecificDeviceDataById.start();
        threadHandlerForGetSpecificDeviceDataById = new Handler(handlerThreadGetSpecificDeviceDataById.getLooper());

        handlerThreadGetSpecificDevicesByAccount.start();
        threadHandlerForGetSpecificDevicesByAccount = new Handler(handlerThreadGetSpecificDevicesByAccount.getLooper());

        handlerThreadGetSpecificNotificationsByAccount.start();
        threadHandlerForGetSpecificNotificationsByAccount = new Handler(handlerThreadGetSpecificNotificationsByAccount.getLooper());

        handlerThreadGetSpecificNotificationsByDevice.start();
        threadHandlerForGetSpecificNotificationsByDevice = new Handler(handlerThreadGetSpecificNotificationsByDevice.getLooper());

        handlerThreadGetNotifications.start();
        threadHandlerForGetNotifications = new Handler(handlerThreadGetNotifications.getLooper());

        handlerThreadGetAccounts.start();
        threadHandlerForGetAccounts = new Handler(handlerThreadGetAccounts.getLooper());
    }



    private class LoginJobRunnable implements Runnable {
        private String username;
        private String password;
        private LoginHandler handler;

        public LoginJobRunnable(String username, String password, LoginHandler handler) {
            this.username = username;
            this.password = password;
            this.handler = handler;
        }

        @Override
        public void run() {


            //URL Connection.
            UrlConnectionMaker urlConnectionMaker = new UrlConnectionMaker();
            Map<String, String> params = new HashMap<>();
            params.put("username",this.username);
            params.put("password",this.password);


            downloader.getText(urlConnectionMaker.createUrl(ServicesName.Login,params), new OnDataReadyHandler() {
                @Override
                public void onDataDownloadCompleted(String downloadedData) {
                    try {

                        //parsing json
                        JSONObject userJSON = new JSONObject(downloadedData);
                        User user;
                        //{"accountid":8,"id":9,"type":"account","email":"ibra123@gmail.com","username":"ibra"}

                        if(userJSON.getString("type").equals("account"))
                        {
                            user = new Account(userJSON.getInt("id"), userJSON.getString("username")   , userJSON.getString("email"),false, userJSON.getInt("accountid"));
                        }
                        else {
                            user = new Admin(userJSON.getInt("id"), userJSON.getString("username"), userJSON.getString("email"));
                        }
                        handler.onLoginSuccess(user);
                    }
                    catch (Exception e)
                    {
                        handler.onLoginFailure();
                    }
                }

                @Override
                public void onDownloadError() {
                    handler.onLoginFailure();
                }
            });
        }
    }

    // general runnable generic class. -in development.
     class BaseRunnable<E>  implements Runnable {
        private BaseHandler handler;
        Map<String, String> params;
        ServicesName serviceName;

        public BaseRunnable(BaseHandler handler, Map<String, String> params,ServicesName serviceName) {
            this.serviceName = serviceName;
            this.handler = handler;
            this.params = params;
        }

        @Override
        public void run() {
            UrlConnectionMaker urlConnectionMaker = new UrlConnectionMaker(); //TODO static
            downloader.getText(urlConnectionMaker.createUrl(serviceName, this.params), new OnDataReadyHandler() {
            //downloader.getText("http://206.72.198.59:8080/ServerTsofen45//Device/SpicificDeviceByFilter?id=5&healthy=1&faulty=1&bank=1&gps=1&tank=1&start=0&num=500", new OnDataReadyHandler() {
                @Override
                public void onDataDownloadCompleted(String downloadedData) {
                    Log.d("generics","onDataDownloadCompleted");
                    // JSON Parser


                    List<E> retrievedEntitiesList = new ArrayList<>();

                    if (handler instanceof DevicesHandler)
                    {

                        retrievedEntitiesList = parseToJsonArray(downloadedData, new Devices());
                        ((DevicesHandler) handler).onDevicesDownloadFinished((List<Devices>) retrievedEntitiesList);
                    }
                    else if (handler instanceof AccountDevicesHandler)
                    {
                        retrievedEntitiesList = parseToJsonArray(downloadedData, new Devices());
                        ((AccountDevicesHandler) handler).onDevicesRelatedToAccountDownloadFinished((List<Devices>) retrievedEntitiesList);
                    }
                    else if(handler instanceof DeviceDataHandler)
                    {
                        retrievedEntitiesList = parseToJsonArray(downloadedData, new DeviceData());
                        ((DeviceDataHandler)handler).onDeviceDataRelatedToDeviceDownloadFinished((List<DeviceData>) retrievedEntitiesList);
                    }
                    else if(handler instanceof AccountsHandler)
                    {
                        retrievedEntitiesList = parseToJsonArray(downloadedData, new Account());
                        ((AccountsHandler)handler).onAccountsDownloadFinished((List<Account>) retrievedEntitiesList);
                    }
                    else if (handler instanceof AccountNotificationsHandler)
                    {
                        retrievedEntitiesList = parseToJsonArray(downloadedData, new Notification());
                        ((NotificationsHandler)handler).onNotificationsDownloadFinished((List<Notification>) retrievedEntitiesList);
                    }
                    else if (handler instanceof DeviceNotificationsHandler)
                    {
                        retrievedEntitiesList = parseToJsonArray(downloadedData, new Notification());
                        ((DeviceNotificationsHandler)handler).onNotificationsRelatedToDeviceDownloadFinished((List<Notification>) retrievedEntitiesList);
                    }
                    else if (handler instanceof NotificationsHandler)
                    {
                        retrievedEntitiesList = parseToJsonArray(downloadedData, new Notification());
                        ((NotificationsHandler)handler).onNotificationsDownloadFinished((List<Notification>) retrievedEntitiesList);
                    }

                }

                @Override
                public void onDownloadError() {

                }
            });
        }
    }



    // API -

    @Override
    public void loginJob(final String username, final String password, final LoginHandler handler) {
        LoginJobRunnable runnable = new LoginJobRunnable(username,password,handler);
        threadHandlerForLogin.post(runnable);


    }

    @Override
    public void getAccountsJob(int start, int num, AccountsHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("num",Integer.toString(num));
        params.put("start",Integer.toString(start));
        BaseRunnable<Account> runnableGeneric = new BaseRunnable<>(handler,params,ServicesName.getAllAccounts);
        threadHandlerForGetAccounts.post(runnableGeneric);
    }

    @Override
    public void getDevicesJob(int start, int num, DevicesHandler handler) {
       /*GetDevicesJobRunnable runnable =  new GetDevicesJobRunnable(0,0,handler);
        runnable.run();
        */
        Map<String, String> params = new HashMap<>();
//        params.put("num",Integer.toString(num));
//        params.put("start",Integer.toString(start));
        BaseRunnable<Devices> runnableGeneric = new BaseRunnable<>(handler,params,ServicesName.getAllDevices);
        threadHandlerForGetDevices.post(runnableGeneric);
    }

    @Override
    public void getNotificationsJob(int start, int num, NotificationsHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("num",Integer.toString(num));
        params.put("start",Integer.toString(start));
        BaseRunnable<Notification> runnableGeneric = new BaseRunnable<>(handler,params,ServicesName.getNotifications);
        threadHandlerForGetNotifications.post(runnableGeneric);
    }

    @Override
    public void getDevicesRelatedToAccountJob(int accountId, int start, int num, AccountDevicesHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("id",Integer.toString(accountId));
        params.put("num",Integer.toString(num));
        params.put("start",Integer.toString(start));
        BaseRunnable<Devices> runnableGeneric = new BaseRunnable<>(handler,params,ServicesName.getDeviceRelatedToAccount);
        threadHandlerForGetSpecificDevicesByAccount.post(runnableGeneric);
    }

    @Override
    public void getNotificationRelatedToDeviceJob(int deviceId, int start, int num, DeviceNotificationsHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("id",Integer.toString(deviceId));
        params.put("num",Integer.toString(num));
        params.put("start",Integer.toString(start));
        BaseRunnable<Notification> runnableGeneric = new BaseRunnable<>(handler,params,ServicesName.getNotificationRelatedToDevice);
        threadHandlerForGetSpecificNotificationsByDevice.post(runnableGeneric);
    }

    @Override
    public void getNotificationRelatedToAccountJob(int accountId, int start, int num, AccountNotificationsHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("id",Integer.toString(accountId));
        params.put("num",Integer.toString(num));
        params.put("start",Integer.toString(start));
        BaseRunnable<Notification> runnableGeneric = new BaseRunnable<>(handler,params,ServicesName.getNotificationsRelatedToAccount);
        threadHandlerForGetSpecificNotificationsByAccount.post(runnableGeneric);
    }

    @Override

    public void getSpecificDeviceDataByIdJob(int deviceId, DeviceDataHandler handler) {

        Map<String, String> params = new HashMap<>();
        params.put("id",Integer.toString(deviceId));
        BaseRunnable<DeviceData> runnableGeneric = new BaseRunnable<>(handler,params,ServicesName.getSpecificDeviceDataById);
        threadHandlerForGetSpecificDeviceDataById.post(runnableGeneric);

    }

    public JSONObject parseToOneJsonObject(String jsonStr) throws JSONException {
        JSONObject jObj = null;
        jObj = new JSONObject(jsonStr);
        if (jObj == null)
            throw new JSONException("json allocation failed");
        return jObj;

    }


    public <T> List<T> parseToJsonArray(String jsonArray, Object clazz) {
        try {
            Type typeOfT = TypeToken.getParameterized(List.class, clazz.getClass()).getType();
            return new GsonBuilder().create().fromJson(jsonArray, typeOfT);

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }



}

