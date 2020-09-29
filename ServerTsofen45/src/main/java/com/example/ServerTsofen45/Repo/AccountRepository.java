package com.example.ServerTsofen45.Repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.ServerTsofen45.Beans.Account;

public interface AccountRepository  extends CrudRepository<Account, Integer>  {

	
	Account findByName(String name);
	ArrayList<Account> findAll();
	
	@Query(nativeQuery = true,value = "SELECT Text FROM public.account;")
	public ArrayList<Account> findAllAccountsName();
	
}
