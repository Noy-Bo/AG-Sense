package com.tsofen.agsenceapp.dataServices;

import java.util.Map;


public class UrlConnectionMaker {

    //http://206.72.198.59:8080/ServerTsofen45/User/Login?password=123123&userName=admin&
    //http://206.72.198.59:8080/ServerTsofen45/User/Login?password=123123&username=admin


    static String baseurl = "http://87.71.39.211:8080";


    public static String createUrl(ServicesName serviceName, Map<String, String> params) {
        String url = baseurl+serviceName.getServiceName()+"?";
        for(String key : params.keySet())
        {
            url+=key+"="+params.get(key)+"&";
        }
        return url;
    }
}