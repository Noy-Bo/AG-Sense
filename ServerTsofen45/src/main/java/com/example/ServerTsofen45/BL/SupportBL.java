package com.example.ServerTsofen45.BL;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ServerTsofen45.Beans.Support;
import com.example.ServerTsofen45.Repo.SupportRepository;


@Service
public class SupportBL {
	@Autowired
	SupportRepository SupportRepository;
	
    public boolean LogIn(Support support){
        Support bySys_id = SupportRepository.findByUserName(support.getUserName());
        if (bySys_id!=null){
            return true;
        }
        return false;
    }


	
        }
