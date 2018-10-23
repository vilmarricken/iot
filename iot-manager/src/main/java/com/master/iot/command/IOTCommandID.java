package com.master.iot.command;

public class IOTCommandID extends IOTCommandAbstract {

	public IOTCommandID() {
		super((byte) 0);
	}

	@Override
	public byte[] getCommand() {
		return new byte[] { IOTCommand.COMMAND_ID };
	}

}
