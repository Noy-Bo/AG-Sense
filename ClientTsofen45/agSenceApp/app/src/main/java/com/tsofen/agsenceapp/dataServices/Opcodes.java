package com.tsofen.agsenceapp.dataServices;

public enum Opcodes {
    Login, getAccounts, getDevices, getNotifications, getNotificationRelatedToDevice,
    getNotificationsRelatedToUser, getDeviceRelatedToUser, getSpecificDeviceDataById, getSpecificAccountByName,
    getSpicificDeviceByname, getRecentLocationRelatedToDevice, getSpicificDeviceByFilter, markNotificationAsRead,
    GetDeviceSetting, setDeviceSettingAlertGeoForce, setDeviceSettingAuthorizedNumber, setDeviceSettingInterval
}
