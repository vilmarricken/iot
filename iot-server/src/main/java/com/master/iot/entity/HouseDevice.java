package com.master.iot.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.master.platform.core.entity.MasterEntityObject;

@Entity
public class HouseDevice extends MasterEntityObject {

	private String address;

	private List<HouseController> controllers;

	private DeviceType deviceType;

	private List<HouseSensor> sensors;

	private Boolean state;

	private String uuid;

	public HouseDevice() {
	}

	public String getAddress() {
		return this.address;
	}

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	public List<HouseController> getControllers() {
		return this.controllers;
	}

	public DeviceType getDeviceType() {
		return this.deviceType;
	}

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	public List<HouseSensor> getSensors() {
		return this.sensors;
	}

	public Boolean getState() {
		return this.state;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public void setControllers(final List<HouseController> controllers) {
		this.controllers = controllers;
	}

	public void setDeviceType(final DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public void setSensors(final List<HouseSensor> sensors) {
		this.sensors = sensors;
	}

	public void setState(final Boolean state) {
		this.state = state;
	}

	public void setUuid(final String uuid) {
		this.uuid = uuid;
	}

}
