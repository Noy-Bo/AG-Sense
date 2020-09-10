package com.tsofen.agsenceapp.dataServices;

import android.os.Bundle;





public class UrlConnectionMaker {
    String url = "http://206.72.198.59:8080/ServerTsofen45/";
    TextDownloader textDownloader ;
    Bundle passResults ;


    public UrlConnectionMaker() {
        super();
        textDownloader =  new TextDownloader();
         passResults = new Bundle();

    }

    public Bundle getParameters(Bundle bundle) {
    /*    int opCode = bundle.getInt("opCode");
        switch (opCode) {
        case Opcodes.Login.ordinal():  //login
        String userName = bundle.getString(userName);
        String password = bundle.getString(password);
        return (Login(userName, password));

        case Opcodes.getAccounts.ordinal():

        int start = bundle.getInt(start);
        int numberOfResults = bundle.getInt(num);
        return (getAccounts(start, numberOfResults));

        case Opcodes.getDevices.ordinal():
        case Opcodes.getNotifications.ordinal():
        case Opcode.getNotificationRelatedToDevice.ordinal():
        case Opcode.getNotificationsRelatedToUser.ordinal():
        case Opcode.getDeviceRelatedToUser.ordinal():
        case Opcode.getSpecificDeviceDataById.ordinal():
        case Opcode.getSpecificAccountByName.ordinal():
        case Opcode.getSpicificDeviceByname.ordinal():
        case Opcode.getRecentLocationRelatedToDevice.ordinal():
        case Opcode.getSpicificDeviceByFilter.ordinal():
        case Opcode.markNotificationAsRead.ordinal():
        case Opcode.GetDeviceSetting.ordinal():
        case Opcode.setDeviceSettingAlertGeoForce.ordinal():
        case Opcode.setDeviceSettingAuthorizedNumber.ordinal():
        case Opcode.setDeviceSettingInterval.ordinal():

        default:
        break;*/
    return null;
    }



    private Bundle Login(String userName, String password) {
        String str = String.format(url + "User/Login?username=" + "%s" + "&password=" + "%s", userName, password);
        String result = textDownloader.getText(str);
        System.out.println(result);
        //Gson gson = new Gson();/// Gson is buggy !!!!!
        //User user = gson.fromJson(result, User.class);
        //passResults.putSerializable(user);
        return passResults;
    }


}
