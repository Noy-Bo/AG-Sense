package com.example.ServerTsofen45.Repo;


import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.example.ServerTsofen45.Beans.UserAccount;
import com.example.ServerTsofen45.Beans.User;

@Transactional
public interface UserAccountRepository extends UserRepository<UserAccount> ,CrudRepository<UserAccount, Integer> {
	@Override
	UserAccount findByUserName(String UserName);

	

}
