package com.master.iot.main;

import org.apache.log4j.Logger;

import com.master.core.exception.MasterException;
import com.master.core.persistence.PersistenceException;
import com.master.core.persistence.filter.Filter;
import com.master.core.persistence.filter.FilterCompare;
import com.master.core.persistence.filter.FilterOperation;
import com.master.core.resource.MasterRunnable;
import com.master.iot.entity.Placa;
import com.master.iot.entity.dao.PlacaDao;

public class RegistryBoard implements MasterRunnable {

	private static final Logger log = Logger.getLogger(RegistryBoard.class);

	private final String identifier;
	private final String address;

	public RegistryBoard(String identifier, String address) {
		this.identifier = identifier;
		this.address = address;
	}

	@Override
	public void run() throws MasterException {
		final PlacaDao placaDao = new PlacaDao();
		final Filter filter = new FilterCompare("name", this.identifier, FilterOperation.EQ);
		try {
			final Placa placa = placaDao.unique(filter);
			if (placa != null) {
				final String ip = placa.getIp();
				if (ip.equals(this.address)) {
					return;
				}
				placa.setIp(this.address);
			}
			if (RegistryBoard.log.isDebugEnabled()) {
				RegistryBoard.log.debug("Saving placa: " + placa);
			}
			placaDao.save(placa);
		} catch (final PersistenceException e) {
			RegistryBoard.log.error(e.getMessage(), e);
		}
	}
}
