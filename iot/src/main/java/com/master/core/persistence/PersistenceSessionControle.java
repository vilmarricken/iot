package com.master.core.persistence;

import org.hibernate.Session;

public class PersistenceSessionControle {

	private final long created;

	private final Session session;

	private final Thread thread;

	public PersistenceSessionControle(final Session session) {
		this.session = session;
		this.created = System.currentTimeMillis();
		this.thread = Thread.currentThread();
	}

	@Override
	public boolean equals(final Object obj) {
		return this.session == ((PersistenceSessionControle) obj).session;
	}

	@Override
	public int hashCode() {
		return this.session.hashCode();
	}

	Session getSession() {
		return this.session;
	}

	void close() {
		this.session.close();
	}

}
