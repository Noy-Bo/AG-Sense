package com.tsofen.agsenceapp.smsServices;

import android.telephony.SmsManager;
import android.util.Log;

import com.tsofen.agsenceapp.activities.LoginActivity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * this class is responsible for all SMS related management, is has an array of trackers that track
 * SMS coming from devices.
 *
 * it contains all SMS that can be received from device
 */
public class SmsMgr {
    private static SmsMgr smsMgr = null;
    public static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 0; // sms permission.

    // each type of settings has its own HashMap so that we can tracks which device sends SMS and
    // for what purpose
    private  static HashMap<String, TrackingSMS> tracking = new HashMap<>();
    private  static HashMap<String, TrackingSMS> speedingAlert = new HashMap<>();
    private static  HashMap<String, TrackingSMS> authorization = new HashMap<>();

    // for each command we need to create an array to contain what SMs we want to receive in response
    // from device
    private static ArrayList<Response> trackingResponse;
    private static ArrayList<Response> speedingAlertResponse;
    private static ArrayList<Response> authorizationResponse;


    private  SmsMgr() {
        //here we define what SMS we want to receive for each kind of command
            trackingResponse = new ArrayList<Response>(
                    Arrays.asList(Response.SET_INTERVAL));
            speedingAlertResponse = new ArrayList<Response>(
                    Arrays.asList(Response.SET_SPEEDING_ALARM,
                            Response.SET_GEO_FENCE));
            authorizationResponse = new ArrayList<Response>(
                    Arrays.asList(Response.ADD_ADMIN_NUM));
    }

    public static SmsMgr getInstance() {
        if (smsMgr == null) {
        return smsMgr= new SmsMgr();
        }
        return smsMgr;
    }

    /**
     * this function create a  tracker in correlation to type of command we send.
     *
     * @param phoneNumber phone number of device
     * @param commands   List of command from Response enum that we want to receive from device
     * @param setting   type of setting settingType enum
     * @param handler   a handler for when all SMS are received
     */
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

    /**
     * types of setting change we can make to device
     */
    public enum settingType
    {
        SPEEDING_ALERT,
        AUTHORIZATION,
        TRACKING;
    }

    /**
     * all the possible conformation responses from device according to SMS COMMAND V1.22 [Pretrace Technology],
     * document is located in \Documentation\Client Side\SMS Management Feature\Pretrace SMS Command-V1.22.pdf
     */
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
        SET_INTERVAL("Set interval ok"),
        SET_INTERVAL_SECOND("Set interval ok."),
        SET_IP_PORT("Set ip and port ok."),
        SET_TIME_ZONE("Set time zone ok."),
        SET_APN("Set apn ok."),
        SET_GEO_FENCE("Set geo fence alarm ok.");


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

    public enum SendFormat
    {
        SET_GEO_FENCE("pw%s,fence=%sn,%se,%sn,%se"),
        //speeding and geo fence setting
        //pw******,fence=latitude1,logitude1,latitude2,logitude2
        //pw123456,fence = 22.123455n,114.235615e,22.125612n,114.236845e
        SET_SPEEDING_ALARM("pw%s, speed=%s"),
        //speeding and geo fence setting
        //pw******, speed=speed value
        //pw123456,speed=100
        ADD_ADMIN_NUM("pw%s,admin=+%s"),
        //authorization number setting
        //pw******,admin=+/-phone number
        //pw123456,admin=+13356785239
        SET_INTERVAL("pw%s,interval=%ss,%ss,%sm,%sd");
        //tracking setting
        //pw******,interval= time interval 1(s), time interval 2(s),distance interval(m), heading change interval(d)
        //pw123456,interval=30s,600s,0m,0d


        private String url;

        SendFormat(String envUrl) {
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

    public static  HashMap<String, TrackingSMS> getTracking() {
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

    public  static ArrayList<Response> getSpeedingAlertResponse() {
        return speedingAlertResponse;
    }

    public static ArrayList<Response> getAuthorizationResponse() {
        return authorizationResponse;
    }


    /**
     * after a tracker has received all its messages it is deleted from the HashMap
     * @param phoneNumber   phone number of device
     * @param type          type of setting that tracks was assigned to
     */
    public static  void deleteTracker(String phoneNumber, settingType type ){
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
