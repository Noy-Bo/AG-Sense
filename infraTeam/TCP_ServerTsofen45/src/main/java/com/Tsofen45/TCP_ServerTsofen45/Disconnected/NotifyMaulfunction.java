package com.Tsofen45.TCP_ServerTsofen45.Disconnected;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.ArrayList;

public class NotifyMaulfunction implements Runnable  {
    private static String current_imei = null;
    private ReentrantReadWriteLock  lock;
    public  NotifyMaulfunction(String imei){
        current_imei = imei;

    }

    @Override
    public void run() {
    	    
    	try
    	{
    		this.lock.writeLock().lock();
            ArrayList<String> arr_ = ReportTimer.getImei_list().get(current_imei).getListHead();
            int index_todelete = ReportTimer.getImei_list().get(current_imei).getIndex();
            String curr_node = arr_.get(ReportTimer.getImei_list().get(current_imei).getIndex());
            arr_.remove(index_todelete);
            ReportTimer.getLists_()[0].add(curr_node);
            ReportTimer.getImei_list().put(current_imei,new NodeIndex(ReportTimer.getLists_()[0],ReportTimer.getLists_()[0].size()-1));
    	}
    	finally
    	{
    		this.lock.writeLock().unlock();
    	}
    }
}
