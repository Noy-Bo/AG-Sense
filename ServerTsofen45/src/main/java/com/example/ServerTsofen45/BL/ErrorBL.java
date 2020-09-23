package com.example.ServerTsofen45.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ServerTsofen45.Beans.Error;
import com.example.ServerTsofen45.Repo.ErrorRepository;


@Service
public class ErrorBL {
    @Autowired
    ErrorRepository errorRepository;
    
    public Error findBycode(int code) {
    	
    	return errorRepository.findBycode(code);
    }

}
