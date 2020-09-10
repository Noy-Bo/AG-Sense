package com.example.ServerTsofen45.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ServerTsofen45.Repo.UserAccountRepository;


@Service
public class UserAccountBL {
    @Autowired
   UserAccountRepository AccountRepository;
  /*  public boolean LogIn(Account account){
        Account byUserNme = AccountRepository.findByUserName(account.getUserName());
        if (byUserNme!=null){
            return true;
        }
        return false;
    }*/
}
