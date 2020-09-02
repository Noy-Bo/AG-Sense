package com.example.ServerTsofen45.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ServerTsofen45.BL.UserBL;
import com.example.ServerTsofen45.Beans.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("User")
public class UserController {
	@Autowired
	UserBL userBL;

	
	@GetMapping("Login")
	String Login(String username, String password) throws JsonProcessingException
	{
		User Resulte= userBL.LogIn(username, password);
		if (Resulte!=null)
		{
	        ObjectMapper Obj = new ObjectMapper(); 
	        

	            return Obj.writeValueAsString(Resulte.toString()); 
		}
		else 
		{
			return "the username or password is incorrect please input again";
		}
		

		
	}
	
	
	
	//	*return all userProfiles in Database
	@GetMapping("AllAccounts")
	String getAccounts()
	{
		try {
			return userBL.findall();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	

}
