package com.example.ServerTsofen45.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ServerTsofen45.BL.DeviceBL;
import com.example.ServerTsofen45.BL.ErrorBL;
import com.example.ServerTsofen45.BL.NotificationBL;
import com.example.ServerTsofen45.BL.UserBL;
import com.example.ServerTsofen45.Beans.Notification;
import com.example.ServerTsofen45.Beans.NotificationDTO;
import com.example.ServerTsofen45.Beans.User;
import com.example.ServerTsofen45.Repo.NotificationRepository;
import com.example.ServerTsofen45.pushNotification.PushNotificationsSender;

import Enums.Severity;
import jdk.jfr.BooleanFlag;

import com.example.ServerTsofen45.Beans.Device;
import com.example.ServerTsofen45.Beans.Error;

import springfox.documentation.spring.web.json.Json;

@RestController
@RequestMapping("Verification")
public class VerificationController {

	@Autowired
	NotificationBL notificationBL;

	@Autowired
	NotificationRepository notificationRepo;

	@Autowired
	ErrorBL errorBL;

	@Autowired
	UserBL userBL;

	@Autowired
	DeviceBL deviceBL;

//		@GetMapping("EmailVerification")
//		  public List<NotificationDTO>  getNotifications(@RequestParam String userName)
//			{
//			
//			 
//			}





}