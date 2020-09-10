package com.example.ServerTsofen45.BL;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ServerTsofen45.Beans.Admin;
import com.example.ServerTsofen45.Repo.AdminRepository;



@Service
public class AdminBL {
	@Autowired
	AdminRepository admin;
	
	
/*    public boolean LogIn(Admin Admin){
        Admin byUserNme = admin.findByUserName(Admin.getUserName());
        if (byUserNme!=null){
            return true;
        }
        return false;
    }
*/
}
