package com.example.ServerTsofen45.Beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

//import com.project.OnTheGoShop.Beans.Device ;
//import com.project.OnTheGoShop.Beans.Notification ;

@Entity
public class Account extends User{
	
	List<Accounts > accounts;
   
	
	
	@OneToMany
    public List<Accounts> getDevices() {
    	
		return accounts;
	}

	public void setDevices(ArrayList<Accounts> accounts) {
		this.accounts = accounts;
	}



}
