package com.example.ServerTsofen45.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ServerTsofen45.BL.AccountBL;
import com.example.ServerTsofen45.Beans.Account;
import com.example.ServerTsofen45.Beans.Device;

@RestController
@RequestMapping("Account")
public class AccountController {
	
	@Autowired
	AccountBL accountBL;
	
	@GetMapping("AccountByName")
	public Account getAccountByName(@RequestParam String name) {

		return accountBL.getAccountByName(name);
	}

}
