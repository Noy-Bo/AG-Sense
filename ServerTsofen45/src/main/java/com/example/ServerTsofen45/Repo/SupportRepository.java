package com.example.ServerTsofen45.Repo;

import org.springframework.data.repository.CrudRepository;

import com.example.ServerTsofen45.Beans.Support;


public interface SupportRepository extends CrudRepository<Support, Integer> {
	Support findByUserName(String UserName);



}
