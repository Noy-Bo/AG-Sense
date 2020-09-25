package com.tsofen.agsenceapp.smsServices;

import android.telephony.SmsManager;
import android.util.Log;

import com.tsofen.agsenceapp.activities.LoginActivity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

public class SmsMgr {
    private static SmsMgr smsMgr = null;
    private static  HashMap<String, TrackingSMS> tracking = new HashMap<>();
    private static  HashMap<String, TrackingSMS> speedingAlert = new HashMap<>();
    private static  HashMap<String, TrackingSMS> authorization = new HashMap<>();
    private static ArrayList<Response> trackingResponse =  new ArrayList<>();
    private static ArrayList<Response> speedingAlertResponse =  new ArrayList<>();
    private static ArrayList<Response> authorizationResponse =  new ArrayList<>();


    private SmsMgr() {
    }

    public static SmsMgr getInstance() {

        if (smsMgr == null) {
            smsMgr = new SmsMgr();
            trackingResponse = new ArrayList<Response>(
                    Arrays.asList(Response.ADD_ADMIN_NUM,
                            Response.RESET_PASS));
            speedingAlertResponse = new ArrayList<Response>(
                    Arrays.asList(Response.ADD_ADMIN_NUM,
                            Response.RESET_PASS));
            authorizationResponse = new ArrayList<Response>(
                    Arrays.asList(Response.SET_TIME_ZONE,
                            Response.RESET_PASS));
        }
        return smsMgr;
    }

    public void createTracker(String phoneNumber, ArrayList<Response> commands, settingType setting, OnAllSmsRecievedHandler handler ){
        switch (setting){
            case TRACKING:
                tracking.put(phoneNumber, new TrackingSMS(commands, phoneNumber, handler, settingType.TRACKING));
                break;
            case AUTHORIZATION:
                authorization.put(phoneNumber, new TrackingSMS(commands, phoneNumber, handler, settingType.AUTHORIZATION));
                break;
            case SPEEDING_ALERT:
                speedingAlert.put(phoneNumber, new TrackingSMS(commands, phoneNumber, handler, settingType.SPEEDING_ALERT));
                break;
            default:
                Log.d("ERROR", "ERROR");
        }

    }

    public enum settingType
    {
        SPEEDING_ALERT,
        AUTHORIZATION,
        TRACKING;
    }

    public enum Response
    {
        RESET_PASS("Reset password ok!"),
        DEFAULT_SETTING("Tracker will be back to default setting…"),
        TRACKER_RESTART("Tracker restarting…"),
        CHANGE_PASSWORD("Change password ok!"),
        G_SENSOR("Set g-sensor value ok!"),
        CANCEL_LIGHTS("Cancel lights ok!"),
        SET_MONITOR("Set monitor ok."),
        BACKUP_CONTROL("Backup control action!"),
        SET_ENGINE_OFF("Set engine off ok."),
        SET_ENGINE_ON("Set engine on ok."),
        SET_SPEEDING_ALARM("Set speeding alarm ok."),
        CANCEL_SPEEDING_ALARM("Cancel speeding alarm ok."),
        SER_GEO_FENCE("Set geo-fence alarm ok."),
        DELETE_GEO_FENCE("Delete geo-fence alarm ok."),
        ENABLE_SMS_TRACKING("Enable sms tracking ok!"),
        CANCEL_SMS_TRACKING("Cancel sms tracking ok!"),
        ADD_ADMIN_NUM("Add admin number ok."),
        SET_INTERVAL("Set interval ok."),
        SET_IP_PORT("Set ip and port ok."),
        SET_TIME_ZONE("Set time zone ok."),
        SET_APN("Set apn ok.");


        private String url;

        Response(String envUrl) {
            this.url = envUrl;
        }

        public String getUrl() {
            return url;
        }
        public static Response contains(String test) {

            for (Response response : Response.values()) {
                if (response.getUrl().equals(test)) {
                    return response;
                }
            }
            return null;
        }
    }

    public static HashMap<String, TrackingSMS> getTracking() {
        return tracking;
    }

    public static HashMap<String, TrackingSMS> getSpeedingAlert() {
        return speedingAlert;
    }

    public static HashMap<String, TrackingSMS> getAuthorization() {
        return authorization;
    }

    public static ArrayList<Response> getTrackingResponse() {
        return trackingResponse;
    }

    public static ArrayList<Response> getSpeedingAlertResponse() {
        return speedingAlertResponse;
    }

    public static ArrayList<Response> getAuthorizationResponse() {
        return authorizationResponse;
    }

    public static  void delteTrakcing (String phoneNumber, settingType type ){
        switch (type){
            case TRACKING:
                tracking.remove(phoneNumber);
                break;
            case AUTHORIZATION:
                authorization.remove(phoneNumber);
                break;
            case SPEEDING_ALERT:
                speedingAlert.remove(phoneNumber);
                break;
            default:
                Log.d("ERROR", "ERROR");
        }
    }
}
