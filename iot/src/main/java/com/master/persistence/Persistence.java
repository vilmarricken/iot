package com.master.persistence;

import org.hibernate.Session;

public class Persistence {

	private final PersistenceSessionControle sessionController;

	private final PersistenceManager manager;

	private final Session session;

	public Persistence(PersistenceManager manager, PersistenceSessionControle session) {
		this.manager = manager;
		this.sessionController = session;
		this.session = session.getSession();
	}

	public void clear() {
		this.session.clear();
	}

	public void close() {
		this.manager.closeSession(this.sessionController);
	}

}
