package com.master.iot.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.master.platform.core.entity.MasterEntityObject;

@Entity
public class HouseDevice extends MasterEntityObject {

	private String address;

	private List<HouseComponent> controllers;

	private DeviceType deviceType;

	private String name;

	private HouseDeviceState state;

	private String uuid;

	public HouseDevice() {
	}

	public String getAddress() {
		return this.address;
	}

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "device")
	public List<HouseComponent> getControllers() {
		return this.controllers;
	}

	public DeviceType getDeviceType() {
		return this.deviceType;
	}

	public String getName() {
		return this.name;
	}

	public HouseDeviceState getState() {
		return this.state;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public void setControllers(final List<HouseComponent> controllers) {
		this.controllers = controllers;
	}

	public void setDeviceType(final DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setState(HouseDeviceState state) {
		this.state = state;
	}

	public void setUuid(final String uuid) {
		this.uuid = uuid;
	}

}
