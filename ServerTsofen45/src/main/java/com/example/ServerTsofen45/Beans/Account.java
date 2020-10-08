package com.example.ServerTsofen45.Beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.json.simple.JSONObject;

@Entity
public class Account {
	String name;
	int Id;
	List<Device> devices;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(String name) {
		super();
		this.name = name;
		devices = new ArrayList<Device>();
	}

	public void Adddevice(Device devices) {
		this.devices.add(devices);
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	@OneToMany
	public List<Device> getDevices() {
		return devices;
	}

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
	
	
	public int faultyDevices()
	{
		int num=0;
		for(Device device: devices)
			if(device.isFaulty())
			{
				num++;
			}
		return num;
	}
	
	
    public boolean faultyAccount()
    {
    	if(faultyDevices()>0)
    		return true;
    	else 
    		return false;
    }
	@SuppressWarnings("unchecked")
	public JSONObject toJson() {
		JSONObject jo = new JSONObject();

		jo.put("name", this.name);
		jo.put("id", this.Id);
		jo.put("numberOfDevices", this.devices.size());
		jo.put("faultyDevices", this.faultyDevices());
		jo.put("faultyAccount", this.faultyAccount());
		return jo;
	}

}
