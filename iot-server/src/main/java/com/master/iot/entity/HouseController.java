package com.master.iot.entity;

import javax.persistence.Entity;

import com.master.platform.core.entity.MasterEntityObject;

@Entity
public class HouseController extends MasterEntityObject {

	private String name;

	private int port;

	private Boolean state;

	public String getName() {
		return this.name;
	}

	public int getPort() {
		return this.port;
	}

	public Boolean getState() {
		return this.state;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setPort(final int port) {
		this.port = port;
	}

	public void setState(final Boolean state) {
		this.state = state;
	}

}
