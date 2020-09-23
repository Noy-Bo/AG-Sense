package com.example.ServerTsofen45.BL;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ServerTsofen45.Beans.UserAccount;
import com.example.ServerTsofen45.Beans.User;
import com.example.ServerTsofen45.Repo.UserAccountRepository;
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
	UserAccountRepository accountRepository;
	@Autowired
	UserRepository<User> userRepository;

	public User LogIn(String Username, String password) {

		User byUserName = userRepository.findByUserName(Username);
		if (byUserName != null && byUserName.validate(password)) {
			byUserName.updateType();

			return byUserName;
		}

		return null;
	}

	public ArrayList<UserAccount> findall() {

		return accountRepository.findAllByOrderBySysIdDesc();

	}

		public List<UserAccount> findallByName(String Name) {
			return accountRepository. findByNameContaining(Name);

	}
		

		public List<Integer> getAllUsersIdForAccountID(int accountId) {
			
			return  userRepository.getAllUsersIdForAccountID(accountId);
			
		}
}
