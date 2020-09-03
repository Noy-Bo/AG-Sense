package com.example.ServerTsofen45.Repo;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.example.ServerTsofen45.Beans.Admin;
@Transactional
public interface AdminRepository extends UserRepository<Admin> ,CrudRepository<Admin, Integer> {


}
