package com.Tsofen45.TCP_ServerTsofen45.Factories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;
import com.Tsofen45.TCP_ServerTsofen45.UPCommands.U111;
import com.Tsofen45.TCP_ServerTsofen45.UPCommands.UpCommand;



@Service
public class CommandsFactory {



    UpCommand commands;

    
    public DeviceData  makeDeviceData(String message){
        String command = message.substring(16,20);
        DeviceData deviceData = new DeviceData();
        switch (command) {
		case "U111":
			U111 comand = new U111();
			deviceData = comand.parse_data(message);
			break;

		default:
			break;
		}
        return deviceData;
    }

}