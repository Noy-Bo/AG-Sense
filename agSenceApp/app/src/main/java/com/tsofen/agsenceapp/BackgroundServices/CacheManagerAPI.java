package com.tsofen.agsenceapp.BackgroundServices;

import com.tsofen.agsenceapp.dataServices.AccountDevicesHandler;
import com.tsofen.agsenceapp.dataServices.AccountNotificationsHandler;
import com.tsofen.agsenceapp.dataServices.AccountsHandler;
import com.tsofen.agsenceapp.dataServices.AdminDashboardInfoHandler;
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
import com.tsofen.agsenceapp.entities.Notification;
import com.tsofen.agsenceapp.utils.AdminDashboardInfo;

import java.util.ArrayList;

public interface CacheManagerAPI {
    /**
     *
     * @param username
     * @param password
     * @param handler
     */
    void loginJob(String username, String password, LoginHandler handler);

    // Admin Methods

    void getAccountsJob(int start, int num, AccountsHandler handler);
    void getDevicesJob(int start, int num, DevicesHandler handler);
    void getLatestDevicesJob(int start, int num, DevicesHandler handler);
    void getNotificationsJob(int start, int num, NotificationsHandler handler);
    void getDevicesRelatedToAccountJob(int accountId, int start, int num, AccountDevicesHandler handler);
    void getLatestAccountsJob(int start, int num, AccountsHandler handler);


    // Account methods
    void getNotificationRelatedToDeviceJob(int deviceId, int start, int num, DeviceNotificationsHandler handler);
    void getNotificationRelatedToAccountJob(int accountId, int start, int num, AccountNotificationsHandler handler);
    void getSpecificDeviceDataByIdJob(int deviceId,DeviceDataHandler handler);


    //v3 api
    void addNewUserJob(String username, String emailAddress, String userType, String accountName, NewUserAddedHandler handler);
    void addNewCompanyJob(String companyName, NewCompanyHandler handler);
    void getAllCompaniesNameJob(CompaniesNameHandler handler);
    void addNewDeviceJob(Long imei, String deviceType, String accountName, String devicePhoneNumber, String devicePassword, NewDeviceAddedHandler handler);
    void setPasswordJob(int userId, String password, PasswordSetHandler handler);
    void editAccountJob(String prevName, String newName, EditAccountHandler handler);
    void editDeviceJob(Long deviceIMEI, String newPhoneNumber, String newPass, EditDeviceHandler handler);
    void changeUserPasswordJob(int userId, String newPass, UserPasswordChangeHandler handler);
    void sendVerificationCodeJob(String email, VerificationCodeSentHandler handler);
    void verifyCodeJob(String email, String verificationCode, VerificationCodeCheckHandler handler);
    void getAdminDashboardInfoJob(int adminId, AdminDashboardInfoHandler handler);
    void markNotificationAsReadJob(int userId, int notificationId, MarkNotificationAsReadHandler handler);















}
