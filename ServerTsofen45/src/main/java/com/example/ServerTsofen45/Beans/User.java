package com.example.ServerTsofen45.Beans;

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
	String hashPassword;
	
	
	


	@Column
	public String getname() {
		return name;
	}

	public void setname(String firstName) {
		this.name = firstName;
	}

	@Column
	public String getHashPassword() {
		return hashPassword;
	}

	protected void setHashPassword(String Password)  {
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

	/*private byte[] hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

		return hash;
	}*/
	
	protected String hashPassword(String base)
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
	return "User [email=" + email + ", name=" + name + ", userName=" + userName + ", sysId=" + sysId +
			 "]";
}

}
