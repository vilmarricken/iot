package com.master.iot.server;

import com.master.core.exception.MasterException;

public class IOTConnectionException extends MasterException {

	private static final long serialVersionUID = 1174940299847361906L;

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

}
