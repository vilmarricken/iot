package com.master.iot.entity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.UUID;

import com.master.core.persistence.PersistenceException;
import com.master.core.persistence.Update;
import com.master.iot.entity.Historico;
import com.master.iot.entity.Monitor;
import com.master.iot.entity.Situacao;
import com.master.iot.entity.Temporizador;

public class HistoricoInsertDao implements Update {

	final Historico historico;

	public HistoricoInsertDao(final Historico historico) {
		this.historico = historico;
	}

	@Override
	public void executeUpdate(final Connection connection) throws PersistenceException {
		final String sql = "INSERT INTO historico(id, idtemporizador, idmonitor, idcomponente, inicio, situacao, error) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			final PreparedStatement stmt = connection.prepareStatement(sql);
			this.historico.setId(UUID.randomUUID());
			stmt.setString(1, this.historico.getId().toString());
			final Temporizador temporizador = this.historico.getTemporizador();
			if (temporizador == null) {
				stmt.setNull(2, Types.VARCHAR);
			} else {
				stmt.setString(2, temporizador.getId().toString());
			}
			final Monitor monitor = this.historico.getMonitor();
			if (monitor == null) {
				stmt.setNull(3, Types.VARCHAR);
			} else {
				stmt.setString(3, monitor.getId().toString());
			}
			final Monitor componente = this.historico.getMonitor();
			if (componente == null) {
				stmt.setNull(4, Types.VARCHAR);
			} else {
				stmt.setString(4, monitor.getId().toString());
			}
			final long inicio = System.currentTimeMillis();
			this.historico.setInicio(inicio);
			stmt.setLong(5, inicio);
			this.historico.setSituacao(Situacao.EXECUTANDO);
			stmt.setInt(6, Situacao.EXECUTANDO.ordinal());
			final String error = this.historico.getErro();
			if (error == null) {
				stmt.executeUpdate();
			}
		} catch (final SQLException e) {
			throw new PersistenceException(e.getMessage(), e);
		}
	}

}
