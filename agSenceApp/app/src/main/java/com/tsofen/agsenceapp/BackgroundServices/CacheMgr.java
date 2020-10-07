package com.tsofen.agsenceapp.BackgroundServices;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.util.Log;
import com.tsofen.agsenceapp.activities.AppBaseActivity;
import com.tsofen.agsenceapp.dataServices.AccountDevicesHandler;
import com.tsofen.agsenceapp.dataServices.AdminDashboardInfoHandler;
import com.tsofen.agsenceapp.dataServices.BaseHandler;
import com.tsofen.agsenceapp.dataServices.AccountNotificationsHandler;
import com.tsofen.agsenceapp.dataServices.AccountsHandler;
import com.tsofen.agsenceapp.dataServices.CompaniesNameHandler;
import com.tsofen.agsenceapp.dataServices.DeviceDataHandler;
import com.tsofen.agsenceapp.dataServices.DeviceNotificationsHandler;
import com.tsofen.agsenceapp.dataServices.EditAccountHandler;
import com.tsofen.agsenceapp.dataServices.EditDeviceHandler;
import com.tsofen.agsenceapp.dataServices.MarkNotificationAsReadHandler;
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
import com.tsofen.agsenceapp.dataServices.UserPasswordChangeHandler;
import com.tsofen.agsenceapp.dataServices.VerificationCodeCheckHandler;
import com.tsofen.agsenceapp.dataServices.VerificationCodeSentHandler;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.Notification;
import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.Devices;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheMgr implements CacheManagerAPI {

    private static CacheMgr cacheMgr=null;
    private List<Notification> notifications;
    private List<Account> accounts;
    private List<Devices> devices;

    /**
     * this waitInterval sets the interval time to request new devices data from server.
     * every waitInterval/1000 we will make a background request for new devices.
     * for further info read Periodic Admin
     */
    private final static int getDevicesPeriodicWaitInterval = 60000; // millis.

    /**
     * this is part of the periodicThread mechanism. do not change.
     * for more info look at handlerForRepeatedGetDevicesJob.
     */
    private boolean stopGetDevicesPeriodic = false;

    /**
     * minimum interval time for requesting new devices. - if our devices timestamp is older then this parameter, we will request new data.
     * otherwise we will deny the request and return current data.
     */
    private  int intervalBetweenServerRequestsForDevices = 5000; // millis.

    /**
     * minimum interval time for requesting new accounts. - if our accounts timestamp is older then this parameter, we will request new data.
     * otherwise we will deny the request and return current data.
     */
    private  int intervalBetweenServerRequestsForAccounts = 5000; // millis.

    private Timestamp devicesTimeStamp;
    private Timestamp accountsTimeStamp ;

    private HandlerThread handlerThreadForGetDevicesPeriodic = new HandlerThread("serverPeriodicJobHandler");
    private Handler threadHandlerForGetDevicesPeriodic;



    private TextDownloader downloader = TextDownloader.getInstance();

    // ==================================================================================
    // --------------------------- CTOR, Getters / Setters ------------------------------
    // ==================================================================================


    private CacheMgr() {
        initializeAllServicesAndParameters();
        devices = new ArrayList<>();
        accounts = new ArrayList<>();
        notifications = new ArrayList<>();
    }

    /**
     * synchronized getInstance, singleton class.
     * @return returns the singleton CacheManager class
     */
    public static CacheMgr getInstance() {
        if (cacheMgr == null)
        {
            synchronized (CacheMgr.class)
            {
                if (cacheMgr == null)
                {
                    cacheMgr = new CacheMgr();
                }
            }
        }
        return cacheMgr;
    }

    //getters \ setters

    public List<Notification> getNotifications() { return notifications; }
    public  void setNotifications(List<Notification> notifications) { this.notifications = notifications; }// public setter for firebase notification onReceive
    public void setStopGetDevicesPeriodic(boolean stopGetDevicesPeriodic) { this.stopGetDevicesPeriodic = stopGetDevicesPeriodic; }
    public List<Account> getAccounts() { return accounts; }
    public void setAccounts(List<Account> accounts) { this.accounts = accounts; }
    public List<Devices> getDevices() { return devices; }
    public void setDevices(List<Devices> devices) { this.devices = devices; }

    // periodic GetDevices configs

    public HandlerThread getHandlerThreadForGetDevicesPeriodic(){ return handlerThreadForGetDevicesPeriodic; }
    public Handler getThreadHandlerForGetDevicesPeriodic() { return threadHandlerForGetDevicesPeriodic; }


    /**
     * this function initialises:
     * looper and handler for periodic thread.
     * setting timestamps to: current timestamp - matching interval.
     */
    private void initializeAllServicesAndParameters()
    {

        //initializing the handlers
        handlerThreadForGetDevicesPeriodic.start();
        threadHandlerForGetDevicesPeriodic = new Handler(handlerThreadForGetDevicesPeriodic.getLooper());

        devicesTimeStamp = new Timestamp(System.currentTimeMillis());
        devicesTimeStamp.setTime(System.currentTimeMillis() - intervalBetweenServerRequestsForDevices);

        accountsTimeStamp = new Timestamp(System.currentTimeMillis());
        accountsTimeStamp.setTime(System.currentTimeMillis() - intervalBetweenServerRequestsForAccounts);

    }

    // ==================================================================================
    // --------------------------- Admin's Periodic GetDevices --------------------------
    // ==================================================================================

    /**
     * this class is the runnable class that runs on the PeriodicTask.
     * the flow is similar to the "GenericAsyncServerRequest"
     * this is the runnable that responsible for requesting the server for devices periodicaly once every  getDevicesPeriodicWaitInterval(variable),
     *  parsing it, and invoking the handlerForRepeatedGetDevicesJob.
     * u must create this class according to its CTOR, it receives handler, hashmap with params to create URL and enum ServiceName.
     * via UrlConnectionMaker class, we crate the URL, send it to the TextDownloader class, to make the network request and download the data.
     * when the TextDownloader finishes it invokes a callback that signals that data is downloaded, the data is then parsed and a devices array is made
     * the array is then  set to be the new Devices Array of the cache.
     */
    private class AdminGetDevicesPeriodicRunnable implements Runnable {

        private BaseHandler handler;
        Map<String, String> params;
        ServicesName serviceName;

        public AdminGetDevicesPeriodicRunnable(BaseHandler handler, Map<String, String> params,ServicesName serviceName) {
            this.serviceName = serviceName;
            this.handler = handler;
            this.params = params;
        }


        /**
         * via UrlConnectionMaker class, we crate the URL, send it to the TextDownloader class, to make the network request and download the data.
         * when the TextDownloader finishes it invokes a callback that signals that data is downloaded, the data is then parsed and a devices array is made
         * the array is then  set to be the new Devices Array of the cache.
         */
        @Override
        public void run() {

            if (isServerRequestDevices(devicesTimeStamp))
            {
                //creating the URL via UrlConnectionMaker class
                UrlConnectionMaker urlConnectionMaker = new UrlConnectionMaker();

                //downloading text via TextDownloader class
                TextDownloader.getInstance().getText(urlConnectionMaker.createUrl(serviceName, this.params), new OnDataReadyHandler() {
                    @Override
                    public void onDataDownloadCompleted(String downloadedData) {
                        Log.d("AdminPeriodic", "onDataDownloadCompleted");
                        // JSON Parser

                        List retrievedEntitiesList = new ArrayList<>();

                        if (handler instanceof DevicesHandler) {
                            retrievedEntitiesList = CacheManagerHandlers.parseToJsonArray(downloadedData, new Devices());
                            ((DevicesHandler) handler).onDevicesDownloadFinished((List<Devices>) retrievedEntitiesList);
                        }
                    }

                    @Override
                    public void onDownloadError() {
                        Log.d("AdminPeriodic", "onDownloadError");
                    }
                });
            }
            else
            {
                ((DevicesHandler) handler).onDevicesDownloadFinished(CacheMgr.getInstance().getDevices());
            }
        }
    }

    /**
     * this is part of the PeriodicTask. this handler is set to be the handler of the runnable.
     * this handler waits the getDevicesPeriodicWaitInterval after finishing the task. and then posts
     * a new runnable of the same kind. using this handler achieves the periodic task.
     */
    private DevicesHandler handlerForRepeatedGetDevicesJob  = new DevicesHandler() {
        @Override
        public void onDevicesDownloadFinished(List<Devices> devices) {
            Log.d("repeated","repeated task completed onDevicesDownloadFinished");
            setDevices(devices);
            SystemClock.sleep(getDevicesPeriodicWaitInterval);
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

    /**
     * this is the AyncTask that responsible for requesting the server for data, parsing it, and invoking handlers callback with the requested data.
     * u must create this class according to its CTOR, it receives handler, hashmap with params to create URL and enum ServiceName.
     * via UrlConnectionMaker class, we crate the URL, send it to the TextDownloader class, to make the network request and download the data.
     * when the TextDownloader finishes it invokes a callback that signals that data is downloaded, from we send the downloadedData to CacheManagerHandlers
     * there we detect the handler type, and parse the data accordingly, and invoke the handler's callback with the requested data.
     * @param <E> currently not used.
     */
    private class GenericAsyncServerRequest<E> extends AsyncTask<Void, Void, Void>
    {
        private BaseHandler handler;
        Map<String, String> params;
        ServicesName serviceName;

        public GenericAsyncServerRequest(BaseHandler handler, Map<String, String> params, ServicesName serviceName) {
            this.handler = handler;
            this.params = params;
            this.serviceName = serviceName;
        }


        /**
         * via UrlConnectionMaker class, we crate the URL, send it to the TextDownloader class, to make the network request and download the data.
         * when the TextDownloader finishes it invokes a callback that signals that data is downloaded, from we send the downloadedData to CacheManagerHandlers
         * there we detect the handler type, and parse the data accordingly, and invoke the handler's callback with the requested data.
         * @param voids we use class parameters here.
         * @return no ret val.
         */
        @Override
        protected Void doInBackground(Void... voids) {
            //creating the URL via UrlConnectionMaker class
            UrlConnectionMaker urlConnectionMaker = new UrlConnectionMaker();

            //downloading text via TextDownloader class
            downloader.getText(urlConnectionMaker.createUrl(serviceName, this.params), new OnDataReadyHandler() {
                @Override
                public void onDataDownloadCompleted(String downloadedData) {
                    Log.d("generics","onDataDownloadCompleted");

                    //sending downloaded data to parse and finish.
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
    // ----------------------- Jobs API for Data Adapters -------------------------------
    // ==================================================================================

    /* this section is the api we reveal to the data adapters layer. each method is constructed for a specific task. */
    /* every task has the same core flow logic - creating hashmap of params for URLConnection. and creating asyncTask for specific task. */


    /**
     * this task is a server request for login authentication.
     * @param username username.
     * @param password password.
     * @param handler LoginHandler, look at class to see its api.
     */
    @Override
    public void loginJob(final String username, final String password, final LoginHandler handler) {

        Map<String, String> params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
        GenericAsyncServerRequest<String> asyncGeneric = new GenericAsyncServerRequest<>(handler, params, ServicesName.Login);
        asyncGeneric.execute();
    }

    @Override
    public void getAccountsJob(int start, int num, AccountsHandler handler) {
        Log.d("testing stamps","on getAccountsJob");
        if (getAccounts().size() == 0)
        {
            Log.d("testing stamps","getting accounts from server");
            newTimeStamp(accountsTimeStamp);
            Map<String, String> params = new HashMap<>();
            params.put("num", Integer.toString(num));
            params.put("start", Integer.toString(start));
            GenericAsyncServerRequest<Devices> asyncGeneric = new GenericAsyncServerRequest<>(handler, params, ServicesName.getAllAccounts);
            asyncGeneric.execute();
            return;

        }
        Log.d("testing stamps","getting accounts from cache");
            handler.onAccountsDownloadFinished(getAccounts());

    }

    @Override
    public void getLatestAccountsJob(int start, int num, AccountsHandler handler) {
        Log.d("testing stamps","on getLatestAccountsJob");
        if (isServerRequestAccounts(accountsTimeStamp))
        {
            Log.d("testing stamps","getting accounts from server");
            Map<String, String> params = new HashMap<>();
            params.put("num", Integer.toString(num));
            params.put("start", Integer.toString(start));
            GenericAsyncServerRequest<Devices> asyncGeneric = new GenericAsyncServerRequest<>(handler, params, ServicesName.getAllAccounts);
            asyncGeneric.execute();
            return;

        }
        Log.d("testing stamps","on latest accounts going to getAccounts");
        getAccountsJob(start,num,handler);

    }

    @Override
    public void getDevicesJob(int start, int num, DevicesHandler handler) {
        Log.d("testing stamps","on getDevicesJob");
        if (getDevices().size() == 0)
        {
            Log.d("testing stamps","getting devices from server");
            newTimeStamp(devicesTimeStamp);
            Map<String, String> params = new HashMap<>();

            GenericAsyncServerRequest<Devices> asyncGeneric = new GenericAsyncServerRequest<>(handler, params, ServicesName.getAllDevices);
            asyncGeneric.execute();
            return;
        }
        Log.d("testing stamps","getting devices from cache");
        handler.onDevicesDownloadFinished(getDevices());


    }

    @Override
    public void getLatestDevicesJob(int start, int num, DevicesHandler handler) {
        Log.d("testing stamps","on getLatestDevicesJob");
        if (isServerRequestDevices(devicesTimeStamp))
        {
            Log.d("testing stamps","getting devices from server");
            Map<String, String> params = new HashMap<>();
            //params.put("num",Integer.toString(num));
            //params.put("start",Integer.toString(start));
            GenericAsyncServerRequest<Devices> asyncGeneric = new GenericAsyncServerRequest<>(handler, params, ServicesName.getAllDevices);
            asyncGeneric.execute();
            return;
        }
        Log.d("testing stamps","on latest going to getdevices");
        getDevicesJob(start,num,handler);


    }

    @Override
    public void getNotificationsJob(int start, int num, NotificationsHandler handler) {
        if (getNotifications().size() == 0)
        {
            Map<String, String> params = new HashMap<>();
            params.put("num", Integer.toString(num));
            params.put("start", Integer.toString(start));
            GenericAsyncServerRequest<Devices> asyncGeneric = new GenericAsyncServerRequest<>(handler, params, ServicesName.getNotifications);
            asyncGeneric.execute();
            return;
        }
        handler.onNotificationsDownloadFinished(getNotifications());

    }

    @Override
    public void getDevicesRelatedToAccountJob(int accountId, int start, int num, AccountDevicesHandler handler) {
        Log.d("testing stamps","on latest going to getDevicesRelatedToAccountJob");

        if (AppBaseActivity.getUser() instanceof Account)
        {
            if  ( getDevices().size() == 0)
            {
                newTimeStamp(accountsTimeStamp);
            }
            else if (isServerRequestAccounts(accountsTimeStamp) == false)
            {
                handler.onDevicesRelatedToAccountDownloadFinished(getDevices());
                return;
            }
        }
        Map<String, String> params = new HashMap<>();
        params.put("id", Integer.toString(accountId));
        params.put("num", Integer.toString(num));
        params.put("start", Integer.toString(start));
        GenericAsyncServerRequest<Devices> asyncGeneric = new GenericAsyncServerRequest<>(handler, params, ServicesName.getDeviceRelatedToAccount);
        asyncGeneric.execute();

//        if (AppBaseActivity.getUser() instanceof Admin)
//        {
//            Map<String, String> params = new HashMap<>();
//            params.put("id", Integer.toString(accountId));
//            params.put("num", Integer.toString(num));
//            params.put("start", Integer.toString(start));
//            GenericAsyncServerRequest<Devices> asyncGeneric = new GenericAsyncServerRequest<>(handler, params, ServicesName.getDeviceRelatedToAccount);
//            asyncGeneric.execute();
//        }
//        else if (AppBaseActivity.getUser() instanceof Account)
//        {
//            if  ( getDevices().size() == 0)
//            {
//                Log.d("testing stamps","getDevices().size() == 0");
//                newTimeStamp(accountsTimeStamp);
//                Map<String, String> params = new HashMap<>();
//                params.put("id", Integer.toString(accountId));
//                params.put("num", Integer.toString(num));
//                params.put("start", Integer.toString(start));
//                GenericAsyncServerRequest<Devices> asyncGeneric = new GenericAsyncServerRequest<>(handler, params, ServicesName.getDeviceRelatedToAccount);
//                asyncGeneric.execute();
//                return;
//            }
//            else if (isServerRequestAccounts(accountsTimeStamp))
//            {
//                Log.d("testing stamps","after timestamp - requesting SERVER for data");
//                Map<String, String> params = new HashMap<>();
//                params.put("id", Integer.toString(accountId));
//                params.put("num", Integer.toString(num));
//                params.put("start", Integer.toString(start));
//                GenericAsyncServerRequest<Devices> asyncGeneric = new GenericAsyncServerRequest<>(handler, params, ServicesName.getDeviceRelatedToAccount);
//                asyncGeneric.execute();
//                return;
//            }
//            Log.d("testing stamps","after timestamp - taking data from CACHE");
//            handler.onDevicesRelatedToAccountDownloadFinished(getDevices());
//        }
    }

    @Override
    public void getNotificationRelatedToDeviceJob(int deviceId, int start, int num, DeviceNotificationsHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("id",Integer.toString(deviceId));
        params.put("num",Integer.toString(num));
        params.put("start",Integer.toString(start));
        GenericAsyncServerRequest<Devices> asyncGeneric = new GenericAsyncServerRequest<>(handler,params,ServicesName.getNotificationRelatedToDevice);
        asyncGeneric.execute();
    }

    @Override
    public void getNotificationRelatedToAccountJob(int accountId, int start, int num, AccountNotificationsHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("id",Integer.toString(accountId));
        params.put("num",Integer.toString(num));
        params.put("start",Integer.toString(start));
        GenericAsyncServerRequest<Devices> asyncGeneric = new GenericAsyncServerRequest<>(handler,params,ServicesName.getNotificationsRelatedToAccount);
        asyncGeneric.execute();
    }

    @Override
    public void getSpecificDeviceDataByIdJob(int deviceId, DeviceDataHandler handler) {

        Map<String, String> params = new HashMap<>();
        params.put("id",Integer.toString(deviceId));
        GenericAsyncServerRequest<Devices> asyncGeneric = new GenericAsyncServerRequest<>(handler,params,ServicesName.getAllDeviceDataById);
        asyncGeneric.execute();

    }

    /**
     * this task  a server request for adding new user, we pass boolean to indicate success or failure on handler's callback.
     * @param username username
     * @param emailAddress email
     * @param userType admin\account
     * @param accountName account name
     * @param handler NewUserAddedHandler, look at class to see API.
     */
    @Override
    public void addNewUserJob(String username, String emailAddress, String userType, String accountName, NewUserAddedHandler handler) {

        Map<String, String> params = new HashMap<>();
        params.put("accountName",accountName);
        params.put("username",username);
        params.put("userType",userType);
        params.put("email",emailAddress);
        GenericAsyncServerRequest<Devices> asyncGeneric = new GenericAsyncServerRequest<>(handler,params,ServicesName.AddToDb);
        asyncGeneric.execute();

    }

    /**
     * this task  a server request for adding new company request to server, we pass boolean to indicate success or failure on handler's callback.
     * @param companyName company name
     * @param handler NewCompanyHandler look at class to see API
     */
    @Override
    public void addNewCompanyJob(String companyName, NewCompanyHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("accountName",companyName);
        GenericAsyncServerRequest<Devices> asyncGeneric = new GenericAsyncServerRequest<>(handler,params,ServicesName.addAccount);
        asyncGeneric.execute();
    }

    /**
     * this task is a server request to get all companies name, we return List<CompanyName> in handler's callback.
     * @param num to get all pass 0
     * @param start to get all pass 0
     * @param handler CompaniesNameHandler, look at class for API.
     */
    @Override
    public void getAllCompaniesNameJob(int num, int start, CompaniesNameHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("num",Integer.toString(num));
        params.put("start",Integer.toString(start));
        GenericAsyncServerRequest<Devices> asyncGeneric = new GenericAsyncServerRequest<>(handler,params,ServicesName.getAllAccountsAccountController);
        asyncGeneric.execute();
    }

    /**
     *  this task is  a server request for adding new device, making request to server with the new device data
     *  we pass boolean to indicate success or failure on handler's callback.
     * @param imei imei
     * @param deviceType look at types
     * @param deviceName device type
     * @param accountName account name
     * @param devicePhoneNumber phone number to communicate with the device
     * @param devicePassword device password
     * @param handler NewDeviceAddedHandler, look at class for API.
     */
    @Override
    public void addNewDeviceJob(Long imei, String deviceType, String deviceName, String accountName, String devicePhoneNumber, String devicePassword, NewDeviceAddedHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("accountName",accountName);
        params.put("devicePassword",devicePassword);
        params.put("imei",Long.toString(imei));
        params.put("phoneNumber",devicePhoneNumber);
        params.put("deviceName",deviceName);
        params.put("type",deviceType);
        GenericAsyncServerRequest<Devices> asyncGeneric = new GenericAsyncServerRequest<>(handler,params,ServicesName.AddNewDevice);
        asyncGeneric.execute();

    }

    /**
     * this task  a server request for changing password, we pass boolean to indicate success or failure on handler's callback.
     * @param userId user id
     * @param password password
     * @param handler PasswordSetHandler look at class for API.
     */
    @Override
    public void setPasswordJob(int userId, String password, PasswordSetHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("userId",Long.toString(userId));
        params.put("pass",password);
        GenericAsyncServerRequest<Devices> asyncGeneric = new GenericAsyncServerRequest<>(handler,params,ServicesName.setPass);
        asyncGeneric.execute();

    }

    /**
     *
     * @param prevName
     * @param newName
     * @param handler
     */
    @Override
    public void editAccountJob(String prevName, String newName, EditAccountHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("prevName",prevName);
        params.put("newName",newName);
        GenericAsyncServerRequest<Devices> asyncGeneric = new GenericAsyncServerRequest<>(handler,params,ServicesName.editAccount);
        asyncGeneric.execute();

    }

    @Override
    public void editDeviceJob(Long deviceIMEI, String newPhoneNumber, String newPass, EditDeviceHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("deviceIMEI",Long.toString(deviceIMEI));
        params.put("newPass",newPhoneNumber);
        params.put("newPhoneNumber",newPhoneNumber);
        GenericAsyncServerRequest<Devices> asyncGeneric = new GenericAsyncServerRequest<>(handler,params,ServicesName.editDevice);
        asyncGeneric.execute();
    }

    @Override
    public void changeUserPasswordJob(int userId, String newPass, UserPasswordChangeHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("newPass",newPass);
        params.put("userId",Integer.toString(userId));
        GenericAsyncServerRequest<Devices> asyncGeneric = new GenericAsyncServerRequest<>(handler,params,ServicesName.changePass);
        asyncGeneric.execute();

    }

    @Override
    public void getAdminDashboardInfoJob(int adminId, AdminDashboardInfoHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("id",Integer.toString(adminId));
        GenericAsyncServerRequest<Devices> asyncGeneric = new GenericAsyncServerRequest<>(handler,params,ServicesName.adminDashboardInfo);
        asyncGeneric.execute();
    }

    @Override
    public void markNotificationAsReadJob(int userId, int notificationId, MarkNotificationAsReadHandler handler) {
        Map<String, String> params = new HashMap<>();
        params.put("notificationId",Integer.toString(userId));
        params.put("notificationId",Integer.toString(notificationId));
        GenericAsyncServerRequest<Devices> asyncGeneric = new GenericAsyncServerRequest<>(handler,params,ServicesName.markNotificationAsRead);
        asyncGeneric.execute();
    }

    @Override
    public void sendVerificationCodeJob(String email, VerificationCodeSentHandler handler) {
        // NO URL FROM SERVER
    }

    @Override
    public void verifyCodeJob(String email, String verificationCode, VerificationCodeCheckHandler handler) {
        // NO URL FROM SERVER
    }


    // ==================================================================================
    // ------------------------- Clear Data, Time Stamps checks  ------------------------
    // ==================================================================================

    /**
     * sets a given TimeStamp to the current time in miilis
     * this function is synchronized for denying race condition over the timestamp.
     * @param timestamp TimeStamp Object.
     */
    public synchronized void newTimeStamp(Timestamp timestamp)
    {
        timestamp.setTime(System.currentTimeMillis());
    }

    /**
     * this function checks if the given stamp object is older then the current time by intervalBetweenServerRequestsForDevices
     * if so, we will make a new stamp, return true, and therefore signaling that Devices array in the cache is old and we need to replace it with
     * new data from server.
     * if the time between the given stamp and the current time is less then intervalBetweenServerRequestsForDevices, we return true to signal that
     * we have latest data and we do not need to request the server.
     * Note that this function is synchronized to prevent race condition over the stamp.
     * @param timeStamp TimeStamp object, pass devicesTimeStamp to check device's timestamp.
     * @return we return true to indicate that the data is old and we need to request new one, false otherwise.
     */
    public synchronized boolean isServerRequestDevices(Timestamp timeStamp)
    {
        if (timeStamp.getTime() + intervalBetweenServerRequestsForDevices < System.currentTimeMillis()) {
            timeStamp.setTime(System.currentTimeMillis());
            return true;
        }
        return false;
    }

    /**
     * this function checks if the given stamp object is older then the current time by intervalBetweenServerRequestsForAccount
     * if so, we will make a new stamp, return true, and therefore signaling that Accounts array in the cache is old and we need to replace it with
     * new data from server.
     * if the time between the given stamp and the current time is less then intervalBetweenServerRequestsForAccounts, we return true to signal that
     * we have latest data and we do not need to request the server.
     * Note that this function is synchronized to prevent race condition over the stamp.
     * @param timeStamp TimeStamp object, pass accountsTimeStamp to check account's timestamp.
     * @return we return true to indicate that the data is old and we need to request new one, false otherwise.
     */
    public synchronized boolean isServerRequestAccounts(Timestamp timeStamp)
    {

        if (timeStamp.getTime() + intervalBetweenServerRequestsForAccounts < System.currentTimeMillis()) {
            timeStamp.setTime(System.currentTimeMillis());
            return true;
        }
        return false;
    }


    public void clearCacheNotification(){notifications.clear();}

    /**
     * this function clears all data currently in cache.
     */
    public  void clearCache(){
        accounts.clear();
        devices.clear();
        notifications.clear();
    }

}

