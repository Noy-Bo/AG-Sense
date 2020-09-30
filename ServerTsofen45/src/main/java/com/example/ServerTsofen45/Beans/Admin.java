package com.example.ServerTsofen45.Beans;

import java.security.NoSuchAlgorithmException;

import javax.persistence.Entity;

@Entity
public class Admin extends User {
	public Admin(String string, String string2, String string3, String string4) throws NoSuchAlgorithmException {
    	super();
    		this.setEmail(string);
    		this.setname(string2);
    		this.setUserName(string3);
    		//this.setHashPassword(string4);
    		this.hashPassword=hashPassword("123456");
    		this.setAccount(null);
    		this.setType("admin");
    	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}	



}
