package com.example.ServerTsofen45.Beans;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class Account extends User{
	
	List<Accounts> accounts;
   
	
	
    public Account() {
		super();
	}


	public Account(String string, String string2, String string3, String string4) throws NoSuchAlgorithmException {
    	super();
    		this.setEmail(string);
    		this.setname(string2);
    		this.setUserName(string3);
    		this.setHashPassword(string4);
    	}	

    
	@OneToMany
    public List<Accounts> getAccounts() {
    	
		return accounts;
	}

	public void setAccounts(List<Accounts> accounts) {
		this.accounts = accounts;
	}
	public void addNewAccount(Accounts account) {
		this.accounts.add(account);
	}






}
