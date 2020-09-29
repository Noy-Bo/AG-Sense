package com.Tsofen45.TCP_ServerTsofen45.UPCommands;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;

public class U111 extends UpCommand{
	//U111
	// 0    GPS type
	// V    GPS validity
	// 20/09/02
	// 11:33:07
	// 3245.5886 Lon
	// N lonIndicator
	// 03501.4622 LAT
	// E LatIndicator
	// 000  SPEED
	// 000  ORIN.
	// 000  ALTTIUDE.
	// 00000000 Mileage Data
	// 0    Satellites
	// 00   HDOP
	// 25   GCM signal
	// 90000000 STATE
	  public U111(){
	        super();
	    }

	    private LocalDateTime get_date_time(String date_time){
	        String date = "20"+date_time.substring(0,2) + "-" + date_time.substring(2,4) + "-" + date_time.substring(4,6);
	        LocalDate ld = LocalDate.parse(date);
	        String time = date_time.substring(6,8) + ":" + date_time.substring(8,10) + ":" + date_time.substring(10,12);
	        LocalTime lt = LocalTime.parse(time);
	        return  LocalDateTime.of(ld,lt);
	    }
	    
	    @Override
	    public DeviceData parse_data(String msg) {
	    	
	    	
            if (msg.contains(","))
                data = msg.substring(20, msg.indexOf(","));
            else
                data = msg.substring(20, msg.indexOf("^"));
	    	
            
            deviceData.setImei(Long.parseLong(msg.substring(1,16)));
	        deviceData.setGpsType(Integer.parseInt(data.substring(0,1)));
	        deviceData.setGpsValid(data.substring(1,2));
	        deviceData.setDateAndTime(get_date_time(data.substring(2,14)));
	        deviceData.setLonIndicator(data.substring(23,24));
	        deviceData.setLon((data.substring(14,16)) + " " + (data.substring(16,24)));
	        deviceData.setLat((data.substring(24,27)) + " " + (data.substring(27,34)));
	        deviceData.setLatIndicator(data.substring(34,35));
	        deviceData.setSpeed(Integer.parseInt(data.substring(35,38)));
	        deviceData.setOrientation(Integer.parseInt(data.substring(38,41)));
	        deviceData.setAltitude(data.substring(41,44));
	        deviceData.setMileage(data.substring(44,52));
	        deviceData.setSatelites(Integer.parseInt(data.substring(52,53)));
	        deviceData.setHdop(Integer.parseInt(data.substring(53,55)));
	        deviceData.setGsmSignal(Integer.parseInt(data.substring(55,57)));
	        deviceData.setState(data.substring(57,65));
	        
	        parse_optional(msg.substring(msg.indexOf("&")+1,msg.indexOf("^")));
	        
	        return deviceData;

	        
	    }

	    public void parse_optional(String opt)
	    {
	        String[] opts = opt.split(",");
	        for(int i = 0;i <opts.length; i++) {
	            switch (opts[i].charAt(0)) {
	                case 'P':
	                    float power;
	                    switch (opts[i].charAt(1)){
	                        case '1':
	                            if(opts[i].charAt(4) == '%') {
	                                power = Integer.parseInt(opts[i].substring(2,4));
	                                deviceData.setInternalBatteryPower(power);
	                            }
	                            else {
	                                power = ((float)Integer.parseInt(opts[i].substring(2), 16))/100;
	                                deviceData.setInternalBatteryPower(power);
	                            }
	                            break;
	                        case '2':
	                            power = ((float)Integer.parseInt(opts[i].substring(2), 16))/100;
	                            deviceData.setExternalPower(power);
	                            break;
	                    }
	                    break;
	                case 'A':
	                    float analog = ((float)Integer.parseInt(opts[i].substring(2), 16))/100;
	                    switch (opts[i].charAt(1)){
	                        case '1':
	                        	deviceData.setAnalog1(analog);
	                            break;
	                        case '2':
	                        	deviceData.setAnalog2(analog);
	                            break;
	                    }
	                    break;
	                case 'T':
	                    float temp = (float) (Integer.parseInt(opts[i].substring(2),16) * 0.25);
	                    switch (opts[i].charAt(1)){
	                        case '1':
	                        	deviceData.setTemperatureExternal(temp);
	                            break;
	                        case '0':
	                        	deviceData.setTemperatureInsideDevice(temp);
	                            break;
	                    }
	                    break;
	                case 'F':
	                    switch (opts[i].charAt(1)){
	                        case '1':
	                            float fuelV = ((float)Integer.parseInt(opts[i].substring(2), 16))/100;
	                            deviceData.setDataOfFuelSensor(fuelV);
	                            break;
	                    }
	                    break;
	                case 'W':
	                    int deviceWeight = Integer.parseInt(opts[i].substring(2),16);
	                    switch (opts[i].charAt(1)){
	                        case '1':
	                            // device.setWeight(deviceWeight);
	                            break;
	                    }
	                    break;
	                case 'H':
	                    switch (opts[i].charAt(1)){
	                        case '1':
	                            int deviceHumidity = Integer.parseInt(opts[i].substring(2),16);
	                            deviceData.setHumidity(deviceHumidity);
	                            break;
	                    }
	                    break;
	                case 'B':
	                    switch (opts[i].charAt(1)){
	                        case '1':
	                            String sub_string = opts[i].substring(2);
	                            String[] data = sub_string.split(".");
	                            /*this.setMcc(data[0]);
	                            this.setMnc(data[1]);
	                            this.setLac(data[2]);
	                            this.setCellid(data[4]);*/
	                            break;
	                    }
	                    break;
	                case 'D':
	                    int distance = Integer.parseInt(opts[i].substring(2),16);
	                    switch (opts[i].charAt(1)){
	                        case '1':
	                        	deviceData.setDistance(distance);
	                            break;
	                    }
	                    break;
	                case 'C':
	                    int device_counter;
	                    switch (opts[i].charAt(1)){
	                        case'1':
	                            device_counter = Integer.parseInt(opts[i].substring(2),16);
	                            //device.setCounter(device_counter);
	                            break;
	                    }
	                    break;
	                case 'R':
	                    switch (opts[i].charAt(1)){
	                        case '1':
	                            String RFID_opts = opts[i].substring(3);
	                            // device.setRfid(RFID_opts);
	                            break;
	                    }
	                    break;
	                case 'S':
	                    switch (opts[i].charAt(1)){
	                        case '1':
	                            int device_pressure = Integer.parseInt(opts[i].substring(2),16);
	                            // this.setPressure(device_pressure);
	                            break;
	                    }
	                    break;
	            }
	        }
	    }


}
