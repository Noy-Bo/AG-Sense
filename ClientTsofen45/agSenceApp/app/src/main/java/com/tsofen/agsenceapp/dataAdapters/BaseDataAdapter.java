package com.tsofen.agsenceapp.dataAdapters;

import com.tsofen.agsenceapp.BackgroundServices.CacheMgr;
import com.tsofen.agsenceapp.CacheManager;

public class BaseDataAdapter {
    static CacheMgr cacheManager;
    static
    {
        cacheManager = CacheMgr.getInstance();
        //cacheManager.start();
    }


}
