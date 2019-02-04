package com.master.iot.controlador;

import java.text.DateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.master.core.exception.MasterException;
import com.master.core.persistence.Update;
import com.master.core.resource.MasterContextTransaction;
import com.master.core.resource.MasterThread;
import com.master.iot.action.ActionUpdate;

public abstract class Controlador {

	private static final Logger log = Logger.getLogger(Controlador.class);

	private boolean executando;

	String id;

	Controlador(final String id) {
		this.id = id;
	}

	public abstract void execute();

	int getValue(Integer value, final int defaultValue, final String identifier) {
		if (value == null) {
			value = defaultValue;
			Controlador.log.warn("Tempo " + identifier + " � nulo, utilizando valor padr�o de " + defaultValue);
			return value.intValue();
		}
		return defaultValue;
	}

	public boolean isExecutando() {
		return this.executando;
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
				Controlador.log.trace("Aguardando " + tempo + " ms at� " + DateFormat.getDateTimeInstance().format(new Date(System.currentTimeMillis() + tempo)));
			}
			Thread.sleep(tempo);
		} catch (final InterruptedException e) {
			Controlador.log.error(e.getMessage(), e);
		}
	}

}
