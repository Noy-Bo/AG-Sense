package com.tsofen.agsenceapp.BackgroundServices;

import static android.os.SystemClock.sleep;

public class WaitPeriod implements Runnable {
    static CacheMgr cacheMgr = CacheMgr.getInstance();
    @Override
    public void run() {
        sleep(2000);
        cacheMgr.serverPeriodicJob();
    }
}
