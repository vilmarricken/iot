package com.master.iot.entity.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.master.core.persistence.PersistenceException;
import com.master.core.persistence.Update;
import com.master.iot.entity.Historico;

public class HistoricoUpdateFinishDao implements Update {

	private final Exception exception;

	private final Historico historico;

	public HistoricoUpdateFinishDao(final Historico historico, final Exception exception) {
		this.historico = historico;
		this.exception = exception;
	}

	@Override
	public void executeUpdate(final Connection connection) throws PersistenceException {
		final String sql = "UPDATE historico SET situacao = ?, fim = ?, error = ? WHERE id = ?";
		try {
			final PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, this.historico.getSituacao().ordinal());
			final long fim = System.currentTimeMillis();
			this.historico.setFim(fim);
			stmt.setLong(2, fim);
			if (this.exception != null) {
				stmt.setNull(3, Types.VARCHAR);
			} else {
				final String erro = this.printErro(this.exception);
				stmt.setString(3, erro);
			}
			stmt.setString(4, this.historico.getId().toString());
			stmt.executeUpdate();
		} catch (final SQLException e) {
			throw new PersistenceException(e.getMessage(), e);
		}
	}

	private String printErro(final Exception exception) {
		final StringWriter sw = new StringWriter(2000);
		final PrintWriter pw = new PrintWriter(sw);
		this.exception.printStackTrace(pw);
		pw.flush();
		return sw.toString();
	}

}
