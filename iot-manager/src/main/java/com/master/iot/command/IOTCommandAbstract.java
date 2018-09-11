package com.master.iot.command;

public abstract class IOTCommandAbstract implements IOTCommand {

	public enum IOTCommandStatus {
		OK, OFF, ERROR
	}

	byte port;

	byte[] response;

	@Override
	public void setResponse(byte[] response) {
		this.response = response;
	}

}
