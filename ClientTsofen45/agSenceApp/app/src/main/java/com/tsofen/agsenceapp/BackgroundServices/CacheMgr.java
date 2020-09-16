package com.tsofen.agsenceapp.BackgroundServices;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tsofen.agsenceapp.CacheManagerAPI;

import com.tsofen.agsenceapp.dataAdapters.DeviceDataAdapter;

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

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private Handler threadHandlerForGetSpecificDeviceDataById;
    private Handler threadHandlerForGetDevices;
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

    //OLD FUNCTIONS, MOVED TO WORK WITH GENERIC BaseRunnable<E>
    /*

    public static class GetDevicesJobRunnable implements Runnable {
       private DevicesHandler handler;
       private int start;
       private int num;

       public GetDevicesJobRunnable( int start, int num, DevicesHandler handler) {
           this.handler = handler;
           this.start = start;
           this.num = num;
       }

       @Override
       public void run() {
           //URL Connection.
           TextDownloader downloader = TextDownloader.getInstance();
           UrlConnectionMaker urlConnectionMaker = new UrlConnectionMaker();
           Map<String, String> params = new HashMap<>();
           params.put("num",Integer.toString(num));
           params.put("start",Integer.toString(start));
           downloader.getText("https://www.google.com/", new OnDataReadyHandler() {
               @Override
               public void onDataDownloadCompleted(String downloadedData) {
                   Log.d("innerclasstest","onDataDownloadCompleted");

                   // we have  results at downloadedData, but we now presenting dummy data.

                   Date date = new Date();
                   date.getTime();
                   Date date1 = new Date();
                   date.setTime(20102020);

                   List<Devices> devices = new ArrayList<>();
                   devices.add(new Devices(0,1,1,"Device",date,date1,true));
                   devices.add(new Devices(0,1,1,"Device",date,date1,true));
                   devices.add(new Devices(0,1,1,"Device",date,date1,true));
                   devices.add(new Devices(0,1,1,"Device",date,date1,true));
                   devices.add(new Devices(0,1,1,"Device",date,date1,true));
                   devices.add(new Devices(0,1,1,"Device",date,date1,true));
                   devices.add(new Devices(0,1,1,"Device",date,date1,true));
                   devices.add(new Devices(0,1,1,"Device",date,date1,true));
                   devices.add(new Devices(0,1,1,"Device",date,date1,true));
                   devices.add(new Devices(0,1,1,"Device",date,date1,true));
                   devices.add(new Devices(0,1,1,"Device",date,date1,true));
                   devices.add(new Devices(0,1,1,"Device",date,date1,true));
                   devices.add(new Devices(0,1,1,"Device",date,date1,true));
                   devices.add(new Devices(0,1,1,"Device",date,date1,true));
                   devices.add(new Devices(0,1,1,"Device",date,date1,true));
                   devices.add(new Devices(0,1,1,"Device",date,date1,true));
                   devices.add(new Devices(0,1,1,"Device",date,date1,true));

                   if (handler != null) {
                       handler.onDevicesDownloadFinished(devices); //  chaining the handlers. -> updating the main handler that devices are ready ---changing
                   }

               }

               @Override
               public void onDownloadError() {
                   //code this case.
               }
           });

       }
   }

    private class GetAccountsJobRunnable implements Runnable {

        private int start;
        private int num;
        private AccountsHandler handler;

        public GetAccountsJobRunnable(int start, int num, AccountsHandler handler) {
            this.start = start;
            this.num = num;
            this.handler = handler;
        }

        @Override
        public void run() {
            //TextDownloader downloader = new TextDownloader();  //URL Connection.
            UrlConnectionMaker urlConnectionMaker = new UrlConnectionMaker();
            Map<String, String> params = new HashMap<>();
            params.put("num",Integer.toString(num));
            params.put("start",Integer.toString(start));


            downloader.getText(urlConnectionMaker.createUrl(ServicesName.getAllAccounts,params), new OnDataReadyHandler() {
                @Override
                public void onDataDownloadCompleted(String downloadedData) {
                    // code what to do when we get the string of accounts.
                }

                @Override
                public void onDownloadError() {
                    // code what to do on error
                }
            });
        }
    }

    private class  getSpecificDeviceDataByIdJobRunnable implements Runnable{

        private int deviceId;
        private int start;
        private int num;
        private DeviceDataHandler handler;

        public getSpecificDeviceDataByIdJobRunnable(int deviceId, int start, int num, DeviceDataHandler handler) {
            this.deviceId = deviceId;
            this.start = start;
            this.num = num;
            this.handler = handler;
        }

        @Override
        public void run() {
            TextDownloader downloader = TextDownloader.getInstance();
            UrlConnectionMaker urlConnectionMaker = new UrlConnectionMaker();
            Map<String, String> params = new HashMap<>();
            params.put("num",Integer.toString(num));
            params.put("start",Integer.toString(start));
            params.put("deviceid",Integer.toString(deviceId));



            downloader.getText(urlConnectionMaker.createUrl(ServicesName.getSpecificDeviceDataById,params), new OnDataReadyHandler() {
                @Override
                public void onDataDownloadCompleted(String downloadedData) {
                    // code what to do when we get the string of deviceata
                }

                @Override
                public void onDownloadError() {
                    // code what to do on error
                }
            });
        }
    }

    private class GetNotificationsJobRunnable implements Runnable{

        private int start;
        private int num;
        private NotificationsHandler handler;

        public GetNotificationsJobRunnable(int start, int num, NotificationsHandler handler) {
            this.start = start;
            this.num = num;
            this.handler = handler;
        }

        @Override
        public void run() {
            TextDownloader downloader = TextDownloader.getInstance();
            UrlConnectionMaker urlConnectionMaker = new UrlConnectionMaker();
            Map<String, String> params = new HashMap<>();
            params.put("num",Integer.toString(num));
            params.put("start",Integer.toString(start));



            downloader.getText(urlConnectionMaker.createUrl(ServicesName.getNotifications,params), new OnDataReadyHandler() {
                @Override
                public void onDataDownloadCompleted(String downloadedData) {
                    // code what to do when we get the string of Notifications.
                }

                @Override
                public void onDownloadError() {
                    // code what to do on error
                }
            });
        }
    }


     */


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
                    if(handler instanceof DeviceDataHandler)
                    {

                        retrievedEntitiesList = parseToJsonArray(downloadedData, new DeviceData());
                        ((DeviceDataHandler)handler).onDeviceDataRelatedToDeviceDownloadFinished((List<DeviceData>) retrievedEntitiesList);
                    }
                    // else if () other handler cases.

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

    }

    @Override
    public void getDevicesRelatedToAccountJob(int accountId, int start, int num, AccountDevicesHandler handler) {

    }

    @Override
    public void getNotificationRelatedToDeviceJob(int deviceId, int start, int num, DeviceNotificationsHandler handler) {

    }

    @Override
    public void getNotificationRelatedToAccountJob(int accountId, int start, int num, AccountNotificationsHandler handler) {

    }

    @Override
    public void getSpecificDeviceDataByIdJob(int deviceId, DeviceDataHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("id",Integer.toString(deviceId));
        BaseRunnable<Devices> runnableGeneric = new BaseRunnable<>(handler,params,ServicesName.getSpecificDeviceDataById);
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

