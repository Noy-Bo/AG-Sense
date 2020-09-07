package com.example.ServerTsofen45.Beans;

import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "DevicesData")
public class DeviceData {

	long ID;
	long imei;
	int gpsType;
	char gpsValid;
	Timestamp dateAndTime;
	Time updateTime;
	String lat;
	char latIndicator;
	String lon;

	char lonIndicator;
	float speed;
	int orientation;
	String altitude;
	String mileage;
	int satelites;
	int hdop;
	int gsmSignal;
	boolean externalPowerOn;
	boolean externalPowerLow;
	boolean internalBatteryLow;
	boolean internalBatteryCharching;
	boolean input1Activated; // for private companies ATM
	boolean switch1Activated; // for private companies ATM
	boolean switch2Activated; // for private companies ATM
	boolean sesmoActivated; // for private companies ATM
	boolean customInputBit0;
	boolean customInputBit1;
	boolean customInputBit2;
	boolean customInputBit3;
	boolean powerCut;
	boolean fuelCut;
	boolean doorLocked;
	boolean doorUnlocked;
	boolean moveAlertActive;
	boolean speedingAlertActive;
	boolean outOfGeoFenceActive;
	boolean intoGeoFenceActive;
	boolean customAlertBit0;
	boolean customAlertBit1;
	boolean customAlertBit2;
	boolean customAlertBit3;
	boolean workingMode1;
	boolean workingMode2;
	boolean workingMode3;
	boolean workingMode4;
	boolean harshBrake;
	boolean harshAccelerate;
	boolean harshTurnRight;
	boolean harshTurnLeft;
	Float externalPower;
	Float internalBattery;
	Float internalBatteryPower;
	Float temperatureInsideDevice;
	Float temperatureExternal;
	Float fuelVoltage;
	Float humidity;
	Float distance;
	Float analog1;
	Float analog2;

	public DeviceData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setID(long iD) {
		ID = iD;
	}

	public void setImei(long imei) {
		this.imei = imei;
	}

	public void setGpsType(int gpsType) {
		this.gpsType = gpsType;
	}

