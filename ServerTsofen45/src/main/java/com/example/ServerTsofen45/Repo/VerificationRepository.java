package com.example.ServerTsofen45.Repo;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.ServerTsofen45.Beans.Notification;
import com.example.ServerTsofen45.Beans.Verification;


public interface VerificationRepository extends CrudRepository<Verification, Integer> {
	
	public Verification findByUserName(String userName);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value =
			"    DELETE FROM public.verifications_code WHERE user_name = ?1 " )
	public int deleteVerificationCode(String userName);
	
}