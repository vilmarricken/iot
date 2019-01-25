package com.master.iot.controlador;

import org.apache.log4j.Logger;

public abstract class Controlador {

	private static final Logger log = Logger.getLogger(Controlador.class);

	private boolean executando;

	String id;

	Controlador(String id) {
		this.id = id;
	}

	public abstract void execute();

	public boolean isExecutando() {
		return this.executando;
	}

	public synchronized void setExecutando(final boolean executando) {
		this.executando = executando;
		if (this.executando) {
			this.notify();
		}
	}

	protected void sleep(final long tempo) {
		try {
			Thread.sleep(tempo);
		} catch (final InterruptedException e) {
			Controlador.log.error(e.getMessage(), e);
		}
	}

	int getValue(Integer value, int defaultValue, String identifier) {
		if (value == null) {
			value = defaultValue;
			Controlador.log.warn("Tempo " + identifier + " é nulo, utilizando valor padrão de " + defaultValue);
			return value.intValue();
		}
		return defaultValue;
	}

}
