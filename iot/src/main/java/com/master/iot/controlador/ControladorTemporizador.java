package com.master.iot.controlador;

import com.master.iot.entity.Temporizador;

public class ControladorTemporizador extends Controlador {

	private final Temporizador temporizador;

	public ControladorTemporizador(final Temporizador temporizador) {
		super();
		this.temporizador = temporizador;
	}

	private void ligar() {

	}

	@Override
	public void run() {
		new Thread(this.temporizador.getNome()) {
			@Override
			public void run() {
				try {
					ControladorTemporizador.this.setExecutando(true);
					final Boolean iniciar = ControladorTemporizador.this.temporizador.getIniciar();
					if (iniciar != null && iniciar.booleanValue()) {
						ControladorTemporizador.this.ligar();
					}
				} finally {
					ControladorTemporizador.this.setExecutando(false);
				}
			}

		}.start();
	}

}
