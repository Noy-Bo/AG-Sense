package com.example.ServerTsofen45.Repo;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.example.ServerTsofen45.Beans.Support;
@Transactional
public interface SupportRepository extends UserRepository<Support>,CrudRepository<Support, Integer> {



}
