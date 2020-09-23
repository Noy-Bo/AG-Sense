package com.Tsofen45.TCP_ServerTsofen45.Alarms;

import org.springframework.stereotype.Service;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;

@Service
public class StateManager {
    DeviceData deviceData;

    public void setDeviceData(DeviceData deviceData) {
        this.deviceData = deviceData;
    }

    public void setStates(){
        PowerState powerState = new PowerState(deviceData);
        powerState.setPowerStates();
        InputState inputState = new InputState(deviceData);
        inputState.setInputState();
        CustomInputState customInputState = new CustomInputState(deviceData);
        customInputState.setCustomInputState();
        OutputState outputState = new OutputState(deviceData);
        outputState.setOutputState();
        AlertState alertState = new AlertState(deviceData);
        alertState.setAlertState();
        CustomAlertState customAlertState = new CustomAlertState(deviceData);
        customAlertState.setCustomAlertState();
        WorkingState workingState = new WorkingState(deviceData);
        workingState.setWorkingState();
        DrivingState drivingState = new DrivingState(deviceData);
        drivingState.setDrivingState();
    }
}
