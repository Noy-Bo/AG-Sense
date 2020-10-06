package com.example.ServerTsofen45.controllers;
import java.security.NoSuchAlgorithmException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.json.simple.*;

import com.example.ServerTsofen45.BL.DeviceBL;
import com.example.ServerTsofen45.BL.UserBL;
import com.example.ServerTsofen45.Beans.UserAccount;
import com.example.ServerTsofen45.Beans.Account;
import com.example.ServerTsofen45.Beans.Admin;
import com.example.ServerTsofen45.Beans.Support;
import com.example.ServerTsofen45.Beans.User;
import com.example.ServerTsofen45.Repo.AccountRepository;
import com.example.ServerTsofen45.Repo.AdminRepository;
import com.example.ServerTsofen45.Repo.DeviceRepository;
import com.example.ServerTsofen45.Repo.UserAccountRepository;

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
	@Autowired
	DeviceRepository drepo;
	@Autowired
	DeviceBL deviceBL;
	

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

	
	@GetMapping("ForgetPassword")
		public JSONObject getEmailAndPhonenumberForUser(@RequestParam String userName)
		{
		
		JSONObject jo = userBL.getEmailAndPhonenumberForUser(userName);
		return jo;
		
		}

	//	*return all userProfiles in Database
	@SuppressWarnings("unchecked")
	@GetMapping("AllAccounts")

	JSONArray getAllAccounts(@RequestParam int start,@RequestParam int num) 
	{
		List<UserAccount> res= userBL.findall();
		JSONArray jsonArray = new JSONArray();
		
		if(start+num>res.size())
			res=res.subList(start, res.size()-1);
		
		else if(!(start==0&&num==0))
			res=  res.subList(start, start+num);
		
		
		
		for(int i=0;i<res.size();i++)
		{
			String faultyDevices = deviceBL.getFaultyDevicesNumberForId(res.get(i).getAccount().getId());
			String numberOfDevices = deviceBL.getDevicesNumberForId(res.get(i).getAccount().getId());
			
			jsonArray.add(res.get(i).toJson(faultyDevices, numberOfDevices));
		}
		
		return jsonArray;


		
	}
	
	//search user names in userProfiles table by name and returns UserProfile arraylist with names whose name contain the string given
	@SuppressWarnings("unchecked")
	@GetMapping("SpecificAccountsByName")
	JSONArray getSpecificAccountsByName(@RequestParam String name,@RequestParam int start,@RequestParam int num) 
	{
		List<UserAccount> res= userBL.findallByName(name);
	    JSONArray jsonArray = new JSONArray();
		if(start+num>res.size())
			res=res.subList(start, res.size()-1);
		else if(!(start==0&&num==0))
			res=  res.subList(start, start+num);
			
			
	    for(int i=0;i<res.size();i++)
	    {
	    	jsonArray.add(res.get(i).toJson());
	    }
		 return jsonArray;
			
	}





	@GetMapping("addNewUser")
	public boolean AddToDb(@RequestParam String username, @RequestParam String email, @RequestParam String userType, @RequestParam String accountName) throws NoSuchAlgorithmException {
		return userBL.addNewUser(username, email, userType, accountName);
	}
	
	@GetMapping("changeUserPassword")
	public boolean changePass(@RequestParam int userId, @RequestParam String newPass) {
		return userBL.setPass(userId, newPass);
	}
	
	@GetMapping("setPassword")
	public boolean setPass(@RequestParam int userId, @RequestParam String pass) {
		return userBL.setPass(userId, pass);
	}
	

}
