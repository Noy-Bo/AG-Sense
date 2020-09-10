package com.example.ServerTsofen45.BL;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ServerTsofen45.Beans.UserAccount;
import com.example.ServerTsofen45.Beans.User;
import com.example.ServerTsofen45.Repo.UserAccountRepository;
import com.example.ServerTsofen45.Repo.AdminRepository;
import com.example.ServerTsofen45.Repo.SupportRepository;
import com.example.ServerTsofen45.Repo.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserBL {
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	SupportRepository supportRepository;
	@Autowired
	UserAccountRepository accountRepository;
	@Autowired
	UserRepository<User> userRepository;

	
	   public User LogIn(String Username,String password){
	    User byUserName = userRepository.findByUserName(Username);
	       if(byUserName.validate(password))
	       {
	    	   byUserName.setAccount(byUserName.getAccount());

	    	   return byUserName;
	       }

	    	return null;
	    }
	    
	    public ArrayList<UserAccount> findall() 
	    {
	    	return (ArrayList<UserAccount>) accountRepository.findAll();
	    	/*ArrayList<Account> allaccounts=accountRepository.findAll();
	    	String jsonstr="";
	    	for(Account a:allaccounts)
	    		jsonstr+= (mapper.writerWithDefaultPrettyPrinter().writeValueAsString(a)+" ");
	    	
	    	return jsonstr;*/
	    }



		public ArrayList<UserAccount> findallByName(String Name) {
			return accountRepository. findByNameContaining(Name);

		}
}

