package com.tsofen.agsenceapp.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class DeviceData implements Serializable {


    protected Integer id;
    protected Long imei;

    protected String latitudeIndecator;
    protected String status;
    protected Boolean moveAlertActive;
    protected Integer gpsType;
    protected String gpsValid;
    protected Timestamp dateAndTime;
    protected Date updateTime;
    protected Float lat;
    protected Float lon;
    protected String lonIndicator;
    protected Float speed;
    protected Integer orientation;
    protected Float altitude;
    protected String mileage;
    protected Integer satelites;
    protected Integer hdop;
    protected Integer gsmSignal;
    protected Boolean externalPowerOn;
    protected Boolean externalPowerLow;
    protected Boolean internalBatteryLow;
    protected Boolean internalBatteryCharching;
    protected Boolean customInputBit0;
    protected Boolean customInputBit1;
    protected Boolean customInputBit2;
    protected Boolean customInputBit3;
    protected Boolean powerCut;
    protected Boolean fuelCut;
    protected Boolean doorLocked;
    protected Boolean doorUnlocked;
    protected Boolean speedingAlterActive;
    protected Boolean outOfGeoFenceAlertActive;
    protected Boolean intoGeoFenceAlertActive;
    protected Boolean customAlertBit0;
    protected Boolean customAlertBit1;
    protected Boolean customAlertBit2;
    protected Boolean customAlertBit3;
    protected Boolean workingMode1;
    protected Boolean workingMode2;
    protected Boolean workingMode3;
    protected Boolean workingMode4;
    protected Boolean harshBrake;
    protected Boolean harshAccelerate;
    protected Boolean harshTurnRight;
    protected Boolean harshTurnLeft;
    protected Integer externalPower;
    protected Integer internalBatteryPercent;
    protected Float internalBatteryPower;
    protected Float temperatureInsideDevice;
    protected Float temperatureExternal;
    protected Integer fuelVoltage;
    protected Integer humidity;
    protected Float analog1;
    protected Float analog2;
    protected Boolean input1Activated;
    protected Boolean switch1Activated;
    protected Boolean switch2Activated;
    protected Boolean sesmoActivated;

    public DeviceData() {
    }

    public DeviceData(Integer id, Long imei, String latitudeIndecator, String status, Boolean isMoving, Integer gpsType, String gpsValid, Timestamp dateAndTime, Date updateTime, Float lat, Float lon, String lonIndicator, Float speed, Integer orientation, Float altitude, String mileage, Integer satelites, Integer hdop, Integer gsmSignal, Boolean externalPowerOn, Boolean externalPowerLow, Boolean internalBatteryLow, Boolean internalBatteryCharching, Boolean customInputBit0, Boolean customInputBit1, Boolean customInputBit2, Boolean customInputBit3, Boolean powerCut, Boolean fuelCut, Boolean doorLocked, Boolean doorUnlocked, Boolean moveAlertActive, Boolean speedingAlterActive, Boolean outOfGeoFenceAlertActive, Boolean intoGeoFenceAlertActive, Boolean customAlertBit0, Boolean customAlertBit1, Boolean customAlertBit2, Boolean customAlertBit3, Boolean workingMode1, Boolean workingMode2, Boolean workingMode3, Boolean workingMode4, Boolean harshBrake, Boolean harshAccelerate, Boolean harshTurnRight, Boolean harshTurnLeft, Integer externalPower, Integer internalBatteryPercent, Float internalBatteryPower, Float temperatureInsideDevice, Float temperatureExternal, Integer fuelVoltage, Integer humidity, Float analog1, Float analog2, Boolean input1Activated, Boolean switch1Activated, Boolean switch2Activated, Boolean sesmoActivated) {
        this.id = id;
        this.imei = imei;
        this.latitudeIndecator = latitudeIndecator;
        this.status = status;
        this.moveAlertActive = isMoving;
        this.gpsType = gpsType;
        this.gpsValid = gpsValid;
        this.dateAndTime = dateAndTime;
        this.updateTime = updateTime;
        this.lat = lat;
        this.lon = lon;
        this.lonIndicator = lonIndicator;
        this.speed = speed;
        this.orientation = orientation;
        this.altitude = altitude;
        this.mileage = mileage;
        this.satelites = satelites;
        this.hdop = hdop;
        this.gsmSignal = gsmSignal;
        this.externalPowerOn = externalPowerOn;
        this.externalPowerLow = externalPowerLow;
        this.internalBatteryLow = internalBatteryLow;
        this.internalBatteryCharching = internalBatteryCharching;
        this.customInputBit0 = customInputBit0;
        this.customInputBit1 = customInputBit1;
        this.customInputBit2 = customInputBit2;
        this.customInputBit3 = customInputBit3;
        this.powerCut = powerCut;
        this.fuelCut = fuelCut;
        this.doorLocked = doorLocked;
        this.doorUnlocked = doorUnlocked;
        this.moveAlertActive = moveAlertActive;
        this.speedingAlterActive = speedingAlterActive;
        this.outOfGeoFenceAlertActive = outOfGeoFenceAlertActive;
        this.intoGeoFenceAlertActive = intoGeoFenceAlertActive;
        this.customAlertBit0 = customAlertBit0;
        this.customAlertBit1 = customAlertBit1;
        this.customAlertBit2 = customAlertBit2;
        this.customAlertBit3 = customAlertBit3;
        this.workingMode1 = workingMode1;
        this.workingMode2 = workingMode2;
        this.workingMode3 = workingMode3;
        this.workingMode4 = workingMode4;
        this.harshBrake = harshBrake;
        this.harshAccelerate = harshAccelerate;
        this.harshTurnRight = harshTurnRight;
        this.harshTurnLeft = harshTurnLeft;
        this.externalPower = externalPower;
        this.internalBatteryPercent = internalBatteryPercent;
        this.internalBatteryPower = internalBatteryPower;
        this.temperatureInsideDevice = temperatureInsideDevice;
        this.temperatureExternal = temperatureExternal;
        this.fuelVoltage = fuelVoltage;
        this.humidity = humidity;
        this.analog1 = analog1;
        this.analog2 = analog2;
        this.input1Activated = input1Activated;
        this.switch1Activated = switch1Activated;
        this.switch2Activated = switch2Activated;
        this.sesmoActivated = sesmoActivated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getImei() {
        return imei;
    }

    public void setImei(Long imei) {
        this.imei = imei;
    }

    public String getLatitudeIndecator() {
        return latitudeIndecator;
    }

    public void setLatitudeIndecator(String latitudeIndecator) {
        this.latitudeIndecator = latitudeIndecator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getGpsType() {
        return gpsType;
    }

    public void setGpsType(Integer gpsType) {
        this.gpsType = gpsType;
    }

    public String getGpsValid() {
        return gpsValid;
    }

    public void setGpsValid(String gpsValid) {
        this.gpsValid = gpsValid;
    }

    public Timestamp getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Timestamp dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public String getLonIndicator() {
        return lonIndicator;
    }

    public void setLonIndicator(String lonIndicator) {
        this.lonIndicator = lonIndicator;
    }

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public Integer getOrientation() {
        return orientation;
    }

    public void setOrientation(Integer orientation) {
        this.orientation = orientation;
    }

    public Float getAltitude() {
        return altitude;
    }

    public void setAltitude(Float altitude) {
        this.altitude = altitude;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public Integer getSatelites() {
        return satelites;
    }

    public void setSatelites(Integer satelites) {
        this.satelites = satelites;
    }

    public Integer getHdop() {
        return hdop;
    }

    public void setHdop(Integer hdop) {
        this.hdop = hdop;
    }

    public Integer getGsmSignal() {
        return gsmSignal;
    }

    public void setGsmSignal(Integer gsmSignal) {
        this.gsmSignal = gsmSignal;
    }

    public Boolean getExternalPowerOn() {
        return externalPowerOn;
    }

    public void setExternalPowerOn(Boolean externalPowerOn) {
        this.externalPowerOn = externalPowerOn;
    }

    public Boolean getExternalPowerLow() {
        return externalPowerLow;
    }

    public void setExternalPowerLow(Boolean externalPowerLow) {
        this.externalPowerLow = externalPowerLow;
    }

    public Boolean getInternalBatteryLow() {
        return internalBatteryLow;
    }

    public void setInternalBatteryLow(Boolean internalBatteryLow) {
        this.internalBatteryLow = internalBatteryLow;
    }

    public Boolean getInternalBatteryCharching() {
        return internalBatteryCharching;
    }

    public void setInternalBatteryCharching(Boolean internalBatteryCharching) {
        this.internalBatteryCharching = internalBatteryCharching;
    }

    public Boolean getCustomInputBit0() {
        return customInputBit0;
    }

    public void setCustomInputBit0(Boolean customInputBit0) {
        this.customInputBit0 = customInputBit0;
    }

    public Boolean getCustomInputBit1() {
        return customInputBit1;
    }

    public void setCustomInputBit1(Boolean customInputBit1) {
        this.customInputBit1 = customInputBit1;
    }

    public Boolean getCustomInputBit2() {
        return customInputBit2;
    }

    public void setCustomInputBit2(Boolean customInputBit2) {
        this.customInputBit2 = customInputBit2;
    }

    public Boolean getCustomInputBit3() {
        return customInputBit3;
    }

    public void setCustomInputBit3(Boolean customInputBit3) {
        this.customInputBit3 = customInputBit3;
    }

    public Boolean getPowerCut() {
        return powerCut;
    }

    public void setPowerCut(Boolean powerCut) {
        this.powerCut = powerCut;
    }

    public Boolean getFuelCut() {
        return fuelCut;
    }

    public void setFuelCut(Boolean fuelCut) {
        this.fuelCut = fuelCut;
    }

    public Boolean getDoorLocked() {
        return doorLocked;
    }

    public void setDoorLocked(Boolean doorLocked) {
        this.doorLocked = doorLocked;
    }

    public Boolean getDoorUnlocked() {
        return doorUnlocked;
    }

    public void setDoorUnlocked(Boolean doorUnlocked) {
        this.doorUnlocked = doorUnlocked;
    }

    public Boolean getMoveAlertActive() {
        return moveAlertActive;
    }

    public void setMoveAlertActive(Boolean moveAlertActive) {
        this.moveAlertActive = moveAlertActive;
    }

    public Boolean getSpeedingAlterActive() {
        return speedingAlterActive;
    }

    public void setSpeedingAlterActive(Boolean speedingAlterActive) {
        this.speedingAlterActive = speedingAlterActive;
    }

    public Boolean getOutOfGeoFenceAlertActive() {
        return outOfGeoFenceAlertActive;
    }

    public void setOutOfGeoFenceAlertActive(Boolean outOfGeoFenceAlertActive) {
        this.outOfGeoFenceAlertActive = outOfGeoFenceAlertActive;
    }

    public Boolean getIntoGeoFenceAlertActive() {
        return intoGeoFenceAlertActive;
    }

    public void setIntoGeoFenceAlertActive(Boolean intoGeoFenceAlertActive) {
        this.intoGeoFenceAlertActive = intoGeoFenceAlertActive;
    }

    public Boolean getCustomAlertBit0() {
        return customAlertBit0;
    }

    public void setCustomAlertBit0(Boolean customAlertBit0) {
        this.customAlertBit0 = customAlertBit0;
    }

    public Boolean getCustomAlertBit1() {
        return customAlertBit1;
    }

    public void setCustomAlertBit1(Boolean customAlertBit1) {
        this.customAlertBit1 = customAlertBit1;
    }

    public Boolean getCustomAlertBit2() {
        return customAlertBit2;
    }

    public void setCustomAlertBit2(Boolean customAlertBit2) {
        this.customAlertBit2 = customAlertBit2;
    }

    public Boolean getCustomAlertBit3() {
        return customAlertBit3;
    }

    public void setCustomAlertBit3(Boolean customAlertBit3) {
        this.customAlertBit3 = customAlertBit3;
    }

    public Boolean getWorkingMode1() {
        return workingMode1;
    }

    public void setWorkingMode1(Boolean workingMode1) {
        this.workingMode1 = workingMode1;
    }

    public Boolean getWorkingMode2() {
        return workingMode2;
    }

    public void setWorkingMode2(Boolean workingMode2) {
        this.workingMode2 = workingMode2;
    }

    public Boolean getWorkingMode3() {
        return workingMode3;
    }

    public void setWorkingMode3(Boolean workingMode3) {
        this.workingMode3 = workingMode3;
    }

    public Boolean getWorkingMode4() {
        return workingMode4;
    }

    public void setWorkingMode4(Boolean workingMode4) {
        this.workingMode4 = workingMode4;
    }

    public Boolean getHarshBrake() {
        return harshBrake;
    }

    public void setHarshBrake(Boolean harshBrake) {
        this.harshBrake = harshBrake;
    }

    public Boolean getHarshAccelerate() {
        return harshAccelerate;
    }

    public void setHarshAccelerate(Boolean harshAccelerate) {
        this.harshAccelerate = harshAccelerate;
    }

    public Boolean getHarshTurnRight() {
        return harshTurnRight;
    }

    public void setHarshTurnRight(Boolean harshTurnRight) {
        this.harshTurnRight = harshTurnRight;
    }

    public Boolean getHarshTurnLeft() {
        return harshTurnLeft;
    }

    public void setHarshTurnLeft(Boolean harshTurnLeft) {
        this.harshTurnLeft = harshTurnLeft;
    }

    public Integer getExternalPower() {
        return externalPower;
    }

    public void setExternalPower(Integer externalPower) {
        this.externalPower = externalPower;
    }

    public Integer getInternalBatteryPercent() {
        return internalBatteryPercent;
    }

    public void setInternalBatteryPercent(Integer internalBatteryPercent) {
        this.internalBatteryPercent = internalBatteryPercent;
    }

    public Float getInternalBatteryPower() {
        return internalBatteryPower;
    }

    public void setInternalBatteryPower(Float internalBatteryPower) {
        this.internalBatteryPower = internalBatteryPower;
    }

    public Float getTemperatureInsideDevice() {
        return temperatureInsideDevice;
    }

    public void setTemperatureInsideDevice(Float temperatureInsideDevice) {
        this.temperatureInsideDevice = temperatureInsideDevice;
    }

    public Float getTemperatureExternal() {
        return temperatureExternal;
    }

    public void setTemperatureExternal(Float temperatureExternal) {
        this.temperatureExternal = temperatureExternal;
    }

    public Integer getFuelVoltage() {
        return fuelVoltage;
    }

    public void setFuelVoltage(Integer fuelVoltage) {
        this.fuelVoltage = fuelVoltage;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Float getAnalog1() {
        return analog1;
    }

    public void setAnalog1(Float analog1) {
        this.analog1 = analog1;
    }

    public Float getAnalog2() {
        return analog2;
    }

    public void setAnalog2(Float analog2) {
        this.analog2 = analog2;
    }

    public Boolean getInput1Activated() {
        return input1Activated;
    }

    public void setInput1Activated(Boolean input1Activated) {
        this.input1Activated = input1Activated;
    }

    public Boolean getSwitch1Activated() {
        return switch1Activated;
    }

    public void setSwitch1Activated(Boolean switch1Activated) {
        this.switch1Activated = switch1Activated;
    }

    public Boolean getSwitch2Activated() {
        return switch2Activated;
    }

    public void setSwitch2Activated(Boolean switch2Activated) {
        this.switch2Activated = switch2Activated;
    }

    public Boolean getSesmoActivated() {
        return sesmoActivated;
    }

    public void setSesmoActivated(Boolean sesmoActivated) {
        this.sesmoActivated = sesmoActivated;
    }
}
