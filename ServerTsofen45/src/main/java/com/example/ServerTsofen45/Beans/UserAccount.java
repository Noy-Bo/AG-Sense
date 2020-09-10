package com.example.ServerTsofen45.Beans;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.json.simple.JSONObject;


@Entity
public class UserAccount extends User{
	
	List<Account> accounts;
   
	
	
    public UserAccount() {
		super();
	}


	public UserAccount(String string, String string2, String string3, String string4) throws NoSuchAlgorithmException {
    	super();
    		this.setEmail(string);
    		this.setname(string2);
    		this.setUserName(string3);
    		//this.setHashPassword(string4);
    		this.hashPassword=hashPassword(string4);

    		this.setAccount(ac);
    	}
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJson()
	{
		   JSONObject jo = new JSONObject();
		   jo.put("accountid", this.account.getId());
		   jo.put("username", this.userName);
		   jo.put("email", this.email);
		   jo.put("id", this.sysId);
		   jo.put("type", this.type);
		   
		   return jo;
	}


    
	@OneToMany
    public List<Account> getAccounts() {
    	
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public void addNewAccount(Account account) {
		this.accounts.add(account);
	}






}
