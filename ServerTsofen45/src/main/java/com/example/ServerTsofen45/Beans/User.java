package com.example.ServerTsofen45.Beans;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class User {
	String email;
	String name;
	String userName;
	int sysId;
	String type;
	byte[] hashPassword;

	@Column
	public String getname() {
		return name;
	}

	public void setname(String firstName) {
		this.name = firstName;
	}

	@Column
	public byte[] getHashPassword() {
		return hashPassword;
	}

	public void setHashPassword(byte[] hashPassword) {
		this.hashPassword = hashPassword;
	}

	@Column
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
		try {
			return (hashPassword(pass)==this.hashPassword);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private byte[] hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

		return hash;
	}

	@Override
	public String toString() {
		return "User [Email=" + email + ", firstName=" + name + ", UserName=" + name
				+ ", sys_id=" + sysId + ", Type=" + type + "]";
	}

}
