package com.master.iot.entity.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;

import com.master.core.persistence.PersistenceException;
import com.master.iot.entity.History;
import com.master.iot.entity.Status;

public class HistoryInsertExceptionDao extends HistoryInsertDao {

	private final Exception exception;

	public HistoryInsertExceptionDao(final History historico, final Exception exception) {
		super(historico);
		this.exception = exception;
	}

	@Override
	public void executeUpdate(final Connection connection) throws PersistenceException {
		this.history.setError(this.printErro(this.exception));
		this.history.setStatus(Status.ERROR);
		super.executeUpdate(connection);
	}

	private String printErro(final Exception exception) {
		final StringWriter sw = new StringWriter(2000);
		final PrintWriter pw = new PrintWriter(sw);
		this.exception.printStackTrace(pw);
		pw.flush();
		return sw.toString();
	}

}
