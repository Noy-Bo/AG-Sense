package com.example.ServerTsofen45.Repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.ServerTsofen45.Beans.User;



public interface UserRepository<T extends User>  extends CrudRepository<T, Integer> {
	T findByUserName(String UserName);
	ArrayList<T> findByFirstnameContaining(String name);
	ArrayList<T> findAll();


}
