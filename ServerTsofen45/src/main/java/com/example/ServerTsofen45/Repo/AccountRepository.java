package com.example.ServerTsofen45.Repo;


import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.example.ServerTsofen45.Beans.Account;

@Transactional
public interface AccountRepository extends UserRepository<Account> ,CrudRepository<Account, Integer> {
	
	

}
