package com.Tsofen45.TCP_ServerTsofen45.Analyzation;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;

@Service
abstract public class Analyzer {
	
	abstract public void Analyze(DeviceData d) throws IOException;
	public void SendPostRequest(DeviceData d) throws IOException{	
		long imei = d.getImei();
		try {
			URL url = new URL("http://206.72.198.59:8080/ServerTsofen45/Notifications/AddNotification");
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			
			//Setting the connection to post connection
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			
			//Gettomg put stream
			OutputStream os = connection.getOutputStream();
			
			//Flushing the output stream
			os.flush();
			os.close();
			
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
