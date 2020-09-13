package com.tsofen.agsenceapp.dataServices;

public enum ServicesName {
    Login("/User/Login"),
    getAccounts("/User/AllAccounts"),
    getDevices(""), getNotifications(""),
    getNotificationRelatedToDevice(""),
    getNotificationsRelatedToAccount("/Notifications/NotificationsRelatedToAccount"),
    getDeviceRelatedToAccount("/Device/getDeviceRelatedToAccount"),
    getNotificationsRelatedToUser(""),
    getDeviceRelatedToUser(""),
    getSpecificDeviceDataById(""),
    getSpecificAccountByName(""),
    getSpicificDeviceByname(""),
    getRecentLocationRelatedToDevice(""),
    getSpicificDeviceByFilter(""),
    markNotificationAsRead(""),
    GetDeviceSetting(""),
    setDeviceSettingAlertGeoForce(""),
    setDeviceSettingAuthorizedNumber(""),
    setDeviceSettingInterval("");

    String service;
    ServicesName(String service)
    {
        this.service = service;
    }

    public String getServiceName()
    {
        return this.service;
    }
}

