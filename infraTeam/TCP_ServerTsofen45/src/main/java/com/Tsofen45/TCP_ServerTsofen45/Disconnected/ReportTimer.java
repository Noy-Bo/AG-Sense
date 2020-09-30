package com.Tsofen45.TCP_ServerTsofen45.Disconnected;

import com.Tsofen45.TCP_ServerTsofen45.Analyzation.Analyzer;
import com.Tsofen45.TCP_ServerTsofen45.Device.Device;
import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;
import com.Tsofen45.TCP_ServerTsofen45.Repos.DeviceRepository;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class ReportTimer extends Analyzer {
	
	
	
	
    private static ReportTimer report_instance = null;
    private static Timer timer_ = null;
    private static HashMap<String,NodeIndex> imei_list = null;
    private static ArrayList[] lists_ = null;
    static int mins = 2;
    @Autowired
    private DeviceRepository devicerRepo;

    public ReportTimer(){
        timer_ = new Timer();
        if(lists_ == null) {
            lists_ = new ArrayList[mins * 6 + 1];
            for (int i = 0; i < mins * 6 + 1; i++)
                lists_[i] = new ArrayList<String>();
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
                imei_list.put("" + imei, new NodeIndex(lists_[0], lists_[0].size() - 1));
            }
        }
    }
    public static HashMap<String, NodeIndex> getImei_list() {
        return imei_list;
    }

    public static void setImei_list(HashMap<String, NodeIndex> imei_list) {
        ReportTimer.imei_list = imei_list;
    }

    public static ArrayList[] getLists_() {
        return lists_;
    }

    public static void setLists_(ArrayList[] lists_) {
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
            lists_[0] = new ArrayList<String>();
        }
    }
    class ReportTask extends TimerTask{

        @Override
        public void run() {
            if(lists_[mins * 6].isEmpty() == false){
                try {
					report_disconnected();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
    }
    public void start_tasks(){
        timer_.schedule(new shiftTask(),10000,10000);
        timer_.schedule(new ReportTask(),11000,10000);
    }

    public static void  report_disconnected() throws IOException{
        //check if in the list[12] any devices and report back.
    	ArrayList<String> imeies = lists_[12];
    	JSONObject json = null;
    	json.put("minutes", mins);
    	for(String imei : imeies) {
			sendNotify(imei,13,json);
    	}
    	
    	
    }
	@Override
	public void Analyze(DeviceData d) throws IOException {
		// TODO Auto-generated method stub
		
	}
}
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
}