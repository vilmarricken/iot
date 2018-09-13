package com.master.iot.command;

public class IOTCommandAction extends IOTCommandAbstract {

	private byte action;

	public IOTCommandAction(byte port, byte action) {
		super(port);
		this.action = action;
	}

	@Override
	public byte[] getCommand() {
		return new byte[] { this.action, this.port };
	}

}
