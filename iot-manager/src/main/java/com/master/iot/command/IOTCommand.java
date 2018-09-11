package com.master.iot.command;

public interface IOTCommand {

	static byte COMMAND_CHECK = 1;

	static byte COMMAND_REGISTRY = 2;

	static byte COMMAND_UNREGISTRY = 3;

	static byte COMMAND_ON = 4;

	static byte COMMAND_OFF = 5;

	static byte COMMAND_READ = 6;

	byte[] getCommand();

	void setResponse(byte[] response);

}
