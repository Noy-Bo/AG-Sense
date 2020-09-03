package com.example.ServerTsofen45.Beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

//import com.project.OnTheGoShop.Beans.Device ;
//import com.project.OnTheGoShop.Beans.Notification ;

@Entity
public class Account extends User{
	
    ArrayList<Notification> Notifications;
	ArrayList<Device > Devices;
    @OneToMany
    public List<Device> getDevices() {
    	
		return Devices;
	}

	public void setDevices(ArrayList<Device> devices) {
		Devices = devices;
	}

	@OneToMany
	public List<Notification> getNotifications() {
		return Notifications;
	}

	public void setNotifications(ArrayList<Notification> notifications) {
		Notifications = notifications;
	}

}
