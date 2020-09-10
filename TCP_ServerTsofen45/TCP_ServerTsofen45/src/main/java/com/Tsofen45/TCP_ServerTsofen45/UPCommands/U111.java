package com.Tsofen45.TCP_ServerTsofen45.UPCommands;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class U111 extends UpCommand{
	  public U111(){
	        super();
	    }

	    private LocalDateTime get_date_time(String date_time){
	        String date = date_time.substring(0,2) + "-" + date_time.substring(2,4) + "-" + date_time.substring(4,6);
	        LocalDate ld = LocalDate.parse(date);
	        String time = date_time.substring(6,8) + ":" + date_time.substring(8,10) + ":" + date_time.substring(10,12);
	        LocalTime lt = LocalTime.parse(time);
	        return  LocalDateTime.of(ld,lt);
	    }
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
	    @Override
	    public boolean parse_data() {
	        if(data == null)
	            return false;
	        this.device.setGpsType(Integer.parseInt(data.substring(0,1)));
	        this.device.setGpsValid(data.substring(1,2));
	        //this.device.setDateAndTime(get_date_time(data.substring(2,14)));
	        this.device.setLon(Double.parseDouble(data.substring(14,23)));
	        String indicator  =  data.substring(23,24);
	        this.device.setLonIndicator(data.substring(23,24));
	        this.device.setLat(Double.parseDouble(data.substring(24,34)));
	        indicator = data.substring(34, 35);
	        this.device.setLatIndicator(data.substring(34,35));
	        this.device.setSpeed(Integer.parseInt(data.substring(35,38)));
	        this.device.setOrientation(Integer.parseInt(data.substring(38,41)));
	        this.device.setAltitude(data.substring(41,44));
	        this.device.setMileage(data.substring(44,52));
	        this.device.setSatelites(Integer.parseInt(data.substring(52,53)));
	        this.device.setHdop(Integer.parseInt(data.substring(53,55)));
	        this.device.setGsmSignal(Integer.parseInt(data.substring(55,57)));
	        this.device.setState(data.substring(57,65));
	        return true;
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
	                                device.setInternalBatteryPower(power);
	                            }
	                            else {
	                                power = ((float)Integer.parseInt(opts[i].substring(2), 16))/100;
	                                device.setInternalBatteryPower(power);
	                            }
	                            break;
	                        case '2':
	                            power = ((float)Integer.parseInt(opts[i].substring(2), 16))/100;
	                            device.setExternalPower(power);
	                            break;
	                    }
	                    break;
	                case 'A':
	                    float analog = ((float)Integer.parseInt(opts[i].substring(2), 16))/100;
	                    switch (opts[i].charAt(1)){
	                        case '1':
	                            device.setAnalog1(analog);
	                            break;
	                        case '2':
	                            device.setAnalog2(analog);
	                            break;
	                    }
	                    break;
	                case 'T':
	                    float temp = (float) (Integer.parseInt(opts[i].substring(2),16) * 0.25);
	                    switch (opts[i].charAt(1)){
	                        case '1':
	                            device.setTemperatureExternal(temp);
	                            break;
	                        case '0':
	                            device.setTemperatureInsideDevice(temp);
	                            break;
	                    }
	                    break;
	                case 'F':
	                    switch (opts[i].charAt(1)){
	                        case '1':
	                            float fuelV = ((float)Integer.parseInt(opts[i].substring(2), 16))/100;
	                            device.setDataOfFuelSensor(fuelV);
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
	                            device.setHumidity(deviceHumidity);
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
	                            device.setDistance(distance);
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

		@Override
		public boolean unserialize(String msg) {
			// TODO Auto-generated method stub
			return false;
		}
}
