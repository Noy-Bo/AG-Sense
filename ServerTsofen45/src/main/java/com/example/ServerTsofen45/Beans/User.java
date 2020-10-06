package com.example.ServerTsofen45.Beans;

import java.security.MessageDigest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.json.simple.JSONObject;


@Entity(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class User {
	String email;
	String name;
	String userName;
	String phoneNumber;
	int notificationFlag;
	int sysId;
	String hashPassword;
	Account account;
	String type;
	@Column
	public String getType() {
		return type;
	}
	public User() {
		super();

		// TODO Auto-generated constructor stub
	}
	
	public void setNotificationFlag(int notificationFlag) {
		this.notificationFlag = notificationFlag;
	}
	
	@Column
	public int getNotificationFlag() {
		return notificationFlag;
	}
	
	public void setType(String type) {
		this.type=type;
}

	
	@Column
	public String getname() {
		return name;
	}

	@ManyToOne
	@JoinColumn(name="AccountId", referencedColumnName="Id")
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;


	}


	public void setname(String firstName) {
		this.name = firstName;
	}

	@Column
	public String getHashPassword() {
		return hashPassword;
	}
	public void setHashPassword(String Password)  {
	//	this.hashPassword =  hashPassword(Password);
		this.hashPassword =  Password;
	}
	@Column
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	@Column
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Id
	@GeneratedValue
	public int getSysId() {
		return sysId;
	}

	public void setSysId(int sysId) {
		this.sysId = sysId;
	}
	public boolean validate(String pass)
	{

		return (hashPassword(pass).equals(this.hashPassword));
	}

	public String hashPassword(String base)
	{
	    try{
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(base.getBytes("UTF-8"));
	        StringBuffer hexString = new StringBuffer();

	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }
	        return hexString.toString();
	    } catch(Exception ex){
	       throw new RuntimeException(ex);
	    }
	}

	@Override
	public String toString() {

		return "User [email=" + email + ", name=" + name + ", userName=" + userName + ", type=" + type + "]";
	}
	
//	public void updateType() {
//		if(this.account==null)
//			this.type="admin";
//		else
//			this.type="account";			
//	}
	@SuppressWarnings("unchecked")
	public JSONObject toJson()
	{
		   JSONObject jo = new JSONObject();

		   jo.put("username", this.userName);
		   jo.put("email", this.email);
		   jo.put("id", this.sysId);
		   jo.put("type", this.type);
		   jo.put("phoneNumber", this.phoneNumber);
		   
		   return jo;
	}

}
