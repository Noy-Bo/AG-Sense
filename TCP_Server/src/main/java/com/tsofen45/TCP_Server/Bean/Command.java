package com.tsofen45.TCP_Server.Bean;

public abstract class Command implements Runnable {
	
	DeviceData deviceData;
	
	protected abstract void parse_optional(String opt);
   
	//get the command we need to check
    public static String get_cmd(String msg) {

        return msg.substring(16,20);
    }
    
    //check sum if valid
    public static String getCheckSum(String str) {
        int xor = str.charAt(0);
        for (int i = 1; i < str.length(); i++)
            xor ^= str.charAt(i);
        return Integer.toHexString(xor);
    }
    
    //check if the deviceData exists
    public static boolean check_imei(String imei)
    {
        return true;
    }

    //unserilized function
    public boolean unserialize(String msg){
    	String checksum_value = msg.substring(msg.indexOf("^") + 1, msg.indexOf(")")); // recives the checksum value from the string
        String calc_checksum = getCheckSum(msg.substring(1, msg.indexOf("^")));    // calculates the checksum of the data
        String imei = msg.substring(1,16);
        if (checksum_value.equals(calc_checksum)) {
            if (check_imei(imei)) {
                String data;
                if (msg.contains(","))
                    data = msg.substring(20, msg.indexOf(","));
                else
                    data = msg.substring(20, msg.indexOf("^"));
                deviceData.setImei(Long.parseLong(imei));
            }
        }
        return false;
    }

	protected abstract void parse_data(String data);

}
