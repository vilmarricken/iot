package com.master.core.exception;

public class MasterException extends Exception {

	private static final long serialVersionUID = -7274867286883635364L;

	public MasterException() {
		super();
	}

	public MasterException(final String message) {
		super(message);
	}

	public MasterException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public MasterException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MasterException(final Throwable cause) {
		super(cause);
	}

}
