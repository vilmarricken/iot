package com.master.iot.command;

public class IOTCommandRead extends IOTCommandAbstract {

	public IOTCommandRead(byte port) {
		super(port);
	}

	@Override
	public byte[] getCommand() {
		return new byte[] { IOTCommand.ACTION_READ, this.port };
	}

}
