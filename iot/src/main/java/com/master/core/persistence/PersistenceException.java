package com.master.core.persistence;

public class PersistenceException extends Exception {

	private static final long serialVersionUID = -7274867286883635364L;

	public PersistenceException() {
		super();
	}

	public PersistenceException(final String message) {
		super(message);
	}

	public PersistenceException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public PersistenceException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PersistenceException(final Throwable cause) {
		super(cause);
	}

}
