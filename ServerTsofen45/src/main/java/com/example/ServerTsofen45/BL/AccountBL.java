package com.example.ServerTsofen45.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ServerTsofen45.Beans.Account;
import com.example.ServerTsofen45.Repo.AccountRepository;


@Service
public class AccountBL {
    @Autowired
   AccountRepository AccountRepository;
    public boolean LogIn(Account account){
        Account bySys_id = AccountRepository.findByUserName(account.getUserName());
        if (bySys_id!=null){
            return true;
        }
        return false;
    }
}
