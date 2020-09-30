package com.Tsofen45.TCP_ServerTsofen45.ServerMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tsofen45.TCP_ServerTsofen45.Authentication.Authenticate;

@Service
public class AddDevice extends ServerMessage {

	final private int lengthOfImei=15;
	@Autowired
	Authenticate auth;
	@Override
	public void Analyze(String s) {
		// TODO Auto-generated method stub
		if(s.contains("AddDevice")) {
			System.out.println("Adding New IMEI: "+s.substring(s.length()-lengthOfImei,s.length()));
			auth.addNewImeie(Long.parseLong(s.substring(s.length()-lengthOfImei,s.length())));
		}
	}

}
