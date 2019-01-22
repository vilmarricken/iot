package com.master.iot.controlador;

import com.master.iot.entity.Temporizador;

public class ControladorTemporizador extends Controlador {

	private final Temporizador temporizador;

	public ControladorTemporizador(final Temporizador temporizador) {
		super();
		this.temporizador = temporizador;
	}

	@Override
	public void run() {
	}

}
