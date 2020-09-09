package com.Tsofen45.TCP_ServerTsofen45.Analyzation;

import org.springframework.stereotype.Service;

@Service
public class MoveAnalyzer extends Analyzer{

	@Override
	public void Analyze(String s) {
		// TODO Auto-generated method stub
		if(Integer.parseInt(s) > 0) {
			//Push Notification
			System.out.println("Sending notificatin on device moved");
		}
	}
	
	
}
