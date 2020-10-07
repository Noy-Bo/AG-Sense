package com.tsofen.agsenceapp.smsServices;

import java.util.ArrayList;

public class TrackingSMS {
    private ArrayList<SmsMgr.Response> commands;
    private String deviceNumber;
    private OnAllSmsRecievedHandler handler;
    private SmsMgr.settingType type;

    public TrackingSMS(ArrayList<SmsMgr.Response> commands, String deviceNumber, OnAllSmsRecievedHandler handler,
                       SmsMgr.settingType type) {
        this.commands = commands;
        this.deviceNumber = deviceNumber;
        this.handler = handler;
        this.type =type;
    }


    /**
     * funtion responsible for updating SMS list waiting ti be received from device.
     *
     * @param command command to delete from list
     */
    void deleteCommand(SmsMgr.Response command){
        if(command==null){
            return;
        }
        if(commands.contains(command)){
            commands.remove(command);
            if(commands.isEmpty()){
                handler.onAllSmsReceivedHandler();
                SmsMgr.deleteTracker(deviceNumber, type);
            }
        }
    }
}
