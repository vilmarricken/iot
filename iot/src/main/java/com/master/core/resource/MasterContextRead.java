package com.master.core.resource;

import com.master.core.exception.MasterException;
import com.master.core.persistence.PersistenceException;
import com.master.core.persistence.PersistenceManager;

public class MasterContextRead implements MasterContext {

	public MasterContextRead() {
	}

	@Override
	public void error() throws MasterException {
		try {
			PersistenceManager.getPersistence().close();
		} catch (final PersistenceException e) {
			throw new MasterException(e);
		}
	}

	@Override
	public void finish() throws MasterException {
		try {
			PersistenceManager.getPersistence().close();
		} catch (final PersistenceException e) {
			throw new MasterException(e);
		}
	}

	@Override
	public void start() throws MasterException {
			Resource.init();
	}

}
