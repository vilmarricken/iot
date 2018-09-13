package com.master.iot.command;

public class IOTCommandCheck extends IOTCommandAbstract {

	public IOTCommandCheck() {
		super((byte) 0);
	}

	@Override
	public byte[] getCommand() {
		return new byte[] { IOTCommand.COMMAND_CHECK };
	}

}
