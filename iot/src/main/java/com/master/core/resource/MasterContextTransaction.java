package com.master.core.resource;

import com.master.core.persistence.PersistenceManager;

public class MasterContextTransaction implements MasterContext {

	public MasterContextTransaction() {
	}

	@Override
	public void error() {
		PersistenceManager.getPersistence().rollback();
		PersistenceManager.getPersistence().close();
	}

	@Override
	public void finish() {
		PersistenceManager.getPersistence().commit();
		PersistenceManager.getPersistence().close();
	}

	@Override
	public void start() {
		Resource.init();
		PersistenceManager.getPersistence().openTransaction();
	}

}
