package com.example.ServerTsofen45.BL;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
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
			return UseraccountRepository.findByNameContaining(Name);

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
				supportRepository.save(sup);
			}
			return true;
		}
		
		
		public boolean setPass(int id,String pass) {
			general_user = userRepository.findBysysId(id);
			general_user.setHashPassword(pass);
			return true;
			
		}
		// http://localhost:8080//User/editUser?userId=101&newEmail=%22newEmail@gmail.com%22&newPhoneNumber=0501234567&newNotificationFlag=1
		public boolean edit_user(int userId,String newMail,String newPhone,int newNFlag) {
			User userAcc =  userRepository.findBysysId(userId);
			if (!(newMail.isEmpty())||(newMail.equalsIgnoreCase(null))) {
				userAcc.setEmail(newMail);
			}
			if (!(newPhone.isEmpty())) {
				userAcc.setPhoneNumber(newPhone);
			}
			if (newNFlag!=3) {
				userAcc.setNotificationFlag(newNFlag);
			}
			userRepository.save(userAcc);
			return true;
		}
		
		
		@SuppressWarnings("unchecked")
		public JSONObject getEmailAndPhonenumberForUser(String userName) {
			
			User user = userRepository.findByUserName(userName);
			
			JSONObject jo = new JSONObject();
			
			String email = user.getEmail().replaceAll("(\\w{1,2})(\\w+)(@.*)", "$1******$3");
			String phoneNumber =  user.getPhoneNumber().replaceAll("\\d(?=\\d{2})", "*");
			   jo.put("email", email);
			   jo.put("phoneNumber",phoneNumber);
			   
			   return jo;
			
		}
		
}