package com.example.ServerTsofen45.Beans;

import java.security.NoSuchAlgorithmException;

import javax.persistence.Entity;

@Entity
public class Admin extends User {
	public Admin(String email, String name, String phoneNumber, String userName, String string4, Account acc) throws NoSuchAlgorithmException {
    	super();
    		this.setEmail(email);
    		this.setname(name);
    		this.setPhoneNumber(phoneNumber);
    		this.setUserName(userName);
    		//this.setHashPassword(string4);
    		this.hashPassword=hashPassword("123456");
    		this.setAccount(acc);
    		this.setType("admin");
    		this.notificationFlag=1;
    	}
	
	
	

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}	



}
