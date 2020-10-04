package com.tsofen.agsenceapp.BackgroundServices;

import androidx.constraintlayout.solver.Cache;

import com.google.gson.GsonBuilder;
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


    public static void parseDataAndSendCallback(String downloadedData, BaseHandler handler)
    {


        if (handler instanceof LoginHandler)
        {
            try {
                //parsing json
                JSONObject userJSON = new JSONObject(downloadedData);
                User user;

                if(userJSON.getString("type").equals("account"))
                {
                    user = new Account(userJSON.getInt("id"), userJSON.getString("username")   , userJSON.getString("email"),false, userJSON.getInt("accountid"));
                }
                else
                {
                    user = new Admin(userJSON.getInt("id"), userJSON.getString("username"), userJSON.getString("email"));
                }
                ((LoginHandler)handler).onLoginSuccess(user);
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
            // parse the StringList?
            ((CompaniesNameHandler) handler).onCompaniesNameReady(/* ADD RESULT OBJECT HERE*/null);
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
            retrievedEntitiesList = parseToJsonArray(downloadedData, new Notification());
            ((AdminDashboardInfoHandler) handler).onAdminDashboardInfoRecieved(/* ADD RESULT OBJECT HERE*/null);
        }
        else if (handler instanceof MarkNotificationAsReadHandler)
        {
            Boolean result = Boolean.valueOf(downloadedData);
            ((MarkNotificationAsReadHandler) handler).onNotificationMarkedAsRead(result);
        }


    }

    // ==================================================================================
    // ------------------------------- JSON Parsers -------------------------------------
    // ==================================================================================

    public static JSONObject parseToOneJsonObject(String jsonStr) throws JSONException {
        JSONObject jObj = null;
        jObj = new JSONObject(jsonStr);
        if (jObj == null)
            throw new JSONException("json allocation failed");
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
