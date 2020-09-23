package com.Tsofen45.TCP_ServerTsofen45.MessageHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.Tsofen45.TCP_ServerTsofen45.Analyzation.AnalyzerManager;
import com.Tsofen45.TCP_ServerTsofen45.Authentication.Authenticate;
import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;
import com.Tsofen45.TCP_ServerTsofen45.Factories.CommandsFactory;
import com.Tsofen45.TCP_ServerTsofen45.Routers.DeviceDataRouter;
import com.Tsofen45.TCP_ServerTsofen45.Validation.Validate;

@Component
public class DeviceMessageHandler implements Runnable {


	private DataInputStream dis;
	private String message ="";
	private DataOutputStream dos;
	
	@Autowired
	Validate validate;
	
	@Autowired
	Authenticate authenticate;
	
	@Autowired
	CommandsFactory cmdfac;
	
	
	DeviceData deviceData;
	
	@Autowired
	AnalyzerManager analyzerManager;
	
	@Autowired
	DeviceDataRouter deviceDatarouter;

	public void setDis(DataInputStream dis) {
		this.dis =dis;
	}
	public void setDos(DataOutputStream dos) {
		this.dos =dos;
	}

	@Override
	public void run() {
		// Getting message from the device from inpu stream
		String message = GetMessage();
		System.out.println(message+"\n");
		
		//Validation
		if(!validate.getCheckSum(message)) {
			System.out.println("Check some not valid!");
			return;
		}
		System.out.println("the message is valid");
		//Authentication
		if(!authenticate.check_imei(message)) {
			System.out.println("Imei not found!");
			return;
		}	
		System.out.println("the imei is fond");
		//Making Device Data
		deviceData = cmdfac.makeDeviceData(message);
		
		//making flags states
		//set your code here
		
		//Making Analyzes if we need to notify the user .. like: Battery low, Device Moved
		try {
			analyzerManager.analyze(deviceData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Saving the Device Data in the data base
		deviceDatarouter.saveDeviceData(deviceData);
		
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
	    finally {
	    	try {
				dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return message;
	}

}