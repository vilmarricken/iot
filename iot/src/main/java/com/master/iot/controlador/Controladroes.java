package com.master.iot.controlador;

import java.util.ArrayList;
import java.util.List;

import com.master.iot.entity.Monitor;
import com.master.iot.entity.Temporizador;

public class Controladroes {

	private static final Controladroes INSTANCE = new Controladroes();

	public static Controladroes getInstance() {
		return Controladroes.INSTANCE;
	}

	private final List<Controlador> controles = new ArrayList<Controlador>();

	private Controladroes() {
	}

	public void addMonitor(final Monitor monitor) {
		final Controlador controlador = new ControladorMonitor(monitor);
		this.controles.add(controlador);
		controlador.run();
	}

	public void addTemporizador(final Temporizador temporizador) {
		final Controlador controlador = new ControladorTemporizador(temporizador);
		this.controles.add(controlador);
		controlador.run();
	}

}
