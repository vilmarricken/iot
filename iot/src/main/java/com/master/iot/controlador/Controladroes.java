package com.master.iot.controlador;

import java.util.ArrayList;
import java.util.List;

public class Controladroes {

	private static final Controladroes INSTANCE = new Controladroes();

	public static Controladroes getInstance() {
		return Controladroes.INSTANCE;
	}

	private final List<Controlador> controles = new ArrayList<Controlador>();

	private Controladroes() {
	}

}
