package com.master.iot.command;

public abstract class IOTCommandAbstract implements IOTCommand {

	public enum IOTCommandStatus {
		OK, OFF, ERROR
	}

	byte port;

	byte status;

	byte[] response;

	public IOTCommandAbstract(byte port) {
		this.port = port;
	}

	@Override
	public byte getStatus() {
		return this.status;
	}

	@Override
	public void setResponse(byte[] response) {
		this.response = response;
	}

	@Override
	public void setStatus(byte status) {
		this.status = status;
	}

}
