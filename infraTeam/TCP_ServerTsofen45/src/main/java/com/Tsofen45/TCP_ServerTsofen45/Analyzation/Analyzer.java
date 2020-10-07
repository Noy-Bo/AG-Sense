package com.Tsofen45.TCP_ServerTsofen45.Analyzation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;

@Service
abstract public class Analyzer {
	
	protected JSONObject json=null;
	protected int code;
	
	private static String GET_URL = "http://206.72.198.59:8080/ServerTsofen45v7/Notifications/AddNotification";
	
	abstract public void Analyze(DeviceData d) throws IOException;
	

	protected static void sendNotify(String imei,int code,JSONObject json) throws IOException {
		//something

			 if(json==null) {
				 GET_URL = GET_URL + "?imei="+  imei  +"&code="+code;
				 }
			 else {			 
				 String params = URLEncoder.encode(json.toString(), StandardCharsets.UTF_8.toString()).toString();
				 
				 GET_URL = GET_URL + "?imei="+  imei  +"&code="+code+"&params="+params;
			 }
				
			URL obj = new URL(GET_URL);
			
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			
			int responseCode = con.getResponseCode();
			System.out.println("GET Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader bfrReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				StringBuffer response = new StringBuffer();
				
				String message = "";
			    for (String line = bfrReader.readLine(); line != null; line = bfrReader.readLine()) {
			        message = message + line +"\n";
			     }
			    bfrReader.close();
	
					// print result
					System.out.println(response.toString());
			} else {
				System.out.println("GET request not worked");
			}
			con.disconnect();

		}
		
	
}
