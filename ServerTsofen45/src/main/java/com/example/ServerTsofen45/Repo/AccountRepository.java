package com.example.ServerTsofen45.Repo;

import org.springframework.data.repository.CrudRepository;

import com.example.ServerTsofen45.Beans.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {
	Account findByID(String id);
	Account findByUserName(String UserName);
	

}
