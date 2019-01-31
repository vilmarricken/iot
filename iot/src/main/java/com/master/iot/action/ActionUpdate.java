package com.master.iot.action;

import com.master.core.exception.MasterException;
import com.master.core.persistence.PersistenceException;
import com.master.core.persistence.PersistenceManager;
import com.master.core.persistence.Update;
import com.master.core.resource.MasterRunnable;

public class ActionUpdate implements MasterRunnable {

	private final Update update;

	public ActionUpdate(final Update update) {
		this.update = update;
	}

	@Override
	public void run() throws MasterException {
		try {
			PersistenceManager.getPersistence().execute(this.update);
		} catch (final PersistenceException e) {
			throw new MasterException(e.getMessage(), e);
		}
	}

}
