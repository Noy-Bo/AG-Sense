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

@Entity
public class Account {
	String Text;
	int Id;
	List<Device> devices;
	

	
  
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(String text, int id) {
		super();
		Text = text;
		Id = id;
	}
	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
	@OneToMany
	public List<Device> getDevices() {
		return devices;
	}
	@Column
	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	
	

}
