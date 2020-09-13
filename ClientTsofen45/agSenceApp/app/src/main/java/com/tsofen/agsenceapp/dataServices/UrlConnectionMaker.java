package com.tsofen.agsenceapp.dataServices;

import com.tsofen.agsenceapp.entities.User;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;


public class UrlConnectionMaker {
    static String baseurl = "http://206.72.198.59:8080/ServerTsofen45v3";
    public static String ctreatUrl(ServicesName serviceName, Map<String, String> params) {
        String url = baseurl+serviceName.getServiceName()+"?";
        for(String key : params.keySet())
        {
            url+=key+"="+params.get(key)+"&";
        }
        return url;
    }
}