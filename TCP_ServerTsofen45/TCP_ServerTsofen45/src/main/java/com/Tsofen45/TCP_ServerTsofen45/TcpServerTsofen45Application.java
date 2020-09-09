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

//		String message= "(864403044134030U1110A2007261356023245.5325N03501.3722E0001800000000001F6012740000000,&P1160,A10203,A2014A^44)";
//		CommandsFactory commandsFactory = context.getBean(CommandsFactory.class);
//		commandsFactory.setMessage(message);
//		Thread t1 =  new Thread(commandsFactory);
//		t1.start();
		try {

			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Server is up");
			while(true)
			{
				Socket socket = serverSocket.accept();
				InputStream is = socket.getInputStream();
				DataInputStream dis = new DataInputStream(is);
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				Thread t = new Thread(new DeviceMessageHandler(dis,dos));
				t.start();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
