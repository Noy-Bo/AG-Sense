package com.example.ServerTsofen45.controllers;


import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.ServerTsofen45.BL.UserBL;
<<<<<<< HEAD
import com.example.ServerTsofen45.Beans.Account;
import com.example.ServerTsofen45.Beans.Admin;
=======
import com.example.ServerTsofen45.Beans.UserAccount;
>>>>>>> branch 'master' of https://github.com/tsofen45/ag-Sence.git
import com.example.ServerTsofen45.Beans.User;
<<<<<<< HEAD
import com.example.ServerTsofen45.Repo.AccountRepository;
import com.example.ServerTsofen45.Repo.AdminRepository;
=======
import com.example.ServerTsofen45.Repo.UserAccountRepository;
>>>>>>> branch 'master' of https://github.com/tsofen45/ag-Sence.git
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("User")
public class UserController {
	@Autowired
	UserBL userBL;
	@Autowired
<<<<<<< HEAD
	AdminRepository adminRepository;
=======
	UserAccountRepository accountRepository;
>>>>>>> branch 'master' of https://github.com/tsofen45/ag-Sence.git
	
	@GetMapping("Login")
	User Login(@RequestParam String username, @RequestParam String password) throws JsonProcessingException
	{
		
		User Resulte= userBL.LogIn(username, password);
		return Resulte;
		/*if (Result!=null)
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
	ArrayList<UserAccount> getAllAccounts() 
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
	ArrayList<UserAccount> getSpecificAccountsByName(@RequestParam String name) 
	{
		return userBL.findallByName(name);
	}

	
	@GetMapping("Add")
	public void AddToDb(@RequestParam String name,@RequestParam String email,@RequestParam String Username,@RequestParam String pass) throws NoSuchAlgorithmException
	{
<<<<<<< HEAD
		Admin ibra=new Admin(email ,name, Username, pass);
		adminRepository.save(ibra);
=======
		UserAccount ibra=new UserAccount(email ,name, Username, pass);
		accountRepository.save(ibra);
>>>>>>> branch 'master' of https://github.com/tsofen45/ag-Sence.git
		
	}
	
	
	

}
