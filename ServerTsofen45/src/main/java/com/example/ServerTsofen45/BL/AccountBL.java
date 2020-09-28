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
}
