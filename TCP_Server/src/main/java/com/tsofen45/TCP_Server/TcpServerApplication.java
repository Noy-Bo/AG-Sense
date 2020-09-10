package com.tsofen45.TCP_Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.tsofen45.TCP_Server.Repos.DeviceDataRepo;
import com.tsofen45.TCP_Server.TCP.DeviceMessageHandler;

@SpringBootApplication
public class TcpServerApplication {

	static int port = 1025;
	public static void main(String[] args) {
		ApplicationContext context= SpringApplication.run(TcpServerApplication.class, args);
		
		try {
			
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Server is up");
			while(true)
			{
				Socket socket = serverSocket.accept();
				InputStream is = socket.getInputStream();
				DataInputStream dis = new DataInputStream(is);
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				System.out.println("dosssss");
				Thread t = new Thread(new DeviceMessageHandler(dis,dos));
				t.run();
				

			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
