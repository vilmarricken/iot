package com.master.iot.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class HouseController {

	private Integer id;
	private String name;
	private int port;

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

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
