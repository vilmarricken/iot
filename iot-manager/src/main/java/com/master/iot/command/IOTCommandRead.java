package com.master.iot.command;

public class IOTCommandRead extends IOTCommandAbstract {

	@Override
	public byte[] getCommand() {
		return new byte[] { IOTCommand.COMMAND_READ, this.port };
	}

}
