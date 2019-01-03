package com.master.persistence;

import org.hibernate.Session;

public class Persistence {

	private Session session;

	public void clear() {
		this.session.clear();
	}

	public void close() {
		this.session.close();
	}

}
