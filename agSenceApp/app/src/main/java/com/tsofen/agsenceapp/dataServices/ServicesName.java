package com.tsofen.agsenceapp.dataServices;

//for every service's list of paramerters you can check the swagger

public enum  ServicesName {
    // ??????????????????????????????????????????????????????????????????????????????????????? getAllDevices is duplicated !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    getAccountByName("/Account/AccountBy"),  // parameters :name
    editAccount("/Account/editAccount"),     // parameters :accountName
    editDevice("/Device/editDevice"),     // parameters : deviceIMEI, newPass, newPhoneNumber
    addAccount("/Account/Add"),              //// parameters:accountName
    getAllAccountsAccountController("/Account/AllAccounts"),  // parameters: num(int-number of accounts) ,start(int)
    getAllAccountsName("/Account/getAllAccountsName"),
    adminDashboardInfo("/Dashboard/AdminDashboardInfo"),  // parameters:id
    AddNewDevice("/Device/Add"),             // parameters: accountName string,devicePassword string ,imei integer,phoneNumber  string ,type string
    getDeviceById("/Device/DeviceById"),     //parameters: deviceId integer
    getAllDevices("/Device/AllDevices"),
    getDeviceByName("/Device/DeviceByName"),                      //parameters: name string
    geDevicesByNameContaining("/Device/DeviceByNameContaining"),  //parameters:name string, num integer ,start integer
    getDeviceByType("/Device/DevicesByType"),                      //parameters: type string
    getFaultyDevices("/Device/FaultyDevices"),
    getDeviceRelatedToAccount("/Device/getDeviceRelatedToAccount"),//  //parameters: id integer, num integer, start integer
    getDevices("/Device/getDevices"),          //parameters: num integer ,start integer
    getDeviceSAettings("/Device/GetDeviceSAettings"),    //parameters:deviceImei integer
    getHealthyDevices("/Device/HealthyDevices"),
    getRecentLocationRelatedToDevice("/Device/recentLocations"), //id integer, num integer, start integer
    setDeviceSettingAlertGeoForce("/Device/SetDeviceSettingAlertGeoForce"),
    setDeviceSettingAuthorizedNumber("/Device/SetDeviceSettingAuthorizedNumber"),
    setDeviceSettingInterval("/Device/SetDeviceSettingInterval"),
    getSpicificDeviceByFilter("/Device/SpicificDeviceByFilter"),
    getSmsInfo("/Device/getSMSInfo"),



    //getAllDevices("/DeviceData/AllDeviceData"),
    getAllDeviceDataById("/DeviceData/getDeviceDataById"),    //id integer


    AddNotification("/Notification/AddNotification"),         // code integer,imei integer
    getNotifications("/Notifications/getNotifications"),      //num integer,start integer
    getNotificationRelatedToDevice("/Notifications/NotificationRelatedToDevice"),    //id integer, num integer,start integer
    getNotificationsRelatedToAccount("/Notifications/NotificationsRelatedToAccount"), //id integer, num integer,start integer
    markNotificationAsRead("/Notifications/Readed"),        //accountIdList array[integer], notificationIdList array[integer]



    AddToDb("/User/addNewUser"),             // accountName string, email string,userType string, username string
    getAllAccounts("/User/AllAccounts"),     //num integer, start integer
    changePass("/User/changeUserPassword"),  //newPass string, userId integer
    Login("/User/Login"),                   //password string, username string
    setPass("/User/setPassword"),           //userId string, userId integer
    getSpecificAccountsByName("/User/SpecificAccountsByName"),      // name string,num integer,start integer
    usernameForgetPassword("/User/ForgotPassword"), // email string, phonenumber string


    //Not Created by Server it, change when it is done;
ConfirmCode("/Verification/ConfirmCode"), //username String, code String
    ConfirmPassword("/Verification/ResetPassword"),    //username String, password String
    EmailPicked("/Verification/VerificationMethod"), //username String , method as email String
PhonePicked("/Verification/VerificationMethod"); // username String, method as phone String
   // getSpecificDeviceDataById("/DeviceData/getDeviceDataById"),
   //getSpicificDeviceByname(""),
    //getDeviceSetting(""),


    String service;

    ServicesName(String service) {
        this.service = service;
    }


    public String getServiceName() {
        return this.service;
    }
}