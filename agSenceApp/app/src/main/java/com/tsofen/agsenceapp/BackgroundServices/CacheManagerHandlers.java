package com.tsofen.agsenceapp.BackgroundServices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.tsofen.agsenceapp.activities.AppBaseActivity;
import com.tsofen.agsenceapp.dataServices.AccountDevicesHandler;
import com.tsofen.agsenceapp.dataServices.AccountNotificationsHandler;
import com.tsofen.agsenceapp.dataServices.AccountsHandler;
import com.tsofen.agsenceapp.dataServices.AdminDashboardInfoHandler;
import com.tsofen.agsenceapp.dataServices.BaseHandler;
import com.tsofen.agsenceapp.dataServices.CompaniesNameHandler;
import com.tsofen.agsenceapp.dataServices.DeviceDataHandler;
import com.tsofen.agsenceapp.dataServices.DeviceNotificationsHandler;
import com.tsofen.agsenceapp.dataServices.DeviceSmsInfoHandler;
import com.tsofen.agsenceapp.dataServices.DevicesHandler;
import com.tsofen.agsenceapp.dataServices.EditAccountHandler;
import com.tsofen.agsenceapp.dataServices.EditDeviceHandler;
import com.tsofen.agsenceapp.dataServices.LoginHandler;
import com.tsofen.agsenceapp.dataServices.MarkNotificationAsReadHandler;
import com.tsofen.agsenceapp.dataServices.NewCompanyHandler;
import com.tsofen.agsenceapp.dataServices.NewDeviceAddedHandler;
import com.tsofen.agsenceapp.dataServices.NewUserAddedHandler;
import com.tsofen.agsenceapp.dataServices.NotificationsHandler;
import com.tsofen.agsenceapp.dataServices.PasswordSetHandler;
import com.tsofen.agsenceapp.dataServices.UserPasswordChangeHandler;
import com.tsofen.agsenceapp.dataServices.VerificationCodeCheckHandler;
import com.tsofen.agsenceapp.dataServices.VerificationCodeSentHandler;
import com.tsofen.agsenceapp.entities.Account;
import com.tsofen.agsenceapp.entities.AccountCompany;
import com.tsofen.agsenceapp.entities.Admin;
import com.tsofen.agsenceapp.entities.DeviceData;
import com.tsofen.agsenceapp.entities.Devices;
import com.tsofen.agsenceapp.entities.Notification;
import com.tsofen.agsenceapp.entities.User;
import com.tsofen.agsenceapp.utils.AdminDashboardInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CacheManagerHandlers {
   static List retrievedEntitiesList = new ArrayList<>();


    // ==================================================================================
    // ----------------------------- main switch function -------------------------------
    // ==================================================================================


    /**
     * this function is called after TextDownloader finishes downloading text successfully.
     * the downloaded data is being parsed and entities are being created according to the handler type.
     * we reuturn entities \ parsed data to the data adapters handlers accordingly.
     * @param downloadedData downloaded data from TextDownloader
     * @param handler the data adapter handler, we will call this handler callback to return results.
     */
    public static void parseDataAndSendCallback(String downloadedData, BaseHandler handler)  {

        if (handler instanceof LoginHandler)
        {
            try {
                //parsing json
                JSONObject userJSON = new JSONObject(downloadedData);
                User user;

                if(userJSON.getString("type").equals("Account"))
                {
                    user = new Account(userJSON.getInt("id"), userJSON.getString("username")   , userJSON.getString("email"),false, userJSON.getInt("accountid"));
                    ((LoginHandler)handler).onLoginSuccess(user);
                }
                else if(userJSON.getString("type").equals("admin"))
                {
                    user = new Admin(userJSON.getInt("id"), userJSON.getString("username"), userJSON.getString("email"));
                    ((LoginHandler)handler).onLoginSuccess(user);
                }
                else
                    throw new Exception();
            }
            catch (Exception e)
            {
                ((LoginHandler)handler).onLoginFailure();
            }
        }

        else if (handler instanceof DevicesHandler)
        {

            retrievedEntitiesList = parseToJsonArray(downloadedData, new Devices());
           CacheMgr.getInstance().setDevices((List<Devices>)retrievedEntitiesList);
            ((DevicesHandler) handler).onDevicesDownloadFinished((List<Devices>) retrievedEntitiesList);


        }

        else if (handler instanceof AccountDevicesHandler)
        {

            retrievedEntitiesList = parseToJsonArray(downloadedData, new Devices());
            if (AppBaseActivity.getUser() instanceof Account)
            {
                CacheMgr.getInstance().setDevices((List<Devices>)retrievedEntitiesList);
            }
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
            CacheMgr.getInstance().setAccounts((List<Account>)retrievedEntitiesList);
            ((AccountsHandler)handler).onAccountsDownloadFinished((List<Account>) retrievedEntitiesList);
        }

        else if (handler instanceof AccountNotificationsHandler)
        {
            retrievedEntitiesList = parseToJsonArray(downloadedData, new Notification());
            ((AccountNotificationsHandler)handler).onNotificationsRelatedToAccountDownloadFinished((List<Notification>) retrievedEntitiesList);
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

        else if (handler instanceof EditDeviceHandler)
        {
            Boolean result = Boolean.valueOf(downloadedData);
            ((EditDeviceHandler) handler).onDeviceEditedFinished(result);
        }

        else if (handler instanceof NewCompanyHandler)
        {
            Boolean result = Boolean.valueOf(downloadedData);
            ((NewCompanyHandler) handler).onNewCompanyAddedFinished(result);
        }

        else if (handler instanceof NewUserAddedHandler)
        {
            Boolean result = Boolean.valueOf(downloadedData);
            ((NewUserAddedHandler) handler).onNewUserAddedFinished(result);
        }

        else if (handler instanceof CompaniesNameHandler)
        {
            retrievedEntitiesList = parseToJsonArray(downloadedData, new AccountCompany());
            ((CompaniesNameHandler) handler).onCompaniesNameReady((List<AccountCompany>) retrievedEntitiesList);
        }

        else if (handler instanceof NewDeviceAddedHandler)
        {
            Boolean result = Boolean.valueOf(downloadedData);
            ((NewDeviceAddedHandler) handler).onNewDeviceAddedFinished(result);
        }

        else if (handler instanceof NewDeviceAddedHandler)
        {
            Boolean result = Boolean.valueOf(downloadedData);
            ((NewDeviceAddedHandler) handler).onNewDeviceAddedFinished(result);
        }

        else if (handler instanceof PasswordSetHandler)
        {
            Boolean result = Boolean.valueOf(downloadedData);
            ((PasswordSetHandler) handler).onPasswordSetFinished(result);
        }

        else if (handler instanceof EditAccountHandler)
        {
            Boolean result = Boolean.valueOf(downloadedData);
            ((EditAccountHandler) handler).onAccountEditedFinished(result);
        }

        else if (handler instanceof EditDeviceHandler)
        {
            Boolean result = Boolean.valueOf(downloadedData);
            ((EditDeviceHandler) handler).onDeviceEditedFinished(result);
        }

        else if (handler instanceof UserPasswordChangeHandler)
        {
            Boolean result = Boolean.valueOf(downloadedData);
            ((UserPasswordChangeHandler) handler).onUserPasswordChangedFinished(result);
        }

        else if (handler instanceof VerificationCodeSentHandler)
        {
            ((VerificationCodeSentHandler) handler).onVerificationCodeSent();
        }

        else if (handler instanceof VerificationCodeCheckHandler)
        {
            Boolean result = Boolean.valueOf(downloadedData);
            ((VerificationCodeCheckHandler) handler).onVerificationCodeFinished(result);
        }

        else if (handler instanceof AdminDashboardInfoHandler)
        {
//            retrievedEntitiesList = parseToJsonArray(downloadedData, new AdminDashboardInfo());
            AdminDashboardInfo dashboardInfo = (new GsonBuilder()).create().fromJson(downloadedData, AdminDashboardInfo.class);
            ((AdminDashboardInfoHandler) handler).onAdminDashboardInfoReceived(dashboardInfo);
        }

        else if (handler instanceof MarkNotificationAsReadHandler)
        {
            Boolean result = Boolean.valueOf(downloadedData);
            ((MarkNotificationAsReadHandler) handler).onNotificationMarkedAsRead(result);
        }

        else if (handler instanceof DeviceSmsInfoHandler)
        {
            if (downloadedData == null)
                ((DeviceSmsInfoHandler) handler).onDeviceSmsInfoReceived("");

           JSONObject downloadedDataJSON = parseToOneJsonObject(downloadedData);
           String devicePasswordAndPhoneNumber = "";
           try{
               devicePasswordAndPhoneNumber += downloadedDataJSON.getString("password");
               devicePasswordAndPhoneNumber += ",";
               devicePasswordAndPhoneNumber += downloadedDataJSON.getString("PhoneNumber");
           }
           catch (JSONException e)
           {
               e.printStackTrace();
           }


           ((DeviceSmsInfoHandler) handler).onDeviceSmsInfoReceived(devicePasswordAndPhoneNumber);
        }

    }

    // ==================================================================================
    // ------------------------------- JSON Parsers -------------------------------------
    // ==================================================================================

    public static JSONObject parseToOneJsonObject(String jsonStr)  {
        JSONObject jObj = null;
        try {
            jObj = new JSONObject(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jObj == null)
            return new JSONObject();
        return jObj;

    }

    public static <T> List<T> parseToJsonArray(String jsonArray, Object clazz) {
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
