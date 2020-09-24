package com.tsofen.agsenceapp.smsServices;

import java.util.List;
import java.util.ArrayList;

public class SmsMgr {
    private static SmsMgr smsMgr = null;
    private static List<String> responces = new ArrayList<>();
    private String phoneNumber;

    private SmsMgr() {
        responces.add("Reset password ok!");
        responces.add("Tracker will be back to default setting…");
        responces.add("Tracker restarting…");
        responces.add("Change password ok!");
        responces.add("Set g-sensor value ok!"); //???
        responces.add("Cancel lights ok!");   //???
        responces.add("Set monitor ok.");
        responces.add("Backup control action!");
        responces.add("Set engine off ok.");
        responces.add("Set engine on ok.");
        responces.add("Set speeding alarm ok.");
        responces.add("Cancel speeding alarm ok.");
        responces.add("Set geo-fence alarm ok.");
        responces.add("Delete geo-fence alarm ok.");
        responces.add("Enable sms tracking ok!");
        responces.add("Cancel sms tracking ok!");
        responces.add("Add admin number ok.");
        responces.add("Set interval ok.");    //there are 2 diffrent responces ...
        responces.add("Set interval ok");
        responces.add("Set ip and port ok.");
        responces.add("Set time zone ok.");
        responces.add("Set apn ok.");
       /* responces.add();                 //??    i've puted the responces that doesnt contain a specific qualifications of the device
        responces.add();                        // so for checking them we need something more complicated - flag for the command category and then parse the responce accordingly
        responces.add();
        responces.add();
        responces.add();*/
    }

    public static SmsMgr getInstance() {

        if (smsMgr == null) {
            smsMgr = new SmsMgr();
        }
        return smsMgr;
    }

    public boolean checkSms(String sms, String number) {
        boolean result = false;

        for (String responce : responces) {
            if (responce.equals(sms)) {
                result = true;
            }
        }
        return result;
    }
}
