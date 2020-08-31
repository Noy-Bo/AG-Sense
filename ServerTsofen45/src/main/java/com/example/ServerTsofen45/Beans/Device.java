package com.example.ServerTsofen45.Beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name="devices")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Device {
	int id;
	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	
}
