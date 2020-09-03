package com.tsofen.agsenceapp.entities;

import java.io.Serializable;
import java.util.Date;

public class DeviceData  implements Serializable {
    protected int id;
    protected long Deviceimei2;
    protected int Deviceid2;
    protected String latitudeIndecator;
    protected String status;
    protected boolean isMoving;
    protected boolean gpsType;
    protected String gpsValid;
    protected Date deviceDateAndTime;
    protected int lat;
    protected int _long;
    protected String logintudeIndecator;
    protected int speed;
    protected int orintation;
    protected int altitude;
    protected int mileage;
    protected int satellites;
    protected int hdop;
    protected int gsmSignal;
    protected boolean externalPowerOn;
    protected boolean externalPowerLow;
    protected boolean internalBatteryLow;
    protected boolean internalBatteryReharging;
    protected boolean customInputBit0;
    protected boolean customInputBit1;
    protected boolean customInputBit2;
    protected boolean customInputBit3;
    protected boolean powerCut;
    protected boolean fuelCut;
    protected boolean doorLocked;
    protected boolean doorUnlocked;
    protected boolean moveAlertActive;
    protected boolean speedingAlterActive;
    protected boolean outOfGeoFenceAlertActive;
    protected boolean intoGeoFenceAlertActive;
    protected boolean customAlertBit0;
    protected boolean customAlertBit1;
    protected boolean customAlertBit2;
    protected boolean customAlertBit3;
    protected boolean workingMode1;
    protected boolean workingMode2;
    protected boolean workingMode3;
    protected boolean workingMode4;
    protected boolean harshBrake;
    protected boolean harshAccelerate;
    protected boolean harshTurnRight;
    protected boolean harshTurnLeft;
    protected int externalPower;
    protected int internalBatteryPercent;
    protected float internalBatteryPower;
    protected float temperatureInsideDevice;
    protected float temperatureExternal;
    protected int fuelVoltage;
    protected int humidity;
    protected float analog1;
    protected float analog2;
    protected boolean input1Activated;
    protected boolean switch1Activated;
    protected boolean switch2Activated;
    protected boolean sesmoActivated;

