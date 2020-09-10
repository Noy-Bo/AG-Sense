package com.tsofen45.TCP_Server.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.tsofen45.TCP_Server.Repos.DeviceDataRepo;

public class U111 extends Command{

	String message;
	@Autowired
	DeviceDataRepo deviceDataRepo;
	
	
	//U111
	// 0    GPS type
	// V    GPS validity
	// 20/09/02
	// 11:33:07
	// 3245.5886N LAT
	// 03501.4622E LAT
	// 000  SPEED
	// 000  ORIN.
	// 000  ALTTIUDE.
	// 00000000 Mileage Data
	// 0    Satellites
	// 00   HDOP
	// 25   GCM signal
	// 90000000 STATE
	public U111(String msg) {
		this.message = msg;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		parse_data(message);
	}
	
	private LocalDateTime get_date_time(String date_time){
        String date = date_time.substring(0,2) + "-" + date_time.substring(2,4) + "-" + date_time.substring(4,6);
        LocalDate ld = LocalDate.parse(date);
        String time = date_time.substring(6,8) + ":" + date_time.substring(8,10) + ":" + date_time.substring(10,12);
        LocalTime lt = LocalTime.parse(time);
        return  LocalDateTime.of(ld,lt);
    }
	
    @Override
    protected void parse_data(String msg) {
    	
        deviceData.setImei(Long.parseLong(msg.substring(1, 15)));
        deviceData.setGpsType(Integer.parseInt(msg, 20));
        deviceData.setGpsValid(msg.charAt(21));
        deviceData.setLon(Long.parseLong(msg.substring(34, 42)));
        deviceData.setLat(Double.parseDouble(msg.substring(44,msg.indexOf('E'))));
        //speed is always integer!!!
        deviceData.setSpeed(Float.parseFloat(msg.substring(msg.indexOf('E')+1,msg.indexOf('E')+3)));
        deviceData.setOrientation(Integer.parseInt(msg.substring(msg.indexOf('E')+4,msg.indexOf('E')+7)));
        //we need to continue for the rest of the fields
        
        
        
        
        //checking the right data type for date and time
        //deviceData.setDateAndTime(dateAndTime);
        
        
        deviceDataRepo.save(deviceData);
        
//        this.deviceData.setGpsType(Integer.parseInt(data.substring(0,1)));
//        this.deviceData.setGpsValid(data.charAt(1));
//        //this.deviceData.setDateAndTime(get_date_time(data.substring(2,14)));
//        this.deviceData.setLon(Double.parseDouble(data.substring(14,24)));
//        this.deviceData.setLat(Double.parseDouble(data.substring(24,35)));
//        this.deviceData.setSpeed(Integer.parseInt(data.substring(35,38)));
//        this.deviceData.setOrientation(Integer.parseInt(data.substring(38,41)));
//        this.deviceData.setAltitude(data.substring(41,44));
//        this.deviceData.setMileage(data.substring(44,52));
//        this.deviceData.setSatelites(Integer.parseInt(data.substring(52,53)));
//        this.deviceData.setHdop(Integer.parseInt(data.substring(53,55)));
//        this.deviceData.setGsmSignal(Integer.parseInt(data.substring(55,57)));
//        this.deviceData.setState(data.substring(57,65));

    }

	@Override
	protected void parse_optional(String opt) {
		// TODO Auto-generated method stub
		
	}

}
