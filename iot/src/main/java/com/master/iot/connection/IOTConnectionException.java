package com.master.iot.connection;

import com.master.core.exception.MasterException;

public class IOTConnectionException extends MasterException {

	private static final long serialVersionUID = -794580355173337905L;

	private static final String IOT_ERROR_INVALID_INDEX_DEVICE = "ERROR:1";
	private static final String IOT_ERROR_INVALID_COMMAND_LENGTH = "ERROR:2";
	private static final String IOT_ERROR_INVALID_DEVICE_TYPE = "ERROR:3";
	private static final String IOT_ERROR_INVALID_COMMAND = "ERROR:4";
	private static final String IOT_ERROR_INVALID_READ_COMMAND = "ERROR:5";
	private static final String IOT_ERROR_INVALID_READ_COMMAND_LENGTH = "ERROR:6";
	private static final String IOT_ERROR_TERMOMETHER_NO_MORE_ADDRESS = "ERROR:7";
	private static final String IOT_ERROR_TERMOMETHER_INVALID_CRC = "ERROR:8";
	private static final String IOT_ERROR_TERMOMETHER_INVALID_FAMILY_DEVICE = "ERROR:9";

	public IOTConnectionException() {
	}

	public IOTConnectionException(String message) {
		super(message);
	}

	public IOTConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

	public IOTConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IOTConnectionException(Throwable cause) {
		super(cause);
	}

	public static IOTConnectionException buildError(String error) {
		final IOTConnectionException execption = null;
		return execption;
	}

}
