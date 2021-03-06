package com.Tsofen45.TCP_ServerTsofen45.MessageHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.Tsofen45.TCP_ServerTsofen45.Disconnected.NotifyMaulfunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Tsofen45.TCP_ServerTsofen45.Alarms.StateManager;
import com.Tsofen45.TCP_ServerTsofen45.Analyzation.AnalyzerManager;
import com.Tsofen45.TCP_ServerTsofen45.Authentication.Authenticate;
import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;
import com.Tsofen45.TCP_ServerTsofen45.Disconnected.ReportTimer;
import com.Tsofen45.TCP_ServerTsofen45.Factories.CommandsFactory;
import com.Tsofen45.TCP_ServerTsofen45.Routers.DeviceDataRouter;
import com.Tsofen45.TCP_ServerTsofen45.Validation.Validate;

@Component
public class DeviceMessageHandler implements Runnable {


	private InputStream is;
	private String message ="";
	private BufferedReader bfrReader;
	
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
	StateManager stateManager ;
	
	@Autowired
	DeviceDataRouter deviceDatarouter;
	
	ReportTimer reportTimer;

	public void setInputStrae(InputStream is) {
		this.is =is;
	}

	@Override
	public void run() {
		// Getting message from the device from inpu stream
		String message = null;
		try {
			message = GetMessage();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		System.out.println("the imei is found");
		//Making Device Data
		deviceData = cmdfac.makeDeviceData(message);
		
		//making flags states
		stateManager.setDeviceData(deviceData);
		stateManager.setStates();

		//(new Thread(new NotifyMaulfunction(""+deviceData.getImei()))).start();

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
	private String GetMessage() throws IOException {
		bfrReader = new BufferedReader(new InputStreamReader(is));
		try {
			
			message = bfrReader.readLine();
//		    for (String line = bfrReader.readLine(); line != null; line = bfrReader.readLine()) {
//		        message = message + line +"\n";
//		     }
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			bfrReader.close();			
		}
		
		
//	    try {
//	    	do {
//	    		c = dis.read();
//	    		message+=(char)c;
//	    	} while(dis.available()>0);
//	    }
//	    catch(Exception e) {
//			e.printStackTrace();
//	    }
//	    finally {
//	    	try {
//				dis.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
		return message;
	}

}