package com.master.iot.command;

public class IOTCommandRegistry extends IOTCommandAbstract {

	private byte type;

	public IOTCommandRegistry(byte type) {
		this.type = type;
	}

	@Override
	public byte[] getCommand() {
		return new byte[] { IOTCommand.COMMAND_REGISTRY, this.port, this.type };
	}

}
