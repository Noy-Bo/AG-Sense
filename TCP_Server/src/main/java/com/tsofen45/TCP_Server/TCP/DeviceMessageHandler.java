package com.tsofen45.TCP_Server.TCP;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.tsofen45.TCP_Server.Bean.U111;

public class DeviceMessageHandler implements Runnable {

	
	private DataInputStream dis;
	private String message ="";
	private DataOutputStream dos;
	

	
	public DeviceMessageHandler(DataInputStream dis, DataOutputStream dos) {
		super();
		this.dis = dis;
		this.dos = dos;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String message =GetMessage();
		System.out.println(message);
		make_cmd(message);
		
	}
	private String GetMessage() {
		int c;
		
	    try {	    	
	    	do {
	    		c = dis.read();
	    		message+=(char)c;
	    	} while(dis.available()>0);

	    }
	    catch(Exception e) {
			e.printStackTrace();
	    }
		return message;
	}
	
	public static String getCheckSum(String str) {
	    int xor = str.charAt(0);
	    for (int i = 1; i < str.length(); i++)
	        xor ^= str.charAt(i);
	    
	    return Integer.toHexString(xor);
	}
    public static void make_cmd(String msg) {

        
 		String cmd =  msg.substring(16,20);
 		System.out.println("******"+cmd+"******");
 		switch (cmd) {
 		case "U111":
 			Thread t = new Thread(new U111(msg));
 			break;

 		default:
 			break;
 		}
    }


	

}
