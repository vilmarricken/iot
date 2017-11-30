package com.master.iot.entity;

import javax.persistence.Entity;

@Entity
public class HouseSensor {

	private String name;

	private int port;

	private HouseSensorType type;

	public String getName() {
		return this.name;
	}

	public int getPort() {
		return this.port;
	}

	public HouseSensorType getType() {
		return this.type;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setPort(final int port) {
		this.port = port;
	}

	public void setType(final HouseSensorType type) {
		this.type = type;
	}

}
