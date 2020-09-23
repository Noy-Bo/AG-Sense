package com.tsofen.agsenceapp.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class MockData {
    static ArrayList<Account>  MockuoAccount = new ArrayList<>();
    static ArrayList<Devices>  MockupDevices = new ArrayList<>();
    static ArrayList<Notification>  MockupNotification = new ArrayList<>();
    static ArrayList<DeviceData> MockupDeviceData = new ArrayList<>();

    public MockData() {
        //adding accounts
        MockuoAccount.add( new Account(1, "acc1","example@email.com" , false, 1));
        MockuoAccount.add( new Account(2, "acc2","example@email.com" , true, 2));
        MockuoAccount.add( new Account(3, "acc3","example@email.com" , true, 2));

        //Devices for acc 1

        //Device1 healthy
        MockupDevices.add(new Devices(1,1,1,"Tank" , null , null,false));


        //Device2 healthy
        MockupDevices.add(new Devices(2,2,1,"Tank" , null , null,false));

        //Devices for acc 2
        //Device3 faulty
        MockupDevices.add(new Devices(3,3,2,"ATM" , null , null,true));
    }

    public static ArrayList<Account> getMockuoAccount() {
        return MockuoAccount;
    }

    public static ArrayList<Devices> getMockupDevices() {
        return MockupDevices;
    }

    public static ArrayList<Notification> getMockupNotification() {
        return MockupNotification;
    }

    public static ArrayList<DeviceData> getMockupDeviceData() {
        return MockupDeviceData;
    }
}
