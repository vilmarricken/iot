package com.master.iot.controlador;

import org.apache.log4j.Logger;

public abstract class Controlador {

	private static final Logger log = Logger.getLogger(Controlador.class);

	private boolean executando;

	Controlador() {
	}

	public abstract void execute();

	public boolean isExecutando() {
		return this.executando;
	}

	public void setExecutando(final boolean executando) {
		this.executando = executando;
	}

	protected void sleep(final long tempo) {
		try {
			Thread.sleep(tempo);
		} catch (final InterruptedException e) {
			Controlador.log.error(e.getMessage(), e);
		}
	}

}
