package com.Tsofen45.TCP_ServerTsofen45.ENUMS;

public enum DeviceType {

	SensorForBanks(1, "SensorForBanks"), GpsForPersonal(2, "GpsForPersonal"),
	lequidHeightForTanks(3, "lequidHeightForTanks");

	int id;
	String type;

	private DeviceType(int id, String type) {
		this.id = id;
		this.type = type;
	}

}
