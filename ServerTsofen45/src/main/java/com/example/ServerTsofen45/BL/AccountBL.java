package com.example.ServerTsofen45.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ServerTsofen45.Repo.AccountRepository;


@Service
public class AccountBL {
    @Autowired
   AccountRepository AccountRepository;
  /*  public boolean LogIn(Account account){
        Account byUserNme = AccountRepository.findByUserName(account.getUserName());
        if (byUserNme!=null){
            return true;
        }
        return false;
    }*/
}
