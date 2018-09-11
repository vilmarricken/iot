package com.master.iot.command;

public class IOTCommandCheck extends IOTCommandAbstract {

	@Override
	public byte[] getCommand() {
		return new byte[] { IOTCommand.COMMAND_CHECK };
	}

}
