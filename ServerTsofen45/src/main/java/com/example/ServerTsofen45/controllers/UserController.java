package com.example.ServerTsofen45.controllers;
<<<<<<< HEAD


=======
>>>>>>> master
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
<<<<<<< HEAD
import com.example.ServerTsofen45.BL.UserBL;
import com.example.ServerTsofen45.Beans.Admin;
import com.example.ServerTsofen45.Beans.User;
import com.example.ServerTsofen45.Beans.UserAccount;
import com.example.ServerTsofen45.Repo.AccountRepository;
import com.example.ServerTsofen45.Repo.AdminRepository;
import com.example.ServerTsofen45.Repo.UserAccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
=======

import org.json.simple.*;
import com.example.ServerTsofen45.BL.UserBL;
import com.example.ServerTsofen45.Beans.UserAccount;
import com.example.ServerTsofen45.Beans.Account;
import com.example.ServerTsofen45.Beans.User;
import com.example.ServerTsofen45.Repo.AccountRepository;
import com.example.ServerTsofen45.Repo.UserAccountRepository;


>>>>>>> master

@RestController
@RequestMapping("User")
public class UserController {
	@Autowired
	UserBL userBL;
	@Autowired
	UserAccountRepository useraccountRepository;
	@Autowired
<<<<<<< HEAD
	AdminRepository adminRepository;
	@Autowired
	AccountRepository accountrepo;
	
	@GetMapping("Login")
	public User Login(@RequestParam String username, @RequestParam String password) throws JsonProcessingException
	{

		return userBL.LogIn(username, password);

	}
	
	

=======
	AccountRepository accountRepository;
	
	@GetMapping("Login")
	public JSONObject Login(@RequestParam String username, @RequestParam String password)
	{
		User res= userBL.LogIn(username, password);
		
	   if(res!=null)
	   {
		   return res.toJson();	   }
	   else
		    return null;

	}
>>>>>>> master



	//	*return all userProfiles in Database
<<<<<<< HEAD
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
=======
	@SuppressWarnings("unchecked")
	@GetMapping("AllAccounts")

	JSONArray getAllAccounts(@RequestParam int start,@RequestParam int num) 
	{
		List<UserAccount> res= userBL.findall();
		if(start+num>res.size())
			return null ;
	    JSONArray jsonArray = new JSONArray();
	    if(!(start==0&&num==0))
			res=  res.subList(start, start+num);
	    for(int i=0;i<res.size();i++)
	    {
	    	jsonArray.add(res.get(i).toJson());
	    }
	    return jsonArray;

>>>>>>> master

		
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
		//Account ac= new Account("delek",1);
		//accountrepo.save(ac);
		Admin ibra=new Admin(email ,name, Username, pass);
		adminRepository.save(ibra);
	//	UserAccount ibra=new UserAccount(email ,name, Username, pass,ac);
	//	useraccountRepository.save(ibra);
		
=======

		Account ac= new Account("delek",1);
		accountRepository.save(ac);
		//Admin ibra=new Admin(email ,name, Username, pass);
	///	adminRepository.save(ibra);
		UserAccount ibra=new UserAccount(email ,name, Username, pass,null);
		useraccountRepository.save(ibra);


>>>>>>> master
	}
	
	
	

}
