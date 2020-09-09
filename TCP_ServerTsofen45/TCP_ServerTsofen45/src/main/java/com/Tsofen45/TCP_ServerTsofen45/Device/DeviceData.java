package com.Tsofen45.TCP_ServerTsofen45.Device;

import java.sql.Time;
import java.time.LocalDateTime;

import javax.persistence.*;



@Entity(name = "DevicesData")
public class DeviceData {
    long ID;
    long imei;
    int gpsType;
    String gpsValid;
    LocalDateTime dateAndTime;
    Time updateTime;
    double lat;
    String latIndicator;
    double lon;
    String lonIndicator;
    float speed;
    int orientation;
    String altitude;
    String mileage;
    String state; // added by me.
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
    float externalPower;
    float internalBattery;
    float internalBatteryPower;
    float temperatureInsideDevice;
    float DataOfFuelSensor;
    float temperatureExternal;
    float fuelVoltage;
    float humidity;
    float distance;
    float analog1;
    float analog2;
    public void setID(long iD) {
        ID = iD;
    }
    public void setImei(long imei) {
        this.imei = imei;
    }
    public void setGpsType(int gpsType) {
        this.gpsType = gpsType;
    }
    public void setGpsValid(String gpsValid) {
        this.gpsValid = gpsValid;
    }
    public void setLatIndicator(String latIndicator) {
        this.latIndicator = latIndicator;
    }
    public void setLonIndicator(String lonIndicator) {
        this.lonIndicator = lonIndicator;
    }
    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
    public Time getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Time updateTime) {
        this.updateTime = updateTime;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public void setLon(double lon) {
        this.lon = lon;
    }
    public void setSpeed(float speed) {
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
    public void setState(String state) {
        this.state = state;
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
    public void setExternalPower(float externalPower) {
        this.externalPower = externalPower;
    }
    public void setInternalBattery(float internalBattery) {
        this.internalBattery = internalBattery;
    }
    public void setInternalBatteryPower(float internalBatteryPower) {
        this.internalBatteryPower = internalBatteryPower;
    }
    public void setTemperatureInsideDevice(float temperatureInsideDevice) {
        this.temperatureInsideDevice = temperatureInsideDevice;
    }
    public void setDataOfFuelSensor(float dataOfFuelSensor) {
        DataOfFuelSensor = dataOfFuelSensor;
    }
    public void setTemperatureExternal(float temperatureExternal) {
        this.temperatureExternal = temperatureExternal;
    }
    public void setFuelVoltage(float fuelVoltage) {
        this.fuelVoltage = fuelVoltage;
    }
    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
    public void setDistance(float distance) {
        this.distance = distance;
    }
    public void setAnalog1(float analog1) {
        this.analog1 = analog1;
    }
    public void setAnalog2(float analog2) {
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
    public String getGpsValid() {
        return gpsValid;
    }
    @Column
    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }
    @Column
    public double getLat() {
        return lat;
    }
    @Column
    public String getLatIndicator() {
        return latIndicator;
    }
    @Column
    public double getLon() {
        return lon;
    }
    @Column
    public String getLonIndicator() {
        return lonIndicator;
    }
    @Column
    public float getSpeed() {
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
    public String getState() {
        return state;
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
    public float getExternalPower() {
        return externalPower;
    }
    @Column
    public float getInternalBattery() {
        return internalBattery;
    }
    @Column
    public float getInternalBatteryPower() {
        return internalBatteryPower;
    }
    @Column
    public float getTemperatureInsideDevice() {
        return temperatureInsideDevice;
    }
    @Column
    public float getDataOfFuelSensor() {
        return DataOfFuelSensor;
    }
    @Column
    public float getTemperatureExternal() {
        return temperatureExternal;
    }
    @Column
    public float getFuelVoltage() {
        return fuelVoltage;
    }
    @Column
    public float getHumidity() {
        return humidity;
    }
    @Column
    public float getDistance() {
        return distance;
    }
    @Column
    public float getAnalog1() {
        return analog1;
    }
    @Column
    public float getAnalog2() {
        return analog2;
    }

    @Override
    public String toString() {
        return "DeviceData{" +
                "\nID=" + ID +
                "\n, imei=" + imei +
                "\n, gpsType=" + gpsType +
                "\n, gpsValid=" + gpsValid +
                "\n, dateAndTime=" + dateAndTime +
                "\n, updateTime=" + updateTime +
                "\n, lat=" + lat +
                "\n, latIndicator=" + latIndicator +
                "\n, lon=" + lon +
                "\n, lonIndicator=" + lonIndicator +
                "\n, speed=" + speed +
                "\n, orientation=" + orientation +
                "\n, altitude='" + altitude + '\'' +
                "\n, mileage='" + mileage + '\'' +
                "\n, state='" + state + '\'' +
                "\n, satelites=" + satelites +
                "\n, hdop=" + hdop +
                "\n, gsmSignal=" + gsmSignal +
                "\n, externalPowerOn=" + externalPowerOn +
                "\n, externalPowerLow=" + externalPowerLow +
                "\n, internalBatteryLow=" + internalBatteryLow +
                "\n, internalBatteryCharching=" + internalBatteryCharching +
                "\n, input1Activated=" + input1Activated +
                "\n, switch1Activated=" + switch1Activated +
                "\n, switch2Activated=" + switch2Activated +
                "\n, sesmoActivated=" + sesmoActivated +
                "\n, customInputBit0=" + customInputBit0 +
                "\n, customInputBit1=" + customInputBit1 +
                "\n, customInputBit2=" + customInputBit2 +
                "\n, customInputBit3=" + customInputBit3 +
                "\n, powerCut=" + powerCut +
                "\n, fuelCut=" + fuelCut +
                "\n, doorLocked=" + doorLocked +
                "\n, doorUnlocked=" + doorUnlocked +
                "\n, moveAlertActive=" + moveAlertActive +
                "\n, speedingAlertActive=" + speedingAlertActive +
                "\n, outOfGeoFenceActive=" + outOfGeoFenceActive +
                "\n, intoGeoFenceActive=" + intoGeoFenceActive +
                "\n, customAlertBit0=" + customAlertBit0 +
                "\n, customAlertBit1=" + customAlertBit1 +
                "\n, customAlertBit2=" + customAlertBit2 +
                "\n, customAlertBit3=" + customAlertBit3 +
                "\n, workingMode1=" + workingMode1 +
                "\n, workingMode2=" + workingMode2 +
                "\n, workingMode3=" + workingMode3 +
                "\n, workingMode4=" + workingMode4 +
                "\n, harshBrake=" + harshBrake +
                "\n, harshAccelerate=" + harshAccelerate +
                "\n, harshTurnRight=" + harshTurnRight +
                "\n, harshTurnLeft=" + harshTurnLeft +
                "\n, externalPower=" + externalPower +
                "\n, internalBattery=" + internalBattery +
                "\n, internalBatteryPower=" + internalBatteryPower +
                "\n, temperatureInsideDevice=" + temperatureInsideDevice +
                "\n, DataOfFuelSensor=" + DataOfFuelSensor +
                "\n, temperatureExternal=" + temperatureExternal +
                "\n, fuelVoltage=" + fuelVoltage +
                "\n, humidity=" + humidity +
                "\n, distance=" + distance +
                "\n, analog1=" + analog1 +
                "\n, analog2=" + analog2 +
                '}';
    }
}
