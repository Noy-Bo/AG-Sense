package com.example.ServerTsofen45.Beans;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Accounts {
	String Text;
	int Id;
	ArrayList<Device > devices;
	
	@OneToMany
	public ArrayList<Device> getDevices() {
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
	@Column
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	
	

}
