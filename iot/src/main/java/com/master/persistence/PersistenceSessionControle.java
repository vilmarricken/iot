package com.master.persistence;

import org.hibernate.Session;

public class PersistenceSessionControle {

	private final long created;

	private final Session session;

	public PersistenceSessionControle(final Session session) {
		this.session = session;
		this.created = System.currentTimeMillis();
	}

	@Override
	public boolean equals(final Object obj) {
		return this.session == ((PersistenceSessionControle) obj).session;
	}

	@Override
	public int hashCode() {
		return this.session.hashCode();
	}

}
