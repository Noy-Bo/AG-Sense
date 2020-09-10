package com.Tsofen45.TCP_ServerTsofen45.Authentication;

import java.util.HashSet;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Authenticate {
	
	
	//TODO need to add imies at the beggining
	HashSet<String> Imeis;
    public boolean check_imei(String imei)
    {
        //TODO connect to the database and check with the device table not the deviceData
        return true;
    }
}
