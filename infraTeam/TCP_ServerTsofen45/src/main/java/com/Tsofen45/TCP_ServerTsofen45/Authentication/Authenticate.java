package com.Tsofen45.TCP_ServerTsofen45.Authentication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.Tsofen45.TCP_ServerTsofen45.Device.Device;
import com.Tsofen45.TCP_ServerTsofen45.Repos.DeviceRepository;

import antlr.collections.List;
import javassist.bytecode.Descriptor.Iterator;

@Service
public class Authenticate {
	
	private boolean firstFlag = false;
	@Autowired
	private DeviceRepository devicerRepo;
	//TODO need to add imies at the beggining
	private Iterable<Device> imeies;
	
    private HashSet<Long> hashSetImeies = new HashSet<Long>();
    private ReentrantReadWriteLock imeisHashLock = new ReentrantReadWriteLock();
    
    public boolean check_imei(String imei)
    {
    	loadImeiesAtBeg();
    	try
    	{
    		imeisHashLock.readLock().lock();
	    	if(!hashSetImeies.contains(Long.parseLong(imei.substring(1,16)))) {
	    		return false;
	    	}
	    	System.out.println("Imeie: "+imei.substring(1,16)+" has been found!");
    	}
    	finally
    	{
    		imeisHashLock.readLock().unlock();
    	}
        return true;
    }
    
    public void loadImeiesAtBeg() {
    	if(!firstFlag) {
    		//loadDeviceData the imes from the data base;
    		imeies = devicerRepo.findAll();	
    		for(Device device : imeies)
    		{
    			addNewImeie(device.getImei());
    		}
    		firstFlag = true;
    	}
    }
    public void addNewImeie(long imeie) {
    	try
    	{
    		this.imeisHashLock.writeLock().lock();
    		hashSetImeies.add(imeie);
    	}
    	finally
    	{
    		this.imeisHashLock.writeLock().unlock();
    	}
    }

}
