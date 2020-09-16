package com.tsofen.agsenceapp.BackgroundServices;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tsofen.agsenceapp.CacheManagerAPI;
import com.tsofen.agsenceapp.dataServices.AccountsHandler;
import com.tsofen.agsenceapp.dataServices.DeviceDataHandler;
import com.tsofen.agsenceapp.dataServices.NotificationsHandler;
import com.tsofen.agsenceapp.dataServices.OnDataReadyHandler;
import com.tsofen.agsenceapp.dataServices.DevicesHandler;
import com.tsofen.agsenceapp.dataServices.LoginHandler;
import com.tsofen.agsenceapp.dataServices.TextDownloader;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.DeviceData;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.Notification;
import com.tsofen.agsenceapp.entities.User;
<<<<<<< HEAD
import org.json.JSONObject;
=======

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

>>>>>>> 47c967e6c40340ff000aa8331d0b4fbc14eddb69
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class CacheMgr implements CacheManagerAPI {
    private static CacheMgr cacheMgr = null;

    //handles
    private HandlerThread handlerThreadServerPeriodic = new HandlerThread("serverPeriodicJobHandler");
    private HandlerThread handlerThreadLogin = new HandlerThread("handlerThreadLogin");
    private Handler threadHandlerForLogin;
    private Handler threadHandlerForServerPeriod;

    


    private CacheMgr() {
        initializeAllServices();
    }

    public static CacheMgr getInstance() {
        if (cacheMgr == null)
            cacheMgr = new CacheMgr(); // TODO - add synchronized.

        return cacheMgr;
    }

    private void initializeAllServices() // remove public
    {
<<<<<<< HEAD
        //initializing the handlers
        handlerThreadLogin.start();
        threadHandlerForLogin = new Handler(handlerThreadLogin.getLooper());

        handlerThreadServerPeriodic.start();
        threadHandlerForServerPeriod = new Handler(handlerThreadServerPeriodic.getLooper());

    }
=======
        private DevicesHandler handler;

        @Override
        public void run() {
            TextDownloader downloader = new TextDownloader();
            downloader.setOnDownloadCompletedListener(new OnDataReadyHandler() { // specifying a new handler for textDownloader
                @Override
                public void onDataDownloadCompleted(String downloadedData) { // creating a new OnDataReadyHandler, and inserting it to downloader as handler.
                    Log.d("DOWNLOAD", "Download text is " + downloadedData);

                    // we have  results at downloadedData, but we now presenting dummy data.

                    Date date = new Date();
                    date.getTime();
                    Date date1 = new Date();
                    date.setTime(20102020);

                    List<Devices> devices = new ArrayList<>();
                    devices.add(new Devices(0, 1, 1, "Device", date, date1, true));
                    devices.add(new Devices(0, 1, 1, "Device", date, date1, true));
                    devices.add(new Devices(0, 1, 1, "Device", date, date1, true));
                    devices.add(new Devices(0, 1, 1, "Device", date, date1, true));
                    devices.add(new Devices(0, 1, 1, "Device", date, date1, true));
                    devices.add(new Devices(0, 1, 1, "Device", date, date1, true));
                    devices.add(new Devices(0, 1, 1, "Device", date, date1, true));
                    devices.add(new Devices(0, 1, 1, "Device", date, date1, true));
                    devices.add(new Devices(0, 1, 1, "Device", date, date1, true));
                    devices.add(new Devices(0, 1, 1, "Device", date, date1, true));
                    devices.add(new Devices(0, 1, 1, "Device", date, date1, true));
                    devices.add(new Devices(0, 1, 1, "Device", date, date1, true));
                    devices.add(new Devices(0, 1, 1, "Device", date, date1, true));
                    devices.add(new Devices(0, 1, 1, "Device", date, date1, true));
                    devices.add(new Devices(0, 1, 1, "Device", date, date1, true));
                    devices.add(new Devices(0, 1, 1, "Device", date, date1, true));
                    devices.add(new Devices(0, 1, 1, "Device", date, date1, true));

                    if (handler != null) {
                        handler.onDevicesDownloadFinished(devices); //  chaining the handlers. -> updating the main handler that devices are ready ---changing
                    }
                }

                @Override
                public void onDownloadError() {



    public void getDevicesJob(final String urlAddress) // param - url to download. result - at OnDevicesReadyHandler
        {

            threadHandlerForServerPeriod.post(new Runnable() {
                private DevicesHandler handler;
                @Override
                public void run() {

                    TextDownloader downloader = new TextDownloader();
                    downloader.setOnDownloadCompletedListener(new OnDataReadyHandler() {
                        @Override
                        public void onDataDownloadCompleted(String downloadedData) {
                            Log.d("DOWNLOAD","Download text is "+downloadedData);

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
                    downloader.getText(urlAddress);
                }
            });
        }




   // public void serverPeriodicJob()
   // {
            //threadHandler.post(new getDevicesRunnable());

            //threadHandler.post(new WaitPeriod());

   // }

   public void loginJob(final String username, final String password, final LoginHandler handler)
    {
        threadHandlerForLogin.post(new Runnable() {
            @Override
            public void run() {
                TextDownloader downloader = new TextDownloader();
                downloader.setOnDownloadCompletedListener(new OnDataReadyHandler() {
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
            });
            downloader.getText("https://www.google.com"); // TODO create the URL in getDevicesRunnable Ctor. // via field.
        }
    }

    private void initializeAllServices() // remove public
    {
        //initializing the serverPeriodJob
        handlerThreadLogin.start();
        threadHandler = new Handler(handlerThreadLogin.getLooper());
    }

    // public void serverPeriodicJob()
    // {
    //threadHandler.post(new getDevicesRunnable());

    //threadHandler.post(new WaitPeriod());

    // }

    public void loginJob(final String username, final String password, final LoginHandler handler) {

          threadHandler.post(new Runnable() {
                @Override
                public void run() {
                    TextDownloader downloader = new TextDownloader();
                    downloader.setOnDownloadCompletedListener(new OnDataReadyHandler() {
                        @Override
                        public void onDataDownloadCompleted(String downloadedData) {
                            Gson gson = new Gson();
                            try {
                                //parsing json
                                JSONObject userJSON =  parseToOneJsonObject(downloadedData);
                                User user;
                                if (userJSON.getString("type").equals("account")) {
                                    user = gson.fromJson(downloadedData,Account.class);
                                } else {
                                    user = gson.fromJson(downloadedData,Admin.class);
                                }
                                handler.onLoginSuccess(user);
                            } catch (Exception e) {
                                handler.onLoginFailure();

                            }
                        }

                        @Override
                        public void onDownloadError() {
                            handler.onLoginFailure();
                        }
                    });
                    downloader.getText("http://206.72.198.59:8080/ServerTsofen45/User/Login?username=" + username + "&password=" + password);

                }
            });
    }


    @Override
    public void getAccountsJob(int start, int num, AccountsHandler handler) {
        handler.onAccountsDownloadFinished(new ArrayList<Account>());
    }

    @Override
    public void getDevicesJob(int start, int num, DevicesHandler handler) {
        handler.onDevicesDownloadFinished(new ArrayList<Devices>());
    }

    @Override
    public void getNotificationsJob(int start, int num, NotificationsHandler handler) {
        System.out.println("Inside getNotificationsJob");
        handler.onNotificationsDownloadFinished(new ArrayList<Notification>());
    }

    @Override
    public void getDevicesRelatedToAccountJob(int accountId, int start, int num, DevicesHandler handler) {
        handler.onDevicesRelatedToAccountDownloadFinished(new ArrayList<Devices>());
    }

    @Override
    public void getNotificationRelatedToDeviceJob(int deviceId, int start, int num, NotificationsHandler handler) {
         handler.onNotificationsRelatedToDeviceDownloadFinished(new ArrayList<Notification>());
    }

    @Override
    public void getNotificationRelatedToAccountJob(int accountId, int start, int num, NotificationsHandler handler) {
        handler.onNotificationsRelatedToAccountDownloadFinished(new ArrayList<Notification>());
    }

    @Override
    public void getSpecificDeviceDataByIdJob(int deviceId, int start, int num, DeviceDataHandler handler) {
        handler.onDeviceDataRelatedToDeviceDownloadFinished(new ArrayList<DeviceData>());
    }

    public JSONObject parseToOneJsonObject(String jsonStr) throws JSONException {
        JSONObject jObj = null;
        jObj = new JSONObject(jsonStr);
        if (jObj == null)
            throw new JSONException("json allocation failed");
        return jObj;

    }


}

    /*public List<type> parseToJsonArray(String jsonStr,Class type) throws JSONException {
        /*JSONArray jArr = new JSONArray(jsonStr);
        if (jArr == null)
            throw new JSONException("json allocation failed");
        return jArr;
        type listType = new TypeToken<ArrayList<type>>() {}.getType();
        List<type> list = new Gson().fromJson(jsonStr, listType);
        return list;
*/
    public <T> List<T> parseToJsonArray(String jsonArray, Class<T> clazz) {
        Type typeOfT = TypeToken.getParameterized(List.class, clazz).getType();
        return new Gson().fromJson(jsonArray, typeOfT);
    }
    }


