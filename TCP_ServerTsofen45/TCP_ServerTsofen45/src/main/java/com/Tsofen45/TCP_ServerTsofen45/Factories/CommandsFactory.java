package com.Tsofen45.TCP_ServerTsofen45.Factories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;
import com.Tsofen45.TCP_ServerTsofen45.Repos.DeviceDataRepo;
import com.Tsofen45.TCP_ServerTsofen45.UPCommands.U111;
import com.Tsofen45.TCP_ServerTsofen45.UPCommands.UpCommand;



@Service
public class CommandsFactory {

    @Autowired
    DeviceDataRepo deviceDataRepo;

    UpCommand commands;

    
    public DeviceData  makeDeviceData(String message){
        String command = message.substring(16,20);
        try {
            Class UPCommand = Class.forName("com.Tsofen45.TCP_ServerTsofen45.UPCommands."+command);
            Class[] types = {};
            Constructor constructor = UPCommand.getConstructor(types);
            Object[] parameters ={};
            Object commandsObj = constructor.newInstance(parameters);
            commands = (UpCommand) commandsObj;
            if(commands.unserialize(message)){
                commands.parse_data();
                if (commands instanceof U111){
                    ((U111)commands).parse_optional(message.substring(message.indexOf("&")+1,message.indexOf("^")));
                }
            }
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return commands.getDevice();
    }

}