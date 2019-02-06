package com.master.iot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MONITOR")
public class Monitor extends com.master.core.persistence.entity.Entity {

	private Float target;

	private Component controller;

	private String description;

	private Component reader;

	private Float limit;

	private String name;

	private MonitorType type;

	@Column(name = "TARGET")
	public Float getTarget() {
		return this.target;
	}

	@ManyToOne
	@JoinColumn(name = "IDCONTROLLER")
	public Component getController() {
		return this.controller;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	@ManyToOne
	@JoinColumn(name = "IDREADER")
	public Component getReader() {
		return this.reader;
	}

	@Column(name = "LIMIT")
	public Float getLimit() {
		return this.limit;
	}

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	@Column(name = "TYPE")
	public MonitorType getType() {
		return this.type;
	}

	public void setTarget(Float target) {
		this.target = target;
	}

	public void setController(Component controller) {
		this.controller = controller;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setReader(Component reader) {
		this.reader = reader;
	}

	public void setLimit(Float limit) {
		this.limit = limit;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(MonitorType type) {
		this.type = type;
	}

}
