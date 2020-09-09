package com.example.ServerTsofen45.Beans;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class UserAccount extends User{
	
   
	
	
    public UserAccount() {
		super();
	}


	public UserAccount(String string, String string2, String string3, String string4,Account ac) throws NoSuchAlgorithmException {
    	super();
    		this.setEmail(string);
    		this.setname(string2);
    		this.setUserName(string3);
    		this.account=ac;
    		//this.setHashPassword(string4);
    		this.hashPassword=hashPassword(string4);
    	}	

    







}
