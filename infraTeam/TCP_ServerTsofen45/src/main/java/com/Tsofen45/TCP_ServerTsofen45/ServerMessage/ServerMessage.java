package com.Tsofen45.TCP_ServerTsofen45.ServerMessage;

import org.springframework.stereotype.Service;

import com.Tsofen45.TCP_ServerTsofen45.Device.DeviceData;

@Service
abstract public class ServerMessage {
	abstract public void Analyze(String s);
}
