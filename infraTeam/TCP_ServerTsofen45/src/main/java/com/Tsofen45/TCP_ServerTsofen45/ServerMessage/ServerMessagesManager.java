package com.Tsofen45.TCP_ServerTsofen45.ServerMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerMessagesManager {
	@Autowired
	AddDevice addDevice;
	
	void analyze(String s) {
		addDevice.Analyze(s);
	}
}
