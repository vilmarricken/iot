package com.master.iot.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.master.platform.core.entity.MasterEntityObject;

@Entity
public class HouseController extends MasterEntityObject {

	private List<HouseComponent> components;
	private String name;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "controller")
	public List<HouseComponent> getComponents() {
		return this.components;
	}

	public String getName() {
		return this.name;
	}

	public void setComponents(final List<HouseComponent> controllers) {
		this.components = controllers;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