    public DeviceData(int id, long deviceimei2, int deviceid2, String latitudeIndecator, String status, boolean isMoving, boolean gpsType, String gpsValid, Date deviceDateAndTime, int lat, int _long, String logintudeIndecator, int speed, int orintation, int altitude, int mileage, int satellites, int hdop, int gsmSignal, boolean externalPowerOn, boolean externalPowerLow, boolean internalBatteryLow, boolean internalBatteryReharging, boolean customInputBit0, boolean customInputBit1, boolean customInputBit2, boolean customInputBit3, boolean powerCut, boolean fuelCut, boolean doorLocked, boolean doorUnlocked, boolean moveAlertActive, boolean speedingAlterActive, boolean outOfGeoFenceAlertActive, boolean intoGeoFenceAlertActive, boolean customAlertBit0, boolean customAlertBit1, boolean customAlertBit2, boolean customAlertBit3, boolean workingMode1, boolean workingMode2, boolean workingMode3, boolean workingMode4, boolean harshBrake, boolean harshAccelerate, boolean harshTurnRight, boolean harshTurnLeft, int externalPower, int internalBatteryPercent, float internalBatteryPower, float temperatureInsideDevice, float temperatureExternal, int fuelVoltage, int humidity, float analog1, float analog2, boolean input1Activated, boolean switch1Activated, boolean switch2Activated, boolean sesmoActivated) {
        this.id = id;
        Deviceimei2 = deviceimei2;
        Deviceid2 = deviceid2;
        this.latitudeIndecator = latitudeIndecator;
        this.status = status;
        this.isMoving = isMoving;
        this.gpsType = gpsType;
        this.gpsValid = gpsValid;
        this.deviceDateAndTime = deviceDateAndTime;
        this.lat = lat;
        this._long = _long;
        this.logintudeIndecator = logintudeIndecator;
        this.speed = speed;
        this.orintation = orintation;
        this.altitude = altitude;
        this.mileage = mileage;
        this.satellites = satellites;
        this.hdop = hdop;
        this.gsmSignal = gsmSignal;
        this.externalPowerOn = externalPowerOn;
        this.externalPowerLow = externalPowerLow;
        this.internalBatteryLow = internalBatteryLow;
        this.internalBatteryReharging = internalBatteryReharging;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDeviceimei2() {
        return Deviceimei2;
    }

    public void setDeviceimei2(long deviceimei2) {
        Deviceimei2 = deviceimei2;
    }

    public int getDeviceid2() {
        return Deviceid2;
    }

    public void setDeviceid2(int deviceid2) {
        Deviceid2 = deviceid2;
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

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public boolean isGpsType() {
        return gpsType;
    }

    public void setGpsType(boolean gpsType) {
        this.gpsType = gpsType;
    }

    public String getGpsValid() {
        return gpsValid;
    }

    public void setGpsValid(String gpsValid) {
        this.gpsValid = gpsValid;
    }

    public Date getDeviceDateAndTime() {
        return deviceDateAndTime;
    }

    public void setDeviceDateAndTime(Date deviceDateAndTime) {
        this.deviceDateAndTime = deviceDateAndTime;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int get_long() {
        return _long;
    }

    public void set_long(int _long) {
        this._long = _long;
    }

    public String getLogintudeIndecator() {
        return logintudeIndecator;
    }

    public void setLogintudeIndecator(String logintudeIndecator) {
        this.logintudeIndecator = logintudeIndecator;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getOrintation() {
        return orintation;
    }

    public void setOrintation(int orintation) {
        this.orintation = orintation;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getSatellites() {
        return satellites;
    }

    public void setSatellites(int satellites) {
        this.satellites = satellites;
    }

    public int getHdop() {
        return hdop;
    }

    public void setHdop(int hdop) {
        this.hdop = hdop;
    }

    public int getGsmSignal() {
        return gsmSignal;
    }

    public void setGsmSignal(int gsmSignal) {
        this.gsmSignal = gsmSignal;
    }

    public boolean isExternalPowerOn() {
        return externalPowerOn;
    }

    public void setExternalPowerOn(boolean externalPowerOn) {
        this.externalPowerOn = externalPowerOn;
    }

    public boolean isExternalPowerLow() {
        return externalPowerLow;
    }

    public void setExternalPowerLow(boolean externalPowerLow) {
        this.externalPowerLow = externalPowerLow;
    }

    public boolean isInternalBatteryLow() {
        return internalBatteryLow;
    }

    public void setInternalBatteryLow(boolean internalBatteryLow) {
        this.internalBatteryLow = internalBatteryLow;
    }

    public boolean isInternalBatteryReharging() {
        return internalBatteryReharging;
    }

    public void setInternalBatteryReharging(boolean internalBatteryReharging) {
        this.internalBatteryReharging = internalBatteryReharging;
    }

    public boolean isCustomInputBit0() {
        return customInputBit0;
    }

    public void setCustomInputBit0(boolean customInputBit0) {
        this.customInputBit0 = customInputBit0;
    }

    public boolean isCustomInputBit1() {
        return customInputBit1;
    }

    public void setCustomInputBit1(boolean customInputBit1) {
        this.customInputBit1 = customInputBit1;
    }

    public boolean isCustomInputBit2() {
        return customInputBit2;
    }

    public void setCustomInputBit2(boolean customInputBit2) {
        this.customInputBit2 = customInputBit2;
    }

    public boolean isCustomInputBit3() {
        return customInputBit3;
    }

    public void setCustomInputBit3(boolean customInputBit3) {
        this.customInputBit3 = customInputBit3;
    }

    public boolean isPowerCut() {
        return powerCut;
    }

    public void setPowerCut(boolean powerCut) {
        this.powerCut = powerCut;
    }

    public boolean isFuelCut() {
        return fuelCut;
    }

    public void setFuelCut(boolean fuelCut) {
        this.fuelCut = fuelCut;
    }

    public boolean isDoorLocked() {
        return doorLocked;
    }

    public void setDoorLocked(boolean doorLocked) {
        this.doorLocked = doorLocked;
    }

    public boolean isDoorUnlocked() {
        return doorUnlocked;
    }

    public void setDoorUnlocked(boolean doorUnlocked) {
        this.doorUnlocked = doorUnlocked;
    }

    public boolean isMoveAlertActive() {
        return moveAlertActive;
    }

    public void setMoveAlertActive(boolean moveAlertActive) {
        this.moveAlertActive = moveAlertActive;
    }

    public boolean isSpeedingAlterActive() {
        return speedingAlterActive;
    }

    public void setSpeedingAlterActive(boolean speedingAlterActive) {
        this.speedingAlterActive = speedingAlterActive;
    }

    public boolean isOutOfGeoFenceAlertActive() {
        return outOfGeoFenceAlertActive;
    }

    public void setOutOfGeoFenceAlertActive(boolean outOfGeoFenceAlertActive) {
        this.outOfGeoFenceAlertActive = outOfGeoFenceAlertActive;
    }

    public boolean isIntoGeoFenceAlertActive() {
        return intoGeoFenceAlertActive;
    }

    public void setIntoGeoFenceAlertActive(boolean intoGeoFenceAlertActive) {
        this.intoGeoFenceAlertActive = intoGeoFenceAlertActive;
    }

    public boolean isCustomAlertBit0() {
        return customAlertBit0;
    }

    public void setCustomAlertBit0(boolean customAlertBit0) {
        this.customAlertBit0 = customAlertBit0;
    }

    public boolean isCustomAlertBit1() {
        return customAlertBit1;
    }

    public void setCustomAlertBit1(boolean customAlertBit1) {
        this.customAlertBit1 = customAlertBit1;
    }

    public boolean isCustomAlertBit2() {
        return customAlertBit2;
    }

    public void setCustomAlertBit2(boolean customAlertBit2) {
        this.customAlertBit2 = customAlertBit2;
    }

    public boolean isCustomAlertBit3() {
        return customAlertBit3;
    }

    public void setCustomAlertBit3(boolean customAlertBit3) {
        this.customAlertBit3 = customAlertBit3;
    }

    public boolean isWorkingMode1() {
        return workingMode1;
    }

    public void setWorkingMode1(boolean workingMode1) {
        this.workingMode1 = workingMode1;
    }

    public boolean isWorkingMode2() {
        return workingMode2;
    }

    public void setWorkingMode2(boolean workingMode2) {
        this.workingMode2 = workingMode2;
    }

    public boolean isWorkingMode3() {
        return workingMode3;
    }

    public void setWorkingMode3(boolean workingMode3) {
        this.workingMode3 = workingMode3;
    }

    public boolean isWorkingMode4() {
        return workingMode4;
    }

    public void setWorkingMode4(boolean workingMode4) {
        this.workingMode4 = workingMode4;
    }

    public boolean isHarshBrake() {
        return harshBrake;
    }

    public void setHarshBrake(boolean harshBrake) {
        this.harshBrake = harshBrake;
    }

    public boolean isHarshAccelerate() {
        return harshAccelerate;
    }

    public void setHarshAccelerate(boolean harshAccelerate) {
        this.harshAccelerate = harshAccelerate;
    }

    public boolean isHarshTurnRight() {
        return harshTurnRight;
    }

    public void setHarshTurnRight(boolean harshTurnRight) {
        this.harshTurnRight = harshTurnRight;
    }

    public boolean isHarshTurnLeft() {
        return harshTurnLeft;
    }

    public void setHarshTurnLeft(boolean harshTurnLeft) {
        this.harshTurnLeft = harshTurnLeft;
    }

    public int getExternalPower() {
        return externalPower;
    }

    public void setExternalPower(int externalPower) {
        this.externalPower = externalPower;
    }

    public int getInternalBatteryPercent() {
        return internalBatteryPercent;
    }

    public void setInternalBatteryPercent(int internalBatteryPercent) {
        this.internalBatteryPercent = internalBatteryPercent;
    }

    public float getInternalBatteryPower() {
        return internalBatteryPower;
    }

    public void setInternalBatteryPower(float internalBatteryPower) {
        this.internalBatteryPower = internalBatteryPower;
    }

    public float getTemperatureInsideDevice() {
        return temperatureInsideDevice;
    }

    public void setTemperatureInsideDevice(float temperatureInsideDevice) {
        this.temperatureInsideDevice = temperatureInsideDevice;
    }

    public float getTemperatureExternal() {
        return temperatureExternal;
    }

    public void setTemperatureExternal(float temperatureExternal) {
        this.temperatureExternal = temperatureExternal;
    }

    public int getFuelVoltage() {
        return fuelVoltage;
    }

    public void setFuelVoltage(int fuelVoltage) {
        this.fuelVoltage = fuelVoltage;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getAnalog1() {
        return analog1;
    }

    public void setAnalog1(float analog1) {
        this.analog1 = analog1;
    }

    public float getAnalog2() {
        return analog2;
    }

    public void setAnalog2(float analog2) {
        this.analog2 = analog2;
    }

    public boolean isInput1Activated() {
        return input1Activated;
    }

    public void setInput1Activated(boolean input1Activated) {
        this.input1Activated = input1Activated;
    }

    public boolean isSwitch1Activated() {
        return switch1Activated;
    }

    public void setSwitch1Activated(boolean switch1Activated) {
        this.switch1Activated = switch1Activated;
    }

    public boolean isSwitch2Activated() {
        return switch2Activated;
    }

    public void setSwitch2Activated(boolean switch2Activated) {
        this.switch2Activated = switch2Activated;
    }

    public boolean isSesmoActivated() {
        return sesmoActivated;
    }

    public void setSesmoActivated(boolean sesmoActivated) {
        this.sesmoActivated = sesmoActivated;
    }
}
