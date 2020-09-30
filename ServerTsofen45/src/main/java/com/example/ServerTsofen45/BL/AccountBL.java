package com.example.ServerTsofen45.BL;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ServerTsofen45.Beans.Account;
import com.example.ServerTsofen45.Beans.Device;
import com.example.ServerTsofen45.Repo.AccountRepository;
import com.example.ServerTsofen45.Repo.DeviceRepository;

@Service
public class AccountBL {

	@Autowired
	AccountRepository accountRepository;

	public Account getAccountByName(String name) {

		Account account = accountRepository.findByName(name);
		return account;

	}
	
	
	public ArrayList<Account> allAccounts()
	{
		ArrayList<Account> accounts = accountRepository.findAll();
		return accounts;
	}
	
	
	
//	public ArrayList<Account> findAllAccountsName(){
//		ArrayList<Account> accounts= new ArrayList<Account>();
//		accounts = accountRepository.findAllAccountsName();
//		return accounts;
//		
//	}
//	
	
	
	public ArrayList<String> findAllAccountsName(){
		ArrayList<Account> accounts= new ArrayList<Account>();
		ArrayList<String> names= new ArrayList<String>();
	     accounts = accountRepository.findAll();
	     
	     for(Account account : accounts)
	     {
	    	 names.add(account.getName());
	     }
		return names;
	}
	
	public boolean editAccount(String prevname,String newname) {
		Account acc = accountRepository.findByName(prevname);
		acc.setName(newname);
		accountRepository.save(acc);
		return true;
	}
	
	
}
