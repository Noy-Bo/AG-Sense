package com.example.ServerTsofen45.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
@Entity(name="users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class User {
	String Email;
	String UserName;
	String Password;
	int sys_id;
	String Type;
	
	@Column
    public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	@Column
    public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
    @Column
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
    @Column
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
    @Id
    @GeneratedValue
	public int getSys_id() {
		return sys_id;
	}
	public void setSys_id(int sys_id) {
		this.sys_id = sys_id;
	}



}