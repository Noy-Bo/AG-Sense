package com.Tsofen45.TCP_ServerTsofen45.Analyzation;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;

@Service
public class MoveAnalyzer extends Analyzer{

	@Override
	public void Analyze(DeviceData d) {
		// TODO Auto-generated method stub
		System.out.println("speed is " +d.getSpeed());

		if(d.getSpeed() > 0) {
			//Push Notification
			try {
				
				//Push Notification
				System.out.println("Sending notificatin on device moved");
				SendPostRequest(d);
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void SendPostRequest(DeviceData d) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("making a post request on devie moved");
		long imei = d.getImei();
		String postData="";
		try {
			URL url = new URL(postData);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			
			//Setting the connection to post connection
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			
			//Gettomg put stream
			OutputStream os = connection.getOutputStream();
			os.write(postData.getBytes());
			
			//Flushing the output stream
			os.flush();
			os.close();
			
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
