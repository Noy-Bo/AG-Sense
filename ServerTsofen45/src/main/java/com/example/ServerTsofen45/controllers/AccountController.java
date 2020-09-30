package com.example.ServerTsofen45.controllers;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ServerTsofen45.BL.AccountBL;
import com.example.ServerTsofen45.Beans.Account;
import com.example.ServerTsofen45.Beans.Device;
import com.example.ServerTsofen45.Beans.UserAccount;
import com.example.ServerTsofen45.Repo.AccountRepository;

import Enums.DeviceType;

@RestController
@RequestMapping("Account")
public class AccountController {

	@Autowired
	AccountBL accountBL;
	@Autowired
	AccountRepository accountRepository;

	@GetMapping("AccountByName")
	public Account getAccountByName(@RequestParam String name) {

		return accountBL.getAccountByName(name);
	}

	// *return all userProfiles in Database
	@SuppressWarnings("unchecked")
	@GetMapping("AllAccounts")

	JSONArray getAllAccounts(@RequestParam int start, @RequestParam int num) {
		List<Account> res = accountBL.allAccounts();
		JSONArray jsonArray = new JSONArray();
		if (start + num > res.size())
			res = res.subList(start, res.size() - 1);
		else if (!(start == 0 && num == 0))
			res = res.subList(start, start + num);
		for (int i = 0; i < res.size(); i++) {
			jsonArray.add(res.get(i).toJson());
		}
		return jsonArray;

	}

	@GetMapping("Add")
	public boolean addAccount(@RequestParam String accountName) {

		if (accountBL.getAccountByName(accountName) != null)
			return false;

		Account account = new Account(accountName);
		accountRepository.save(account);

		return true;
	}

	@GetMapping("getAllAccountsName")
	public ArrayList<String> getAllAccountsName() {
		ArrayList<String> accounts = new ArrayList<>();
		accounts = accountBL.findAllAccountsName();
		return accounts;
	}

}
