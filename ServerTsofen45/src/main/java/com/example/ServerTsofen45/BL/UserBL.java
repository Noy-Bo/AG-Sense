package com.example.ServerTsofen45.BL;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.stereotype.Service;

import com.example.ServerTsofen45.Beans.Account;
import com.example.ServerTsofen45.Beans.User;
import com.example.ServerTsofen45.Repo.AccountRepository;
import com.example.ServerTsofen45.Repo.AdminRepository;
import com.example.ServerTsofen45.Repo.SupportRepository;
import com.example.ServerTsofen45.Repo.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserBL {
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	SupportRepository supportRepository;
	@Autowired
	AccountRepository accountRepository;
	//@Autowired
	//UserRepository< User> userRepository;
	ObjectMapper mapper = new ObjectMapper();

		public ArrayList<User> Containing(String Name)
		{
			return null;
			//return userRepository.findByFirstnameContaining(Name);
		}
	
		public User getByName(String Name)
		{
			return null;
		//	return userRepository.findByUserName(Name);
		}
	
	    public User LogIn(String Username,String password){
     //   User byUserName = userRepository.findByUserName(Username);
        
    //   if(byUserName.validate(password))
       {
    	//   return byUserName;
       }
    	   
    	return null;
    }
	    public String findall() throws JsonProcessingException
	    {
	    	String jsonstr="";
	    	ArrayList<Account> allaccounts=accountRepository.findAll();
	    	for(Account a:allaccounts)
	    		jsonstr+= (mapper.writerWithDefaultPrettyPrinter().writeValueAsString(a)+" ");
	    	
	    	return jsonstr;
	    }
}
