package com.master.iot.entity;

public enum ComponentType {

	RELAY("1"), RELAY_SOLID("2"), THERMOMETER("3");

	private String type;

	ComponentType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

}
