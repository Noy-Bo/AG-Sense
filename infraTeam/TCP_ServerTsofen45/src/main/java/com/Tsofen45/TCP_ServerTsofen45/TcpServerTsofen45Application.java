package com.Tsofen45.TCP_ServerTsofen45;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.Tsofen45.TCP_ServerTsofen45.Disconnected.ReportTimer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.Tsofen45.TCP_ServerTsofen45.MessageHandler.DeviceMessageHandler;




@SpringBootApplication
public class TcpServerTsofen45Application {

	static int port = 1025;
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TcpServerTsofen45Application.class, args);
//		ReportTimer reportTimer = context.getBean(ReportTimer.class);
//		reportTimer.load_imei();
//		reportTimer.start_tasks();
		//The purpose of this class is to make thread for each message that is recieved from the device 
		try {

			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Server is up");

			while(true)
			{
				//Open a socket for new device
				Socket socket = serverSocket.accept();
				
				//Getting the input stream from that socket
				InputStream is = socket.getInputStream();
				
				//making the thread
				DeviceMessageHandler dvcHandler = context.getBean(DeviceMessageHandler.class);
				dvcHandler.setInputStrae(is);
				
				
				Thread t = new Thread(dvcHandler);
				
				t.start();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {

		}
	}
}
