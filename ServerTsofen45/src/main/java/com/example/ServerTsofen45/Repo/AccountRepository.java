package com.example.ServerTsofen45.Repo;


import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.example.ServerTsofen45.Beans.Account;
import com.example.ServerTsofen45.Beans.User;

@Transactional
public interface AccountRepository extends UserRepository<Account> ,CrudRepository<Account, Integer> {
	@Override
	Account findByUserName(String UserName);

	

}
