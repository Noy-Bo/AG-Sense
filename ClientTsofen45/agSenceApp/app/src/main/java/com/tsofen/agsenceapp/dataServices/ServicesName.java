package com.tsofen.agsenceapp.dataServices;

public enum ServicesName {
    login("/User/Login"),
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
    getDeviceSetting(""),
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

