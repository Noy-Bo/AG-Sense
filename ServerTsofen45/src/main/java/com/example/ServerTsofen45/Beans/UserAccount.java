package com.example.ServerTsofen45.Beans;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;


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
