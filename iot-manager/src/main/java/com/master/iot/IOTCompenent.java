package com.master.iot;

import java.io.Serializable;

public class IOTCompenent implements Serializable {

	private static final long serialVersionUID = -6926115521392878254L;

	private String name;

	private boolean on;

	private int index = -1;

	public IOTCompenent(String name, boolean on, int index) {
		this.name = name;
		this.on = on;
		this.index = index;
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

	public int getIndex() {
		return index;
	}

}
