package com.tsofen.agsenceapp.dataServices;

import com.tsofen.agsenceapp.entities.User;

import android.os.Bundle;


enum opcodes {
    Login, getAccounts, getDevices, getNotifications, getNotificationRelatedToDevice,
    getNotificationsRelatedToUser, getDeviceRelatedToUser, getSpecificDeviceDataById, getSpecificAccountByName,
    getSpicificDeviceByname, getRecentLocationRelatedToDevice, getSpicificDeviceByFilter, markNotificationAsRead,
    GetDeviceSetting, setDeviceSettingAlertGeoForce, setDeviceSettingAuthorizedNumber, setDeviceSettingInterval
}

public class UrlConnectionMaker {
    String url = "http://206.72.198.59:8080/ServerTsofen45/";
    TextDownloader textDownloader = new TextDownloader();
    Bundle passResults = new Bundle();

    public UrlConnectionMaker() {
        super();
    }

    public Bundle getParameters(Bundle bundle) {
        int opCode = bundle.getInt(opCode);
        switch (opCode) :
        case opcodes.Login:  //login
        String userName = bundle.getString(userName);
        String password = bundle.getString(password);
        return (Login(userName, password));

        case opcodes.getAccounts:
        int start = bundle.getInt(start);
        int numberOfResults = bundle.getInt(num);
        return (getAccounts(start, numberOfResults));

        case opcodes.getDevices:
        case opcodes.getNotifications:
        case opcodes.getNotificationRelatedToDevice:
        case opcodes.getNotificationsRelatedToUser:
        case opcodes.getDeviceRelatedToUser:
        case opcodes.getSpecificDeviceDataById:
        case opcodes.getSpecificAccountByName:
        case opcodes.getSpicificDeviceByname:
        case opcodes.getRecentLocationRelatedToDevice:
        case opcodes.getSpicificDeviceByFilter:
        case opcodes.markNotificationAsRead:
        case opcodes.GetDeviceSetting:
        case opcodes.setDeviceSettingAlertGeoForce:
        case opcodes.setDeviceSettingAuthorizedNumber:
        case opcodes.setDeviceSettingInterval:
        default:
        break;
    }

}

    private Bundle Login(String userName, String password) {
        String str = String.format(url + "User/Login?username=" + "%s" + "&password=" + "%s", userName, password);
        String result = textDownloader.getText(str);
        System.out.println(result);
        Gson gson = new Gson();/// Gson is buggy !!!!!
        User user = gson.fromJson(result, User.class);
        passResults.putSerializable(user);
        return passResults;
    }

}
