package com.master.iot.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class HouseRegion {

	private Integer id;
	private String name;
	private List<HouseController> controllers;
	private List<HouseSensor> sensors;

	@Id
	@GeneratedValue(generator = "InvSeq")
	@SequenceGenerator(name = "InvSeq", sequenceName = "INV_SEQ", allocationSize = 5)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	public List<HouseController> getControllers() {
		return controllers;
	}

	public void setControllers(List<HouseController> controllers) {
		this.controllers = controllers;
	}

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	public List<HouseSensor> getSensors() {
		return sensors;
	}

	public void setSensors(List<HouseSensor> sensors) {
		this.sensors = sensors;
	}

}
