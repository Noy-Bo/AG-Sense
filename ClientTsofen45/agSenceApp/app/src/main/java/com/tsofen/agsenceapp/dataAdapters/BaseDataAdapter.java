package com.tsofen.agsenceapp.dataAdapters;

import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;

public class BaseDataAdapter {
    static CacheMgr cacheManager;
    static
    {
        cacheManager = CacheMgr.getInstance();
        //cacheManager.start();
    }


}
