package com.example.ServerTsofen45.Beans;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name="DevicesData")
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
	@Id
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	@Column
	public int getGpsType() {
		return GpsType;
	}
	public void setGpsType(int gpsType) {
		GpsType = gpsType;
	}
	@Column
	public char getGpsValidity() {
		return GpsValidity;
	}
	public void setGpsValidity(char gpsValidity) {
		GpsValidity = gpsValidity;
	}
	@Column
	public Time getDeviceDateAndTime() {
		return DeviceDateAndTime;
	}
	public void setDeviceDateAndTime(Time deviceDateAndTime) {
		DeviceDateAndTime = deviceDateAndTime;
	}
	@Column
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	@Column
	public char getLatitudeIndicator() {
		return LatitudeIndicator;
	}
	public void setLatitudeIndicator(char latitudeIndicator) {
		LatitudeIndicator = latitudeIndicator;
	}
	@Column
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	@Column
	public char getLongitudeIndicator() {
		return LongitudeIndicator;
	}
	public void setLongitudeIndicator(char longitudeIndicator) {
		LongitudeIndicator = longitudeIndicator;
	}
	@Column
	public float getSpeed() {
		return Speed;
	}
	public void setSpeed(float speed) {
		Speed = speed;
	}
	@Column
	public int getOrientation() {
		return Orientation;
	}
	public void setOrientation(int orientation) {
		Orientation = orientation;
	}
	@Column
	public String getAltitude() {
		return Altitude;
	}
	public void setAltitude(String altitude) {
		Altitude = altitude;
	}
	@Column
	public String getMileageData() {
		return MileageData;
	}
	public void setMileageData(String mileageData) {
		MileageData = mileageData;
	}
	@Column
	public int getSatellites() {
		return Satellites;
	}
	public void setSatellites(int satellites) {
		Satellites = satellites;
	}
	@Column
	public int getHdop() {
		return Hdop;
	}
	public void setHdop(int hdop) {
		Hdop = hdop;
	}
	@Column
	public int getGsmSignal() {
		return GsmSignal;
	}
	public void setGsmSignal(int gsmSignal) {
		GsmSignal = gsmSignal;
	}
	@Column
	public boolean isInput1Activated() {
		return Input1Activated;
	}
	public void setInput1Activated(boolean input1Activated) {
		Input1Activated = input1Activated;
	}
	@Column
	public boolean isSwitch1Activated() {
		return Switch1Activated;
	}
	public void setSwitch1Activated(boolean switch1Activated) {
		Switch1Activated = switch1Activated;
	}
	@Column
	public boolean isSwitch2Activated() {
		return Switch2Activated;
	}
	public void setSwitch2Activated(boolean switch2Activated) {
		Switch2Activated = switch2Activated;
	}
	@Column
	public boolean isSesmoActivated() {
		return SesmoActivated;
	}
	public void setSesmoActivated(boolean sesmoActivated) {
		SesmoActivated = sesmoActivated;
	}
	@Column
	public boolean isCustomInputBit0() {
		return CustomInputBit0;
	}
	public void setCustomInputBit0(boolean customInputBit0) {
		CustomInputBit0 = customInputBit0;
	}
	@Column
	public boolean isCustomInputBit1() {
		return CustomInputBit1;
	}
	public void setCustomInputBit1(boolean customInputBit1) {
		CustomInputBit1 = customInputBit1;
	}
	@Column
	public boolean isCustomInputBit2() {
		return customInputBit2;
	}
	public void setCustomInputBit2(boolean customInputBit2) {
		this.customInputBit2 = customInputBit2;
	}
	@Column
	public boolean isCustomInputBit3() {
		return customInputBit3;
	}
	public void setCustomInputBit3(boolean customInputBit3) {
		this.customInputBit3 = customInputBit3;
	}
	@Column
	public boolean isPowerCut() {
		return PowerCut;
	}
	public void setPowerCut(boolean powerCut) {
		PowerCut = powerCut;
	}
	@Column
	public boolean isFuelCut() {
		return FuelCut;
	}
	public void setFuelCut(boolean fuelCut) {
		FuelCut = fuelCut;
	}
	@Column
	public boolean isDoorLocked() {
		return DoorLocked;
	}
	public void setDoorLocked(boolean doorLocked) {
		DoorLocked = doorLocked;
	}
	@Column
	public boolean isDoorUnlocked() {
		return DoorUnlocked;
	}
	public void setDoorUnlocked(boolean doorUnlocked) {
		DoorUnlocked = doorUnlocked;
	}
	@Column
	public boolean isMoveAlertActive() {
		return MoveAlertActive;
	}
	public void setMoveAlertActive(boolean moveAlertActive) {
		MoveAlertActive = moveAlertActive;
	}
	@Column
	public boolean isSpeedingAlertActive() {
		return SpeedingAlertActive;
	}
	public void setSpeedingAlertActive(boolean speedingAlertActive) {
		SpeedingAlertActive = speedingAlertActive;
	}
	@Column
	public boolean isOutOfGeoFenceAlertActive() {
		return outOfGeoFenceAlertActive;
	}
	public void setOutOfGeoFenceAlertActive(boolean outOfGeoFenceAlertActive) {
		this.outOfGeoFenceAlertActive = outOfGeoFenceAlertActive;
	}
	@Column
	public boolean isIntoGeoFenceAlertActive() {
		return IntoGeoFenceAlertActive;
	}
	public void setIntoGeoFenceAlertActive(boolean intoGeoFenceAlertActive) {
		IntoGeoFenceAlertActive = intoGeoFenceAlertActive;
	}
	@Column
	public boolean isCustomAlertBit0() {
		return CustomAlertBit0;
	}
	public void setCustomAlertBit0(boolean customAlertBit0) {
		CustomAlertBit0 = customAlertBit0;
	}
	@Column
	public boolean isCustomAlertBit1() {
		return CustomAlertBit1;
	}
	public void setCustomAlertBit1(boolean customAlertBit1) {
		CustomAlertBit1 = customAlertBit1;
	}
	@Column
	public boolean isCustomAlertBit2() {
		return CustomAlertBit2;
	}
	public void setCustomAlertBit2(boolean customAlertBit2) {
		CustomAlertBit2 = customAlertBit2;
	}
	@Column
	public boolean isCustomAlertBit3() {
		return CustomAlertBit3;
	}
	public void setCustomAlertBit3(boolean customAlertBit3) {
		CustomAlertBit3 = customAlertBit3;
	}
	@Column
	public boolean isWorkingMode1() {
		return WorkingMode1;
	}
	public void setWorkingMode1(boolean workingMode1) {
		WorkingMode1 = workingMode1;
	}
	@Column
	public boolean isWorkingMode2() {
		return WorkingMode2;
	}
	public void setWorkingMode2(boolean workingMode2) {
		WorkingMode2 = workingMode2;
	}
	@Column
	public boolean isWorkingMode3() {
		return WorkingMode3;
	}
	public void setWorkingMode3(boolean workingMode3) {
		WorkingMode3 = workingMode3;
	}
	@Column
	public boolean isWorkingMode4() {
		return WorkingMode4;
	}
	public void setWorkingMode4(boolean workingMode4) {
		WorkingMode4 = workingMode4;
	}
	@Column
	public boolean isHarshBrake() {
		return HarshBrake;
	}
	public void setHarshBrake(boolean harshBrake) {
		HarshBrake = harshBrake;
	}
	@Column
	public boolean isHarshAccelerate() {
		return HarshAccelerate;
	}
	public void setHarshAccelerate(boolean harshAccelerate) {
		HarshAccelerate = harshAccelerate;
	}
	@Column
	public boolean isHarshTurnRight() {
		return HarshTurnRight;
	}
	public void setHarshTurnRight(boolean harshTurnRight) {
		HarshTurnRight = harshTurnRight;
	}
	@Column
	public boolean isHarshTurnLeft() {
		return HarshTurnLeft;
	}
	public void setHarshTurnLeft(boolean harshTurnLeft) {
		HarshTurnLeft = harshTurnLeft;
	}
	@Column
	public float getDataOfPower() {
		return DataOfPower;
	}
	public void setDataOfPower(float dataOfPower) {
		DataOfPower = dataOfPower;
	}
	@Column
	public float getDataOfTemperature() {
		return DataOfTemperature;
	}
	public void setDataOfTemperature(float dataOfTemperature) {
		DataOfTemperature = dataOfTemperature;
	}	
	@Column

	public float getDataOfFuelSensor() {
		return DataOfFuelSensor;
	}
	public void setDataOfFuelSensor(float dataOfFuelSensor) {
		DataOfFuelSensor = dataOfFuelSensor;
	}	@Column

	public float getHumidity() {
		return humidity;
	}	@Column

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}	@Column

	public float getAnalog1() {
		return analog1;
	}
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
