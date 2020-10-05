package com.Tsofen45.TCP_ServerTsofen45.Disconnected;

import com.Tsofen45.TCP_ServerTsofen45.Device.Device;
import com.Tsofen45.TCP_ServerTsofen45.Repos.DeviceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class ReportTimer {
    private static ReportTimer report_instance = null;
    private static Timer timer_ = null;
    private static HashMap<String,HashSet> imei_list = null;
    private static HashSet[] lists_ = null;
    int mins = 2;
    @Autowired
    private DeviceRepository devicerRepo;

    public ReportTimer(){
        timer_ = new Timer();
        if(lists_ == null) {
            lists_ = new HashSet[mins * 6 + 1];
            for (int i = 0; i < mins * 6 + 1; i++)
                lists_[i] = new HashSet<String>();
            imei_list = new HashMap<>();
        }
    }
    public  void load_imei(){
        Iterable<Device> imeies = devicerRepo.findAll();
        for(Device device : imeies)
        {
            long imei = device.getImei();
            if(imei_list.get(""+ imei) == null) {
                lists_[0].add("" + imei);
                imei_list.put("" + imei,lists_[0]);
            }
        }
    }
    public static HashMap<String, HashSet> getImei_list() {
        return imei_list;
    }

    public static void setImei_list(HashMap<String, HashSet> imei_list) {
        ReportTimer.imei_list = imei_list;
    }

    public static HashSet[] getLists_() {
        return lists_;
    }

    public static void setLists_(HashSet[] lists_) {
        ReportTimer.lists_ = lists_;
    }

    public static ReportTimer getInstance(){
        if(report_instance == null)
            report_instance = new ReportTimer();
        return report_instance;
    }

    class shiftTask extends TimerTask {

        @Override
        public void run() {
            // shift lists heads forward
            for (int i = 0 ; i < mins * 6  ; i++){
                lists_[mins * 6 - i] = lists_[mins * 6 - i - 1];
            }
            lists_[0] = new HashSet<String>();
            new Thread(new NotifyMaulfunction("864403044133669")).start();
            new Thread(new NotifyMaulfunction("866854035804858")).start();
            new Thread(new NotifyMaulfunction("864403044179308")).start();

        }
    }
    class ReportTask extends TimerTask{

        @Override
        public void run() {
            if(lists_[mins * 6].isEmpty() == false){
                report_disconnected();
            }
        }
    }
    public void start_tasks(){
        timer_.schedule(new shiftTask(),10000,10000);
        timer_.schedule(new ReportTask(),11000,10000);
    }

    public static void  report_disconnected(){
        //check if in the list[12] any devices and report back.
    }
}
/*
class NodeIndex{
    private ArrayList<String> ListHead = null;
    private int index = -1;
    public NodeIndex(ArrayList<String> list_,int index_){
        ListHead = list_;
        index = index_;
    }

    public ArrayList<String> getListHead() {
        return ListHead;
    }

    public void setListHead(ArrayList<String> listHead) {
        ListHead = listHead;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}*/
