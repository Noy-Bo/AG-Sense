package com.Tsofen45.TCP_ServerTsofen45.MessageHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.Tsofen45.TCP_ServerTsofen45.Factories.CommandsFactory;


public class DeviceMessageHandler implements Runnable {


	private DataInputStream dis;
	private String message ="";
	private DataOutputStream dos;
	
	@Autowired
	CommandsFactory cmdfac =new CommandsFactory();


	public DeviceMessageHandler(DataInputStream dis, DataOutputStream dos) {
		super();
		this.dis = dis;
		this.dos = dos;

	}

	@Override
	public void run() {
		// Getting message from the device from inpu stream
		String message = GetMessage();
		System.out.println(message);
		
		
		
		cmdfac.makecommand(message);

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

}