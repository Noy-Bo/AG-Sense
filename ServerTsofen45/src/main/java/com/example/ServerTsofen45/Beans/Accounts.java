package com.example.ServerTsofen45.Beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Accounts {
	String Text;
	int Id;
	List<Device> devices;
	
	@OneToMany
	public List<Device> getDevices() {
		return devices;
	}
	public void setDevices(ArrayList<Device> devices) {
		this.devices = devices;
	}
	@Column
	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
	}
	@Id
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	
	

}
