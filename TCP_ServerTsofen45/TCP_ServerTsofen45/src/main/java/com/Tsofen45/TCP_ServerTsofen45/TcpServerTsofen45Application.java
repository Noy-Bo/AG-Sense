package com.Tsofen45.TCP_ServerTsofen45;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.Tsofen45.TCP_ServerTsofen45.MessageHandler.DeviceMessageHandler;




@SpringBootApplication
public class TcpServerTsofen45Application {

	static int port = 1025;
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TcpServerTsofen45Application.class, args);


		//The purpose of this class is to make thread for each message that is recieved from the device 
		try {

			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Server is up");
			while(true)
			{
				//Open a socket for new device
				Socket socket = serverSocket.accept();
				
				//Getting the input stream from that socket
				InputStream is = socket.getInputStream();
				
				//getting the data from input stream
				DataInputStream dis = new DataInputStream(is);
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				
				//making the thread
				DeviceMessageHandler dvcHandler = new DeviceMessageHandler();
				dvcHandler.setDis(dis);
				dvcHandler.setDos(dos);
				
				Thread t = new Thread(dvcHandler);
				
				t.start();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
