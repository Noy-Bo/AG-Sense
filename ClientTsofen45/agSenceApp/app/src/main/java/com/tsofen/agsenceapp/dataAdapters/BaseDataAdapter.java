package com.tsofen.agsenceapp.dataAdapters;

import com.tsofen.agsenceapp.CacheManager;

public class BaseDataAdapter {
    static CacheManager cacheManager;
    static
    {
        cacheManager = CacheManager.getInstance();
        cacheManager.start();
    }


}
