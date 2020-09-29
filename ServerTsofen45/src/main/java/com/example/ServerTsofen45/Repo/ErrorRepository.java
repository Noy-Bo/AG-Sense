package com.example.ServerTsofen45.Repo;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import com.example.ServerTsofen45.Beans.Error;

@Transactional
public interface ErrorRepository extends CrudRepository<Error, Integer> {

	public Error findBycode(int code);

}
