package com.example.ServerTsofen45.controllers;


import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.ServerTsofen45.BL.UserBL;
import com.example.ServerTsofen45.Beans.Account;
import com.example.ServerTsofen45.Beans.User;
import com.example.ServerTsofen45.Repo.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("User")
public class UserController {
	@Autowired
	UserBL userBL;
	@Autowired
	AccountRepository accountRepository;
	
	@GetMapping("Login")
	User Login(@RequestParam String username, @RequestParam String password) throws JsonProcessingException
	{
		
		User Resulte= userBL.LogIn(username, password);
		return Resulte;
		/*if (Resulte!=null)
		{
	        ObjectMapper Obj = new ObjectMapper(); 
	       return Obj.writeValueAsString(Resulte.toString()); 
		}
		else 
		{
			return "the username or password is incorrect please input again";
		}*/		
	}
	
	

	//	*return all userProfiles in Database
	@GetMapping("AllAccounts")
	ArrayList<Account> getAllAccounts() 
	{

		
		return userBL.findall();

	/*	try {
					return userBL.findall();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;*/
		
	}
	
	//search user names in userProfiles table by name and returns UserProfile arraylist with names whose name contain the string given
	@GetMapping("SpecificAccountsByName")
	ArrayList<Account> getSpecificAccountsByName() 
	{
		return userBL.findallByName();
	}

	
	@GetMapping("Add")
	public void AddToDb(@RequestParam String name,@RequestParam String email,@RequestParam String Username,@RequestParam String pass) throws NoSuchAlgorithmException
	{
		Account ibra=new Account(email ,name, Username, pass);
		accountRepository.save(ibra);
		
	}
	
	
	

}
