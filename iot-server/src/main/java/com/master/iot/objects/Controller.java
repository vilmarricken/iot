package com.master.iot.objects;

import java.io.Serializable;

public class Controller implements Serializable {

	private static final long serialVersionUID = -5799304038772362078L;

	private String name;

	private String id;

	private boolean on;
	
	private Type type;

	public Controller(String id, String name, boolean on, Type type) {
		this.id = id;
		this.name = name;
		this.on = on;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
}
