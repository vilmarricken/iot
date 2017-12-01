package com.master.iot.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.master.platform.core.entity.MasterEntityObject;

@Entity
public class HouseComponent extends MasterEntityObject {

	private String name;

	private int port;

	private HouseDevice device;

	private HouseController controller;

	@JoinColumn(name = "IDCONTROLLER")
	@ManyToOne()
	public HouseController getController() {
		return this.controller;
	}

	@JoinColumn(name = "IDDEVICE")
	@ManyToOne()
	public HouseDevice getDevice() {
		return this.device;
	}

	public String getName() {
		return this.name;
	}

	public int getPort() {
		return this.port;
	}

	public void setController(HouseController region) {
		this.controller = region;
	}

	public void setDevice(HouseDevice device) {
		this.device = device;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setPort(final int port) {
		this.port = port;
	}

}
