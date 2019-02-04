package com.master.iot.controlador;

import java.text.DateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.master.core.exception.MasterException;
import com.master.core.persistence.Update;
import com.master.core.resource.MasterContextTransaction;
import com.master.core.resource.MasterThread;
import com.master.iot.action.ActionUpdate;
import com.master.iot.entity.Componente;
import com.master.iot.entity.Historico;
import com.master.iot.entity.Placa;
import com.master.iot.entity.Situacao;
import com.master.iot.entity.dao.HistoricoInsertDao;
import com.master.iot.entity.dao.HistoricoInsertExceptionDao;
import com.master.iot.server.IOTConnection;

public abstract class Controlador {

	private static final Logger log = Logger.getLogger(Controlador.class);

	private final IOTConnection connection = new IOTConnection();

	private boolean executando;

	String id;

	Controlador(final String id) {
		this.id = id;
	}

	protected void desligar(final Componente componente, final Historico historico) throws MasterException {
		final Placa placa = componente.getPlaca();
		try {
			if (Controlador.log.isTraceEnabled()) {
				Controlador.log.trace("Desligando " + componente);
			}
			this.connection.desligar(placa.getIp(), componente.getPorta().toString());
			this.saveHistorico(new HistoricoInsertDao(historico));
		} catch (final Exception e) {
			final MasterException ex = new MasterException("Erro ao conectar na placa " + placa.getNome() + " - " + placa.getIp() + " na porta " + componente.getPorta(), e);
			this.saveHistorico(new HistoricoInsertExceptionDao(historico, e));
			throw ex;
		}
	}

	public abstract void execute();

	int getValue(Integer value, final int defaultValue, final String identifier) {
		if (value == null) {
			value = defaultValue;
			Controlador.log.warn("Tempo " + identifier + " é nulo, utilizando valor padrão de " + defaultValue);
			return value.intValue();
		}
		return defaultValue;
	}

	public boolean isExecutando() {
		return this.executando;
	}

	protected void ligar(final Componente componente, final Historico historico) throws MasterException {
		final Placa placa = componente.getPlaca();
		if (Controlador.log.isTraceEnabled()) {
			Controlador.log.trace("Ligando " + componente);
		}
		historico.setComponente(componente);
		historico.setInicio(System.currentTimeMillis());
		historico.setSituacao(Situacao.LIGADO);
		try {
			this.connection.ligar(placa.getIp(), componente.getPorta().toString());
			this.saveHistorico(new HistoricoInsertDao(historico));
		} catch (final Exception e) {
			final MasterException ex = new MasterException("Erro ao conectar na placa " + placa.getNome() + " - " + placa.getIp() + " na porta " + componente.getPorta(), e);
			this.saveHistorico(new HistoricoInsertExceptionDao(historico, e));
			throw ex;
		}
	}

	void saveHistorico(final Update update) throws MasterException {
		new MasterThread(new ActionUpdate(update), new MasterContextTransaction());
	}

	public synchronized void setExecutando(final boolean executando) {
		this.executando = executando;
		if (this.executando) {
			this.notify();
		}
	}

	protected void sleep(final long tempo) {
		try {
			if (Controlador.log.isTraceEnabled()) {
				Controlador.log.trace("Aguardando " + tempo + " ms até " + DateFormat.getDateTimeInstance().format(new Date(System.currentTimeMillis() + tempo)));
			}
			Thread.sleep(tempo);
		} catch (final InterruptedException e) {
			Controlador.log.error(e.getMessage(), e);
		}
	}

}
