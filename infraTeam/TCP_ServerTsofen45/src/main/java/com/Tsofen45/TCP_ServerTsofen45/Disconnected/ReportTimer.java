package com.Tsofen45.TCP_ServerTsofen45.Disconnected;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ReportTimer {
    private static ReportTimer report_instance = null;
    private static Timer timer_ = null;

    private ReportTimer(){
        timer_ = new Timer();

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
            NotifyMaulfunction.getLists_()[0] = new ArrayList<String>();
            for (int i = 0 ; i < 12 ; i++){
                NotifyMaulfunction.getLists_()[i+1] = NotifyMaulfunction.getLists_()[i];
            }
        }
    }
    class ReportTask extends TimerTask{

        @Override
        public void run() {
            if(NotifyMaulfunction.getLists_()[12].isEmpty() == false){
                report_disconnected();
            }
        }
    }
    public void start_tasks(){
        timer_.schedule(new shiftTask(),10000);
        timer_.schedule(new ReportTask(),11000);
    }
    public static void  report_disconnected(){
        //check if in the list[12] any devices and report back.
    }
}
