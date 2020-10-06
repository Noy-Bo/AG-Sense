package com.Tsofen45.TCP_ServerTsofen45.Disconnected;

import java.util.HashSet;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.ArrayList;

public class NotifyMaulfunction implements Runnable  {

    private  String current_imei = null;
    final private Object lock = new Object();
    public  NotifyMaulfunction(String imei){
        current_imei = imei;
    }

    @Override
    public void run() {

        synchronized (lock) {
            HashSet<String> arr_ = ReportTimer.getImei_list().get(current_imei);
            arr_.remove(current_imei);
            ReportTimer.getLists_()[0].add(current_imei);
            ReportTimer.getImei_list().put(current_imei, ReportTimer.getLists_()[0]);
        }

    }
}
