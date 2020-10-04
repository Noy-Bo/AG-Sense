package com.example.ServerTsofen45.BL;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ServerTsofen45.Beans.UserAccount;
import com.example.ServerTsofen45.Beans.Account;
import com.example.ServerTsofen45.Beans.Admin;
import com.example.ServerTsofen45.Beans.Support;
import com.example.ServerTsofen45.Beans.User;
import com.example.ServerTsofen45.Repo.UserAccountRepository;
import com.example.ServerTsofen45.Repo.AccountRepository;
import com.example.ServerTsofen45.Repo.AdminRepository;
import com.example.ServerTsofen45.Repo.SupportRepository;
import com.example.ServerTsofen45.Repo.UserRepository;

@Service
public class UserBL {
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	SupportRepository supportRepository;
	@Autowired
	UserAccountRepository UseraccountRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AccountRepository accountRepository;
	User general_user;
	public User LogIn(String Username, String password) {

		User byUserName = userRepository.findByUserName(Username);
		if (byUserName != null && byUserName.validate(password)) {
			

			return byUserName;
		}

		return null;
	}

	public ArrayList<UserAccount> findall() {

		return UseraccountRepository.findAllByOrderBySysIdDesc();

	}

		public List<UserAccount> findallByName(String Name) {
			return UseraccountRepository. findByNameContaining(Name);

	}
		

		public List<User> getAllUsersForAccountID(int accountId) {
			
			return  userRepository.getAllUsersForAccountID(accountId);
			
		}
		
		public int getAccountIDForUser(int id) {
			
			return  userRepository.getAccountIDForUser(id);
		}
		
		public boolean addNewUser(String username, String email, String userType, String accountName) throws NoSuchAlgorithmException {
			Account acc = accountRepository.findByName(accountName);
			if (userType.contentEquals("Admin")) {
				Admin admin = new Admin(email,username,username,null,acc);
				adminRepository.save(admin);
			}
			else if(userType.contentEquals("Account")) {
				UserAccount user_acc= new UserAccount(email,username,username,null,acc);
				UseraccountRepository.save(user_acc);
			}
			else if(userType.contentEquals("Support")) {
				Support sup = new Support();
				userRepository.save(sup);
			}
			return true;
		}
		
		
		public boolean setPass(int id,String pass) {
			general_user = userRepository.findBysysId(id);
			general_user.setHashPassword(pass);
			return true;
			
		}
		
		
}
