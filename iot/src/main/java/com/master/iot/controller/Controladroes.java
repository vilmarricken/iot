package com.master.iot.controller;

import java.util.ArrayList;
import java.util.List;

import com.master.iot.entity.Monitor;
import com.master.iot.entity.Timer;

public class Controladroes {

	private static final Controladroes INSTANCE = new Controladroes();

	public static Controladroes getInstance() {
		return Controladroes.INSTANCE;
	}

	private final List<Controller> controles = new ArrayList<>();

	private Controladroes() {
	}

	public void addMonitor(final Monitor monitor) {
		final Controller controlador = new ControllerMonitor(monitor);
		this.controles.add(controlador);
		controlador.execute();
	}

	public void addTemporizador(final Timer temporizador) {
		final Controller controlador = new ControladorTemporizador(temporizador);
		this.controles.add(controlador);
		controlador.execute();
	}

}
