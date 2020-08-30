package com.example.ServerTsofen45.Beans;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public abstract class DeviceData {

	long ID; // imei
	int GpsType;
	char GpsValidity;
	Time DeviceDateAndTime;
	String Latitude;
	char LatitudeIndicator;
	String Longitude;
	char LongitudeIndicator;
	float Speed;
	int Orientation;
	String Altitude;
	String MileageData;
	int Satellites;
	int Hdop;
	int GsmSignal;
	boolean Input1Activated;  // for private companies ATM
	boolean Switch1Activated; // for private companies ATM
	boolean Switch2Activated; // for private companies ATM
	boolean SesmoActivated;   // for private companies ATM
	boolean CustomInputBit0;
	boolean CustomInputBit1;
	boolean customInputBit2;
	boolean customInputBit3;
	boolean PowerCut;
	boolean FuelCut;
	boolean DoorLocked;
	boolean DoorUnlocked;
	boolean MoveAlertActive;
	boolean SpeedingAlertActive;
	boolean outOfGeoFenceAlertActive;
	boolean IntoGeoFenceAlertActive;
	boolean CustomAlertBit0;
	boolean CustomAlertBit1;
	boolean CustomAlertBit2;
	boolean CustomAlertBit3;
	boolean WorkingMode1;
	boolean WorkingMode2;
	boolean WorkingMode3;
	boolean WorkingMode4;
	boolean HarshBrake;
	boolean HarshAccelerate;
	boolean HarshTurnRight;
	boolean HarshTurnLeft;
	float DataOfPower;
	float DataOfTemperature;
	float DataOfFuelSensor;
	float humidity;
	float analog1;
	float analog2;
	
	
	
	public long getID() {
		return ID;
	}
	@Id
	public void setID(long iD) {
		ID = iD;
	}
	public int getGpsType() {
		return GpsType;
	}
	@Column
	public void setGpsType(int gpsType) {
		GpsType = gpsType;
	}
	public char getGpsValidity() {
		return GpsValidity;
	}
	@Column
	public void setGpsValidity(char gpsValidity) {
		GpsValidity = gpsValidity;
	}
	public Time getDeviceDateAndTime() {
		return DeviceDateAndTime;
	}
	@Column
	public void setDeviceDateAndTime(Time deviceDateAndTime) {
		DeviceDateAndTime = deviceDateAndTime;
	}
	public String getLatitude() {
		return Latitude;
	}
	@Column
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public char getLatitudeIndicator() {
		return LatitudeIndicator;
	}
	@Column
	public void setLatitudeIndicator(char latitudeIndicator) {
		LatitudeIndicator = latitudeIndicator;
	}
	public String getLongitude() {
		return Longitude;
	}
	@Column
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public char getLongitudeIndicator() {
		return LongitudeIndicator;
	}
	@Column
	public void setLongitudeIndicator(char longitudeIndicator) {
		LongitudeIndicator = longitudeIndicator;
	}
	public float getSpeed() {
		return Speed;
	}
	@Column
	public void setSpeed(float speed) {
		Speed = speed;
	}
	public int getOrientation() {
		return Orientation;
	}
	@Column
	public void setOrientation(int orientation) {
		Orientation = orientation;
	}
	public String getAltitude() {
		return Altitude;
	}
	@Column
	public void setAltitude(String altitude) {
		Altitude = altitude;
	}
	public String getMileageData() {
		return MileageData;
	}
	@Column
	public void setMileageData(String mileageData) {
		MileageData = mileageData;
	}
	public int getSatellites() {
		return Satellites;
	}
	@Column
	public void setSatellites(int satellites) {
		Satellites = satellites;
	}
	public int getHdop() {
		return Hdop;
	}
	@Column
	public void setHdop(int hdop) {
		Hdop = hdop;
	}
	public int getGsmSignal() {
		return GsmSignal;
	}
	@Column
	public void setGsmSignal(int gsmSignal) {
		GsmSignal = gsmSignal;
	}
	public boolean isInput1Activated() {
		return Input1Activated;
	}
	@Column
	public void setInput1Activated(boolean input1Activated) {
		Input1Activated = input1Activated;
	}
	public boolean isSwitch1Activated() {
		return Switch1Activated;
	}
	@Column
	public void setSwitch1Activated(boolean switch1Activated) {
		Switch1Activated = switch1Activated;
	}
	public boolean isSwitch2Activated() {
		return Switch2Activated;
	}
	@Column
	public void setSwitch2Activated(boolean switch2Activated) {
		Switch2Activated = switch2Activated;
	}
	public boolean isSesmoActivated() {
		return SesmoActivated;
	}
	@Column
	public void setSesmoActivated(boolean sesmoActivated) {
		SesmoActivated = sesmoActivated;
	}
	public boolean isCustomInputBit0() {
		return CustomInputBit0;
	}
	@Column
	public void setCustomInputBit0(boolean customInputBit0) {
		CustomInputBit0 = customInputBit0;
	}
	public boolean isCustomInputBit1() {
		return CustomInputBit1;
	}
	@Column
	public void setCustomInputBit1(boolean customInputBit1) {
		CustomInputBit1 = customInputBit1;
	}
	public boolean isCustomInputBit2() {
		return customInputBit2;
	}
	@Column
	public void setCustomInputBit2(boolean customInputBit2) {
		this.customInputBit2 = customInputBit2;
	}
	public boolean isCustomInputBit3() {
		return customInputBit3;
	}
	@Column
	public void setCustomInputBit3(boolean customInputBit3) {
		this.customInputBit3 = customInputBit3;
	}
	public boolean isPowerCut() {
		return PowerCut;
	}
	@Column
	public void setPowerCut(boolean powerCut) {
		PowerCut = powerCut;
	}
	public boolean isFuelCut() {
		return FuelCut;
	}
	@Column
	public void setFuelCut(boolean fuelCut) {
		FuelCut = fuelCut;
	}
	public boolean isDoorLocked() {
		return DoorLocked;
	}
	@Column
	public void setDoorLocked(boolean doorLocked) {
		DoorLocked = doorLocked;
	}
	public boolean isDoorUnlocked() {
		return DoorUnlocked;
	}
	@Column
	public void setDoorUnlocked(boolean doorUnlocked) {
		DoorUnlocked = doorUnlocked;
	}
	public boolean isMoveAlertActive() {
		return MoveAlertActive;
	}
	@Column
	public void setMoveAlertActive(boolean moveAlertActive) {
		MoveAlertActive = moveAlertActive;
	}
	public boolean isSpeedingAlertActive() {
		return SpeedingAlertActive;
	}
	@Column
	public void setSpeedingAlertActive(boolean speedingAlertActive) {
		SpeedingAlertActive = speedingAlertActive;
	}
	public boolean isOutOfGeoFenceAlertActive() {
		return outOfGeoFenceAlertActive;
	}
	@Column
	public void setOutOfGeoFenceAlertActive(boolean outOfGeoFenceAlertActive) {
		this.outOfGeoFenceAlertActive = outOfGeoFenceAlertActive;
	}
	public boolean isIntoGeoFenceAlertActive() {
		return IntoGeoFenceAlertActive;
	}
	@Column
	public void setIntoGeoFenceAlertActive(boolean intoGeoFenceAlertActive) {
		IntoGeoFenceAlertActive = intoGeoFenceAlertActive;
	}
	public boolean isCustomAlertBit0() {
		return CustomAlertBit0;
	}
	@Column
	public void setCustomAlertBit0(boolean customAlertBit0) {
		CustomAlertBit0 = customAlertBit0;
	}
	public boolean isCustomAlertBit1() {
		return CustomAlertBit1;
	}
	@Column
	public void setCustomAlertBit1(boolean customAlertBit1) {
		CustomAlertBit1 = customAlertBit1;
	}
	public boolean isCustomAlertBit2() {
		return CustomAlertBit2;
	}
	@Column
	public void setCustomAlertBit2(boolean customAlertBit2) {
		CustomAlertBit2 = customAlertBit2;
	}
	public boolean isCustomAlertBit3() {
		return CustomAlertBit3;
	}
	@Column
	public void setCustomAlertBit3(boolean customAlertBit3) {
		CustomAlertBit3 = customAlertBit3;
	}
	public boolean isWorkingMode1() {
		return WorkingMode1;
	}
	@Column
	public void setWorkingMode1(boolean workingMode1) {
		WorkingMode1 = workingMode1;
	}
	public boolean isWorkingMode2() {
		return WorkingMode2;
	}
	@Column
	public void setWorkingMode2(boolean workingMode2) {
		WorkingMode2 = workingMode2;
	}
	public boolean isWorkingMode3() {
		return WorkingMode3;
	}
	@Column
	public void setWorkingMode3(boolean workingMode3) {
		WorkingMode3 = workingMode3;
	}
	public boolean isWorkingMode4() {
		return WorkingMode4;
	}
	@Column
	public void setWorkingMode4(boolean workingMode4) {
		WorkingMode4 = workingMode4;
	}
	public boolean isHarshBrake() {
		return HarshBrake;
	}
	@Column
	public void setHarshBrake(boolean harshBrake) {
		HarshBrake = harshBrake;
	}
	public boolean isHarshAccelerate() {
		return HarshAccelerate;
	}
	@Column
	public void setHarshAccelerate(boolean harshAccelerate) {
		HarshAccelerate = harshAccelerate;
	}
	public boolean isHarshTurnRight() {
		return HarshTurnRight;
	}
	@Column
	public void setHarshTurnRight(boolean harshTurnRight) {
		HarshTurnRight = harshTurnRight;
	}
	public boolean isHarshTurnLeft() {
		return HarshTurnLeft;
	}
	@Column
	public void setHarshTurnLeft(boolean harshTurnLeft) {
		HarshTurnLeft = harshTurnLeft;
	}
	public float getDataOfPower() {
		return DataOfPower;
	}
	@Column
	public void setDataOfPower(float dataOfPower) {
		DataOfPower = dataOfPower;
	}
	public float getDataOfTemperature() {
		return DataOfTemperature;
	}
	@Column
	public void setDataOfTemperature(float dataOfTemperature) {
		DataOfTemperature = dataOfTemperature;
	}
	public float getDataOfFuelSensor() {
		return DataOfFuelSensor;
	}
	@Column
	public void setDataOfFuelSensor(float dataOfFuelSensor) {
		DataOfFuelSensor = dataOfFuelSensor;
	}
	public float getHumidity() {
		return humidity;
	}
	@Column
	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}
	
	public float getAnalog1() {
		return analog1;
	}
	@Column
	public void setAnalog1(float analog1) {
		this.analog1 = analog1;
	}
	@Column
	public float getAnalog2() {
		return analog2;
	}
	public void setAnalog2(float analog2) {
		this.analog2 = analog2;
	}	
}
