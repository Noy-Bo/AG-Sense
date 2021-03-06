package com.example.ServerTsofen45.Beans;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.json.simple.JSONObject;


@Entity
public class UserAccount extends User{
	
	
    public UserAccount() {
		super();
	}


	public UserAccount(String string, String string2, String phoneNumber, String string3, String string4,Account ac) throws NoSuchAlgorithmException {
    	super();
    		this.setEmail(string);
    		this.setname(string2);
    		this.setPhoneNumber(phoneNumber);
    		this.setUserName(string3);
    		//this.setHashPassword(string4);
    		this.hashPassword=hashPassword("123456");
    		this.setType("Account");
    		this.setAccount(ac);
    		this.setNotificationFlag(1);
    	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJson()
	{
		
		
		   JSONObject jo = new JSONObject();
		   jo.put("username", this.userName);
		   jo.put("email", this.email);
		   jo.put("id", this.sysId);
		   jo.put("type", this.type);
		   jo.put("numberOfDevices", this.account.getDevices().size());
		   jo.put("faultyDevices", this.account.faultyDevices());
		   jo.put("faultyAccount", this.account.faultyAccount());
		   jo.put("phoneNumber", this.phoneNumber);
		   jo.put("notificationFlag",this.notificationFlag);
		   jo.put("CompanyName",this.account.getName());
		   return jo;
	}


    

	@SuppressWarnings("unchecked")
	public JSONObject toJson(String faultyDevices, String numberOfDevices)
	{
		
		
		
		   JSONObject jo = new JSONObject();
		   jo.put("username", this.userName);
		   jo.put("email", this.email);
		   jo.put("id", this.sysId);
		   jo.put("type", this.type);
		   jo.put("numberOfDevices", numberOfDevices);
		   jo.put("faultyDevices", faultyDevices);
		   jo.put("faultyAccount", (Integer.parseInt(faultyDevices) >0));
		   jo.put("phoneNumber", this.phoneNumber);
		   jo.put("CompanyName",this.account.getName());
		   return jo;
	}





}
