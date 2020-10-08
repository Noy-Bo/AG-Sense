		package com.example.ServerTsofen45.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ServerTsofen45.Beans.Verification;
import com.example.ServerTsofen45.Repo.VerificationRepository;

@Service
public class VerificationBL {
	
	@Autowired
	VerificationRepository verificationsRepository;
	


	public Verification findByUsername(String username) {

		return  verificationsRepository.findByUserName(username);


	}

	public int deleteVerificationCode(String userName) {

		return verificationsRepository.deleteVerificationCode(userName);
	}

}
