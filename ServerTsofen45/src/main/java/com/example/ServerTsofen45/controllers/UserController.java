package com.example.ServerTsofen45.controllers;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.json.simple.*;
import com.example.ServerTsofen45.BL.UserBL;
import com.example.ServerTsofen45.Beans.UserAccount;
import com.example.ServerTsofen45.Beans.Account;
import com.example.ServerTsofen45.Beans.User;
import com.example.ServerTsofen45.Repo.AccountRepository;
import com.example.ServerTsofen45.Repo.UserAccountRepository;



@RestController
@RequestMapping("User")
public class UserController {
	@Autowired
	UserBL userBL;
	@Autowired
	UserAccountRepository useraccountRepository;
	@Autowired
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



	//	*return all userProfiles in Database
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

		Account ac= new Account("delek",1);
		accountRepository.save(ac);
		//Admin ibra=new Admin(email ,name, Username, pass);
	///	adminRepository.save(ibra);
		UserAccount ibra=new UserAccount(email ,name, Username, pass,null);
		useraccountRepository.save(ibra);


	}
	
	
	

}
