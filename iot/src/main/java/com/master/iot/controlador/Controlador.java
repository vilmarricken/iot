package com.master.iot.controlador;

public abstract class Controlador {

	private boolean executando;

	Controlador() {
	}

	public boolean isExecutando() {
		return this.executando;
	}

	public abstract void run();

	public void setExecutando(final boolean executando) {
		this.executando = executando;
	}

}
