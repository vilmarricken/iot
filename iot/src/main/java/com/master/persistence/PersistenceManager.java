package com.master.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PersistenceManager {

	public static final Persistence getPersistence() {
		return new Persistence();
	}

	private SessionFactory sessionFactoryObj;

	private Session sessionObj;

}
