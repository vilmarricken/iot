package com.master.iot.entity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.UUID;

import com.master.core.persistence.PersistenceException;
import com.master.core.persistence.Update;
import com.master.iot.entity.Component;
import com.master.iot.entity.History;
import com.master.iot.entity.Monitor;
import com.master.iot.entity.Timer;

public class HistoryInsertDao implements Update {

	final History history;

	public HistoryInsertDao(final History history) {
		this.history = history;
	}

	@Override
	public void executeUpdate(final Connection connection) throws PersistenceException {
		final String sql = "INSERT INTO HISTORY(ID, IDTIMER, IDMONITOR, IDCOMPONENT, RUN, READ, STATUS, ERROR) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			final PreparedStatement stmt = connection.prepareStatement(sql);
			this.history.setId(UUID.randomUUID());
			stmt.setObject(1, this.history.getId());
			final Timer timer = this.history.getTimer();
			if (timer == null) {
				stmt.setNull(2, Types.NULL);
			} else {
				stmt.setObject(2, timer.getId());
			}
			final Monitor monitor = this.history.getMonitor();
			if (monitor == null) {
				stmt.setNull(3, Types.NULL);
			} else {
				stmt.setObject(3, monitor.getId());
			}
			final Component component = this.history.getComponent();
			if (component == null) {
				stmt.setNull(4, Types.NULL);
			} else {
				stmt.setObject(4, component.getId());
			}
			final long run = System.currentTimeMillis();
			this.history.setRun(run);
			stmt.setLong(5, run);
			final Float read = this.history.getRead();
			if (read == null) {
				stmt.setNull(6, Types.FLOAT);
			} else {
				stmt.setFloat(6, read);
			}
			stmt.setInt(7, this.history.getStatus().ordinal());
			final String error = this.history.getError();
			if (error == null) {
				stmt.setNull(8, Types.VARCHAR);
			} else {
				stmt.setString(8, error);
			}
			stmt.executeUpdate();
		} catch (final SQLException e) {
			throw new PersistenceException(e.getMessage(), e);
		}
	}

}
