package com.example.ServerTsofen45.controllers;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ServerTsofen45.BL.DeviceBL;
import com.example.ServerTsofen45.BL.NotificationBL;
import com.example.ServerTsofen45.BL.UserBL;
import com.example.ServerTsofen45.BL.VerificationBL;
import com.example.ServerTsofen45.Beans.User;
import com.example.ServerTsofen45.Beans.Verification;
import com.example.ServerTsofen45.Repo.UserRepository;
import com.example.ServerTsofen45.Repo.VerificationRepository;

@RestController
@RequestMapping("Verification")
public class VerificationController {

	@Autowired
	NotificationBL notificationBL;

	@Autowired
	VerificationRepository verificationRepository;

	@Autowired
	VerificationBL verificationBL ;

	@Autowired
	UserBL userBL;

	@Autowired
	DeviceBL deviceBL;
	@Autowired
	UserRepository userRepository;
	
//	ConfirmCode("/User/confirmCode"), //username String, code String
//    ConfirmPassword("/User/confirmPassword"),    //username String, password String
    
    
   // EmailPicked("/User/EmailPicked"); //username String
	
	
		@GetMapping("VerificationMethod")
		  public boolean  VerificationMethod(@RequestParam String userName, @RequestParam String method)     // method = email / sms
			{
			
			User user = userRepository.findByUserName(userName);
			Verification verification = new Verification();
			verification.setUserName(userName);


			if(method.equals("email")) {

				if(verification.sendVerificationMail(user.getEmail())) {
					verification.setVerified(false);
					verificationRepository.save(verification);
					return(true);
				}
			}



			return(false);

			}

		
		@GetMapping("ConfirmCode")
		  public boolean  ConfirmCode(@RequestParam String userName, @RequestParam String code)  
			{
			

			Verification verification = verificationBL.findByUsername(userName);


			if(compareTwoTimeStamps(new Timestamp(System.currentTimeMillis()), verification.getSentTime()) < 120) {  // after 2 mints the verification code will not work  
				if(verification.getCode().equals(code)) {

					verification.setVerified(true);
					verificationRepository.save(verification);
					return true;
				}

			}



			return false ;

			}

		
		@GetMapping("ResetPassword")
		  public boolean  ResetPassword(@RequestParam String userName, @RequestParam String code, @RequestParam String password)  
			{
			

			Verification verification = verificationBL.findByUsername(userName);
			User user = userRepository.findByUserName(userName);
			
			
				if(verification.isVerified() && verification.getCode().equals(code)) {
					
					userBL.setPass(user.getSysId(), user.hashPassword(password));
					 verificationBL.deleteVerificationCode(userName);
					return true;
				}
				
			
			return false ;

			}

		
		

		public static long compareTwoTimeStamps(java.sql.Timestamp currentTime, java.sql.Timestamp oldTime)
		{
			long milliseconds1 = oldTime.getTime();
			long milliseconds2 = currentTime.getTime();

			long diff = milliseconds2 - milliseconds1;
			long diffSeconds = diff / 1000;


			return diffSeconds;
		}


}