package com.example.ServerTsofen45.Beans;
import java.sql.Timestamp;
import java.util.*;  
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ServerTsofen45.Repo.VerificationRepository;


@Entity
@Table(name = "VerificationsCode")
public class Verification {
	
	String userName;
	String code;
	Timestamp SentTime;
	boolean verified;
	

	@Column
	@Id
	public String getUserName() {
		return userName;
	}
	
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column
	public Timestamp getSentTime() {
		return SentTime;
	}

	public void setSentTime(Timestamp sentTime) {
		SentTime = sentTime;
	}

	
	@Column
	public boolean isVerified() {
		return verified;
	}


	public void setVerified(boolean verified) {
		this.verified = verified;
	}



	public boolean sendVerificationMail(String mail) {
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		// Get a Properties object
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");
		final String username = "noreply.Agsense@gmail.com"; // this is the sender email address
		final String password = "C35GcMhfSr73Yb?e4*MFaC";   // this is the sender email password
		try{
			Session session = Session.getDefaultInstance(props, 
					new Authenticator(){
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}});
		

this.setCode(RandomNumber());
this.setSentTime(new Timestamp(System.currentTimeMillis()));




			Message msg = new MimeMessage(session);
			//  Multipart multipart = new MimeMultipart();
			//  multipart.addBodyPart(imagePart);
			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress("xxxx@gmail.com"));
			String To = mail; 				 // this is the receiver email address
			String EditedTo = new String();
			EditedTo = To;
			EditedTo = EditedTo.replaceAll("(\\w{1,2})(\\w+)(@.*)", "$1******$3");

			msg.setRecipients(Message.RecipientType.TO, 
					InternetAddress.parse(To,false));

			msg.setSubject("Ag-Sense account password reset");
			//  msg.setContent(multipart);
			msg.setContent(
					"<html>\r\n" + 
							"<head>\r\n" + 
							"<style type=\"text/css\">\r\n" + 
							"<!--\r\n" + 
							"body { font-family: Arial; font-size: 24.2px }\r\n" + 
							".pos { position: absolute; z-index: 0; left: 0px; top: 0px }\r\n" + 
							"-->\r\n" + 
							"</style>\r\n" + 
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<nobr><nowrap>\r\n" + 
							"<div class=\"pos\" id=\"_0:0\" style=\"top:0\">\r\n" + 
							"<img src=\"cid:image1\" height=\"84\" width=\"241\" border=\"0\" usemap=\"#Map\"></div>\r\n" + 
							"<div class=\"pos\" id=\"_100:104\" style=\"top:104;left:100\">\r\n" + 
							"<span id=\"_17.7\" style=\" font-family:Arial; font-size:17.7px; color:#6f6f6f\">\r\n" + 
							"Ag-sense</span>\r\n" + 
							"<br>"+
							"<br>" +
							"</div>\r\n" + 
							"<div class=\"pos\" id=\"_100:208\" style=\"top:208;left:100\">\r\n" + 
							"<span id=\"_39.1\" style=\" font-family:Arial; font-size:39.1px; color:#2571ec\">\r\n" + 
							"Password reset code</span>\r\n" + 
							"<br>"+
							"<br>" +
							"</div>\r\n" + 
							"<div class=\"pos\" id=\"_100:339\" style=\"top:339;left:100\">\r\n" + 
							"<span id=\"_13.9\" style=\" font-family:Arial; font-size:13.9px; color:#2a2a2a\">\r\n" + 
							"Please use this code to reset the password for the Ag-Sense account "+EditedTo+".</span>\r\n" + 
							"</div>\r\n" + 
							"<br>"+
							"<div class=\"pos\" id=\"_100:384\" style=\"top:384;left:100\">\r\n" + 
							"<span id=\"_13.9\" style=\" font-family:Arial; font-size:13.9px; color:#2a2a2a\">\r\n" + 
							"Here is your code: <span style=\"font-weight:bold\"> "+ this.getCode() +"</span></span>\r\n" + 
							"</div>\r\n" + 
							"<br>"+
							"<div class=\"pos\" id=\"_100:430\" style=\"top:430;left:100\">\r\n" + 
							"<span id=\"_13.9\" style=\" font-family:Arial; font-size:13.9px; color:#2a2a2a\">\r\n" + 
							"If you don't recognize the Ag-Sense account "+EditedTo+", you can click here to remove </span>\r\n" + 
							"</div>\r\n" + 
							"<div class=\"pos\" id=\"_100:449\" style=\"top:449;left:100\">\r\n" + 
							"<span id=\"_13.9\" style=\" font-family:Arial; font-size:13.9px; color:#2a2a2a\">\r\n" + 
							"your email address from that account.</span>\r\n" + 
							"</div>\r\n" + 
							"<br>"+
							"<div class=\"pos\" id=\"_100:495\" style=\"top:495;left:100\">\r\n" + 
							"<span id=\"_13.9\" style=\" font-family:Arial; font-size:13.9px; color:#2a2a2a\">\r\n" + 
							"Thanks,</span>\r\n" + 
							"</div>\r\n" + 
							"<div class=\"pos\" id=\"_100:514\" style=\"top:514;left:100\">\r\n" + 
							"<span id=\"_13.9\" style=\" font-family:Arial; font-size:13.9px; color:#2a2a2a\">\r\n" + 
							"The Ag-sense account team</span>\r\n" + 
							"</div>\r\n" + 
							"</nowrap></nobr>\r\n" + 
							"</body>\r\n" + 
							"</html>\r\n" + 
							"",
					"text/html");


			msg.setSentDate(new Date());
			Transport.send(msg);
			return true;
		}
		
		catch (MessagingException e)
		{ System.out.println("Erreur d'envoi, cause: " + e);
		return false;
		}
		
	}
	
	private String RandomNumber() {
	    // It will generate 6 digit random Number.
	    // from 0 to 999999
	    Random rnd = new Random();
	    int number = rnd.nextInt(999999);

	    // this will convert any number sequence into 6 character.
	    return String.format("%06d", number);
	}
	
}