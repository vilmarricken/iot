package com.master.core.persistence;

import org.hibernate.Session;

public class PersistenceSessionController {

	private final long created;

	private final Session session;

	private final Thread thread;

	public PersistenceSessionController(final Session session) {
		this.session = session;
		this.created = System.currentTimeMillis();
		this.thread = Thread.currentThread();
	}

	void close() {
		this.session.close();
	}

	@Override
	public boolean equals(final Object obj) {
		return this.session == ((PersistenceSessionController) obj).session;
	}

	Session getSession() {
		return this.session;
	}

	@Override
	public int hashCode() {
		return this.session.hashCode();
	}

	@Override
	public String toString() {
		return super.toString() + " - " + this.created + " - " + this.thread;
	}

}
