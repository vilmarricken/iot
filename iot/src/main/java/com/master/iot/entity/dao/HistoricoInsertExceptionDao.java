package com.master.iot.entity.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;

import com.master.core.persistence.PersistenceException;
import com.master.iot.entity.Historico;
import com.master.iot.entity.Situacao;

public class HistoricoInsertExceptionDao extends HistoricoInsertDao {

	private final Exception exception;

	public HistoricoInsertExceptionDao(final Historico historico, final Exception exception) {
		super(historico);
		this.exception = exception;
	}

	@Override
	public void executeUpdate(final Connection connection) throws PersistenceException {
		this.historico.setErro(this.printErro(this.exception));
		this.historico.setSituacao(Situacao.ERRO);
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
