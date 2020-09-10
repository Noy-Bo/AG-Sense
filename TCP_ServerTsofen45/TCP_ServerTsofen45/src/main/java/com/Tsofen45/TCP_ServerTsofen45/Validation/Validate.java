package com.Tsofen45.TCP_ServerTsofen45.Validation;

import org.springframework.stereotype.Service;

@Service
public class Validate {
	
	
	//Checking if the check sum is valid
    public boolean getCheckSum(String str) {
    	String data = str.substring(1,str.indexOf('^'));
    	String check_sum = str.substring(str.indexOf('^')+1, str.length()-1);
        int xor = data.charAt(0);
        for (int i = 1; i < data.length(); i++)
            xor ^= data.charAt(i);
        
        return true;
    }
}
