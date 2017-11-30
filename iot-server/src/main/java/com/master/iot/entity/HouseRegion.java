package com.master.iot.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.master.platform.core.entity.MasterEntityObject;

@Entity
public class HouseRegion extends MasterEntityObject {

	private List<HouseController> controllers;
	private String name;
	private List<HouseSensor> sensors;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	public List<HouseController> getControllers() {
		return this.controllers;
	}

	public String getName() {
		return this.name;
	}

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	public List<HouseSensor> getSensors() {
		return this.sensors;
	}

	public void setControllers(final List<HouseController> controllers) {
		this.controllers = controllers;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setSensors(final List<HouseSensor> sensors) {
		this.sensors = sensors;
	}

}
