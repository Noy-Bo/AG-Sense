package com.tsofen.agsenceapp.dataServices;

public enum ServicesName {

    Login("/User/Login"),
    AddToDb("/User/Add"),
    getAllAccounts("/User/AllAccounts"),
    getDevices("/Device/getDevices"),
    getAllDevices("/Device/AllDevices"),
    getNotifications("/Notifications/getNotifications"),
    getNotificationRelatedToDevice("/Notifications/NotificationRelatedToDevice"),
    getNotificationsRelatedToAccount("/Notifications/NotificationsRelatedToAccount"),
    getDeviceRelatedToAccount("/Device/getDeviceRelatedToAccount"),
    getSpecificDeviceDataById("/DeviceData/AllDevicesDataById"),
    getSpecificAccountByName("/User/SpecificAccountsByName"),
    getSpicificDeviceByname(""),
    getFaultyDevices("/Device/FaultyDevices"),
    getRecentLocationRelatedToDevice("/Device/recentLocations"),
    getSpicificDeviceByFilter(""),
    getHealthyDevices("/Device/HealthyDevices"),
    markNotificationAsRead("/Notifications/Readed"),
    getDeviceSetting(""),
    getDevicesByNameContaining("/DeviceByNameContaining"),
    getDeviceByName("/Device/DeviceByName"),
    getDeviceById("/Device/DeviceById"),
    getDeviceByType("/Device/DevicesByType"),
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

