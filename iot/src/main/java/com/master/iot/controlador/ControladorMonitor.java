package com.master.iot.controlador;

import com.master.iot.entity.Monitor;

public class ControladorMonitor extends Controlador {

	private final Monitor monitor;

	public ControladorMonitor(final Monitor monitor) {
		super();
		this.monitor = monitor;
	}

	@Override
	public void run() {
	}

}