	public void setGpsValid(char gpsValid) {
		this.gpsValid = gpsValid;
	}

	
	public void setDateAndTime(Timestamp dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public Time getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Time updateTime) {
		this.updateTime = updateTime;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public void setLatIndicator(char latIndicator) {
		this.latIndicator = latIndicator;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public void setLonIndicator(char lonIndicator) {
		this.lonIndicator = lonIndicator;
	}

	public void setSpeed(Float speed) {
		this.speed = speed;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public void setSatelites(int satelites) {
		this.satelites = satelites;
	}

	public void setHdop(int hdop) {
		this.hdop = hdop;
	}

	public void setGsmSignal(int gsmSignal) {
		this.gsmSignal = gsmSignal;
	}

	public void setExternalPowerOn(boolean externalPowerOn) {
		this.externalPowerOn = externalPowerOn;
	}

	public void setExternalPowerLow(boolean externalPowerLow) {
		this.externalPowerLow = externalPowerLow;
	}

	public void setInternalBatteryLow(boolean internalBatteryLow) {
		this.internalBatteryLow = internalBatteryLow;
	}

	public void setInternalBatteryCharching(boolean internalBatteryCharching) {
		this.internalBatteryCharching = internalBatteryCharching;
	}

	public void setInput1Activated(boolean input1Activated) {
		this.input1Activated = input1Activated;
	}

	public void setSwitch1Activated(boolean switch1Activated) {
		this.switch1Activated = switch1Activated;
	}

	public void setSwitch2Activated(boolean switch2Activated) {
		this.switch2Activated = switch2Activated;
	}

	public void setSesmoActivated(boolean sesmoActivated) {
		this.sesmoActivated = sesmoActivated;
	}

	public void setCustomInputBit0(boolean customInputBit0) {
		this.customInputBit0 = customInputBit0;
	}

	public void setCustomInputBit1(boolean customInputBit1) {
		this.customInputBit1 = customInputBit1;
	}

	public void setCustomInputBit2(boolean customInputBit2) {
		this.customInputBit2 = customInputBit2;
	}

	public void setCustomInputBit3(boolean customInputBit3) {
		this.customInputBit3 = customInputBit3;
	}

	public void setPowerCut(boolean powerCut) {
		this.powerCut = powerCut;
	}

	public void setFuelCut(boolean fuelCut) {
		this.fuelCut = fuelCut;
	}

	public void setDoorLocked(boolean doorLocked) {
		this.doorLocked = doorLocked;
	}

	public void setDoorUnlocked(boolean doorUnlocked) {
		this.doorUnlocked = doorUnlocked;
	}

	public void setMoveAlertActive(boolean moveAlertActive) {
		this.moveAlertActive = moveAlertActive;
	}

	public void setSpeedingAlertActive(boolean speedingAlertActive) {
		this.speedingAlertActive = speedingAlertActive;
	}

	public void setOutOfGeoFenceActive(boolean outOfGeoFenceActive) {
		this.outOfGeoFenceActive = outOfGeoFenceActive;
	}

	public void setIntoGeoFenceActive(boolean intoGeoFenceActive) {
		this.intoGeoFenceActive = intoGeoFenceActive;
	}

	public void setCustomAlertBit0(boolean customAlertBit0) {
		this.customAlertBit0 = customAlertBit0;
	}

	public void setCustomAlertBit1(boolean customAlertBit1) {
		this.customAlertBit1 = customAlertBit1;
	}

	public void setCustomAlertBit2(boolean customAlertBit2) {
		this.customAlertBit2 = customAlertBit2;
	}

	public void setCustomAlertBit3(boolean customAlertBit3) {
		this.customAlertBit3 = customAlertBit3;
	}

	public void setWorkingMode1(boolean workingMode1) {
		this.workingMode1 = workingMode1;
	}

	public void setWorkingMode2(boolean workingMode2) {
		this.workingMode2 = workingMode2;
	}

	public void setWorkingMode3(boolean workingMode3) {
		this.workingMode3 = workingMode3;
	}

	public void setWorkingMode4(boolean workingMode4) {
		this.workingMode4 = workingMode4;
	}

	public void setHarshBrake(boolean harshBrake) {
		this.harshBrake = harshBrake;
	}

	public void setHarshAccelerate(boolean harshAccelerate) {
		this.harshAccelerate = harshAccelerate;
	}

	public void setHarshTurnRight(boolean harshTurnRight) {
		this.harshTurnRight = harshTurnRight;
	}

	public void setHarshTurnLeft(boolean harshTurnLeft) {
		this.harshTurnLeft = harshTurnLeft;
	}

	public void setExternalPower(Float externalPower) {
		this.externalPower = externalPower;
	}

	public void setInternalBattery(Float internalBattery) {
		this.internalBattery = internalBattery;
	}

	public void setInternalBatteryPower(Float internalBatteryPower) {
		this.internalBatteryPower = internalBatteryPower;
	}

	public void setTemperatureInsideDevice(Float temperatureInsideDevice) {
		this.temperatureInsideDevice = temperatureInsideDevice;
	}

	public void setTemperatureExternal(Float temperatureExternal) {
		this.temperatureExternal = temperatureExternal;
	}

	public void setFuelVoltage(Float fuelVoltage) {
		this.fuelVoltage = fuelVoltage;
	}

	public void setHumidity(Float humidity) {
		this.humidity = humidity;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}

	public void setAnalog1(Float analog1) {
		this.analog1 = analog1;
	}

	public void setAnalog2(Float analog2) {
		this.analog2 = analog2;
	}

	@Id
	@GeneratedValue
	public long getID() {
		return ID;
	}

	@Column
	public long getImei() {
		return imei;
	}

	@Column
	public int getGpsType() {
		return gpsType;
	}

	@Column
	public char getGpsValid() {
		return gpsValid;
	}

	@Column
	public Timestamp getDateAndTime() {
		return dateAndTime;
	}

	@Column
	public String getLat() {
		return lat;
	}

	@Column
	public char getLatIndicator() {
		return latIndicator;
	}

	@Column
	public String getLon() {
		return lon;
	}

	@Column
	public char getLonIndicator() {
		return lonIndicator;
	}

	@Column
	public Float getSpeed() {
		return speed;
	}

	@Column
	public int getOrientation() {
		return orientation;
	}

	@Column
	public String getAltitude() {
		return altitude;
	}

	@Column
	public String getMileage() {
		return mileage;
	}

	@Column
	public int getSatelites() {
		return satelites;
	}

	@Column
	public int getHdop() {
		return hdop;
	}

	@Column
	public int getGsmSignal() {
		return gsmSignal;
	}

	@Column
	public boolean isExternalPowerOn() {
		return externalPowerOn;
	}

	@Column
	public boolean isExternalPowerLow() {
		return externalPowerLow;
	}

	@Column
	public boolean isInternalBatteryLow() {
		return internalBatteryLow;
	}

	@Column
	public boolean isInternalBatteryCharching() {
		return internalBatteryCharching;
	}

	@Column
	public boolean isInput1Activated() {
		return input1Activated;
	}

	@Column
	public boolean isSwitch1Activated() {
		return switch1Activated;
	}

	@Column
	public boolean isSwitch2Activated() {
		return switch2Activated;
	}

	@Column
	public boolean isSesmoActivated() {
		return sesmoActivated;
	}

	@Column
	public boolean isCustomInputBit0() {
		return customInputBit0;
	}

	@Column
	public boolean isCustomInputBit1() {
		return customInputBit1;
	}

	@Column
	public boolean isCustomInputBit2() {
		return customInputBit2;
	}

	@Column
	public boolean isCustomInputBit3() {
		return customInputBit3;
	}

	@Column
	public boolean isPowerCut() {
		return powerCut;
	}

	@Column
	public boolean isFuelCut() {
		return fuelCut;
	}

	@Column
	public boolean isDoorLocked() {
		return doorLocked;
	}

	@Column
	public boolean isDoorUnlocked() {
		return doorUnlocked;
	}

	@Column
	public boolean isMoveAlertActive() {
		return moveAlertActive;
	}

	@Column
	public boolean isSpeedingAlertActive() {
		return speedingAlertActive;
	}

	@Column
	public boolean isOutOfGeoFenceActive() {
		return outOfGeoFenceActive;
	}

	@Column
	public boolean isIntoGeoFenceActive() {
		return intoGeoFenceActive;
	}

	@Column
	public boolean isCustomAlertBit0() {
		return customAlertBit0;
	}

	@Column
	public boolean isCustomAlertBit1() {
		return customAlertBit1;
	}

	@Column
	public boolean isCustomAlertBit2() {
		return customAlertBit2;
	}

	@Column
	public boolean isCustomAlertBit3() {
		return customAlertBit3;
	}

	@Column
	public boolean isWorkingMode1() {
		return workingMode1;
	}

	@Column
	public boolean isWorkingMode2() {
		return workingMode2;
	}

	@Column
	public boolean isWorkingMode3() {
		return workingMode3;
	}

	@Column
	public boolean isWorkingMode4() {
		return workingMode4;
	}

	@Column
	public boolean isHarshBrake() {
		return harshBrake;
	}

	@Column
	public boolean isHarshAccelerate() {
		return harshAccelerate;
	}

	@Column
	public boolean isHarshTurnRight() {
		return harshTurnRight;
	}

	@Column
	public boolean isHarshTurnLeft() {
		return harshTurnLeft;
	}

	@Column
	public Float getExternalPower() {
		return externalPower;
	}

	@Column
	public Float getInternalBattery() {
		return internalBattery;
	}

	@Column
	public Float getInternalBatteryPower() {
		return internalBatteryPower;
	}

	@Column
	public Float getTemperatureInsideDevice() {
		return temperatureInsideDevice;
	}

	@Column
	public Float getTemperatureExternal() {
		return temperatureExternal;
	}

	@Column
	public Float getFuelVoltage() {
		return fuelVoltage;
	}

	@Column
	public Float getHumidity() {
		return humidity;
	}

	@Column
	public Float getDistance() {
		return distance;
	}

	@Column
	public Float getAnalog1() {
		return analog1;
	}

	@Column
	public Float getAnalog2() {
		return analog2;
	}

}
