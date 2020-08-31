package com.example.ServerTsofen45.Repo;

import org.springframework.data.repository.CrudRepository;

import com.example.ServerTsofen45.Beans.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer>{
	Admin findByUserName(String UserName);


}
