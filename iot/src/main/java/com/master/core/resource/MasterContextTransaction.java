package com.master.core.resource;

import com.master.core.exception.MasterException;
import com.master.core.persistence.Persistence;
import com.master.core.persistence.PersistenceException;
import com.master.core.persistence.PersistenceManager;

public class MasterContextTransaction implements MasterContext {

	public MasterContextTransaction() {
	}

	@Override
	public void error() throws MasterException {
		try {
			Persistence persistence = PersistenceManager.getPersistence();
			persistence.rollback();
			persistence.close();
		} catch (final PersistenceException e) {
			throw new MasterException(e);
		}
	}

	@Override
	public void finish() throws MasterException {
		try {
			Persistence persistence = PersistenceManager.getPersistence();
			persistence.commit();
			persistence.close();
		} catch (final PersistenceException e) {
			throw new MasterException(e);
		}
	}

	@Override
	public void start() throws MasterException {
		try {
			Resource.init();
			PersistenceManager.getPersistence().openTransaction();
		} catch (final PersistenceException e) {
			throw new MasterException(e);
		}
	}

}
