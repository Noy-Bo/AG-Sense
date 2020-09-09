package com.example.ServerTsofen45.controllers;


import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.ServerTsofen45.BL.UserBL;
import com.example.ServerTsofen45.Beans.Admin;
import com.example.ServerTsofen45.Beans.User;
import com.example.ServerTsofen45.Beans.UserAccount;
import com.example.ServerTsofen45.Repo.AccountRepository;
import com.example.ServerTsofen45.Repo.AdminRepository;
import com.example.ServerTsofen45.Repo.UserAccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("User")
public class UserController {
	@Autowired
	UserBL userBL;
	@Autowired
	UserAccountRepository useraccountRepository;
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	AccountRepository accountrepo;
	
	@GetMapping("Login")
	public User Login(@RequestParam String username, @RequestParam String password) throws JsonProcessingException
	{

		return userBL.LogIn(username, password);

	}
	
	




	//	*return all userProfiles in Database
	@GetMapping("AllAccounts")
	List<UserAccount> getAllAccounts(@RequestParam int start,@RequestParam int num) 
	{

		
		ArrayList<UserAccount> res= userBL.findall();
		if(start+num>res.size())
			return null ;
	//	Collections.sort(res, Collections.reverseOrder());
	    if(start==0&&num==0)
			return res;
		else
			return  res.subList(start, start+num);

		
	}
	
	//search user names in userProfiles table by name and returns UserProfile arraylist with names whose name contain the string given
	@GetMapping("SpecificAccountsByName")
	ArrayList<UserAccount> getSpecificAccountsByName(@RequestParam String name) 
	{
		return userBL.findallByName(name);
	}

	
	@GetMapping("Add")
	public void AddToDb(@RequestParam String name,@RequestParam String email,@RequestParam String Username,@RequestParam String pass) throws NoSuchAlgorithmException
	{
		//Account ac= new Account("delek",1);
		//accountrepo.save(ac);
		Admin ibra=new Admin(email ,name, Username, pass);
		adminRepository.save(ibra);
	//	UserAccount ibra=new UserAccount(email ,name, Username, pass,ac);
	//	useraccountRepository.save(ibra);
		
	}
	
	
	

}
