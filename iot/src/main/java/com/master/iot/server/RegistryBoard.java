package com.master.iot.server;

import org.apache.log4j.Logger;

import com.master.core.exception.MasterException;
import com.master.core.persistence.PersistenceException;
import com.master.core.persistence.filter.Filter;
import com.master.core.persistence.filter.FilterCompare;
import com.master.core.persistence.filter.FilterOperation;
import com.master.core.resource.MasterRunnable;
import com.master.iot.entity.Board;
import com.master.iot.entity.dao.BoardDao;

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
		final BoardDao placaDao = new BoardDao();
		final Filter filter = new FilterCompare("name", this.identifier, FilterOperation.EQ);
		try {
			Board board = placaDao.unique(filter);
			if (board != null) {
				final RegistryComponent rc = new RegistryComponent();
				final String ip = board.getIp();
				if (!ip.equals(this.address)) {
					board.setIp(this.address);
				}
				if (board.getComponents() != null) {
					rc.registryComponents(board.getComponents());
				}
			} else {
				board = new Board();
				board.setIp(this.address);
				board.setName(this.identifier);
			}
			if (RegistryBoard.log.isDebugEnabled()) {
				RegistryBoard.log.debug("Saving placa: " + board);
			}
			placaDao.save(board);
		} catch (final PersistenceException e) {
			RegistryBoard.log.error(e.getMessage(), e);
		}
	}
}
